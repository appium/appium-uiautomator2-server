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
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public abstract class AxNodeInfoExtractor {

    @Nullable
    public static AccessibilityNodeInfo toNullableAxNodeInfo(UiObject object) {
        return extractAxNodeInfo(object);
    }

    @Nullable
    public static AccessibilityNodeInfo toNullableAxNodeInfo(UiObject2 object, boolean checkStaleness) {
        return checkStaleness
                ? extractAxNodeInfo(object)
                : (AccessibilityNodeInfo) getField("mCachedNode", object);
    }

    @NonNull
    public static AccessibilityNodeInfo toAxNodeInfo(Object object) {
        AccessibilityNodeInfo result = extractAxNodeInfo(object);
        if (result == null) {
            throw new StaleElementReferenceException();
        }
        return result;
    }

    @Nullable
    private static AccessibilityNodeInfo extractAxNodeInfo(Object object) {
        if (object instanceof UiObject2) {
            return (AccessibilityNodeInfo) invoke(getMethod(UiObject2.class,
                    "getAccessibilityNodeInfo"), object);
        } else if (object instanceof UiObject) {
            return (AccessibilityNodeInfo) invoke(getMethod(UiObject.class,
                    "findAccessibilityNodeInfo", long.class), object, 0L);
        }
        throw new IllegalArgumentException(String.format("Unknown object type '%s'",
                object == null ? null : object.getClass().getName()));
    }
}
