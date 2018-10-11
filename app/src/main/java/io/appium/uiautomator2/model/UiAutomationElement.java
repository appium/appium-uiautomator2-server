/*
 * Copyright (C) 2013 DroidDriver committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.model;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.Range;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;

import static android.support.test.internal.util.Checks.checkNotNull;
import static io.appium.uiautomator2.model.settings.Settings.ALLOW_INVISIBLE_ELEMENTS;

/**
 * A UiElement that gets attributes via the Accessibility API.
 */
@TargetApi(18)
public class UiAutomationElement extends UiElement<AccessibilityNodeInfo, UiAutomationElement> {
    private final static Map<AccessibilityNodeInfo, UiAutomationElement> cache = new WeakHashMap<>();
    private final Map<Attribute, Object> attributes;
    private final boolean visible;
    @SuppressWarnings("unused")
    private final Rect visibleBounds;
    private final UiAutomationElement parent;
    private final List<UiAutomationElement> children;

    /**
     * A snapshot of all attributes is taken at construction. The attributes of a
     * {@code UiAutomationElement} instance are immutable. If the underlying
     * {@link AccessibilityNodeInfo} is updated, a new {@code UiAutomationElement}
     * instance will be created in
     */
    protected UiAutomationElement(AccessibilityNodeInfo node,
                                  UiAutomationElement parent, int index) {
        this.node = checkNotNull(node);
        this.parent = parent;

        Map<Attribute, Object> attribs = new EnumMap<>(Attribute.class);

        put(attribs, Attribute.INDEX, index);
        put(attribs, Attribute.PACKAGE, charSequenceToString(node.getPackageName()));
        put(attribs, Attribute.CLASS, charSequenceToString(node.getClassName()));
        put(attribs, Attribute.TEXT, charSequenceToString(node.getText()));
        put(attribs, Attribute.CONTENT_DESC, charSequenceToString(node.getContentDescription()));
        put(attribs, Attribute.RESOURCE_ID, charSequenceToString(node.getViewIdResourceName()));
        put(attribs, Attribute.CHECKABLE, node.isCheckable());
        put(attribs, Attribute.CHECKED, node.isChecked());
        put(attribs, Attribute.CLICKABLE, node.isClickable());
        put(attribs, Attribute.ENABLED, node.isEnabled());
        put(attribs, Attribute.FOCUSABLE, node.isFocusable());
        put(attribs, Attribute.FOCUSED, node.isFocused());
        put(attribs, Attribute.LONG_CLICKABLE, node.isLongClickable());
        put(attribs, Attribute.PASSWORD, node.isPassword());
        put(attribs, Attribute.SCROLLABLE, node.isScrollable());
        Range<Integer> selectionRange = ElementHelpers.getSelectionRange(node);
        if (selectionRange != null) {
            attribs.put(Attribute.SELECTION_START, selectionRange.getLower());
            attribs.put(Attribute.SELECTION_END, selectionRange.getUpper());
        }
        put(attribs, Attribute.SELECTED, node.isSelected());
        put(attribs, Attribute.BOUNDS, getBounds(node));
        attributes = Collections.unmodifiableMap(attribs);

        // Order matters as getVisibleBounds depends on visible
        visible = node.isVisibleToUser();
        visibleBounds = getVisibleBounds();
        List<UiAutomationElement> mutableChildren = buildChildren(node);
        this.children = mutableChildren == null ? null : Collections.unmodifiableList(mutableChildren);
    }

    protected UiAutomationElement(String hierarchyClassName,
                                  AccessibilityNodeInfo childNode, int index) {
        this.parent = null;
        Map<Attribute, Object> attribs = new EnumMap<>(Attribute.class);

        put(attribs, Attribute.INDEX, index);
        put(attribs, Attribute.CLASS, charSequenceToString(hierarchyClassName));
        put(attribs, Attribute.CHECKABLE, false);
        put(attribs, Attribute.CHECKED, false);
        put(attribs, Attribute.CLICKABLE, false);
        put(attribs, Attribute.ENABLED, false);
        put(attribs, Attribute.FOCUSABLE, false);
        put(attribs, Attribute.FOCUSED, false);
        put(attribs, Attribute.LONG_CLICKABLE, false);
        put(attribs, Attribute.PASSWORD, false);
        put(attribs, Attribute.SCROLLABLE, false);
        put(attribs, Attribute.SELECTED, false);

        this.attributes = Collections.unmodifiableMap(attribs);
        this.visible = true;
        this.visibleBounds = null;
        List<UiAutomationElement> mutableChildren = new ArrayList<>();
        mutableChildren.add(new UiAutomationElement(childNode, this /* parent UiAutomationElement*/, 0/* index */));
        this.children = mutableChildren;
    }

    public static UiAutomationElement rebuildForNewRoot(AccessibilityNodeInfo rawElement,
                                                        @Nullable List<CharSequence> toastMSGs) {
        cache.clear();

        UiAutomationElement rootElement = new UiAutomationElement("hierarchy", rawElement, 0);
        if (toastMSGs != null && !toastMSGs.isEmpty()) {
            for (CharSequence toastMSG : toastMSGs) {
                Logger.debug("Adding toastMSG to root:" + toastMSG);
                rootElement.addToastMsgToRoot(toastMSG);
            }
        }
        return rootElement;
    }

    @Nullable
    public static UiAutomationElement getCachedElement(AccessibilityNodeInfo rawElement,
                                                       AccessibilityNodeInfo windowRoot) {
        if (cache.get(rawElement) == null) {
            rebuildForNewRoot(windowRoot, null);
        }
        return cache.get(rawElement);
    }

    private static UiAutomationElement getOrCreateElement(AccessibilityNodeInfo rawElement,
                                                          UiAutomationElement parent, int index) {
        UiAutomationElement element = cache.get(rawElement);
        if (element == null) {
            element = new UiAutomationElement(rawElement, parent, index);
            cache.put(rawElement, element);
        }
        return element;
    }

    @Nullable
    public static String charSequenceToString(CharSequence input) {
        return input == null ? null : input.toString();
    }

    private void put(Map<Attribute, Object> attribs, Attribute key, Object value) {
        if (value != null) {
            attribs.put(key, value);
        }
    }

    private void addToastMsgToRoot(CharSequence tokenMSG) {
        AccessibilityNodeInfo node = AccessibilityNodeInfo.obtain();
        node.setText(tokenMSG);
        node.setClassName(Toast.class.getName());
        node.setPackageName("com.android.settings");

        this.children.add(new UiAutomationElement(node, this, 0));
    }

    private List<UiAutomationElement> buildChildren(AccessibilityNodeInfo node) {
        List<UiAutomationElement> children;
        int childCount = node.getChildCount();
        if (childCount == 0) {
            children = null;
        } else {
            children = new ArrayList<>(childCount);
            Object allowInvisibleElements = Session.capabilities.get(ALLOW_INVISIBLE_ELEMENTS.toString());
            boolean isAllowInvisibleElements = allowInvisibleElements != null && (boolean) allowInvisibleElements;

            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo child = node.getChild(i);
                //Ignore if element is not visible on the screen
                if (child != null && (child.isVisibleToUser() || isAllowInvisibleElements)) {
                    children.add(getOrCreateElement(child, this, i));
                }
            }
        }
        return children;
    }

    private Rect getBounds(AccessibilityNodeInfo node) {
        Rect rect = new Rect();
        node.getBoundsInScreen(rect);
        return rect;
    }

    @SuppressLint("CheckResult")
    private Rect getVisibleBounds() {
        if (!visible) {
            return new Rect();
        }
        Rect visibleBounds = getBounds(this.node);
        UiAutomationElement parent = getParent();
        Rect parentBounds;
        while (parent != null && parent.node != null) {
            parentBounds = parent.getBounds(this.parent.node);
            visibleBounds.intersect(parentBounds);
            parent = parent.getParent();
        }
        return visibleBounds;
    }

    public UiAutomationElement getParent() {
        return parent;
    }

    @Override
    protected List<UiAutomationElement> getChildren() {
        if (children == null) {
            return Collections.emptyList();
        }
        return children;
    }

    @Override
    protected Map<Attribute, Object> getAttributes() {
        return attributes;
    }
}
