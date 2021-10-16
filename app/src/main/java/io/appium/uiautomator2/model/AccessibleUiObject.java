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

import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;

import java.util.ArrayList;
import java.util.List;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toNullableAxNodeInfo;

public class AccessibleUiObject {
    private final Object value;
    private final AccessibilityNodeInfo info;

    public AccessibleUiObject(@NonNull UiObject value, @NonNull AccessibilityNodeInfo info) {
        this.value = value;
        this.info = info;
    }

    public AccessibleUiObject(@NonNull UiObject2 value, @NonNull AccessibilityNodeInfo info) {
        this.value = value;
        this.info = info;
    }

    @NonNull
    public Object getValue() {
        return this.value;
    }

    @NonNull
    public AccessibilityNodeInfo getInfo() {
        return this.info;
    }

    @Nullable
    public static AccessibleUiObject toAccessibleUiObject(@Nullable UiObject uiObject) {
        if (uiObject == null) {
            return null;
        }
        AccessibilityNodeInfo info = toNullableAxNodeInfo(uiObject);
        return info == null ? null : new AccessibleUiObject(uiObject, info);
    }

    public static List<AccessibleUiObject> toAccessibleUiObjects(List<?> uiObjects) {
        List<AccessibleUiObject> result = new ArrayList<>();
        for (Object obj: uiObjects) {
            if (obj instanceof UiObject) {
                AccessibilityNodeInfo info = toNullableAxNodeInfo((UiObject) obj);
                if (info != null) {
                    result.add(new AccessibleUiObject((UiObject) obj, info));
                }
            } else if (obj instanceof UiObject2) {
                AccessibilityNodeInfo info = toNullableAxNodeInfo((UiObject2) obj, false);
                if (info != null) {
                    result.add(new AccessibleUiObject((UiObject2) obj, info));
                }
            }
        }
        return result;
    }

    @Nullable
    public static AccessibleUiObject toAccessibleUiObject(@Nullable UiObject2 uiObject2) {
        if (uiObject2 == null) {
            return null;
        }
        AccessibilityNodeInfo info = toNullableAxNodeInfo(uiObject2, false);
        return info == null ? null : new AccessibleUiObject(uiObject2, info);
    }
}
