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

import android.util.Pair;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.util.Collections;
import java.util.List;

import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.model.AccessibleUiObject.toAccessibleUiObject;
import static io.appium.uiautomator2.model.AccessibleUiObject.toAccessibleUiObjects;
import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;

public class UiObject2Element extends BaseElement {
    private final UiObject2 element;

    public UiObject2Element(UiObject2 element, boolean isSingleMatch, By by, @Nullable String contextId) {
        super(isSingleMatch, by, contextId);
        this.element = element;
    }

    @Override
    UiObject2Element withId(String id) {
        return (UiObject2Element) super.withId(id);
    }

    @Override
    public String getName() {
        return element.getContentDescription();
    }

    @Override
    public String getContentDesc() {
        return element.getContentDescription();
    }

    @Override
    public UiObject2 getUiObject() {
        return element;
    }

    @Nullable
    @Override
    public String getAttribute(String attr) throws UiObjectNotFoundException {
        final Attribute dstAttribute = Attribute.fromString(attr);
        if (dstAttribute == null) {
            throw generateNoAttributeException(attr);
        }

        final Object result;
        switch (dstAttribute) {
            case TEXT:
                result = getText();
                break;
            case CONTENT_DESC:
                result = element.getContentDescription();
                break;
            case CLASS:
                result = element.getClassName();
                break;
            case RESOURCE_ID:
                result = element.getResourceName();
                break;
            case CONTENT_SIZE:
                result = ElementHelpers.getContentSize(this);
                break;
            case ENABLED:
                result = element.isEnabled();
                break;
            case CHECKABLE:
                result = element.isCheckable();
                break;
            case CHECKED:
                result = element.isChecked();
                break;
            case CLICKABLE:
                result = element.isClickable();
                break;
            case FOCUSABLE:
                result = element.isFocusable();
                break;
            case FOCUSED:
                result = element.isFocused();
                break;
            case LONG_CLICKABLE:
                result = element.isLongClickable();
                break;
            case SCROLLABLE:
                result = element.isScrollable();
                break;
            case SELECTED:
                result = element.isSelected();
                break;
            case DISPLAYED:
                result = AxNodeInfoHelper.isVisible(toAxNodeInfo(element));
                break;
            case PASSWORD:
                result = AxNodeInfoHelper.isPassword(toAxNodeInfo(element));
                break;
            case BOUNDS:
                result = getBounds().toShortString();
                break;
            case PACKAGE:
                result = AxNodeInfoHelper.getPackageName(toAxNodeInfo(element));
                break;
            case SELECTION_END:
            case SELECTION_START:
                Pair<Integer, Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(toAxNodeInfo(element));
                result = selectionRange == null
                        ? null
                        : (dstAttribute == Attribute.SELECTION_END ? selectionRange.second : selectionRange.first);
                break;
            default:
                throw generateNoAttributeException(attr);
        }
        if (result == null) {
            return null;
        }
        return (result instanceof String) ? (String) result : String.valueOf(result);
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Nullable
    @Override
    public AccessibleUiObject getChild(final Object selector) throws UiObjectNotFoundException {
        if (selector instanceof UiSelector) {
            /*
             * We can't find the child element with UiSelector on UiObject2,
             * as an alternative creating UiObject with UiObject2's AccessibilityNodeInfo
             * and finding the child element on UiObject.
             */
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            UiSelector uiSelector = UiSelectorHelper.toUiSelector(nodeInfo);
            AccessibleUiObject root = CustomUiDevice.getInstance().findObject(uiSelector);
            if (root == null || !(root.getValue() instanceof UiObject)) {
                return null;
            }
            UiObject child = ((UiObject) root.getValue()).getChild((UiSelector) selector);
            return toAccessibleUiObject(child);
        }
        UiObject2 child = element.findObject((BySelector) selector);
        return toAccessibleUiObject(child);
    }

    @Override
    public List<AccessibleUiObject> getChildren(final Object selector, final By by) {
        if (selector instanceof UiSelector) {
            /*
             * We can't find the child elements with UiSelector on UiObject2,
             * as an alternative creating UiObject with UiObject2's AccessibilityNodeInfo
             * and finding the child elements on UiObject.
             */
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            UiSelector uiSelector = UiSelectorHelper.toUiSelector(nodeInfo);
            AccessibleUiObject root = CustomUiDevice.getInstance().findObject(uiSelector);
            if (root == null || !(root.getValue() instanceof UiObject)) {
                return Collections.emptyList();
            }
            UiObjectElement rootElement = new UiObjectElement(
                    (UiObject) root.getValue(), true, by, getContextId());
            return rootElement.getChildren(selector, by);
        }
        List<UiObject2> children = element.findObjects((BySelector) selector);
        return toAccessibleUiObjects(children);
    }

    @Override
    public boolean dragTo(Object destObj, int steps) throws UiObjectNotFoundException {
        if (destObj instanceof UiObject) {
            int destX = ((UiObject) destObj).getBounds().centerX();
            int destY = ((UiObject) destObj).getBounds().centerY();
            element.drag(new android.graphics.Point(destX, destY), steps);
            return true;
        }
        if (destObj instanceof UiObject2) {
            android.graphics.Point coord = ((UiObject2) destObj).getVisibleCenter();
            element.drag(coord, steps);
            return true;
        }
        Logger.error("Destination should be either UiObject or UiObject2");
        return false;
    }

    @Override
    public boolean dragTo(int destX, int destY, int steps) {
        Point coords = new Point(destX, destY);
        coords = PositionHelper.getDeviceAbsPos(coords);
        element.drag(new android.graphics.Point(coords.x.intValue(), coords.y.intValue()), steps);
        return true;
    }
}
