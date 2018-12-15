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

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

/**
 * For parsing strings which create new UiSelector objects into UiSelector object.
 */
public class UiSelectorParser extends UiExpressionParser<UiSelector, UiSelector> {

    public UiSelectorParser(String expression) {
        super(UiSelector.class, expression);
    }

    @Override
    protected void prepareForParsing() {
        if (!(expression.startsWith(getConstructorExpression())
                || expression.startsWith(clazz.getSimpleName())
                || expression.startsWith("."))) {
            expression.getStringBuilder().insert(0, ".");
        }

        super.prepareForParsing();

        if (!expression.startsWith(getConstructorExpression())) {
            expression.getStringBuilder().insert(0, "()");
            expression.getStringBuilder().insert(0, getConstructorExpression());
        }
    }

    public UiSelector parse() throws UiSelectorSyntaxException, UiObjectNotFoundException {
        resetCurrentIndex();
        consumeConstructor();
        while (hasMoreDataToParse()) {
            consumePeriod();
            final Object result = consumeMethodCall();
            if (!(result instanceof UiSelector)) {
                throw new UiSelectorSyntaxException(expression.toString(),
                        String.format("Unsupported return value type:`%s`. " +
                                        "Only methods with return type `UiSelector` are supported.",
                                result.getClass().getSimpleName()));
            }
            setTarget((UiSelector) result);
        }
        return getTarget();
    }
}
