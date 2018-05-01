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
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import io.appium.uiautomator2.common.exceptions.CompressScreenshotException;
import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
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

    private static Bitmap takeDeviceScreenshot() throws TakeScreenshotException {
        final Bitmap screenshot = uia.takeScreenshot();

        if (screenshot == null) {
            throw new TakeScreenshotException();
        }

        return screenshot;
    }

    private static byte[] compress(final Bitmap bitmap) throws CompressScreenshotException {
        final ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (!bitmap.compress(PNG, 100, stream)) {
            throw new CompressScreenshotException(PNG);
        }
        return stream.toByteArray();
    }

    private static Bitmap crop(final Bitmap bitmap, final Rect cropArea) throws
            CropScreenshotException {
        final Rect bitmapRect = new Rect(0, 0,
                bitmap.getWidth(), bitmap.getHeight());
        final Rect intersectionRect = new Rect();

        if (!intersectionRect.setIntersect(bitmapRect, cropArea)) {
            throw new CropScreenshotException(bitmapRect, cropArea);
        }

        return Bitmap.createBitmap(bitmap,
                intersectionRect.left, intersectionRect.top,
                intersectionRect.width(), intersectionRect.height());
    }

}
