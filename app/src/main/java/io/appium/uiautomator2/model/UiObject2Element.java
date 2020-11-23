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

import androidx.annotation.Nullable;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.util.List;
import java.util.Objects;

import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class UiObject2Element extends BaseElement {
    private final UiObject2 element;
    private String id;
    private final By by;
    private final String contextId;
    private final boolean isSingleMatch;

    public UiObject2Element(UiObject2 element, boolean isSingleMatch, By by, @Nullable String contextId) {
        this.element = element;
        this.by = by;
        this.contextId = contextId;
        this.isSingleMatch = isSingleMatch;
    }

    @Override
    public void click() {
        AxNodeInfoHelper.click(toAxNodeInfo(element));
    }

    @Override
    public void longClick() {
        AxNodeInfoHelper.longClick(toAxNodeInfo(element));
    }

    @Override
    public void longClick(long durationMs) {
        AxNodeInfoHelper.longClick(toAxNodeInfo(element), durationMs);
    }

    @Override
    public void drag(Point dest) {
        AxNodeInfoHelper.drag(toAxNodeInfo(element), dest.toNativePoint());
    }

    @Override
    public void drag(Point dest, @Nullable Integer speed) {
        AxNodeInfoHelper.drag(toAxNodeInfo(element), dest.toNativePoint(), speed);
    }

    @Override
    public void pinchClose(float percent) {
        AxNodeInfoHelper.pinchClose(toAxNodeInfo(element), percent);
    }

    @Override
    public void pinchClose(float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.pinchClose(toAxNodeInfo(element), percent, speed);
    }

    @Override
    public void pinchOpen(float percent) {
        AxNodeInfoHelper.pinchOpen(toAxNodeInfo(element), percent);
    }

    @Override
    public void pinchOpen(float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.pinchOpen(toAxNodeInfo(element), percent, speed);
    }

    @Override
    public void swipe(Direction direction, float percent) {
        AxNodeInfoHelper.swipe(toAxNodeInfo(element), direction, percent);
    }

    @Override
    public void swipe(Direction direction, float percent, @Nullable Integer speed) {
        AxNodeInfoHelper.swipe(toAxNodeInfo(element), direction, percent, speed);
    }

    @Override
    public boolean scroll(Direction direction, float percent) {
        return AxNodeInfoHelper.scroll(toAxNodeInfo(element), direction, percent);
    }

    @Override
    public boolean scroll(Direction direction, float percent, @Nullable Integer speed) {
        return AxNodeInfoHelper.scroll(toAxNodeInfo(element), direction, percent, speed);
    }

    @Override
    public boolean fling(Direction direction) {
        return AxNodeInfoHelper.fling(toAxNodeInfo(element), direction);
    }

    @Override
    public boolean fling(Direction direction, @Nullable Integer speed) {
        return AxNodeInfoHelper.fling(toAxNodeInfo(element), direction, speed);
    }

    @Override
    public String getText() {
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
                Range<Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(toAxNodeInfo(element));
                result = selectionRange == null
                        ? null
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
    public void setProgress(float value) {
        ElementHelpers.setProgress(element, value);
    }

    @Override
    public boolean canSetProgress() {
        return ElementHelpers.canSetProgress(element);
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

    UiObject2Element withId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public Rect getBounds() {
        return AxNodeInfoHelper.getBounds(toAxNodeInfo(element));
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
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            UiSelector uiSelector = UiSelectorHelper.toUiSelector(nodeInfo);
            Object uiObject = CustomUiDevice.getInstance().findObject(uiSelector);
            if (!(uiObject instanceof UiObject)) {
                return null;
            }
            UiObject result = ((UiObject) uiObject).getChild((UiSelector) selector);
            if (result != null && !result.exists()) {
                return null;
            }
            return result;
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
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            UiSelector uiSelector = UiSelectorHelper.toUiSelector(nodeInfo);
            UiObject uiObject = (UiObject) CustomUiDevice.getInstance().findObject(uiSelector);
            UiObjectElement androidElement = new UiObjectElement(uiObject, true, by, getContextId());
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof UiObject2Element)) {
            return false;
        }
        if (this == other) {
            return true;
        }

        UiObject2Element otherEl = (UiObject2Element)other;
        return Objects.equals(this.element, otherEl.element)
                && Objects.equals(by, otherEl.by)
                && Objects.equals(contextId, otherEl.contextId)
                && this.isSingleMatch == otherEl.isSingleMatch;
    }
}
