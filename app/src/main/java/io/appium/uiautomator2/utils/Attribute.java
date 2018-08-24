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

public enum Attribute {
    CHECKABLE("checkable"),
    CHECKED("checked"),
    CLASS("class"),
    CLICKABLE("clickable"),
    CONTENT_DESC("content-desc"),
    ENABLED("enabled"),
    FOCUSABLE("focusable"),
    FOCUSED("focused"),
    LONG_CLICKABLE("long-clickable"),
    PACKAGE("package"),
    PASSWORD("password"),
    RESOURCE_ID("resource-id"),
    SCROLLABLE("scrollable"),
    SELECTION_START("selection-start"),
    SELECTION_END("selection-end"),
    SELECTED("selected"),
    TEXT("text"),
    BOUNDS("bounds"),
    INDEX("index");

    private final String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
