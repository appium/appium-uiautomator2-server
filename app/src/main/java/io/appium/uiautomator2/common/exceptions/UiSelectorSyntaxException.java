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

import android.support.annotation.Nullable;

import io.appium.uiautomator2.utils.UiSelectorParser;

@SuppressWarnings("serial")
public class UiSelectorSyntaxException extends UiAutomator2Exception {

    /**
     * An exception involving an {@link UiSelectorParser}.
     *
     * @param msg A descriptive message describing the error.
     */
    public UiSelectorSyntaxException(final String expression, final String msg) {
        this(expression, msg, null);
    }

    public UiSelectorSyntaxException(final String expression, final String msg,
                                     final int position) {
        this(expression, msg + " at position " + position, null);
    }

    public UiSelectorSyntaxException(final String expression, final String msg,
                                     @Nullable final Throwable cause) {
        super(String.format("Could not parse expression `%s`: %s", expression, msg), cause);
    }
}
