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

import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

abstract class UiExpressionParser<T, U> {
    protected final Class<T> clazz;
    protected String expression;
    private T target;

    UiExpressionParser(Class<T> clazz, String expression) {
        this.clazz = clazz;
        this.expression = expression;
        prepareForParsing();
    }

    protected String getConstructorExpression() {
        return "new " + clazz.getSimpleName();
    }

    public abstract U parse() throws UiSelectorSyntaxException, UiObjectNotFoundException;

    // prepares text for the main parsing loop
    protected void prepareForParsing() {
        expression = expression.trim();
        if (expression.startsWith(clazz.getSimpleName())) {
            expression = "new " + expression;
        }
    }

    @SuppressWarnings("unchecked")
    protected void consumeConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expression = expression.trim();
        if (!expression.startsWith(getConstructorExpression())) {
            throw new UiSelectorSyntaxException(String.format(
                    "Was trying to parse as %1$s, but didn't start with an acceptable prefix. " +
                            "Acceptable prefixes are: `new %1$s` or `%1$s`. Saw: `%2$s`",
                    clazz.getSimpleName(), expression));
        }
        expression = expression.substring(getConstructorExpression().length());
        final List<String> params = consumeMethodParameters();
        final Pair<Constructor, List<Object>> constructor = findConstructor(params);
        try {
            target = (T) constructor.first.newInstance(constructor.second.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new UiSelectorSyntaxException("Can not create instance of " +
                    clazz.getSimpleName(), e);
        }
    }

    protected void consumePeriod() throws UiSelectorSyntaxException {
        expression = expression.trim();
        if (expression.startsWith(".")) {
            expression = expression.substring(1);
        } else {
            throw new UiSelectorSyntaxException("Expected \".\" but saw \"" + expression.charAt(0) +
                    "\"");
        }
    }

    protected String consumeMethodName() throws UiSelectorSyntaxException {
        expression = expression.trim();
        final int firstParenIndex = expression.indexOf('(');
        if (firstParenIndex < 0) {
            throw new UiSelectorSyntaxException("No opening parenthesis after method name: " +
                    expression);
        }
        final String methodName = expression.substring(0, firstParenIndex).trim();
        expression = expression.substring(firstParenIndex);
        return methodName;
    }

    protected List<String> consumeMethodParameters() throws UiSelectorSyntaxException {
        expression = expression.trim();
        final List<String> arguments = new ArrayList<>();
        final Stack<Character> parenthesesStack = new Stack<>();
        int startIndex = 0;
        int currentIndex = 0;
        boolean isInsideStringLiteral = false;
        do {
            final char currentChar = expression.charAt(currentIndex);

            if (currentChar == '"') {
                /* Skip escaped quotes */
                isInsideStringLiteral = !(isInsideStringLiteral && currentIndex > 0
                        && expression.charAt(currentIndex - 1) != '\\');
            }

            if (!isInsideStringLiteral) {
                switch (currentChar) {
                    case ')':
                        if (parenthesesStack.peek() == '(') {
                            parenthesesStack.pop();
                        } else {
                            parenthesesStack.push(currentChar);
                        }
                        break;
                    case '(':
                        parenthesesStack.push(currentChar);
                        break;
                    case ',':
                        final String argument = expression.substring(startIndex + 1,
                                currentIndex);
                        if (!argument.isEmpty()) {
                            arguments.add(argument.trim());
                        }
                        startIndex = currentIndex;
                        break;
                }
            }
            currentIndex++;
        } while (!parenthesesStack.empty() && currentIndex < expression.length());

        if (!parenthesesStack.isEmpty()) {
            throw new UiSelectorSyntaxException("unclosed paren in expression: " + expression);
        }

        final String argument = expression.substring(startIndex + 1, currentIndex - 1);
        if (!argument.isEmpty()) {
            arguments.add(argument.trim());
        }

        expression = expression.substring(currentIndex);
        return arguments;
    }

    /**
     * consume [a-z]* then an open paren, this is our methodName
     * consume .* and count open/close parens until the original open paren is close, this is
     * our
     * argument
     */
    protected <V> V consumeMethodCall() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        final String methodName = consumeMethodName();
        final List<String> arguments = consumeMethodParameters();
        final Pair<Method, List<Object>> methodWithArgument = findMethod(methodName, arguments);
        return invokeMethod(target, methodWithArgument.first, methodWithArgument.second);
    }

    protected Pair<Method, List<Object>> findMethod(String methodName, List<String> arguments)
            throws UiSelectorSyntaxException, UiObjectNotFoundException {
        final List<Method> candidates = new ArrayList<>();
        for (final Method method : clazz.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                candidates.add(method);
            }
        }

        if (candidates.isEmpty()) {
            throw new UiSelectorSyntaxException(String.format("%s has no `%s` method",
                    getTarget().getClass().getSimpleName(), methodName));
        }

        UiSelectorSyntaxException exThrown = null;
        for (final Method method : candidates) {
            try {
                final Type[] parameterTypes = method.getGenericParameterTypes();
                final List<Object> args = coerceArgsToTypes(parameterTypes, arguments);
                return new Pair<>(method, args);
            } catch (UiSelectorSyntaxException e) {
                exThrown = e;
            }
        }

        final String errorMsg = "`%s` doesn't have suitable method `%s` with arguments %s" +
                (exThrown != null ? ": " + exThrown.getMessage() : "");

        throw new UiSelectorSyntaxException(String.format(errorMsg, clazz.getSimpleName(),
                methodName, arguments), exThrown);
    }

    private Pair<Constructor, List<Object>> findConstructor(List<String> arguments) throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        UiSelectorSyntaxException exThrown = null;
        for (final Constructor constructor : clazz.getConstructors()) {
            try {
                final Type[] parameterTypes = constructor.getGenericParameterTypes();
                final List<Object> args = coerceArgsToTypes(parameterTypes, arguments);
                return new Pair<>(constructor, args);
            } catch (UiSelectorSyntaxException e) {
                exThrown = e;
            }
        }

        throw new UiSelectorSyntaxException(String.format("%s has no suitable constructor with " +
                        "arguments %s", clazz.getSimpleName(), arguments), exThrown);
    }

    @SuppressWarnings("unchecked")
    protected <V> V invokeMethod(Object receiver, Method method, List<Object> arguments) throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        try {
            return (V) method.invoke(receiver, arguments.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new UiSelectorSyntaxException("problem using reflection to call this method", e);
        } catch (InvocationTargetException e) {
            Throwable targetException = e.getTargetException();
            if (targetException instanceof UiObjectNotFoundException) {
                throw (UiObjectNotFoundException) targetException;
            }
            throw new UiAutomator2Exception(targetException);
        }
    }

    private List<Object> coerceArgsToTypes(Type[] types, List<String> arguments) throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        if (types.length != arguments.size()) {
            throw new UiSelectorSyntaxException(String.format("Invalid arguments count. Actual:%s" +
                    ". Expected:%s.", arguments.size(), types.length));
        }
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            result.add(coerceArgToType(types[i], arguments.get(i)));
        }
        return result;
    }

    private Object coerceArgToType(Type type, String argument) throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        Logger.debug(String.format("UiSelector coerce type:%s arg:%s", type, argument));
        if (type == boolean.class) {
            if (argument.matches("^(true|false)$")) {
                return Boolean.valueOf(argument);
            }
            throw new UiSelectorSyntaxException(argument + " is not a boolean");
        }

        if (type == String.class) {
            if (argument.matches("^\"[\\s\\S]*\"$")) {
                return argument.substring(1, argument.length() - 1).replaceAll("\\\\\"", "\"");
            }
            throw new UiSelectorSyntaxException(argument + " is not a string");
        }

        if (type == int.class) {
            try {
                return Integer.parseInt(argument);
            } catch (NumberFormatException e) {
                throw new UiSelectorSyntaxException(argument + " is not a integer");
            }
        }

        if ("java.lang.Class<T>".equals(type.toString())) {
            try {
                return Class.forName(argument);
            } catch (ClassNotFoundException e) {
                throw new UiSelectorSyntaxException(argument + " class could not be found");
            }
        }

        if (type == UiSelector.class) {
            UiSelectorParser parser = new UiSelectorParser(argument);
            return parser.parse();
        }

        throw new UiSelectorSyntaxException(String.format("Type `%s` is not supported.", type));
    }


    protected T getTarget() {
        return target;
    }

    protected void setTarget(T target) {
        this.target = target;
    }
}
