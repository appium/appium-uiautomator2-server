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

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class GestureController {
    private static final String POINTER_GESTURE_CLASS = "androidx.test.uiautomator.PointerGesture";

    private Object wrappedInstance;
    private static Class<?> pointerGestureClass;

    GestureController(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    private synchronized static Class<?> getPointerGestureClass() {
        if (pointerGestureClass == null) {
            try {
                pointerGestureClass = Class.forName(POINTER_GESTURE_CLASS);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(String.format("Cannot access %s class", POINTER_GESTURE_CLASS), e);
            }
        }
        return pointerGestureClass;
    }

    private void performGesture(Object... gestures) {
        Method performGestureMethod = method(wrappedInstance.getClass(), "performGesture",
                new Class<?>[] { getPointerGestureClass() });
        invoke(performGestureMethod, wrappedInstance, gestures);
    }

    public void click(Point point) {
        // new PointerGesture(point).pause(0);
        Object gesture;
        try {
            Constructor<?> constructor = getPointerGestureClass().getConstructor(Point.class);
            gesture = constructor.newInstance(point);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Cannot perform click gesture at %s", point), e);
        }
        Method pauseMethod = method(getPointerGestureClass(), "pause", long.class);
        gesture = invoke(pauseMethod, gesture, 0L);
        performGesture(gesture);
    }
}
