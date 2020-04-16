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

import io.appium.uiautomator2.utils.Device;

public enum ScreenOrientation {
    ROTATION_0, ROTATION_90, ROTATION_180, ROTATION_270;

    public static final String LANDSCAPE = "LANDSCAPE";
    public static final String PORTRAIT = "PORTRAIT";

    public static ScreenOrientation current() {
        int rotation = Device.getUiDevice().getDisplayRotation();
        for (ScreenOrientation val : values()) {
            if (rotation == val.ordinal()) {
                return val;
            }
        }
        throw new IllegalArgumentException(
                String.format("Orientation value '%s' is not supported", rotation));
    }

    public static ScreenOrientation ofDegrees(int degrees) {
        switch (degrees) {
            case 0:
                return ROTATION_0;
            case 1:
                return ROTATION_90;
            case 2:
                return ROTATION_180;
            case 3:
                return ROTATION_270;
        }
        throw new IllegalArgumentException(
                String.format("Orientation value is not supported for %s degrees", degrees));
    }

    @Override
    public String toString() {
        switch (this) {
            case ROTATION_0:
            case ROTATION_180:
                return PORTRAIT;
            case ROTATION_90:
            case ROTATION_270:
                return LANDSCAPE;
            default:
                return String.format("UNKNOWN(%s)", ordinal());
        }
    }
}
