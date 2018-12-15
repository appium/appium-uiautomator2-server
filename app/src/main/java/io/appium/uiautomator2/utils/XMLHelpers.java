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

import java.util.regex.Pattern;

import androidx.annotation.Nullable;

public abstract class XMLHelpers {
    // XML 1.0 Legal Characters (http://stackoverflow.com/a/4237934/347155)
    // #x9 | #xA | #xD | [#x20-#xD7FF] | [#xE000-#xFFFD] | [#x10000-#x10FFFF]
    private final static Pattern XML10_PATTERN = Pattern.compile("[^" + "\u0009\r\n" +
            "\u0020-\uD7FF" + "\uE000-\uFFFD" + "\ud800\udc00-\udbff\udfff" + "]");
    // https://stackoverflow.com/questions/3158274/what-would-be-a-regex-for-valid-xml-names
    private final static Pattern XML10_START_TAG_PATTERN = Pattern.compile("^[^" + "_" +
            "A-Z" + "a-z" + "\u00C0-\u00D6" + "\u00F8-\u02FF" +
            "\u200C-\u200D" + "\u2070-\u218F" + "\u2C00-\u2FEF" +
            "\u3001-\uD7FF" + "\uF900-\uFDCF" + "\uFDF0-\uFFFD" +
            "\ud800\udc00-\udbff\udfff" + "]+");
    private final static Pattern XML10_TAG_PATTERN = Pattern.compile("[^" + "\\-._" +
            "0-9" + "\u00B7" + "\u0300-\u036F" + "\u203F-\u2040" +
            "A-Z" + "a-z" + "\u00C0-\u00D6" + "\u00F8-\u02FF" +
            "\u200C-\u200D" + "\u2070-\u218F" + "\u2C00-\u2FEF" +
            "\u3001-\uD7FF" + "\uF900-\uFDCF" + "\uFDF0-\uFFFD" +
            "\ud800\udc00-\udbff\udfff" + "]");

    public static String toNodeName(String str) {
        String nodeName = XML10_START_TAG_PATTERN
                .matcher(str)
                .replaceAll("");
        return XML10_TAG_PATTERN
                .matcher(nodeName)
                .replaceAll("_");
    }

    @Nullable
    public static String toSafeString(@Nullable Object source, String replacement) {
        return source == null ? null : XML10_PATTERN
                .matcher(String.valueOf(source))
                .replaceAll(replacement);
    }
}
