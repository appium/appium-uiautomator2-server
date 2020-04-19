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
import androidx.test.uiautomator.Configurator;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.core.AccessibilityNodeInfoHelpers;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class UiObjectElement extends BaseElement {

    private static final Pattern endsWithInstancePattern = Pattern.compile(".*INSTANCE=\\d+]$");
    private final UiObject element;
    private final String id;
    private final By by;
    private final String contextId;
    private final boolean isSingleMatch;

    public UiObjectElement(String id, UiObject element, boolean isSingleMatch, By by,
                           @Nullable String contextId) {
        this.id = id;
        this.element = element;
        this.by = by;
        this.contextId = contextId;
        this.isSingleMatch = isSingleMatch;
    }

    @Override
    public void click() throws UiObjectNotFoundException {
        element.click();
    }

    @Override
    public boolean longClick() throws UiObjectNotFoundException {
        return element.longClick();
    }

    @Override
    public String getText() throws UiObjectNotFoundException {
        // By convention the text is replaced with an empty string if it equals to null
        return ElementHelpers.getText(element);
    }

    @Override
    public String getName() throws UiObjectNotFoundException {
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
                result = getResourceId();
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
                result = element.exists() && AccessibilityNodeInfoHelpers.isVisible(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            case PASSWORD:
                result = AccessibilityNodeInfoHelpers.isPassword(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            case BOUNDS:
                result = element.getVisibleBounds().toShortString();
                break;
            case PACKAGE: {
                result = AccessibilityNodeInfoHelpers.getPackageName(AccessibilityNodeInfoGetter.fromUiObject(element));
                break;
            }
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
    public void clear() throws UiObjectNotFoundException {
        element.setText("");
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Rect getBounds() throws UiObjectNotFoundException {
        return element.getVisibleBounds();
    }

    @Nullable
    @Override
    public Object getChild(final Object selector) throws UiObjectNotFoundException {
        if (selector instanceof BySelector) {
            /*
             * We can't find the child element with BySelector on UiObject,
             * as an alternative creating UiObject2 with UiObject's AccessibilityNodeInfo
             * and finding the child element on UiObject2.
             */
            AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
            Object uiObject2 = CustomUiDevice.getInstance().findObject(nodeInfo);
            return (uiObject2 instanceof UiObject2)
                    ? ((UiObject2) uiObject2).findObject((BySelector) selector) : null;
        }
        return element.getChild((UiSelector) selector);
    }

    @Override
    public List<?> getChildren(final Object selector, final By by) throws UiObjectNotFoundException {
        if (selector instanceof BySelector) {
            /*
             * We can't find the child elements with BySelector on UiObject,
             * as an alternative creating UiObject2 with UiObject's AccessibilityNodeInfo
             * and finding the child elements on UiObject2.
             */
            AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
            UiObject2 uiObject2 = (UiObject2) CustomUiDevice.getInstance().findObject(nodeInfo);
            if (uiObject2 == null) {
                throw new ElementNotFoundException();
            }
            return uiObject2.findObjects((BySelector) selector);
        }
        return this.getChildElements((UiSelector) selector);
    }


    public ArrayList<UiObject> getChildElements(final UiSelector sel) throws UiObjectNotFoundException {
        boolean keepSearching = true;
        final String selectorString = sel.toString();
        final boolean useIndex = selectorString.contains("CLASS_REGEX=");
        final boolean endsWithInstance = endsWithInstancePattern.matcher(selectorString).matches();
        Logger.debug("getElements selector:" + selectorString);
        final ArrayList<UiObject> elements = new ArrayList<>();

        // If sel is UiSelector[CLASS=android.widget.Button, INSTANCE=0]
        // then invoking instance with a non-0 argument will corrupt the selector.
        //
        // sel.instance(1) will transform the selector into:
        // UiSelector[CLASS=android.widget.Button, INSTANCE=1]
        //
        // The selector now points to an entirely different element.
        if (endsWithInstance) {
            Logger.debug("Selector ends with instance.");
            // There's exactly one element when using instance.
            UiObject instanceObj = Device.getUiDevice().findObject(sel);
            if (instanceObj != null && instanceObj.exists()) {
                elements.add(instanceObj);
            }
            return elements;
        }

        UiObject lastFoundObj;

        UiSelector tmp;
        int counter = 0;
        while (keepSearching) {
            if (element == null) {
                Logger.debug("Element] is null: (" + counter + ")");

                if (useIndex) {
                    Logger.debug("  using index...");
                    tmp = sel.index(counter);
                } else {
                    tmp = sel.instance(counter);
                }

                Logger.debug("getElements tmp selector:" + tmp.toString());
                lastFoundObj = Device.getUiDevice().findObject(tmp);
            } else {
                Logger.debug("Element is " + getId() + ", counter: " + counter);
                lastFoundObj = element.getChild(sel.instance(counter));
            }
            counter++;
            if (lastFoundObj != null && lastFoundObj.exists()) {
                elements.add(lastFoundObj);
            } else {
                keepSearching = false;
            }
        }
        return elements;
    }

    @Override
    public String getContentDesc() throws UiObjectNotFoundException {
        return element.getContentDescription();
    }

    @Override
    public UiObject getUiObject() {
        return element;
    }

    @Override
    public Point getAbsolutePosition(final Point offset) throws UiObjectNotFoundException {
        final Rect bounds = this.getBounds();
        Logger.debug("Element bounds: " + bounds.toShortString());
        return PositionHelper.getAbsolutePosition(new Point(bounds.left, bounds.top), bounds, offset, false);
    }

    public String getResourceId() {
        String resourceId = "";

        try {
            /*
             * Unfortunately UiObject does not implement a getResourceId method.
             * There is currently no way to determine the resource-id of a given
             * element represented by UiObject. Until this support is added to
             * UiAutomater, we try to match the implementation pattern that is
             * already used by UiObject for getting attributes using reflection.
             * The returned string matches exactly what is displayed in the
             * UiAutomater inspector.
             */
            AccessibilityNodeInfo node = (AccessibilityNodeInfo) invoke(method(element.getClass(), "findAccessibilityNodeInfo", long.class),
                    element, Configurator.getInstance().getWaitForSelectorTimeout());

            if (node == null) {
                throw new UiObjectNotFoundException(element.getSelector().toString());
            }

            resourceId = node.getViewIdResourceName();
        } catch (final Exception e) {
            Logger.error("Exception: " + e + " (" + e.getMessage() + ")");
        }

        return resourceId;
    }

    @Override
    public boolean dragTo(final int destX, final int destY, final int steps) throws UiObjectNotFoundException {
        Point coords = new Point(destX, destY);
        coords = PositionHelper.getDeviceAbsPos(coords);
        return element.dragTo(coords.x.intValue(), coords.y.intValue(), steps);
    }

    @Override
    public boolean dragTo(final Object destObj, final int steps) throws UiObjectNotFoundException {
        if (destObj instanceof UiObject) {
            return element.dragTo((UiObject) destObj, steps);
        }

        if (destObj instanceof UiObject2) {
            android.graphics.Point coords = ((UiObject2) destObj).getVisibleCenter();
            return dragTo(coords.x, coords.y, steps);
        }

        Logger.error("Destination should be either UiObject or UiObject2");
        return false;
    }
}
