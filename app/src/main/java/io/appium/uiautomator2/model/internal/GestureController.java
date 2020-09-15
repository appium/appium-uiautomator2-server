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

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.appium.uiautomator2.utils.ReflectionUtils;

import static io.appium.uiautomator2.utils.ReflectionUtils.getConstructor;
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
            pointerGestureClass = ReflectionUtils.getClass(POINTER_GESTURE_CLASS);
        }
        return pointerGestureClass;
    }

    private void performGesture(Object... gestures) {
        Method[] methods = wrappedInstance.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals("performGesture")) {
                method.setAccessible(true);
                Object args = Array.newInstance(getPointerGestureClass(), gestures.length);
                for (int i = 0; i < gestures.length; ++i) {
                    Array.set(args, i, gestures[i]);
                }
                invoke(method, wrappedInstance, args);
                return;
            }
        }
        throw new IllegalStateException(String.format("Cannot perform '%s' gesture", gestures));
    }

    public void click(Point point) {
        // new PointerGesture(point).pause(0);
        Object gesture;
        try {
            gesture = getConstructor(getPointerGestureClass(), Point.class).newInstance(point);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new IllegalStateException(String.format("Cannot perform click gesture at %s", point), e);
        }
        Method pauseMethod = method(getPointerGestureClass(), "pause", long.class);
        gesture = invoke(pauseMethod, gesture, 0L);
        performGesture(gesture);
    }
}
