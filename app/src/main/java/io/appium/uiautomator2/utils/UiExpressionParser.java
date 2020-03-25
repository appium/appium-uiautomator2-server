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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

abstract class UiExpressionParser<T, U> {
    protected final Class<T> clazz;
    protected final StringBuilderWrapper expression;
    private int currentIndex;
    private T target;

    UiExpressionParser(Class<T> clazz, String expression) {
        this.clazz = clazz;
        this.expression = new StringBuilderWrapper(expression);
        prepareForParsing();
    }

    protected String getConstructorExpression() {
        return "new " + clazz.getSimpleName();
    }

    public abstract U parse() throws UiSelectorSyntaxException, UiObjectNotFoundException;

    // prepares text for the main parsing loop
    protected void prepareForParsing() {
        if (expression.startsWith(clazz.getSimpleName())) {
            expression.getStringBuilder().insert(0, "new ");
        }
    }

    @SuppressWarnings("unchecked")
    protected T consumeConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        skipLeadingSpaces();
        final String constructorExpression = getConstructorExpression();
        if (!expression.startsWith(constructorExpression, currentIndex)) {
            throw new UiSelectorSyntaxException(expression.toString(), String.format(
                    "Was trying to parse as %1$s, but didn't start with an acceptable prefix. " +
                            "Acceptable prefixes are: `new %1$s` or `%1$s`",
                    clazz.getSimpleName()));
        }
        currentIndex += constructorExpression.length();
        final List<String> params = consumeMethodParameters();
        final Pair<Constructor, List<Object>> constructor = findConstructor(params);
        try {
            target = (T) constructor.first.newInstance(constructor.second.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new UiAutomator2Exception("Can not create instance of " +
                    clazz.getSimpleName(), e);
        }
        return target;
    }

    protected void consumePeriod() throws UiSelectorSyntaxException {
        skipLeadingSpaces();
        if (hasMoreDataToParse() && expression.getStringBuilder().charAt(currentIndex) == '.') {
            currentIndex++;
        } else {
            throw new UiSelectorSyntaxException(expression.toString(), "Expected \".\"",
                    currentIndex);
        }
    }

    protected String consumeMethodName() throws UiSelectorSyntaxException {
        skipLeadingSpaces();
        final int firstParenIndex = expression.getStringBuilder().indexOf("(", currentIndex);
        if (firstParenIndex < 0) {
            throw new UiSelectorSyntaxException(expression.toString(),
                    "No opening parenthesis after method name", currentIndex);
        }
        final String methodName = expression.getStringBuilder()
                .substring(currentIndex, firstParenIndex).trim();
        if (methodName.isEmpty()) {
            throw new UiSelectorSyntaxException(expression.toString(),
                    "Missing method name", currentIndex);
        }
        currentIndex = firstParenIndex;
        return methodName;
    }

    protected List<String> consumeMethodParameters() throws UiSelectorSyntaxException {
        skipLeadingSpaces();
        final List<String> arguments = new ArrayList<>();
        final Stack<Character> parenthesesStack = new Stack<>();
        int startIndex = currentIndex;
        boolean isInsideStringLiteral = false;
        do {
            final char currentChar = expression.getStringBuilder().charAt(currentIndex);

            if (currentChar == '"') {
                /* Skip escaped quotes */
                isInsideStringLiteral = !(isInsideStringLiteral && currentIndex > 0
                        && expression.getStringBuilder().charAt(currentIndex - 1) != '\\');
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
                        final String argument = expression.getStringBuilder()
                                .substring(startIndex + 1, currentIndex).trim();
                        if (argument.isEmpty()) {
                            throw new UiSelectorSyntaxException(expression.toString(),
                                    "Missing argument", startIndex);
                        }
                        arguments.add(argument);
                        startIndex = currentIndex;
                        break;
                    default:
                        break;
                }
            }
            currentIndex++;
        } while (!parenthesesStack.empty() && hasMoreDataToParse());

        if (!parenthesesStack.isEmpty()) {
            throw new UiSelectorSyntaxException(expression.toString(),
                    "Unclosed paren in expression");
        }

        final String argument = expression.getStringBuilder()
                .substring(startIndex + 1, currentIndex - 1).trim();

        if (!argument.isEmpty()) {
            arguments.add(argument);
        } else if (!arguments.isEmpty()) {
            /* Throw exception if the last argument is missing */
            throw new UiSelectorSyntaxException(expression.toString(), "Missing argument",
                    startIndex);
        }

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
            throw new UiSelectorSyntaxException(expression.toString(),
                    String.format("%s has no `%s` method", getTarget().getClass().getSimpleName(),
                            methodName));
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

        throw new UiSelectorSyntaxException(expression.toString(),
                String.format(errorMsg, clazz.getSimpleName(), methodName, arguments), exThrown);
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

        throw new UiSelectorSyntaxException(expression.toString(),
                String.format("%s has no suitable constructor with arguments %s",
                        clazz.getSimpleName(), arguments), exThrown);
    }

    @SuppressWarnings("unchecked")
    protected <V> V invokeMethod(Object receiver, Method method, List<Object> arguments) throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        try {
            return (V) method.invoke(receiver, arguments.toArray());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new UiSelectorSyntaxException(expression.toString(),
                    String.format("Problem using reflection to call `%s` method",
                            method.getName()), e);
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
            throw new UiSelectorSyntaxException(expression.toString(),
                    String.format("Invalid arguments count. Actual: %s. Expected: %s.",
                            arguments.size(), types.length));
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
            throw new UiSelectorSyntaxException(expression.toString(),
                    argument + " is not a boolean");
        }

        if (type == String.class) {
            if (argument.matches("^\"[\\s\\S]*\"$")) {
                return argument.substring(1, argument.length() - 1).replaceAll("\\\\\"", "\"");
            }
            throw new UiSelectorSyntaxException(expression.toString(),
                    argument + " is not a string");
        }

        if (type == int.class) {
            try {
                return Integer.parseInt(argument);
            } catch (NumberFormatException e) {
                throw new UiSelectorSyntaxException(expression.toString(),
                        argument + " is not a integer");
            }
        }

        if (type == double.class) {
          return Double.parseDouble(argument);
        }

        if ("java.lang.Class<T>".equals(type.toString())) {
            try {
                return Class.forName(argument);
            } catch (ClassNotFoundException e) {
                throw new UiSelectorSyntaxException(expression.toString(),
                        argument + " class could not be found");
            }
        }

        if (type == UiSelector.class) {
            UiSelectorParser parser = new UiSelectorParser(argument);
            return parser.parse();
        }

        throw new UiSelectorSyntaxException(expression.toString(),
                String.format("Type `%s` is not supported.", type));
    }


    protected T getTarget() {
        return target;
    }

    protected void setTarget(T target) {
        this.target = target;
    }

    protected void skipLeadingSpaces() {
        while (hasMoreDataToParse() && expression.getStringBuilder().charAt(currentIndex) == ' ') {
            currentIndex++;
        }
    }

    protected void resetCurrentIndex() {
        currentIndex = 0;
    }

    protected boolean hasMoreDataToParse() {
        return currentIndex < expression.getStringBuilder().length();
    }

    class StringBuilderWrapper {

        private final StringBuilder sb;

        public StringBuilderWrapper(String string) {
            sb = new StringBuilder(string.trim());
        }

        public boolean startsWith(String str, int index) {
            return sb.indexOf(str, index) == index;
        }

        public boolean startsWith(String str) {
            return startsWith(str, 0);
        }

        public StringBuilder getStringBuilder() {
            return sb;
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }
}
