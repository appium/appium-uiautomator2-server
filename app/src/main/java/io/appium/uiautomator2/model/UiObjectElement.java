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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.model.AccessibleUiObject.toAccessibleUiObject;
import static io.appium.uiautomator2.model.AccessibleUiObject.toAccessibleUiObjects;
import static io.appium.uiautomator2.utils.ElementHelpers.generateNoAttributeException;

public class UiObjectElement extends BaseElement {
    private static final Pattern endsWithInstancePattern = Pattern.compile(".*INSTANCE=\\d+]$");

    private final UiObject element;

    public UiObjectElement(UiObject element, boolean isSingleMatch, By by, @Nullable String contextId) {
        super(isSingleMatch, by, contextId);
        this.element = element;
    }

    @Override
    UiObjectElement withId(String id) {
        return (UiObjectElement) super.withId(id);
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
                result = toAxNodeInfo(element).getViewIdResourceName();
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
                result = element.exists() && AxNodeInfoHelper.isVisible(toAxNodeInfo(element));
                break;
            case PASSWORD:
                result = AxNodeInfoHelper.isPassword(toAxNodeInfo(element));
                break;
            case BOUNDS:
                result = getBounds().toShortString();
                break;
            case PACKAGE: {
                result = AxNodeInfoHelper.getPackageName(toAxNodeInfo(element));
                break;
            }
            case SELECTION_END:
            case SELECTION_START:
                Pair<Integer, Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(toAxNodeInfo(element));
                result = selectionRange == null ? null
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
    public void clear() throws UiObjectNotFoundException {
        element.setText("");
    }

    @Override
    public String getContentDesc() throws UiObjectNotFoundException {
        return element.getContentDescription();
    }

    @Override
    public UiObject getUiObject() {
        return element;
    }

    @Nullable
    @Override
    public AccessibleUiObject getChild(final Object selector) throws UiObjectNotFoundException {
        if (selector instanceof BySelector) {
            /*
             * We can't find the child element with BySelector on UiObject,
             * as an alternative creating UiObject2 with UiObject's AccessibilityNodeInfo
             * and finding the child element on UiObject2.
             */
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            AccessibleUiObject root = CustomUiDevice.getInstance().findObject(nodeInfo);
            if (root != null && root.getValue() instanceof UiObject2) {
                UiObject2 child = ((UiObject2) root.getValue()).findObject((BySelector) selector);
                return toAccessibleUiObject(child);
            }
            return null;
        }
        UiObject child = element.getChild((UiSelector) selector);
        return toAccessibleUiObject(child);
    }

    @Override
    public List<AccessibleUiObject> getChildren(final Object selector, final By by) {
        if (selector instanceof BySelector) {
            /*
             * We can't find the child elements with BySelector on UiObject,
             * as an alternative creating UiObject2 with UiObject's AccessibilityNodeInfo
             * and finding the child elements on UiObject2.
             */
            AccessibilityNodeInfo nodeInfo = toAxNodeInfo(element);
            AccessibleUiObject root = CustomUiDevice.getInstance().findObject(nodeInfo);
            if (root == null || !(root.getValue() instanceof UiObject2)) {
                throw new ElementNotFoundException();
            }
            List<UiObject2> children = ((UiObject2) root.getValue()).findObjects((BySelector) selector);
            return toAccessibleUiObjects(children);
        }
        return this.getChildElements((UiSelector) selector);
    }

    private ArrayList<AccessibleUiObject> getChildElements(final UiSelector sel) {
        final String selectorString = sel.toString();
        Logger.debug("getElements selector:" + selectorString);
        final ArrayList<AccessibleUiObject> elements = new ArrayList<>();
        // If sel is UiSelector[CLASS=android.widget.Button, INSTANCE=0]
        // then invoking instance with a non-0 argument will corrupt the selector.
        //
        // sel.instance(1) will transform the selector into:
        // UiSelector[CLASS=android.widget.Button, INSTANCE=1]
        //
        // The selector now points to an entirely different element.
        if (endsWithInstancePattern.matcher(selectorString).matches()) {
            Logger.debug("Selector ends with instance.");
            // There's exactly one element when using instance.
            AccessibleUiObject instanceObj = toAccessibleUiObject(Device.getUiDevice().findObject(sel));
            if (instanceObj != null) {
                elements.add(instanceObj);
            }
            return elements;
        }

        final boolean useIndex = selectorString.contains("CLASS_REGEX=");
        UiSelector tmp;
        int counter = 0;
        do {
            AccessibleUiObject lastFoundObj;
            if (element == null) {
                Logger.debug("Element] is null: (" + counter + ")");

                if (useIndex) {
                    Logger.debug("  using index...");
                    tmp = sel.index(counter);
                } else {
                    tmp = sel.instance(counter);
                }

                Logger.debug("getElements tmp selector:" + tmp.toString());
                lastFoundObj = toAccessibleUiObject(Device.getUiDevice().findObject(tmp));
            } else {
                Logger.debug("Element is " + getId() + ", counter: " + counter);
                try {
                    lastFoundObj = toAccessibleUiObject(element.getChild(sel.instance(counter)));
                } catch (UiObjectNotFoundException e) {
                    lastFoundObj = null;
                }
            }

            if (lastFoundObj == null) {
                return elements;
            }
            counter++;
            elements.add(lastFoundObj);
        } while (true);
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
