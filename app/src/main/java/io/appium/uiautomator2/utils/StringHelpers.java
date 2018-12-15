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

public class StringHelpers {
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
}
