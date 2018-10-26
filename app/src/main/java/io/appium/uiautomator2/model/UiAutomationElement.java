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
    private final static String ROOT_NODE_NAME = "hierarchy";

    private final static Map<AccessibilityNodeInfo, UiAutomationElement> cache = new WeakHashMap<>();
    private final Map<Attribute, Object> attributes;
    private final List<UiAutomationElement> children;

    /**
     * A snapshot of all attributes is taken at construction. The attributes of a
     * {@code UiAutomationElement} instance are immutable. If the underlying
     * {@link AccessibilityNodeInfo} is updated, a new {@code UiAutomationElement}
     * instance will be created in
     */
    protected UiAutomationElement(AccessibilityNodeInfo node, int index) {
        super(checkNotNull(node));

        Map<Attribute, Object> attributes = new EnumMap<>(Attribute.class);
        put(attributes, Attribute.INDEX, index);
        put(attributes, Attribute.PACKAGE, charSequenceToString(node.getPackageName()));
        put(attributes, Attribute.CLASS, charSequenceToString(node.getClassName()));
        put(attributes, Attribute.TEXT, ElementHelpers.getText(node));
        put(attributes, Attribute.CONTENT_DESC, charSequenceToString(node.getContentDescription()));
        put(attributes, Attribute.RESOURCE_ID, charSequenceToString(node.getViewIdResourceName()));
        put(attributes, Attribute.CHECKABLE, node.isCheckable());
        put(attributes, Attribute.CHECKED, node.isChecked());
        put(attributes, Attribute.CLICKABLE, node.isClickable());
        put(attributes, Attribute.ENABLED, node.isEnabled());
        put(attributes, Attribute.FOCUSABLE, node.isFocusable());
        put(attributes, Attribute.FOCUSED, node.isFocused());
        put(attributes, Attribute.LONG_CLICKABLE, node.isLongClickable());
        put(attributes, Attribute.PASSWORD, node.isPassword());
        put(attributes, Attribute.SCROLLABLE, node.isScrollable());
        Range<Integer> selectionRange = ElementHelpers.getSelectionRange(node);
        if (selectionRange != null) {
            attributes.put(Attribute.SELECTION_START, selectionRange.getLower());
            attributes.put(Attribute.SELECTION_END, selectionRange.getUpper());
        }
        put(attributes, Attribute.SELECTED, node.isSelected());
        put(attributes, Attribute.BOUNDS, getBounds(node));
        // Skip CONTENT_SIZE as it is quite expensive to compute it for each element
        this.attributes = Collections.unmodifiableMap(attributes);
        this.children = buildChildren(node);
    }

    protected UiAutomationElement(String hierarchyClassName, AccessibilityNodeInfo childNode, int index) {
        super(null);
        Map<Attribute, Object> attribs = new EnumMap<>(Attribute.class);
        put(attribs, Attribute.INDEX, index);
        put(attribs, Attribute.CLASS, charSequenceToString(hierarchyClassName));
        this.attributes = Collections.unmodifiableMap(attribs);
        List<UiAutomationElement> mutableChildren = new ArrayList<>();
        mutableChildren.add(new UiAutomationElement(childNode, 0));
        this.children = mutableChildren;
    }

    public static UiAutomationElement rebuildForNewRoot(AccessibilityNodeInfo rawElement,
                                                        @Nullable List<CharSequence> toastMSGs) {
        cache.clear();

        UiAutomationElement rootElement = new UiAutomationElement(ROOT_NODE_NAME, rawElement, 0);
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

    private static UiAutomationElement getOrCreateElement(AccessibilityNodeInfo rawElement, int index) {
        UiAutomationElement element = cache.get(rawElement);
        if (element == null) {
            element = new UiAutomationElement(rawElement, index);
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

        this.children.add(new UiAutomationElement(node, 0));
    }

    private List<UiAutomationElement> buildChildren(AccessibilityNodeInfo node) {
        int childCount = node.getChildCount();
        if (childCount == 0) {
            return Collections.emptyList();
        }

        List<UiAutomationElement> children = new ArrayList<>(childCount);
        Object allowInvisibleElements = Session.capabilities.get(ALLOW_INVISIBLE_ELEMENTS.toString());
        boolean isAllowInvisibleElements = allowInvisibleElements != null && (boolean) allowInvisibleElements;
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            //Ignore if element is not visible on the screen
            if (child != null && (child.isVisibleToUser() || isAllowInvisibleElements)) {
                children.add(getOrCreateElement(child, i));
            }
        }
        return children;
    }

    private String getBounds(AccessibilityNodeInfo node) {
        Rect rect = new Rect();
        node.getBoundsInScreen(rect);
        return rect.toShortString();
    }

    @Override
    public List<UiAutomationElement> getChildren() {
        return children;
    }

    @Override
    protected Map<Attribute, Object> getAttributes() {
        return attributes;
    }
}
