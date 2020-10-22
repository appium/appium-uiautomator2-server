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

package io.appium.uiautomator2.model;

import androidx.test.platform.app.InstrumentationRegistry;

import io.appium.uiautomator2.utils.Device;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public enum ScreenRotation {
    ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270;

    public static final String LANDSCAPE = "LANDSCAPE";
    public static final String PORTRAIT = "PORTRAIT";

    public static ScreenRotation current() {
        int rotation = Device.getUiDevice().getDisplayRotation();
        for (ScreenRotation val : values()) {
            if (rotation == val.ordinal()) {
                return val;
            }
        }
        throw new IllegalStateException(String.format("Rotation value %s is not known", rotation));
    }

    public static ScreenRotation ofDegrees(int degrees) {
        switch (degrees) {
            case 0:
                return ROTATION_0;
            case 90:
                return ROTATION_90;
            case 180:
                return ROTATION_180;
            case 270:
                return ROTATION_270;
            default:
                throw new IllegalArgumentException(
                        String.format("Rotation value of %s degrees is not supported. " +
                                "Only 0, 90, 180 and 270 degrees could be translated into " +
                                "a valid screen rotation", degrees));
        }
    }

    public static ScreenRotation ofOrientation(String abbr) {
        int currentOrientation = getOrientationFromConfig();
        ScreenRotation currentRotation = current();
        switch (abbr.toUpperCase()) {
            case LANDSCAPE:
                if (currentOrientation == ORIENTATION_LANDSCAPE) {
                    if (currentRotation == ROTATION_90) {
                        return ROTATION_270;
                    }
                    if (currentRotation == ROTATION_180) {
                        return ROTATION_0;
                    }
                    return currentRotation;
                }

                return currentRotation == ROTATION_270 || currentRotation == ROTATION_90
                    ? ROTATION_0
                    : ROTATION_270;
            case PORTRAIT:
                if (currentOrientation != ORIENTATION_LANDSCAPE) {
                    if (currentRotation == ROTATION_90) {
                        return ROTATION_270;
                    }
                    if (currentRotation == ROTATION_180) {
                        return ROTATION_0;
                    }
                    return currentRotation;
                }

                return currentRotation == ROTATION_270 || currentRotation == ROTATION_90
                        ? ROTATION_0
                        : ROTATION_270;
            default:
                throw new IllegalArgumentException(
                        String.format("Orientation value '%s' is not supported. " +
                                "Only '%s' and '%s' values could be translated into " +
                                "a valid screen orientation", abbr, LANDSCAPE, PORTRAIT));
        }
    }

    public String toOrientation() {
        int orientation = getOrientationFromConfig();
        switch (orientation) {
            case ORIENTATION_PORTRAIT:
                return PORTRAIT;
            case ORIENTATION_LANDSCAPE:
                return LANDSCAPE;
            default:
                return String.format("UNKNOWN(%s/%s)", orientation, current().ordinal());
        }
    }

    private static int getOrientationFromConfig() {
        return InstrumentationRegistry.getInstrumentation()
                .getContext().getResources().getConfiguration().orientation;
    }
}
