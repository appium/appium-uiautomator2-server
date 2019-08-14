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

package io.appium.uiautomator2.common.exceptions;

import androidx.annotation.Nullable;

public class UiSelectorSyntaxException extends InvalidSelectorException {

    private static String formatErrorMessage(String expression, String message) {
        return String.format("Could not parse expression `%s`: %s", expression, message);
    }

    public UiSelectorSyntaxException(final String expression, final String msg) {
        super(formatErrorMessage(expression, msg));
    }

    public UiSelectorSyntaxException(final String expression, final String msg,
                                     final int position) {
        super(formatErrorMessage(expression, String.format("%s at position %s", msg, position)));
    }

    public UiSelectorSyntaxException(final String expression, final String msg,
                                     @Nullable final Throwable cause) {
        super(formatErrorMessage(expression, msg), cause);
    }
}
