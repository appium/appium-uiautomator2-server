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
import android.util.Range;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.core.AccessibilityNodeInfoHelpers;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.internal.util.Checks.checkNotNull;
import static io.appium.uiautomator2.model.settings.Settings.ALLOW_INVISIBLE_ELEMENTS;
import static io.appium.uiautomator2.utils.ReflectionUtils.setField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;

/**
 * A UiElement that gets attributes via the Accessibility API.
 */
@TargetApi(18)
public class UiAutomationElement extends UiElement<AccessibilityNodeInfo, UiAutomationElement> {
    private final static String ROOT_NODE_NAME = "hierarchy";
    // https://github.com/appium/appium/issues/12545
    private final static int MAX_DEPTH = 70;

    private final static Map<AccessibilityNodeInfo, UiAutomationElement> cache = new WeakHashMap<>();
    private final Map<Attribute, Object> attributes;
    private final List<UiAutomationElement> children;
    private int depth = 0;

    /**
     * A snapshot of all attributes is taken at construction. The attributes of a
     * {@code UiAutomationElement} instance are immutable. If the underlying
     * {@link AccessibilityNodeInfo} is updated, a new {@code UiAutomationElement}
     * instance will be created in
     */
    private UiAutomationElement(AccessibilityNodeInfo node, int index) {
        super(checkNotNull(node));

        Map<Attribute, Object> attributes = new LinkedHashMap<>();
        // The same sequence will be used for node attributes in xml page source
        put(attributes, Attribute.INDEX, index);
        put(attributes, Attribute.PACKAGE, charSequenceToNullableString(node.getPackageName()));
        put(attributes, Attribute.CLASS, charSequenceToNullableString(node.getClassName()));
        put(attributes, Attribute.TEXT, AccessibilityNodeInfoHelpers.getText(node, true));
        put(attributes, Attribute.ORIGINAL_TEXT, AccessibilityNodeInfoHelpers.getText(node, false));
        put(attributes, Attribute.CONTENT_DESC, charSequenceToNullableString(node.getContentDescription()));
        put(attributes, Attribute.RESOURCE_ID, node.getViewIdResourceName());
        put(attributes, Attribute.CHECKABLE, node.isCheckable());
        put(attributes, Attribute.CHECKED, node.isChecked());
        put(attributes, Attribute.CLICKABLE, node.isClickable());
        put(attributes, Attribute.ENABLED, node.isEnabled());
        put(attributes, Attribute.FOCUSABLE, node.isFocusable());
        put(attributes, Attribute.FOCUSED, node.isFocused());
        put(attributes, Attribute.LONG_CLICKABLE, node.isLongClickable());
        put(attributes, Attribute.PASSWORD, node.isPassword());
        put(attributes, Attribute.SCROLLABLE, node.isScrollable());
        Range<Integer> selectionRange = AccessibilityNodeInfoHelpers.getSelectionRange(node);
        if (selectionRange != null) {
            attributes.put(Attribute.SELECTION_START, selectionRange.getLower());
            attributes.put(Attribute.SELECTION_END, selectionRange.getUpper());
        }
        put(attributes, Attribute.SELECTED, node.isSelected());
        put(attributes, Attribute.BOUNDS, AccessibilityNodeInfoHelpers.getVisibleBounds(node).toShortString());
        put(attributes, Attribute.DISPLAYED, node.isVisibleToUser());
        // Skip CONTENT_SIZE as it is quite expensive to compute it for each element
        this.attributes = Collections.unmodifiableMap(attributes);
        this.children = buildChildren(node);
    }

    private UiAutomationElement(String hierarchyClassName, AccessibilityNodeInfo[] childNodes, int index) {
        super(null);
        Map<Attribute, Object> attribs = new LinkedHashMap<>();
        put(attribs, Attribute.INDEX, index);
        put(attribs, Attribute.CLASS, hierarchyClassName);
        this.attributes = Collections.unmodifiableMap(attribs);
        List<UiAutomationElement> children = new ArrayList<>();
        for (AccessibilityNodeInfo childNode : childNodes) {
            children.add(new UiAutomationElement(childNode, children.size()));
        }
        this.children = children;
    }

    private int getDepth() {
        return this.depth;
    }

    private void setDepth(int depth) {
        this.depth = depth;
    }

    public static UiAutomationElement rebuildForNewRoots(AccessibilityNodeInfo[] roots) {
        return rebuildForNewRoots(roots, Collections.<CharSequence>emptyList());
    }

    public static UiAutomationElement rebuildForNewRoots(AccessibilityNodeInfo[] roots, List<CharSequence> toastMSGs) {
        cache.clear();
        UiAutomationElement root = new UiAutomationElement(ROOT_NODE_NAME, roots, 0);
        for (CharSequence toastMSG : toastMSGs) {
            Logger.debug(String.format("Adding toast message to root: %s", toastMSG));
            root.addToastMsgToRoot(toastMSG);
        }
        return root;
    }

    @Nullable
    public static UiAutomationElement getCachedElement(AccessibilityNodeInfo rawElement, AccessibilityNodeInfo[] windowRoots) {
        if (cache.get(rawElement) == null) {
            rebuildForNewRoots(windowRoots);
        }
        return cache.get(rawElement);
    }

    private static UiAutomationElement getOrCreateElement(AccessibilityNodeInfo rawElement, int index, int depth) {
        UiAutomationElement element = cache.get(rawElement);
        if (element == null) {
            element = new UiAutomationElement(rawElement, index);
            element.setDepth(depth);
            cache.put(rawElement, element);
        }
        return element;
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
        setField("mSealed", true, node);

        this.children.add(new UiAutomationElement(node, this.children.size()));
    }

    private List<UiAutomationElement> buildChildren(AccessibilityNodeInfo node) {
        final int childCount = node.getChildCount();
        if (childCount == 0 || getDepth() >= MAX_DEPTH) {
            if (getDepth() >= MAX_DEPTH) {
                Logger.warn(String.format("Skipping building children of '%s' because the maximum " +
                        "recursion depth (%s) has been reached", node, MAX_DEPTH));
            }
            return Collections.emptyList();
        }

        List<UiAutomationElement> children = new ArrayList<>(childCount);
        boolean areInvisibleElementsAllowed = AppiumUIA2Driver
                .getInstance()
                .getSessionOrThrow()
                .getCapability(ALLOW_INVISIBLE_ELEMENTS.toString(), false);
        for (int i = 0; i < childCount; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            //Ignore if element is not visible on the screen
            if (child != null && (child.isVisibleToUser() || areInvisibleElementsAllowed)) {
                children.add(getOrCreateElement(child, i, getDepth() + 1));
            }
        }
        return children;
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
