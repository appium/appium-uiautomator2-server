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
import android.support.annotation.Nullable;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.NoAttributeFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class UiObject2Element implements AndroidElement {

    private final UiObject2 element;
    private final String id;
    private final By by;

    public UiObject2Element(String id, UiObject2 element, By by) {
        this.id = id;
        this.element = element;
        this.by = by;
    }

    private static boolean isToastElement(AccessibilityNodeInfo nodeInfo) {
        // Using Objects.equals to handle the case when class name can be null
        return Objects.equals(nodeInfo.getClassName(), Toast.class.getName());
    }

    public void click() {
        element.click();
    }

    public boolean longClick() {
        element.longClick();
        return true;
    }

    public String getText() {
        AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) getField(UiObject2.class,
                "mCachedNode", element);
        if (nodeInfo == null) {
            return "";
        }

        /*
         * If the given element is TOAST element, we can't perform any operation on {@link UiObject2} as it
         * not formed with valid AccessibilityNodeInfo, Instead we are using custom created AccessibilityNodeInfo of
         * TOAST Element to retrieve the Text.
         */
        if (isToastElement(nodeInfo)) {
            return nodeInfo.getText().toString();
        }

        if (nodeInfo.getRangeInfo() != null) {
            /* Refresh accessibility node info to get actual state of element */
            nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
            if (nodeInfo != null && nodeInfo.getRangeInfo() != null) {
                return Float.toString(nodeInfo.getRangeInfo().getCurrent());
            }
        }
        // on null returning empty string
        return element.getText() != null ? element.getText() : "";
    }

    public String getName() {
        return element.getContentDescription();
    }

    public String getStringAttribute(final String attr) throws NoAttributeFoundException {
        if ("name".equalsIgnoreCase(attr) || "text".equalsIgnoreCase(attr)) {
            return getText();
        }
        if ("contentDescription".equalsIgnoreCase(attr)) {
            return element.getContentDescription();
        }
        if ("className".equalsIgnoreCase(attr)) {
            return element.getClassName();
        }
        if ("resourceId".equalsIgnoreCase(attr) || "resource-id".equalsIgnoreCase(attr)) {
            return element.getResourceName();
        }
        throw generateNoAttributeException(attr);
    }

    public boolean getBoolAttribute(final String attr) throws UiAutomator2Exception {
        switch (attr) {
            case "enabled":
                return element.isEnabled();
            case "checkable":
                return element.isCheckable();
            case "checked":
                return element.isChecked();
            case "clickable":
                return element.isClickable();
            case "focusable":
                return element.isFocusable();
            case "focused":
                return element.isFocused();
            case "longClickable":
                return element.isLongClickable();
            case "scrollable":
                return element.isScrollable();
            case "selected":
                return element.isSelected();
            case "displayed":
                return AccessibilityNodeInfoGetter.fromUiObject(element) != null;
            case "password":
                AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
                return nodeInfo != null && nodeInfo.isPassword();
            default:
                throw generateNoAttributeException(attr);
        }
    }

    public boolean setText(final String text) {
        return ElementHelpers.setText(element, text);
    }

    public By getBy() {
        return by;
    }

    public void clear() {
        element.clear();
    }

    public String getId() {
        return this.id;
    }

    public Rect getBounds() {
        return element.getVisibleBounds();
    }

    @Nullable
    public Object getChild(final Object selector)
            throws UiObjectNotFoundException, InvalidSelectorException, ClassNotFoundException {
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

    public List<Object> getChildren(final Object selector, final By by)
            throws UiObjectNotFoundException, InvalidSelectorException, ClassNotFoundException {
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
            AndroidElement androidElement = getAndroidElement(id, uiObject, by);
            return androidElement.getChildren(selector, by);
        }
        //noinspection unchecked
        return (List) element.findObjects((BySelector) selector);
    }

    public String getContentDesc() {
        return element.getContentDescription();
    }

    public UiObject2 getUiObject() {
        return element;
    }

    public Point getAbsolutePosition(final Point point)
            throws InvalidCoordinatesException {
        final Rect rect = this.getBounds();

        Logger.debug("Element bounds: " + rect.toShortString());

        return PositionHelper.getAbsolutePosition(point, rect, new Point(rect.left, rect.top), false);
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
    public boolean dragTo(int destX, int destY, int steps) throws InvalidCoordinatesException {
        Point coords = new Point(destX, destY);
        coords = PositionHelper.getDeviceAbsPos(coords);
        element.drag(new android.graphics.Point(coords.x.intValue(), coords.y.intValue()), steps);
        return true;
    }
}
