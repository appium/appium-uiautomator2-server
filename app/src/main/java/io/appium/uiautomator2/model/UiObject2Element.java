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

import android.graphics.Rect;
import android.util.Range;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;
import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.core.AccessibilityNodeInfoHelpers;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class UiObject2Element extends BaseElement {

    private final UiObject2 element;
    private final String id;
    private final By by;
    private final String contextId;
    private final boolean isSingleMatch;

    public UiObject2Element(String id, UiObject2 element, boolean isSingleMatch, By by,
                            @Nullable String contextId) {
        this.id = id;
        this.element = element;
        this.by = by;
        this.contextId = contextId;
        this.isSingleMatch = isSingleMatch;
    }

    @Override
    public void click() {
        element.click();
    }

    @Override
    public boolean longClick() {
        element.longClick();
        return true;
    }

    @Override
    public String getText() throws UiObjectNotFoundException {
        // By convention the text is replaced with an empty string if it equals to null
        return ElementHelpers.getText(element);
    }

    @Override
    public String getName() {
        return element.getContentDescription();
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
                result = AccessibilityNodeInfoHelpers.isVisible(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            case PASSWORD:
                result = AccessibilityNodeInfoHelpers.isPassword(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            case BOUNDS:
                result = element.getVisibleBounds().toShortString();
                break;
            case PACKAGE:
                result = AccessibilityNodeInfoHelpers.getPackageName(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            case SELECTION_END:
            case SELECTION_START:
                Range<Integer> selectionRange = AccessibilityNodeInfoHelpers.getSelectionRange(AccessibilityNodeInfoGetter.fromUiObject(element));
                result = selectionRange == null ? null
                        : (dstAttribute == Attribute.SELECTION_END ? selectionRange.getUpper() : selectionRange.getLower());
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
    public boolean setText(final String text) {
        return ElementHelpers.setText(element, text);
    }

    @Override
    public By getBy() {
        return by;
    }

    @Override
    public String getContextId() {
        return isBlank(contextId) ? null : contextId;
    }

    @Override
    public boolean isSingleMatch() {
        return isSingleMatch;
    }

    @Override
    public void clear() {
        element.clear();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Rect getBounds() {
        return element.getVisibleBounds();
    }

    @Nullable
    @Override
    public Object getChild(final Object selector) throws UiObjectNotFoundException {
        if (selector instanceof UiSelector) {
            /*
             * We can't find the child element with UiSelector on UiObject2,
             * as an alternative creating UiObject with UiObject2's AccessibilityNodeInfo
             * and finding the child element on UiObject.
             */
            AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);

            UiSelector uiSelector = new UiSelector();
            CustomUiSelector customUiSelector = new CustomUiSelector(uiSelector);
            uiSelector = customUiSelector.getUiSelector(nodeInfo);
            Object uiObject = CustomUiDevice.getInstance().findObject(uiSelector);
            if (uiObject instanceof UiObject) {
                AccessibilityNodeInfoGetter.fromUiObject(element);
                return ((UiObject) uiObject).getChild((UiSelector) selector);
            }
            return null;
        }
        return element.findObject((BySelector) selector);
    }

    @Override
    public List<?> getChildren(final Object selector, final By by) throws UiObjectNotFoundException {
        if (selector instanceof UiSelector) {
            /*
             * We can't find the child elements with UiSelector on UiObject2,
             * as an alternative creating UiObject with UiObject2's AccessibilityNodeInfo
             * and finding the child elements on UiObject.
             */
            AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);

            UiSelector uiSelector = new UiSelector();
            CustomUiSelector customUiSelector = new CustomUiSelector(uiSelector);
            uiSelector = customUiSelector.getUiSelector(nodeInfo);
            UiObject uiObject = (UiObject) CustomUiDevice.getInstance().findObject(uiSelector);
            String id = UUID.randomUUID().toString();
            AndroidElement androidElement = getAndroidElement(id, uiObject, true, by, getContextId());
            return androidElement.getChildren(selector, by);
        }
        return element.findObjects((BySelector) selector);
    }

    @Override
    public String getContentDesc() {
        return element.getContentDescription();
    }

    @Override
    public UiObject2 getUiObject() {
        return element;
    }

    @Override
    public Point getAbsolutePosition(final Point offset) {
        final Rect bounds = this.getBounds();
        Logger.debug("Element bounds: " + bounds.toShortString());
        return PositionHelper.getAbsolutePosition(new Point(bounds.left, bounds.top), bounds, offset, false);
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
