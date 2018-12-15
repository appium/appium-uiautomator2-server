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

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.appium.uiautomator2.utils.Attribute;

/**
 * UiElement that implements the common operations.
 *
 * @param <R> the type of the raw element this class wraps, for example, View or
 *            AccessibilityNodeInfo
 * @param <E> the type of the concrete subclass of UiElement
 */
public abstract class UiElement<R, E extends UiElement<R, E>> {
    // These two attribute names are used for debugging only.
    // The two constants are used internally and must match to-uiautomator.xsl.
    public static final String ATTRIB_VISIBLE_BOUNDS = "VisibleBounds";
    public static final String ATTRIB_NOT_VISIBLE = "NotVisible";
    private final AccessibilityNodeInfo node;

    public UiElement(AccessibilityNodeInfo node) {
        this.node = node;
    }

    @Nullable
    public AccessibilityNodeInfo getNode() {
        return this.node;
    }

    public Set<Attribute> attributeKeys() {
        return getAttributes().keySet();
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T get(Attribute attribute) {
        if (!getAttributes().containsKey(attribute)) {
            return null;
        }
        return (T) getAttributes().get(attribute);
    }

    @SuppressWarnings("unchecked")
    private  <T> T get(Attribute attribute, T defaultValue) {
        if (!getAttributes().containsKey(attribute)) {
            return defaultValue;
        }
        return (T) getAttributes().get(attribute);
    }

    @NonNull
    public String getText() {
        //noinspection ConstantConditions
        return get(Attribute.TEXT);
    }

    @Nullable
    public String getOriginalText() {
        return get(Attribute.ORIGINAL_TEXT);
    }

    @Nullable
    public String getContentDescription() {
        return get(Attribute.CONTENT_DESC);
    }

    @Nullable
    public String getClassName() {
        return get(Attribute.CLASS);
    }

    @Nullable
    public String getResourceId() {
        return get(Attribute.RESOURCE_ID);
    }

    @Nullable
    public String getPackageName() {
        return get(Attribute.PACKAGE);
    }

    public boolean isCheckable() {
        return get(Attribute.CHECKABLE, false);
    }

    public boolean isChecked() {
        return get(Attribute.CHECKED, false);
    }

    public boolean isClickable() {
        return get(Attribute.CLICKABLE, false);
    }

    public boolean isEnabled() {
        return get(Attribute.ENABLED, false);
    }

    public boolean isFocusable() {
        return get(Attribute.FOCUSABLE, false);
    }

    public boolean isFocused() {
        return get(Attribute.FOCUSED, false);
    }

    public boolean isScrollable() {
        return get(Attribute.SCROLLABLE, false);
    }

    public boolean isLongClickable() {
        return get(Attribute.LONG_CLICKABLE, false);
    }

    public boolean isPassword() {
        return get(Attribute.PASSWORD, false);
    }

    public boolean isSelected() {
        return  get(Attribute.SELECTED, false);
    }

    public int getIndex() {
        return get(Attribute.INDEX, -1);
    }

    public abstract List<E> getChildren();

    public String getBounds() {
        return get(Attribute.BOUNDS);
    }

    protected abstract Map<Attribute, Object> getAttributes();
}
