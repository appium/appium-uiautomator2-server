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
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public abstract class AxNodeExtractor {

    @Nullable
    public static AccessibilityNodeInfo toNullableAxNode(Object object) {
        return getAxNode(object);
    }

    @NonNull
    public static AccessibilityNodeInfo toAxNode(Object object) {
        AccessibilityNodeInfo result = getAxNode(object);
        if (result == null) {
            throw new StaleElementReferenceException();
        }
        return result;
    }

    @Nullable
    private static AccessibilityNodeInfo getAxNode(Object object) {
        if (object instanceof UiObject2) {
            return (AccessibilityNodeInfo) invoke(method(UiObject2.class,
                    "getAccessibilityNodeInfo"), object);
        } else if (object instanceof UiObject) {
            long timeout = Configurator.getInstance().getWaitForSelectorTimeout();
            return (AccessibilityNodeInfo) invoke(method(UiObject.class,
                    "findAccessibilityNodeInfo", long.class), object, timeout);
        }
        throw new IllegalArgumentException(String.format("Unknown object type '%s'",
                object == null ? null : object.getClass().getName()));
    }
}
