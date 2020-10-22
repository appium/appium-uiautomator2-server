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

import io.appium.uiautomator2.model.settings.UseResourcesForOrientationDetection;
import io.appium.uiautomator2.utils.Device;

import static io.appium.uiautomator2.model.settings.Settings.USE_RESOURCES_FOR_ORIENTATION_DETECTION;

public enum ScreenRotation {
    ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270;

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
        ScreenOrientation desiredOrientation;
        try {
            desiredOrientation = ScreenOrientation.valueOf(abbr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Orientation value '%s' is not supported. " +
                                    "Only '%s' and '%s' values could be translated into " +
                                    "a valid screen orientation", abbr,
                            ScreenOrientation.LANDSCAPE.name(), ScreenOrientation.PORTRAIT.name()));
        }
        ScreenRotation currentRotation = current();

        if (!((UseResourcesForOrientationDetection) USE_RESOURCES_FOR_ORIENTATION_DETECTION.getSetting()).getValue()) {
            return desiredOrientation == ScreenOrientation.LANDSCAPE ? ROTATION_270 : ROTATION_0;
        }

        ScreenOrientation currentOrientation = ScreenOrientation.current();
        if (desiredOrientation == ScreenOrientation.LANDSCAPE) {
            if (currentOrientation == ScreenOrientation.LANDSCAPE) {
                if (currentRotation == ROTATION_90) {
                    return ROTATION_270;
                }
                if (currentRotation == ROTATION_180) {
                    return ROTATION_0;
                }
                return currentRotation;
            }
        } else {
            if (currentOrientation != ScreenOrientation.LANDSCAPE) {
                if (currentRotation == ROTATION_90) {
                    return ROTATION_270;
                }
                if (currentRotation == ROTATION_180) {
                    return ROTATION_0;
                }
                return currentRotation;
            }
        }
        return currentRotation == ROTATION_270 || currentRotation == ROTATION_90
                ? ROTATION_0
                : ROTATION_270;
    }

    public ScreenOrientation toOrientation() {
        return this == ROTATION_0 || this == ROTATION_180 ? ScreenOrientation.PORTRAIT : ScreenOrientation.LANDSCAPE;
    }
}
