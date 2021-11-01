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

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public enum ScreenOrientation {
    LANDSCAPE, PORTRAIT;

    public static ScreenOrientation ofString(String abbr) {
        try {
            return ScreenOrientation.valueOf(abbr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Orientation value '%s' is not supported. " +
                                    "Only '%s' and '%s' values could be translated into " +
                                    "a valid screen orientation", abbr, LANDSCAPE.name(), PORTRAIT.name()));
        }
    }

    public static ScreenOrientation current() {
        int orientation = asInteger();
        switch (orientation) {
            case ORIENTATION_PORTRAIT:
                return PORTRAIT;
            case ORIENTATION_LANDSCAPE:
                return LANDSCAPE;
            default:
                throw new IllegalStateException("The current screen orientation cannot be retrieved from resources");
        }
    }

    private static int asInteger() {
        return getInstrumentation().getTargetContext()
                .getResources().getConfiguration().orientation;
    }
}
