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
import android.graphics.Rect;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.common.exceptions.CompressScreenshotException;
import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.ElementNotVisibleException;
import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static android.graphics.Bitmap.CompressFormat.PNG;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class ScreenshotHelper {
    private static final UiAutomation uia = CustomUiDevice.getInstance().getInstrumentation()
            .getUiAutomation();

    public static String takeScreenshot() throws CropScreenshotException,
            CompressScreenshotException, TakeScreenshotException {
        return takeDeviceScreenshot(null);
    }

    public static String takeScreenshot(Rect cropArea) throws CropScreenshotException,
            CompressScreenshotException, TakeScreenshotException {
        return takeDeviceScreenshot(cropArea);
    }

    private static String takeDeviceScreenshot(@Nullable final Rect cropArea) throws TakeScreenshotException,
            CompressScreenshotException, CropScreenshotException {
        if (cropArea != null && (cropArea.height() == 0 || cropArea.width() == 0)) {
            throw new ElementNotVisibleException("Cannot take the screenshot of an invisible element");
        }

        // We cannot use uia.getTakeScreenshot method because of https://github.com/appium/appium/issues/12199
        Object uiAutomationConnection = getField("mUiAutomationConnection", uia);
        Method takeScreenshot = method("android.app.UiAutomationConnection", "takeScreenshot",
                Rect.class, int.class);
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        android.graphics.Point realSize = new android.graphics.Point();
        display.getRealSize(realSize);
        double scale = 1.0 * metrics.widthPixels / realSize.x;
        final Rect scaledCropArea = cropArea == null
                ? new Rect(0, 0, metrics.widthPixels, metrics.heightPixels)
                : new Rect((int) (cropArea.left * scale), (int) (cropArea.top * scale), (int) (cropArea.right * scale), (int) (cropArea.bottom * scale));
        Logger.debug(String.format("Taking the screenshot of %s screen area (display logical size: %sx%s, display size in pixels: %sx%s)",
                scaledCropArea.toShortString(), realSize.x, realSize.y, metrics.widthPixels, metrics.heightPixels));
        final Object screenshotObj = invoke(takeScreenshot, uiAutomationConnection, scaledCropArea, display.getRotation());
        if (!(screenshotObj instanceof Bitmap) ||
                (cropArea == null && (((Bitmap) screenshotObj).getHeight() == 0 || ((Bitmap) screenshotObj).getWidth() == 0))) {
            throw new TakeScreenshotException();
        }
        final Bitmap screenshot = (Bitmap) screenshotObj;
        if (cropArea != null && (screenshot.getHeight() == 0 || screenshot.getWidth() == 0)) {
            throw new CropScreenshotException(new Rect(0, 0, metrics.widthPixels, metrics.heightPixels), cropArea);
        }
        return Base64.encodeToString(compress(screenshot), Base64.DEFAULT);
    }

    private static byte[] compress(final Bitmap bitmap) throws CompressScreenshotException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (!bitmap.compress(PNG, 100, stream)) {
            throw new CompressScreenshotException(PNG);
        }
        return stream.toByteArray();
    }

}
