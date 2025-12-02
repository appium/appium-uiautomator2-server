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

import android.app.Service;
import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.CompressScreenshotException;
import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.CurrentDisplayId;
import io.appium.uiautomator2.model.settings.Settings;

import static android.graphics.Bitmap.CompressFormat.JPEG;
import static android.graphics.Bitmap.CompressFormat.PNG;
import static android.util.DisplayMetrics.DENSITY_DEFAULT;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class ScreenshotHelper {
    private static final int PNG_MAGIC_LENGTH = 8;

    /**
     * Grab device screenshot and crop it to specified area if cropArea is not null.
     * Compress it to PNG format and convert to Base64 byte-string.
     *
     * @param cropArea Area to crop.
     * @return Base64-encoded screenshot string.
     */
    public static String takeScreenshot(@Nullable final Rect cropArea) throws TakeScreenshotException {
        if (cropArea == null) {
            return takeDeviceScreenshot(String.class);
        }

        Bitmap fullScreenshot = takeDeviceScreenshot(Bitmap.class);
        Bitmap elementScreenshot = null;
        try {
            elementScreenshot = crop(fullScreenshot, cropArea);
            return bitmapToBase64Png(elementScreenshot);
        } finally {
            fullScreenshot.recycle();
            if (elementScreenshot != null && elementScreenshot != fullScreenshot) {
                elementScreenshot.recycle();
            }
        }
    }

    public static String takeScreenshot() throws TakeScreenshotException {
        return takeScreenshot(null);
    }

    /**
     * Converts a Bitmap to a base64-encoded PNG string.
     *
     * @param bitmap The bitmap to convert.
     * @return Base64-encoded PNG string.
     * @throws TakeScreenshotException if the bitmap cannot be compressed to PNG.
     */
    public static String bitmapToBase64Png(Bitmap bitmap) throws TakeScreenshotException {
        return Base64.encodeToString(compress(bitmap), Base64.NO_WRAP);
    }

    /**
     * Takes a shot of the current device's screen
     *
     * @param outputType Either String.class or Bitmap.class
     * @return Either base64-encoded content of the PNG screenshot or the screenshot as bitmap image
     * @throws TakeScreenshotException if there was an error while taking the screenshot
     */
    private static <T> T takeDeviceScreenshot(Class<T> outputType) throws TakeScreenshotException {
        int currentDisplayId = UiAutomatorBridge.getInstance().getCurrentDisplayId();
        boolean isCustomDisplayId = currentDisplayId != Settings.get(CurrentDisplayId.class).getDefaultValue();
        Display display = UiAutomatorBridge.getInstance().getCurrentDisplay();
        if (display == null) {
            throw new TakeScreenshotException(
                    String.format("Cannot take a screenshot of display %s. Does the display exist?",
                            currentDisplayId)
            );
        }
        // If the phone display has a custom density, we need to use screencap to take a screenshot of it.
        // See https://github.com/appium/appium/issues/12199 for more details.
        boolean shouldTryScreencap = isCustomDisplayId || doesDisplayHaveCustomDensity(display);

        UiAutomation automation = CustomUiDevice.getInstance().getUiAutomation();
        if (shouldTryScreencap) {
            try {
                Long physicalDisplayId = DisplayIdHelper.getPhysicalDisplayId(display);
                if (physicalDisplayId == null && isCustomDisplayId) {
                    String virtualDeviceId = findVirtualDisplayId(automation, display.getName());
                    if (virtualDeviceId != null) {
                        return retrieveScreenshotViaScreencap(
                                String.format("screencap -d %s -p", virtualDeviceId), automation, outputType
                        );
                    }

                    throw new TakeScreenshotException(
                            String.format("Cannot take a screenshot of display %s " +
                                            "because its physical id cannot be determined", display.getDisplayId())
                    );
                }
                String command = physicalDisplayId == null
                        ? "screencap -p"
                        : String.format("screencap -d %d -p", physicalDisplayId);
                return retrieveScreenshotViaScreencap(command, automation, outputType);
            } catch (Exception e) {
                if (isCustomDisplayId) {
                    throw new TakeScreenshotException(
                            String.format("Cannot take a screenshot of display %s: %s",
                                    display.getDisplayId(), e.getMessage()), e
                    );
                } else {
                    Logger.info("Cannot take a screenshot via screencap, defaulting to UiAutomator API", e);
                }
            }
        }

        Bitmap screenshot = automation.takeScreenshot();
        validateScreenshot(screenshot);
        return formatScreenshotOutput(screenshot, outputType);
    }

    /**
     * Attempts to take screenshot using screencap shell command with display ID support.
     *
     * @return Result of the requested type if successful
     */
    private static <T> T retrieveScreenshotViaScreencap(
            String command, UiAutomation automation, Class<T> outputType
    ) throws IOException {
        byte[] pngBytes = executeScreencapCommand(automation, command);

        if (outputType == String.class) {
            // For String output, return Base64-encoded PNG bytes directly
            return outputType.cast(Base64.encodeToString(pngBytes, Base64.NO_WRAP));
        }

        // For Bitmap output, decode the PNG bytes
        Bitmap bitmap = BitmapFactory.decodeByteArray(pngBytes, 0, pngBytes.length);
        return outputType.cast(bitmap);
    }

    /**
     * Finds the virtual display ID by parsing SurfaceFlinger output for a given display name.
     *
     * @param automation UiAutomation instance to execute shell commands
     * @param displayName The name of the display to search for
     * @return The virtual display ID if found, null otherwise
     */
    @Nullable
    private static String findVirtualDisplayId(UiAutomation automation, String displayName) {
        try (
                ParcelFileDescriptor pfd = automation.executeShellCommand("dumpsys SurfaceFlinger --displays");
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(pfd.getFileDescriptor())))
        ) {
            Pattern idPattern = Pattern.compile("Virtual Display (\\d+)");
            Pattern namePattern = Pattern.compile("name=\"([^\"]+)\"");
            String currentDisplayId = null;

            for (String line; (line = br.readLine()) != null; ) {
                Matcher idMatcher = idPattern.matcher(line);
                if (idMatcher.find()) {
                    currentDisplayId = idMatcher.group(1);
                    continue;
                }

                if (currentDisplayId != null) {
                    Matcher nameMatcher = namePattern.matcher(line);
                    if (nameMatcher.find() && displayName.equals(nameMatcher.group(1))) {
                        return currentDisplayId;
                    }
                }
            }
        } catch (IOException e) {
            Logger.error("Failed to find virtual display ID", e);
        }

        return null;
    }

    @Nullable
    private static Display getDisplayById(int displayId) {
        DisplayManager displayManager = (DisplayManager) getInstrumentation().getTargetContext()
                .getSystemService(Service.DISPLAY_SERVICE);
        return displayManager.getDisplay(displayId);
    }

    private static boolean doesDisplayHaveCustomDensity(Display display) {
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Logger.debug(String.format("Display metrics: %s", metrics));
        return metrics.densityDpi != DENSITY_DEFAULT;
    }

    /**
     * Executes the screencap command and returns PNG bytes.
     */
    private static byte[] executeScreencapCommand(UiAutomation automation, String command) throws IOException {
        try (
                ParcelFileDescriptor pfd = automation.executeShellCommand(command);
                InputStream is = new FileInputStream(pfd.getFileDescriptor())
        ) {
            byte[] pngBytes = StringHelpers.inputStreamToByteArray(is);
            if (pngBytes.length <= PNG_MAGIC_LENGTH) {
                throw new IllegalStateException("screencap returned an invalid response");
            }
            return pngBytes;
        }
    }

    /**
     * Validates that the screenshot is not null and has valid dimensions.
     */
    private static void validateScreenshot(Bitmap screenshot) throws TakeScreenshotException {
        if (screenshot == null || screenshot.getWidth() == 0 || screenshot.getHeight() == 0) {
            throw new TakeScreenshotException();
        }
        Logger.info(String.format(
            "Got screenshot with resolution: %sx%s",
            screenshot.getWidth(),
            screenshot.getHeight()
        ));
    }

    /**
     * Formats the screenshot output according to the requested type.
     */
    private static <T> T formatScreenshotOutput(
            Bitmap screenshot, Class<T> outputType
    ) throws TakeScreenshotException {
        if (outputType == String.class) {
            try {
                return outputType.cast(bitmapToBase64Png(screenshot));
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
