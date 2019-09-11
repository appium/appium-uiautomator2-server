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

import static io.appium.uiautomator2.utils.AXWindowHelpers.getCachedWindowRoots;


public class CustomUiSelector {
    private UiSelector selector;

    CustomUiSelector(UiSelector selector) {
        this.selector = selector;
    }

    /**
     * @param node
     * @return UiSelector object, based on UiAutomationElement attributes
     */
    public UiSelector getUiSelector(AccessibilityNodeInfo node) {
        UiAutomationElement uiAutomationElement = UiAutomationElement.getCachedElement(node, getCachedWindowRoots());
        if (uiAutomationElement == null) {
            throw new IllegalArgumentException(String.format("The '%s' node is not found in the cache", node));
        }
        put(Attribute.PACKAGE, uiAutomationElement.getPackageName());
        put(Attribute.CLASS, uiAutomationElement.getClassName());
        // For proper selector matching it is important to not replace nulls with empty strings
        put(Attribute.TEXT, uiAutomationElement.getOriginalText());
        put(Attribute.CONTENT_DESC, uiAutomationElement.getContentDescription());
        put(Attribute.RESOURCE_ID, uiAutomationElement.getResourceId());
        put(Attribute.CHECKABLE, uiAutomationElement.isCheckable());
        put(Attribute.CHECKED, uiAutomationElement.isChecked());
        put(Attribute.CLICKABLE, uiAutomationElement.isClickable());
        put(Attribute.ENABLED, uiAutomationElement.isEnabled());
        put(Attribute.FOCUSABLE, uiAutomationElement.isFocusable());
        put(Attribute.FOCUSED, uiAutomationElement.isFocused());
        put(Attribute.LONG_CLICKABLE, uiAutomationElement.isLongClickable());
        put(Attribute.PASSWORD, uiAutomationElement.isPassword());
        put(Attribute.SCROLLABLE, uiAutomationElement.isScrollable());
        put(Attribute.SELECTED, uiAutomationElement.isSelected());
        put(Attribute.INDEX, uiAutomationElement.getIndex());

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
