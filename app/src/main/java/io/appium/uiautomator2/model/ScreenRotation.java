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

import java.util.Arrays;
import java.util.Objects;

import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.UseResourcesForOrientationDetection;
import io.appium.uiautomator2.utils.Device;

public enum ScreenRotation {
    ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270;

    public static ScreenRotation current() {
        int rotation = Device.getUiDevice().getDisplayRotation();
        return Arrays.stream(values())
                .filter(val -> rotation == val.ordinal())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Rotation value %s is not known", rotation)));
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

    private static ScreenRotation align(ScreenRotation value) {
        if (value == ROTATION_90) {
            return ROTATION_270;
        }
        if (value == ROTATION_180) {
            return ROTATION_0;
        }
        return value;
    }

    public static ScreenRotation ofOrientation(ScreenOrientation desiredOrientation) {
        if (!Settings.get(UseResourcesForOrientationDetection.class).getValue()) {
            return desiredOrientation == ScreenOrientation.LANDSCAPE ? ROTATION_270 : ROTATION_0;
        }

        ScreenOrientation currentOrientation = ScreenOrientation.current();
        ScreenRotation currentRotation = current();
        if (Objects.equals(desiredOrientation, currentOrientation)) {
            // Align the resulting rotation if the screen already has the desired orientation
            return align(currentRotation);
        }
        // Otherwise apply both align and flip to the resulting rotation value
        return currentRotation == ROTATION_270 || currentRotation == ROTATION_90 ? ROTATION_0 : ROTATION_270;
    }

    public ScreenOrientation toOrientation() {
        return this == ROTATION_0 || this == ROTATION_180 ? ScreenOrientation.PORTRAIT : ScreenOrientation.LANDSCAPE;
    }
}
