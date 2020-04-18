/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.annotation.Nullable;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import io.appium.uiautomator2.common.exceptions.CompressScreenshotException;
import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static android.graphics.Bitmap.CompressFormat.PNG;
import static android.graphics.Bitmap.CompressFormat.JPEG;
import static android.util.DisplayMetrics.DENSITY_DEFAULT;

public class ScreenshotHelper {
    private static final int PNG_MAGIC_LENGTH = 8;
    private static final UiAutomation uia =
        CustomUiDevice.getInstance().getInstrumentation().getUiAutomation();

    /**
     * Grab device screenshot and crop it to specifyed area if cropArea is not null.
     * Compress it to PGN format and convert to Base64 byte-string.
     *
     * @param cropArea Area to crop.
     * @return Base64-encoded screenshot string.
     */
    public static String takeScreenshot(@Nullable final Rect cropArea) throws TakeScreenshotException {
        Object screenshotObj = takeDeviceScreenshot(cropArea == null ? String.class : Bitmap.class);

        if (cropArea == null) {
            return (String) screenshotObj;
        }

        Bitmap screenshot = (Bitmap) screenshotObj;
        try {
            final Bitmap elementScreenshot = crop(screenshot, cropArea);
            screenshot.recycle();
            screenshot = elementScreenshot;
            return Base64.encodeToString(compress(screenshot), Base64.DEFAULT);
        } finally {
            screenshot.recycle();
        }
    }

    public static String takeScreenshot() throws TakeScreenshotException {
        return takeScreenshot(null);
    }

    /**
     * Takes a shot of the current device's screen
     *
     * @param outputType Either String.class or Bitmap.class
     * @return Either base64-encoded content of the PNG screenshot or the screenshot as bitmap image
     * @throws TakeScreenshotException if there was an error while taking the screenshot
     */
    private static <T> T takeDeviceScreenshot(Class<T> outputType) throws TakeScreenshotException {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Bitmap screenshot = null;
        Logger.debug(String.format("Display metrics: %s", metrics));
        // Workaround for https://github.com/appium/appium/issues/12199
        // executeShellCommand seems to be faulty on Android 5
        if (metrics.densityDpi != DENSITY_DEFAULT && Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            try {
                ParcelFileDescriptor pfd = uia.executeShellCommand("screencap -p");
                try (InputStream is = new FileInputStream(pfd.getFileDescriptor())) {
                    byte[] pngBytes = IOUtils.toByteArray(is);
                    if (pngBytes.length <= PNG_MAGIC_LENGTH) {
                        throw new IllegalStateException("screencap returned an invalid response");
                    }
                    if (outputType == String.class) {
                        return outputType.cast(Base64.encodeToString(pngBytes, Base64.DEFAULT));
                    }
                    screenshot = BitmapFactory.decodeByteArray(
                        pngBytes,
                        0,
                        pngBytes.length
                    );
                } finally {
                    try {
                        pfd.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                Logger.error(e);
                Logger.info("Falling back to UiAutomator-based screenshoting");
            }
        }

        if (screenshot == null) {
            screenshot = uia.takeScreenshot();
        }

        if (screenshot == null || screenshot.getWidth() == 0 || screenshot.getHeight() == 0) {
            throw new TakeScreenshotException();
        }

        Logger.info(String.format(
            "Got screenshot with resolution: %sx%s",
            screenshot.getWidth(),
            screenshot.getHeight()
        ));

        if (outputType == String.class) {
            try {
                return outputType.cast(Base64.encodeToString(compress(screenshot), Base64.DEFAULT));
            } finally {
                screenshot.recycle();
            }
        }
        return outputType.cast(screenshot);
    }

    private static byte[] compress(final Bitmap bitmap) throws TakeScreenshotException {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            if (!bitmap.compress(PNG, 100, stream)) {
                throw new CompressScreenshotException(PNG);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            throw new CompressScreenshotException(PNG, e);
        }
    }

    public static byte[] compressJpeg(final Bitmap bitmap, float scale, int quality, boolean filter) throws TakeScreenshotException {
        Bitmap resultBitmap;
        if (Math.abs(scale - 1.0f) < Float.MIN_NORMAL) {
            resultBitmap = bitmap;
        } else {
            int width = Math.round(bitmap.getWidth() * scale);
            int height = Math.round(bitmap.getHeight() * scale);
            resultBitmap = Bitmap.createScaledBitmap(
                bitmap,
                width,
                height,
                filter
            );
            bitmap.recycle();
        }

        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            if (!resultBitmap.compress(JPEG, quality, stream)) {
                throw new CompressScreenshotException(JPEG);
            }
            return stream.toByteArray();
        } catch (IOException e) {
            throw new CompressScreenshotException(JPEG, e);
        }
    }

    private static Bitmap crop(Bitmap bitmap, Rect cropArea) throws CropScreenshotException {
        final Rect bitmapRect = new Rect(
            0,
            0,
            bitmap.getWidth(),
            bitmap.getHeight()
         );
        final Rect intersectionRect = new Rect();

        if (!intersectionRect.setIntersect(bitmapRect, cropArea)) {
            throw new CropScreenshotException(bitmapRect, cropArea);
        }

        return Bitmap.createBitmap(
            bitmap,
            intersectionRect.left,
            intersectionRect.top,
            intersectionRect.width(),
            intersectionRect.height()
        );
    }
}
