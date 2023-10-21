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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class StringHelpers {
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    private static final int EOF = -1;

    public static String abbreviate(@Nullable String str, int maxLen) {
        if (str == null) {
            return null;
        }
        if (maxLen >= str.length()) {
            return str;
        }

        return str.substring(0, maxLen) + "\u2026";
    }

    public static boolean isBlank(@Nullable String str) {
        return str == null || str.trim().length() == 0;
    }

    @Nullable
    public static String charSequenceToNullableString(@Nullable CharSequence input) {
        return charSequenceToString(input, false);
    }

    @NonNull
    public static String charSequenceToNonNullString(@Nullable CharSequence input) {
        return charSequenceToString(input, true);
    }

    @Nullable
    public static String charSequenceToString(@Nullable CharSequence input, boolean replaceNull) {
        return input == null ? (replaceNull ? "" : null) : input.toString();
    }

    @NonNull
    public static String toNonNullString(@Nullable String input) {
        return toNullableString(input, true);
    }

    @Nullable
    public static String toNullableString(@Nullable String input, boolean replaceNull) {
        return input == null ? (replaceNull ? "" : null) : input;
    }

    @NonNull
    public static String pluralize(long count, String singularWord) {
        return pluralize(count, singularWord, true);
    }

    @NonNull
    public static String pluralize(long count, String singularWord, boolean includeCount) {
        if (count == 1) {
            return includeCount
                    ? String.format("%s %s", count, singularWord)
                    : String.valueOf(singularWord);
        }
        return includeCount
                ? String.format("%s %ss", count, singularWord)
                : String.format("%ss", singularWord);
    }

    public static byte[] inputStreamToByteArray(InputStream input) throws IOException {
        try (ByteArrayOutputStream result = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) != EOF) {
                result.write(buffer, 0, length);
            }
            return result.toByteArray();
        }
    }

    public static String inputStreamToString(InputStream input, Charset encoding) throws IOException {
        return new String(inputStreamToByteArray(input), encoding);
    }
}
