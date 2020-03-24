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

import android.util.Pair;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;


/**
 * For parsing strings that create UiScrollable objects into UiScrollable objects
 */
public class UiScrollableParser extends UiExpressionParser<UiScrollable, UiSelector> {

    private static final List<String> LEGACY_METHOD_NAMES = Arrays.asList("scrollTextIntoView",
            "scrollDescriptionIntoView", "scrollIntoView");

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
        UiObject self = consumeConstructor();
        List<Object> results = new ArrayList<>();
        while (hasMoreDataToParse()) {
            consumePeriod();
            Object result = consumeMethodCall();
            if (result == null) {
                continue;
            }

            results.add(result);
            if (result instanceof UiScrollable) {
                setTarget((UiScrollable) result);
            }
        }

        ListIterator<Object> resultsIterator = results.listIterator(results.size());
        while (resultsIterator.hasPrevious()) {
            Object item = resultsIterator.previous();
            if (item instanceof UiObject) {
                // assign the result to the last method in the chain
                // that returns UiObject
                uiSelector = ((UiObject) item).getSelector();
                break;
            }
        }
        if (uiSelector == null && self != null) {
            // if none of the methods in the chain return UiObject instance
            // the assign the result to self instance
            uiSelector = self.getSelector();
        }
        if (uiSelector == null) {
            throw new UiSelectorSyntaxException(expression.toString(),
                    "At least one method called on a UiScrollable object must return an UiObject instance");
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
                    uiSelector = new UiSelector().text((String) firstArg);
                    break;
                case "scrollDescriptionIntoView":
                    uiSelector = new UiSelector().description((String) firstArg);
                    break;
                default:
                    for (final Object arg : methodWithArguments.second) {
                        if (arg instanceof UiSelector) {
                            uiSelector = (UiSelector) arg;
                            break;
                        }
                    }
            }

            /*
                TODO: It looks like a dirty hack, but we need to keep it for backward compatibility
            */
            if (LEGACY_METHOD_NAMES.contains(methodName)) {
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
