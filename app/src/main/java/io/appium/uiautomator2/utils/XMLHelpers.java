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

package io.appium.uiautomator2.utils;

import android.support.annotation.Nullable;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;


public abstract class XMLHelpers {
    // XML 1.0 Legal Characters (http://stackoverflow.com/a/4237934/347155)
    // #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
    private final static Pattern XML10_PATTERN = Pattern.compile("[^" + "\u0009\r\n" +
            "\u0020-\uD7FF" + "\uE000-\uFFFD" + "\ud800\udc00-\udbff\udfff" + "]");
    public final static String DEFAULT_VIEW_CLASS_NAME = "android.view.View";

    public static String toNodeName(String className) {
        if (StringUtils.isBlank(className)) {
            return DEFAULT_VIEW_CLASS_NAME;
        }

        String fixedName = className
                .replaceAll("[$@#&]", ".")
                // https://github.com/appium/appium/issues/9934
                .replaceAll("[ˋˊ\\s]", ""); // "ˋ" is \xCB\x8B in UTF-8
        // https://github.com/appium/appium/issues/9934
        //noinspection ConstantConditions
        fixedName = toSafeXmlString(fixedName, "?")
                .replaceAll("\\?", "")
                .replaceAll("\\.+", ".")
                .replaceAll("(^\\.|\\.$)", "");
        if (!fixedName.equals(className)) {
            Logger.info(String.format("Rewrote XML tag name '%s' to '%s'", className, fixedName));
        }
        return StringUtils.isBlank(fixedName) ? DEFAULT_VIEW_CLASS_NAME : fixedName;
    }

    @Nullable
    public static String toSafeXmlString(Object source, String replacement) {
        return source == null ? null : XML10_PATTERN.matcher(String.valueOf(source)).replaceAll(replacement);
    }
}
