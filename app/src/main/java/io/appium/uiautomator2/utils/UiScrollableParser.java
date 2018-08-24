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

import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.util.Pair;

import java.lang.reflect.Method;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;


/**
 * For parsing strings that create UiScrollable objects into UiScrollable objects
 */
public class UiScrollableParser extends UiExpressionParser<UiScrollable, UiSelector> {

    private UiSelector uiSelector;

    UiScrollableParser(String expression) {
        super(UiScrollable.class, expression);
    }

    /*
     * Returns whether or not the input string is trying to instantiate a UiScrollable, and use
     * its methods
     */
    public boolean isUiScrollable() {
        return expression.startsWith(getConstructorExpression());
    }

    /*
     * Parse a string into a UiSelector, but use UiScrollable class and methods
     */
    @Override
    public UiSelector parse() throws UiSelectorSyntaxException, UiObjectNotFoundException {
        resetCurrentIndex();
        Object result = null;
        consumeConstructor();
        while (hasMoreDataToParse()) {
            consumePeriod();
            result = consumeMethodCall();
            if (result instanceof UiScrollable) {
                setTarget((UiScrollable) result);
            }
        }

        if (result instanceof UiObject) {
            uiSelector = ((UiObject) result).getSelector();
        }

        if (uiSelector == null) {
            throw new UiSelectorSyntaxException(expression.toString(),
                    "Last method called on a UiScrollable object must return a UiObject object");
        }
        return uiSelector;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <V> V consumeMethodCall() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        final String methodName = consumeMethodName();
        final List<String> arguments = consumeMethodParameters();
        final Pair<Method, List<Object>> methodWithArguments = findMethod(methodName, arguments);

        /*
            There are few methods in UiScrollable that take UiSelector or String as argument
            but don't return UiObject (e.g. scrollIntoView, scrollTextIntoView).
            However the result of parsing should be UiSelector.
            So we can store this UiSelector and use it at the end of parsing.
        */
        if (!(methodWithArguments.first.getGenericReturnType() instanceof UiObject
                || methodWithArguments.second.isEmpty())) {
            final Object firstArg = methodWithArguments.second.get(0);
            switch (methodName) {
                case "scrollTextIntoView":
                    uiSelector = new UiSelector().text(String.class.cast(firstArg));
                    break;
                case "scrollDescriptionIntoView":
                    uiSelector = new UiSelector().description(String.class.cast(firstArg));
                    break;
                default:
                    for (final Object arg : methodWithArguments.second) {
                        if (arg instanceof UiSelector) {
                            uiSelector = UiSelector.class.cast(arg);
                            break;
                        }
                    }
            }

            /*
                TODO: It looks like a dirty hack, but we need to keep it for backward compatibility
            */
            if ("scrollTextIntoView".equals(methodName)
                    || "scrollDescriptionIntoView".equals(methodName)
                    || "scrollIntoView".equals(methodName)) {
                /* Skip invocation to avoid exception if scrollable container does not exist */
                final UiObject uiObject = createUiObject(uiSelector);
                if (uiObject.exists()) {
                    return (V) uiObject;
                }
            }
        }

        return invokeMethod(getTarget(), methodWithArguments.first, methodWithArguments.second);
    }

    protected UiObject createUiObject(UiSelector uiSelector) {
        return new UiObject(uiSelector);
    }
}
