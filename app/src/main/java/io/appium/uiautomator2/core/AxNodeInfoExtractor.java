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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.Configurator;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public abstract class AxNodeInfoExtractor {

    // The timeout argument only affects the lookup timeout for UiObject instances
    // null timeout value means to use the default
    // `Configurator.getInstance().getWaitForSelectorTimeout()` timeout which equals to 10000ms

    @Nullable
    public static AccessibilityNodeInfo toNullableAxNodeInfo(Object object, @Nullable Long timeoutMs) {
        return extractAxNodeInfo(object, timeoutMs);
    }

    @Nullable
    public static AccessibilityNodeInfo toNullableAxNodeInfo(UiObject2 object, boolean checkStaleness) {
        return checkStaleness
                ? extractAxNodeInfo(object, null)
                : (AccessibilityNodeInfo) getField("mCachedNode", object);
    }

    @NonNull
    public static AccessibilityNodeInfo toAxNodeInfo(Object object) {
        return toAxNodeInfo(object, null);
    }

    @NonNull
    public static AccessibilityNodeInfo toAxNodeInfo(Object object, @Nullable Long timeoutMs) {
        AccessibilityNodeInfo result = extractAxNodeInfo(object, timeoutMs);
        if (result == null) {
            throw new StaleElementReferenceException();
        }
        return result;
    }

    @Nullable
    private static AccessibilityNodeInfo extractAxNodeInfo(Object object, @Nullable Long timeoutMs) {
        if (object instanceof UiObject2) {
            return (AccessibilityNodeInfo) invoke(getMethod(UiObject2.class,
                    "getAccessibilityNodeInfo"), object);
        } else if (object instanceof UiObject) {
            long timeout = timeoutMs == null
                    ? Configurator.getInstance().getWaitForSelectorTimeout()
                    : timeoutMs;
            return (AccessibilityNodeInfo) invoke(getMethod(UiObject.class,
                    "findAccessibilityNodeInfo", long.class), object, timeout);
        }
        throw new IllegalArgumentException(String.format("Unknown object type '%s'",
                object == null ? null : object.getClass().getName()));
    }
}
