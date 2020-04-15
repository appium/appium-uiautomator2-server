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

package io.appium.uiautomator2.utils;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import androidx.annotation.Nullable;

public enum Attribute {
    CHECKABLE(new String[]{"checkable"}),
    CHECKED(new String[]{"checked"}),
    CLASS(new String[]{"class", "className"}),
    CLICKABLE(new String[]{"clickable"}),
    CONTENT_DESC(new String[]{"content-desc", "contentDescription"}),
    ENABLED(new String[]{"enabled"}),
    FOCUSABLE(new String[]{"focusable"}),
    FOCUSED(new String[]{"focused"}),
    LONG_CLICKABLE(new String[]{"long-clickable", "longClickable"}),
    PACKAGE(new String[]{"package"}),
    PASSWORD(new String[]{"password"}),
    RESOURCE_ID(new String[]{"resource-id", "resourceId"}),
    SCROLLABLE(new String[]{"scrollable"}),
    SELECTION_START(new String[]{"selection-start"}),
    SELECTION_END(new String[]{"selection-end"}),
    SELECTED(new String[]{"selected"}),
    TEXT(new String[]{"text", "name"}),
    // The main difference of this attribute from the preceding one is that
    // it does not replace null values with empty strings
    ORIGINAL_TEXT(new String[]{"original-text"}, false, false),
    BOUNDS(new String[]{"bounds"}),
    INDEX(new String[]{"index"}, false, true),
    DISPLAYED(new String[]{"displayed"}),
    CONTENT_SIZE(new String[]{"contentSize"}, true, false);

    private final String[] aliases;
    // Defines if the attribute is visible to the user from getAttribute call
    private boolean isExposable = true;
    // Defines if the attribute is visible to the user in the xml tree/xpath search
    private boolean isExposableToXml = true;

    Attribute(String[] aliases) {
        this.aliases = aliases;
    }

    Attribute(String[] aliases, boolean isExposable, boolean isExposableToXml) {
        this(aliases);
        this.isExposable = isExposable;
        this.isExposableToXml = isExposableToXml;
    }

    public String getName() {
        return aliases[0];
    }

    public boolean isExposableToXml() {
        return isExposableToXml;
    }

    @Override
    public String toString() {
        return aliases[0];
    }

    public static String[] exposableAliases() {
        final List<String> result = new ArrayList<>();
        for (Attribute attribute : Attribute.values()) {
            if (!attribute.isExposable) {
                continue;
            }

            if (attribute.aliases.length > 1) {
                result.add(String.format("{%s}", TextUtils.join(",", attribute.aliases)));
            } else {
                result.add(attribute.aliases[0]);
            }
        }
        return result.toArray(new String[0]);
    }

    @Nullable
    public static Attribute fromString(String alias) {
        if (alias == null) {
            return null;
        }

        for (Attribute attribute : Attribute.values()) {
            for (String attrAlias : attribute.aliases) {
                if (attrAlias.equalsIgnoreCase(alias)) {
                    return attribute;
                }
            }
        }
        return null;
    }
}
