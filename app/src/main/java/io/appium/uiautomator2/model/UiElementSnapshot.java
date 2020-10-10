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

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.internal.util.Checks.checkNotNull;
import static io.appium.uiautomator2.model.settings.Settings.ALLOW_INVISIBLE_ELEMENTS;
import static io.appium.uiautomator2.utils.ReflectionUtils.setField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;

/**
 * A UiElement that gets attributes via the Accessibility API.
 * https://android.googlesource.com/platform/frameworks/testing/+/476328047e3f82d6d9be8ab23f502a670613f94c/uiautomator/library/src/com/android/uiautomator/core/AccessibilityNodeInfoDumper.java
 */
@TargetApi(18)
public class UiElementSnapshot extends UiElement<AccessibilityNodeInfo, UiElementSnapshot> {
    private final static String ROOT_NODE_NAME = "hierarchy";
    // https://github.com/appium/appium/issues/12545
    private final static int DEFAULT_MAX_DEPTH = 70;

    private final Set<Attribute> includedAttributes = new HashSet<>();
    private final Map<Attribute, Object> attributes;
    private final List<UiElementSnapshot> children;
    private final int depth;
    private final int maxDepth;

    private UiElementSnapshot(AccessibilityNodeInfo node, int index, int depth, int maxDepth,
                              @Nullable Set<Attribute> includedAttributes) {
        super(checkNotNull(node));
        this.depth = depth;
        this.maxDepth = maxDepth;
        if (includedAttributes != null) {
            // Class name attribute should always be there
            this.includedAttributes.add(Attribute.CLASS);
            this.includedAttributes.addAll(includedAttributes);
        }
        this.attributes = collectAttributes(node, index);
        this.children = buildChildren(node);
    }

    private UiElementSnapshot(AccessibilityNodeInfo node, int index, int depth,
                              @Nullable Set<Attribute> includedAttributes) {
        this(node, index, depth, DEFAULT_MAX_DEPTH, includedAttributes);
    }

    private UiElementSnapshot(String hierarchyClassName, AccessibilityNodeInfo[] childNodes, int index,
                              @Nullable Set<Attribute> includedAttributes) {
        super(null);
        this.depth = 0;
        this.maxDepth = DEFAULT_MAX_DEPTH;
        Map<Attribute, Object> attribs = new LinkedHashMap<>();
        setAttribute(attribs, Attribute.INDEX, index);
        setAttribute(attribs, Attribute.CLASS, hierarchyClassName);
        this.attributes = Collections.unmodifiableMap(attribs);
        List<UiElementSnapshot> children = new ArrayList<>(childNodes.length);
        for (int childNodeIdx = 0; childNodeIdx < childNodes.length; ++childNodeIdx) {
            UiElementSnapshot child = new UiElementSnapshot(childNodes[childNodeIdx],  childNodeIdx,
                    this.depth + 1, includedAttributes);
            children.add(child);
        }
        this.children = children;
    }

    private boolean shouldIncludeAttribute(Attribute key) {
        return includedAttributes.isEmpty() || includedAttributes.contains(key);
    }

    private void setAttribute(Map<Attribute, Object> attribs, Attribute key, Object value) {
        if (value != null) {
            attribs.put(key, value);
        }
    }

    private Map<Attribute, Object> collectAttributes(AccessibilityNodeInfo node, int index) {
        Map<Attribute, Object> result = new LinkedHashMap<>();
        // The same sequence will be used for node attributes in xml page source
        if (shouldIncludeAttribute(Attribute.INDEX)) {
            setAttribute(result, Attribute.INDEX, index);
        }
        if (shouldIncludeAttribute(Attribute.PACKAGE)) {
            setAttribute(result, Attribute.PACKAGE, charSequenceToNullableString(node.getPackageName()));
        }
        if (shouldIncludeAttribute(Attribute.CLASS)) {
            setAttribute(result, Attribute.CLASS, charSequenceToNullableString(node.getClassName()));
        }
        if (shouldIncludeAttribute(Attribute.TEXT)) {
            setAttribute(result, Attribute.TEXT, AxNodeInfoHelper.getText(node, true));
        }
        if (shouldIncludeAttribute(Attribute.ORIGINAL_TEXT)) {
            setAttribute(result, Attribute.ORIGINAL_TEXT, AxNodeInfoHelper.getText(node, false));
        }
        if (shouldIncludeAttribute(Attribute.CONTENT_DESC)) {
            setAttribute(result, Attribute.CONTENT_DESC,
                    charSequenceToNullableString(node.getContentDescription()));
        }
        if (shouldIncludeAttribute(Attribute.RESOURCE_ID)) {
            setAttribute(result, Attribute.RESOURCE_ID, node.getViewIdResourceName());
        }
        if (shouldIncludeAttribute(Attribute.CHECKABLE)) {
            setAttribute(result, Attribute.CHECKABLE, node.isCheckable());
        }
        if (shouldIncludeAttribute(Attribute.CHECKED)) {
            setAttribute(result, Attribute.CHECKED, node.isChecked());
        }
        if (shouldIncludeAttribute(Attribute.CLICKABLE)) {
            setAttribute(result, Attribute.CLICKABLE, node.isClickable());
        }
        if (shouldIncludeAttribute(Attribute.ENABLED)) {
            setAttribute(result, Attribute.ENABLED, node.isEnabled());
        }
        if (shouldIncludeAttribute(Attribute.FOCUSABLE)) {
            setAttribute(result, Attribute.FOCUSABLE, node.isFocusable());
        }
        if (shouldIncludeAttribute(Attribute.FOCUSED)) {
            setAttribute(result, Attribute.FOCUSED, node.isFocused());
        }
        if (shouldIncludeAttribute(Attribute.LONG_CLICKABLE)) {
            setAttribute(result, Attribute.LONG_CLICKABLE, node.isLongClickable());
        }
        if (shouldIncludeAttribute(Attribute.PASSWORD)) {
            setAttribute(result, Attribute.PASSWORD, node.isPassword());
        }
        if (shouldIncludeAttribute(Attribute.SCROLLABLE)) {
            setAttribute(result, Attribute.SCROLLABLE, node.isScrollable());
        }
        if (shouldIncludeAttribute(Attribute.SELECTION_START)
                || shouldIncludeAttribute(Attribute.SELECTION_END)) {
            Range<Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(node);
            if (selectionRange != null) {
                if (shouldIncludeAttribute(Attribute.SELECTION_START)) {
                    result.put(Attribute.SELECTION_START, selectionRange.getLower());
                }
                if (shouldIncludeAttribute(Attribute.SELECTION_END)) {
                    result.put(Attribute.SELECTION_END, selectionRange.getUpper());
                }
            }
        }
        if (shouldIncludeAttribute(Attribute.SELECTED)) {
            setAttribute(result, Attribute.SELECTED, node.isSelected());
        }
        if (shouldIncludeAttribute(Attribute.BOUNDS)) {
            setAttribute(result, Attribute.BOUNDS, AxNodeInfoHelper.getBounds(node).toShortString());
        }
        if (shouldIncludeAttribute(Attribute.DISPLAYED)) {
            setAttribute(result, Attribute.DISPLAYED, node.isVisibleToUser());
        }
        // Skip CONTENT_SIZE as it is quite expensive to compute it for each element
        return Collections.unmodifiableMap(result);
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo[] roots, List<CharSequence> toastMSGs,
                                         @Nullable Set<Attribute> includedAttributes) {
        UiElementSnapshot uiRoot = new UiElementSnapshot(ROOT_NODE_NAME, roots, 0, includedAttributes);
        for (CharSequence toastMSG : toastMSGs) {
            Logger.info(String.format("Adding toast message to root: %s", toastMSG));
            uiRoot.addToastMsg(toastMSG);
        }
        return uiRoot;
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo rootElement,
                                         @Nullable Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, AxNodeInfoHelper.calculateIndex(rootElement), 0,
                includedAttributes);
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo rootElement, int maxDepth,
                                         @Nullable Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, AxNodeInfoHelper.calculateIndex(rootElement), 0,
                maxDepth, includedAttributes);
    }

    private static UiElementSnapshot take(AccessibilityNodeInfo rootElement, int index, int depth,
                                          @Nullable Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, index, depth, includedAttributes);
    }

    private void addToastMsg(CharSequence tokenMSG) {
        AccessibilityNodeInfo node = AccessibilityNodeInfo.obtain();
        node.setText(tokenMSG);
        node.setClassName(Toast.class.getName());
        node.setPackageName("com.android.settings");
        node.setVisibleToUser(true);
        setField("mSealed", true, node);
        this.children.add(new UiElementSnapshot(node, this.children.size(), 0, null));
    }

    private List<UiElementSnapshot> buildChildren(AccessibilityNodeInfo node) {
        final int childCount = node.getChildCount();
        if (childCount == 0 || (maxDepth >= 0 && depth >= maxDepth)) {
            if (depth >= maxDepth) {
                Logger.info(String.format("Skipping building children of '%s' because the maximum " +
                        "recursion depth (%s) has been reached", node, maxDepth));
            }
            return Collections.emptyList();
        }

        List<UiElementSnapshot> children = new ArrayList<>(childCount);
        boolean areInvisibleElementsAllowed = AppiumUIA2Driver.getInstance().getSessionOrThrow()
                .getCapability(ALLOW_INVISIBLE_ELEMENTS.toString(), false);
        for (int index = 0; index < childCount; ++index) {
            AccessibilityNodeInfo child = node.getChild(index);
            if (child == null) {
                Logger.info(String.format("The child node #%s of %s is null", index, node));
                continue;
            }

            // Ignore if the element is not visible on the screen
            if (areInvisibleElementsAllowed || child.isVisibleToUser()) {
                children.add(take(child, index, depth + 1, includedAttributes));
            }
        }
        return children;
    }

    @Override
    public List<UiElementSnapshot> getChildren() {
        return Collections.unmodifiableList(children);
    }

    @Override
    protected Map<Attribute, Object> getAttributes() {
        return attributes;
    }
}
