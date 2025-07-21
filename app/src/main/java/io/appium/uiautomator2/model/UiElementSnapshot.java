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

import android.os.Build;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.Pair;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import io.appium.uiautomator2.core.AxNodeInfoHelper;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.IncludeA11yActionsInPageSource;
import io.appium.uiautomator2.model.settings.IncludeExtrasInPageSource;
import io.appium.uiautomator2.model.settings.SnapshotMaxDepth;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ReflectionUtils.setField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;

/**
 * A UiElement that gets attributes via the Accessibility API.
 * https://android.googlesource.com/platform/frameworks/testing/+/476328047e3f82d6d9be8ab23f502a670613f94c/uiautomator/library/src/com/android/uiautomator/core/AccessibilityNodeInfoDumper.java
 */
public class UiElementSnapshot extends UiElement<AccessibilityNodeInfo, UiElementSnapshot> {
    private final static String ROOT_NODE_NAME = "hierarchy";
    // The same order will be used for node attributes in xml page source
    public final static Attribute[] SUPPORTED_ATTRIBUTES = new Attribute[]{
            Attribute.INDEX, Attribute.PACKAGE, Attribute.CLASS, Attribute.TEXT,
            Attribute.ORIGINAL_TEXT, Attribute.CONTENT_DESC, Attribute.RESOURCE_ID,
            Attribute.CHECKABLE, Attribute.CHECKED, Attribute.CLICKABLE, Attribute.ENABLED,
            Attribute.FOCUSABLE, Attribute.FOCUSED, Attribute.LONG_CLICKABLE,
            Attribute.PASSWORD, Attribute.SCROLLABLE, Attribute.SELECTION_START,
            Attribute.SELECTION_END, Attribute.SELECTED, Attribute.BOUNDS, Attribute.DISPLAYED,
            Attribute.HINT, Attribute.EXTRAS, Attribute.IMPORTANT_FOR_ACCESSIBILITY,
            Attribute.SCREEN_READER_FOCUSABLE, Attribute.INPUT_TYPE, Attribute.DRAWING_ORDER,
            Attribute.SHOWING_HINT_TEXT, Attribute.TEXT_ENTRY_KEY, Attribute.MULTI_LINE,
            Attribute.DISMISSABLE, Attribute.ACCESSIBILITY_FOCUSED, Attribute.HEADING,
            Attribute.LIVE_REGION, Attribute.CONTEXT_CLICKABLE, Attribute.MAX_TEXT_LENGTH,
            Attribute.CONTENT_INVALID, Attribute.ERROR_TEXT, Attribute.PANE_TITLE,
            Attribute.TOOLTIP_TEXT, Attribute.TEXT_HAS_CLICKABLE_SPAN, Attribute.ACTIONS
            // Skip CONTENT_SIZE as it is quite expensive to compute it for each element
    };
    private final static Attribute[] TOAST_NODE_ATTRIBUTES = new Attribute[]{
            Attribute.TEXT, Attribute.CLASS, Attribute.PACKAGE, Attribute.DISPLAYED,
            Attribute.INDEX
    };

    private final Set<Attribute> includedAttributes = new HashSet<>();
    private final Map<Attribute, Object> attributes;
    private final List<UiElementSnapshot> children;
    private final int depth;
    private final int maxDepth;
    private final int index;

    private UiElementSnapshot(AccessibilityNodeInfo node, int index, int depth, int maxDepth,
                              Set<Attribute> includedAttributes) {
        super(Objects.requireNonNull(node));
        this.depth = depth;
        this.maxDepth = maxDepth;
        this.index = index;
        // Class name attribute should always be there
        this.includedAttributes.add(Attribute.CLASS);
        this.includedAttributes.addAll(includedAttributes);
        this.attributes = collectAttributes();
        this.children = buildChildren(node);
    }

    private UiElementSnapshot(AccessibilityNodeInfo node, int index, int depth,
                              Set<Attribute> includedAttributes) {
        this(node, index, depth, Settings.get(SnapshotMaxDepth.class).getValue(), includedAttributes);
    }

    private UiElementSnapshot(AccessibilityNodeInfo[] childNodes,
                              Set<Attribute> includedAttributes) {
        super(null);
        this.depth = 0;
        this.index = 0;
        this.maxDepth = Settings.get(SnapshotMaxDepth.class).getValue();
        Map<Attribute, Object> attribs = new LinkedHashMap<>();
        putAttribute(attribs, Attribute.INDEX, this.index);
        putAttribute(attribs, Attribute.CLASS, ROOT_NODE_NAME);
        this.attributes = Collections.unmodifiableMap(attribs);
        List<UiElementSnapshot> children = new ArrayList<>(childNodes.length);
        for (int childNodeIdx = 0; childNodeIdx < childNodes.length; ++childNodeIdx) {
            UiElementSnapshot child = new UiElementSnapshot(childNodes[childNodeIdx], childNodeIdx,
                    this.depth + 1, includedAttributes);
            children.add(child);
        }
        this.children = children;
    }

    private static void putAttribute(Map<Attribute, Object> attribs, Attribute key, Object value) {
        if (value != null) {
            attribs.put(key, value);
        }
    }

    private @Nullable Object getNodeAttributeValue(Attribute attr) {
        AccessibilityNodeInfo node = Objects.requireNonNull(getNode());
        switch (attr) {
            case CHECKABLE:
                return node.isCheckable();
            case CHECKED:
                return node.isChecked();
            case CLASS:
                return charSequenceToNullableString(node.getClassName());
            case CLICKABLE:
                return node.isClickable();
            case CONTENT_DESC:
                return charSequenceToNullableString(node.getContentDescription());
            case ENABLED:
                return node.isEnabled();
            case FOCUSABLE:
                return node.isFocusable();
            case FOCUSED:
                return node.isFocused();
            case LONG_CLICKABLE:
                return node.isLongClickable();
            case PACKAGE:
                return charSequenceToNullableString(node.getPackageName());
            case PASSWORD:
                return node.isPassword();
            case RESOURCE_ID:
                return node.getViewIdResourceName();
            case SCROLLABLE:
                return node.isScrollable();
            case SELECTION_START: {
                Pair<Integer, Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(node);
                return selectionRange == null ? null : selectionRange.first;
            }
            case SELECTION_END: {
                Pair<Integer, Integer> selectionRange = AxNodeInfoHelper.getSelectionRange(node);
                return selectionRange == null ? null : selectionRange.second;
            }
            case SELECTED:
                return node.isSelected();
            case TEXT:
                return AxNodeInfoHelper.getText(node, true);
            case HINT:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? node.getHintText() : null;
            case IMPORTANT_FOR_ACCESSIBILITY:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
                        ? node.isImportantForAccessibility()
                        : null;
            case SCREEN_READER_FOCUSABLE:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                        ? node.isScreenReaderFocusable()
                        : null;
            case INPUT_TYPE:
                return node.getInputType() != 0 ? node.getInputType() : null;
            case DRAWING_ORDER:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ? node.getDrawingOrder() : null;
            case SHOWING_HINT_TEXT:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? node.isShowingHintText() : null;
            case ACTIONS:
                return BaseElement.getA11yActionsAsString(node);
            case TEXT_ENTRY_KEY:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q ? node.isTextEntryKey() : null;
            case MULTI_LINE:
                return node.getMaxTextLength() != -1 ? node.isMultiLine() : null;
            case DISMISSABLE:
                return node.isDismissable();
            case ACCESSIBILITY_FOCUSED:
                return node.isAccessibilityFocused();
            case HEADING:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ? node.isHeading() : null;
            case LIVE_REGION:
                return node.getLiveRegion();
            case CONTEXT_CLICKABLE:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        ? node.isContextClickable()
                        : null;
            case MAX_TEXT_LENGTH:
                return node.getMaxTextLength() != -1 ? node.getMaxTextLength() : null;
            case CONTENT_INVALID:
                return node.isContentInvalid();
            case ERROR_TEXT:
                return charSequenceToNullableString(node.getError());
            case PANE_TITLE:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                        ? charSequenceToNullableString(node.getPaneTitle())
                        : null;
            case TOOLTIP_TEXT:
                return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                        ? charSequenceToNullableString(node.getTooltipText())
                        : null;
            case TEXT_HAS_CLICKABLE_SPAN:
                return isTextView(node) && textHasClickableSpan(node) ? true : null;
            case EXTRAS:
                return BaseElement.getExtrasAsString(node);
            case ORIGINAL_TEXT:
                return AxNodeInfoHelper.getText(node, false);
            case BOUNDS:
                return AxNodeInfoHelper.getBounds(node).toShortString();
            case INDEX:
                return index;
            case DISPLAYED:
                return node.isVisibleToUser();
            default:
                return null;
        }
    }

    private Map<Attribute, Object> collectAttributes() {
        Map<Attribute, Object> result = new LinkedHashMap<>();
        for (Attribute attr : SUPPORTED_ATTRIBUTES) {
            if (attr.equals(Attribute.EXTRAS) &&
                    !Settings.get(IncludeExtrasInPageSource.class).getValue()) {
                continue;
            }
            if (attr.equals(Attribute.ACTIONS) &&
                    !Settings.get(IncludeA11yActionsInPageSource.class).getValue()) {
                continue;
            }
            if (includedAttributes.isEmpty() || includedAttributes.contains(attr)) {
                putAttribute(result, attr, getNodeAttributeValue(attr));
            }
        }
        return Collections.unmodifiableMap(result);
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo[] roots, List<CharSequence> toastMSGs,
                                         Set<Attribute> includedAttributes) {
        UiElementSnapshot uiRoot = new UiElementSnapshot(roots, includedAttributes);
        for (CharSequence toastMSG : toastMSGs) {
            Logger.info(String.format("Adding toast message to root: %s", toastMSG));
            uiRoot.addToastMsg(toastMSG);
        }
        return uiRoot;
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo rootElement, Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, AxNodeInfoHelper.calculateIndex(rootElement), 0,
                includedAttributes);
    }

    public static UiElementSnapshot take(AccessibilityNodeInfo rootElement, int maxDepth,
                                         Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, AxNodeInfoHelper.calculateIndex(rootElement), 0,
                maxDepth, includedAttributes);
    }

    private static UiElementSnapshot take(AccessibilityNodeInfo rootElement, int index, int depth,
                                          Set<Attribute> includedAttributes) {
        return new UiElementSnapshot(rootElement, index, depth, includedAttributes);
    }

    private void addToastMsg(CharSequence tokenMSG) {
        AccessibilityNodeInfo node = AccessibilityNodeInfo.obtain();
        node.setText(tokenMSG);
        node.setClassName(Toast.class.getName());
        node.setPackageName("com.android.settings");
        node.setVisibleToUser(true);
        setField("mSealed", true, node);
        this.children.add(new UiElementSnapshot(node, this.children.size(), 0,
                new HashSet<>(Arrays.asList(TOAST_NODE_ATTRIBUTES))));
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
        boolean areInvisibleElementsAllowed = Settings.get(AllowInvisibleElements.class).getValue();
        List<Integer> nullNodeIndexes = new ArrayList<>();
        for (int index = 0; index < childCount; ++index) {
            AccessibilityNodeInfo child = node.getChild(index);
            if (child == null) {
                nullNodeIndexes.add(index);
                continue;
            }

            // Ignore if the element is not visible on the screen
            if (areInvisibleElementsAllowed || child.isVisibleToUser()) {
                children.add(take(child, index, depth + 1, includedAttributes));
            }
        }
        if (!nullNodeIndexes.isEmpty()) {
            Logger.info(String.format(
                    "The following child nodes of %s are nulls: %s", node, nullNodeIndexes
            ));
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

    private static boolean isTextView(AccessibilityNodeInfo node) {
        CharSequence className = node.getClassName();
        return className != null && "android.widget.TextView".equals(className.toString());
    }

    private static boolean textHasClickableSpan(AccessibilityNodeInfo node) {
        CharSequence text = node.getText();
        if (!(text instanceof Spanned)) {
            return false;
        }
        Spanned spanned = (Spanned) text;
        return hasSpansOfType(spanned, ClickableSpan.class) ||
                hasSpansOfType(spanned, URLSpan.class);
    }

    private static <T> boolean hasSpansOfType(Spanned spanned, Class<T> type) {
        return spanned.getSpans(0, spanned.length(), type).length > 0;
    }
}
