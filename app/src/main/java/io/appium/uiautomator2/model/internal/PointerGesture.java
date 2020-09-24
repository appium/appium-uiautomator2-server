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

package io.appium.uiautomator2.model.internal;

import android.graphics.Point;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.utils.ReflectionUtils.getConstructor;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public class PointerGesture {
    private static final String POINTER_GESTURE_CLASS = "androidx.test.uiautomator.PointerGesture";

    private static Class<?> pointerGestureClass;
    private static Constructor<?> pointerGestureConstructor;
    private final Object wrappedInstance;

    public synchronized static Class<?> getWrappedClass() {
        if (pointerGestureClass == null) {
            pointerGestureClass = ReflectionUtils.getClass(POINTER_GESTURE_CLASS);
        }
        return pointerGestureClass;
    }

    private static synchronized Constructor<?> getWrappedConstructor() {
        if (pointerGestureConstructor == null) {
            pointerGestureConstructor = getConstructor(getWrappedClass(), Point.class);
        }
        return pointerGestureConstructor;
    }

    public PointerGesture(Object wrappedInstanceOrPoint) {
        if (wrappedInstanceOrPoint instanceof Point) {
            try {
                this.wrappedInstance = getWrappedConstructor().newInstance((Point) wrappedInstanceOrPoint);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new IllegalStateException(String.format("Cannot perform gesture at %s", wrappedInstanceOrPoint), e);
            }
        } else {
            this.wrappedInstance = wrappedInstanceOrPoint;
        }
    }

    public PointerGesture pause(long ms) {
        Method pauseMethod = ReflectionUtils.getMethod(getWrappedClass(), "pause", long.class);
        invoke(pauseMethod, wrappedInstance, ms);
        return this;
    }

    public Object getWrappedInstance() {
        return this.wrappedInstance;
    }
}
