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
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.common.exceptions.CompressScreenshotException;
import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static android.graphics.Bitmap.CompressFormat.PNG;

public class ScreenshotHelper {

    private static final UiAutomation uia = CustomUiDevice.getInstance().getInstrumentation()
            .getUiAutomation();

    /**
     * Grab device screenshot and crop it to specifyed area if cropArea is not null.
     * Compress it to PGN format and convert to Base64 byte-string.
     *
     * @param cropArea Area to crop.
     * @return Base64-encoded screenshot string.
     */
    public static String takeScreenshot(@Nullable final Rect cropArea) throws
            TakeScreenshotException, CompressScreenshotException, CropScreenshotException {
        Bitmap screenshot = takeDeviceScreenshot();
        try {
            if (cropArea != null) {
                final Bitmap elementScreenshot = crop(screenshot, cropArea);
                screenshot.recycle();
                screenshot = elementScreenshot;
            }
            return Base64.encodeToString(compress(screenshot), Base64.DEFAULT);
        } finally {
            screenshot.recycle();
        }
    }

    public static String takeScreenshot() throws CropScreenshotException,
            CompressScreenshotException, TakeScreenshotException {
        return takeScreenshot(null);
    }

    private static boolean isScreenScaled() {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        DisplayMetrics realMetrics = new DisplayMetrics();
        display.getRealMetrics(realMetrics);
        android.graphics.Point realSize = new android.graphics.Point();
        display.getRealSize(realSize);
        return realMetrics.widthPixels != realSize.x;
    }

    private static android.graphics.Point getScreenSize() {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        android.graphics.Point realSize = new android.graphics.Point();
        display.getRealSize(realSize);
        return realSize;
    }

    private static Bitmap takeDeviceScreenshot() throws TakeScreenshotException {
        Bitmap screenshot = null;
        if (isScreenScaled()) {
            // Workaround for https://github.com/appium/appium/issues/12199
            Logger.info("Making the screenshot with screencap utility to workaround " +
                    "the scaling issue");
            ParcelFileDescriptor pfd = uia.executeShellCommand("screencap -p");
            try (InputStream is = new FileInputStream(pfd.getFileDescriptor())) {
                byte[] pngBytes = IOUtils.toByteArray(is);
                screenshot = BitmapFactory.decodeByteArray(pngBytes, 0, pngBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    pfd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            screenshot = uia.takeScreenshot();
        }

        if (screenshot == null || screenshot.getWidth() == 0 || screenshot.getHeight() == 0) {
            throw new TakeScreenshotException();
        }

        Logger.info(String.format("Got screenshot with pixel resolution: %sx%s",
                screenshot.getWidth(), screenshot.getHeight()));
        return screenshot;
    }

    private static byte[] compress(final Bitmap bitmap) throws CompressScreenshotException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (!bitmap.compress(PNG, 100, stream)) {
            throw new CompressScreenshotException(PNG);
        }
        return stream.toByteArray();
    }

    private static Bitmap crop(Bitmap bitmap, Rect cropArea) throws CropScreenshotException {
        final Rect bitmapRect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final Rect intersectionRect = new Rect();

        android.graphics.Point logicalScreenSize = getScreenSize();
        if (bitmapRect.width() != logicalScreenSize.x) {
            double scale = 1.0 * bitmapRect.width() / logicalScreenSize.x;
            Logger.info(String.format("Applying scale factor %s to the element area %s",
                    scale, cropArea.toShortString()));
            cropArea = new Rect((int) (cropArea.left * scale), (int) (cropArea.top * scale),
                    (int) (cropArea.right * scale), (int) (cropArea.bottom * scale));
        }

        if (!intersectionRect.setIntersect(bitmapRect, cropArea)) {
            throw new CropScreenshotException(bitmapRect, cropArea);
        }

        return Bitmap.createBitmap(bitmap,
                intersectionRect.left, intersectionRect.top,
                intersectionRect.width(), intersectionRect.height());
    }

}
