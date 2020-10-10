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

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiSelector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import io.appium.uiautomator2.utils.Attribute;

public class UiSelectorHelper {
    private static final Set<Attribute> MATCHING_ATTRIBUTES = new HashSet<>(
            Arrays.asList(Attribute.PACKAGE, Attribute.CLASS, Attribute.ORIGINAL_TEXT,
                    Attribute.CONTENT_DESC, Attribute.RESOURCE_ID, Attribute.CHECKABLE,
                    Attribute.CHECKED, Attribute.CLICKABLE, Attribute.ENABLED, Attribute.FOCUSABLE,
                    Attribute.FOCUSED, Attribute.LONG_CLICKABLE, Attribute.PASSWORD, Attribute.SCROLLABLE,
                    Attribute.SELECTED, Attribute.INDEX)
    );

    private UiSelector selector;

    private UiSelectorHelper(UiSelector selector, UiElementSnapshot uiElementSnapshot) {
        this.selector = selector;
        addSearchCriteria(Attribute.PACKAGE, uiElementSnapshot.getPackageName());
        addSearchCriteria(Attribute.CLASS, uiElementSnapshot.getClassName());
        // For proper selector matching it is important to not replace nulls with empty strings
        addSearchCriteria(Attribute.TEXT, uiElementSnapshot.getOriginalText());
        addSearchCriteria(Attribute.CONTENT_DESC, uiElementSnapshot.getContentDescription());
        addSearchCriteria(Attribute.RESOURCE_ID, uiElementSnapshot.getResourceId());
        addSearchCriteria(Attribute.CHECKABLE, uiElementSnapshot.isCheckable());
        addSearchCriteria(Attribute.CHECKED, uiElementSnapshot.isChecked());
        addSearchCriteria(Attribute.CLICKABLE, uiElementSnapshot.isClickable());
        addSearchCriteria(Attribute.ENABLED, uiElementSnapshot.isEnabled());
        addSearchCriteria(Attribute.FOCUSABLE, uiElementSnapshot.isFocusable());
        addSearchCriteria(Attribute.FOCUSED, uiElementSnapshot.isFocused());
        addSearchCriteria(Attribute.LONG_CLICKABLE, uiElementSnapshot.isLongClickable());
        addSearchCriteria(Attribute.PASSWORD, uiElementSnapshot.isPassword());
        addSearchCriteria(Attribute.SCROLLABLE, uiElementSnapshot.isScrollable());
        addSearchCriteria(Attribute.SELECTED, uiElementSnapshot.isSelected());
        addSearchCriteria(Attribute.INDEX, uiElementSnapshot.getIndex());
    }

    /**
     * @param node the source accessibility node
     * @return UiSelector object, based on UiAutomationElement attributes
     */
    public static UiSelector toUiSelector(AccessibilityNodeInfo node) {
        UiElementSnapshot uiElementSnapshot = UiElementSnapshot.take(node, 0, MATCHING_ATTRIBUTES);
        return new UiSelectorHelper(new UiSelector(), uiElementSnapshot).selector;
    }

    private void addSearchCriteria(Attribute key, @Nullable Object value) {
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
                selector = selector.description((String) value);
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
