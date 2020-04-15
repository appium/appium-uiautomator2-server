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

import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

/**
 * Static helper class for getting {@link AccessibilityNodeInfo} instances.
 */
public abstract class AccessibilityNodeInfoGetter {
    private static final long TIME_IN_MS = 10000;

    /**
     * Gets the {@link AccessibilityNodeInfo} associated with the given {@link UiObject2}
     */
    @Nullable
    public static AccessibilityNodeInfo fromUiObject(Object object) {
        return fromUiObject(object, TIME_IN_MS);
    }

    @Nullable
    public static AccessibilityNodeInfo fromUiObject(Object object, long timeout) {
        if (object instanceof UiObject2) {
            return (AccessibilityNodeInfo) invoke(method(UiObject2.class,
                    "getAccessibilityNodeInfo"), object);
        } else if (object instanceof UiObject) {
            return (AccessibilityNodeInfo) invoke(method(UiObject.class,
                    "findAccessibilityNodeInfo", long.class), object, timeout);
        }
        throw new UiAutomator2Exception("Unknown object type: " + object.getClass().getName());
    }

    @Nullable
    public static AccessibilityNodeInfo fromUiObjectDefaultTimeout(Object object) {
        long timeout = TIME_IN_MS;
        if (object instanceof UiObject) {
            timeout = (long) ReflectionUtils.getField(UiObject.class,
                    "WAIT_FOR_SELECTOR_TIMEOUT", object);
        }
        return fromUiObject(object, timeout);
    }
}
