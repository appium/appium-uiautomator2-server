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

import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.utils.Attribute;

public class CustomUiSelector {
    private UiSelector selector;

    CustomUiSelector(UiSelector selector) {
        this.selector = selector;
    }

    /**
     * @param node the source accessibility node
     * @return UiSelector object, based on UiAutomationElement attributes
     */
    public UiSelector getUiSelector(AccessibilityNodeInfo node) {
        UiElementSnapshot uiElementSnapshot = UiElementSnapshot.take(node, 0);
        put(Attribute.PACKAGE, uiElementSnapshot.getPackageName());
        put(Attribute.CLASS, uiElementSnapshot.getClassName());
        // For proper selector matching it is important to not replace nulls with empty strings
        put(Attribute.TEXT, uiElementSnapshot.getOriginalText());
        put(Attribute.CONTENT_DESC, uiElementSnapshot.getContentDescription());
        put(Attribute.RESOURCE_ID, uiElementSnapshot.getResourceId());
        put(Attribute.CHECKABLE, uiElementSnapshot.isCheckable());
        put(Attribute.CHECKED, uiElementSnapshot.isChecked());
        put(Attribute.CLICKABLE, uiElementSnapshot.isClickable());
        put(Attribute.ENABLED, uiElementSnapshot.isEnabled());
        put(Attribute.FOCUSABLE, uiElementSnapshot.isFocusable());
        put(Attribute.FOCUSED, uiElementSnapshot.isFocused());
        put(Attribute.LONG_CLICKABLE, uiElementSnapshot.isLongClickable());
        put(Attribute.PASSWORD, uiElementSnapshot.isPassword());
        put(Attribute.SCROLLABLE, uiElementSnapshot.isScrollable());
        put(Attribute.SELECTED, uiElementSnapshot.isSelected());
        put(Attribute.INDEX, uiElementSnapshot.getIndex());

        return selector;
    }

    private void put(Attribute key, Object value) {
        if (value == null) {
            return;
        }
        switch (key) {
            case PACKAGE:
                selector = selector.packageName((String) value);
                break;
            case CLASS:
                selector = selector.className((String) value);
                break;
            case TEXT:
                selector = selector.text((String) value);
                break;
            case CONTENT_DESC:
                selector = selector.descriptionContains((String) value);
                break;
            case RESOURCE_ID:
                selector = selector.resourceId((String) value);
                break;
            case CHECKABLE:
                selector = selector.checkable((Boolean) value);
                break;
            case CHECKED:
                selector = selector.checked((Boolean) value);
                break;
            case CLICKABLE:
                selector = selector.clickable((Boolean) value);
                break;
            case ENABLED:
                selector = selector.enabled((Boolean) value);
                break;
            case FOCUSABLE:
                selector = selector.focusable((Boolean) value);
                break;
            case LONG_CLICKABLE:
                selector = selector.longClickable((Boolean) value);
                break;
            case SCROLLABLE:
                selector = selector.scrollable((Boolean) value);
                break;
            case SELECTED:
                selector = selector.selected((Boolean) value);
                break;
            case INDEX:
                selector = selector.index((Integer) value);
                break;
            default: //ignore
        }
    }
}
