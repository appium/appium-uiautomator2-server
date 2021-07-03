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

import androidx.annotation.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

public class ReflectionUtils {
    public static Class<?> getClass(final String name) {
        try {
            return Class.forName(name);
        } catch (final ClassNotFoundException e) {
            throw new UiAutomator2Exception(String.format("Unable to find class %s", name), e);
        }
    }

    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameterTypes) {
        try {
            Constructor<?> result = clazz.getDeclaredConstructor(parameterTypes);
            result.setAccessible(true);
            return result;
        } catch (NoSuchMethodException e) {
            throw new UiAutomator2Exception(
                    String.format("Cannot find %s class constructor", clazz.getCanonicalName()), e);
        }
    }

    public static Object getField(final Class<?> clazz, final String fieldName,
                                  @Nullable final Object object) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new UiAutomator2Exception(
                    String.format("Cannot get field %s from object %s (class %s)",
                            fieldName, object, clazz.getCanonicalName()), e);
        }
    }

    public static Object getField(final String field, final Object object) throws UiAutomator2Exception {
        return getField(object.getClass(), field, object);
    }

    public static Object getField(final String className, final String field, final Object object) {
        return getField(getClass(className), field, object);
    }

    public static void setField(final String fieldName, final Object value, final Object dstObject) {
        try {
            Field declaredField = dstObject.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            declaredField.set(dstObject, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new UiAutomator2Exception(String.format("Cannot set %s's field '%s' to '%s'",
                    dstObject.getClass().getSimpleName(), fieldName, value), e);
        }
    }

    public static Object invoke(final Method method, final Object object, final Object... parameters) {
        try {
            return method.invoke(object, parameters);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new UiAutomator2Exception(String.format(
                    "Cannot invoke method %s on object %s with parameters %s",
                    method, object, Arrays.toString(parameters)), e);
        }
    }

    public static Method getMethod(final Class<?> clazz, final String methodName, final Class<?>... parameterTypes) {
        try {
            final Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            throw new UiAutomator2Exception(String.format("Cannot get method %s from class %s with parameter types %s",
                    methodName, clazz.getCanonicalName(), Arrays.toString(parameterTypes)), e);
        }
    }

    public static Method getMethod(final String className, final String method, final Class<?>... parameterTypes) {
        return getMethod(getClass(className), method, parameterTypes);
    }
}
