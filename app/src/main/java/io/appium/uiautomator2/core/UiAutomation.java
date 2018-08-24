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
package io.appium.uiautomator2.core;

import android.app.UiAutomation.OnAccessibilityEventListener;
import android.support.annotation.Nullable;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class UiAutomation {
    private static final String FIELD_ON_ACCESSIBILITY_EVENT_LISTENER =
            "mOnAccessibilityEventListener";
    private static final UiAutomation INSTANCE = new UiAutomation();

    private final android.app.UiAutomation uiAutomation;

    private UiAutomation() {
        uiAutomation = UiAutomatorBridge.getInstance().getUiAutomation();
    }

    public static UiAutomation getInstance() {
        return INSTANCE;
    }

    @Nullable
    public OnAccessibilityEventListener getOnAccessibilityEventListener() {
        try {
            return (OnAccessibilityEventListener) getField(android.app.UiAutomation.class,
                    FIELD_ON_ACCESSIBILITY_EVENT_LISTENER, uiAutomation);
        } catch (Exception e) {
            /* mOnAccessibilityEventListener is no longer accessible on Android P */
            return null;
        }
    }

    public void setOnAccessibilityEventListener(OnAccessibilityEventListener listener) {
        uiAutomation.setOnAccessibilityEventListener(listener);
    }
}
