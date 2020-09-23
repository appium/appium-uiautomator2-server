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
import android.view.ViewConfiguration;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public class GestureController {

    private final Object wrappedInstance;
    private final Method performGestureMethod;
    private final Gestures gestures;

    GestureController(Object wrappedInstance, Gestures gestures) {
        this.wrappedInstance = wrappedInstance;
        this.performGestureMethod = extractPerformGestureMethod(wrappedInstance);
        this.gestures = gestures;
    }

    private static Method extractPerformGestureMethod(Object wrappedInstance) {
        for (Method method : wrappedInstance.getClass().getDeclaredMethods()) {
            if (method.getName().equals("performGesture")) {
                method.setAccessible(true);
                return method;
            }
        }
        throw new IllegalStateException(String.format("Cannot retrieve performGesture method from %s",
                wrappedInstance.getClass().getCanonicalName()));
    }

    private void performGesture(PointerGesture... gestures) {
        Object args = Array.newInstance(PointerGesture.getWrappedClass(), gestures.length);
        for (int i = 0; i < gestures.length; ++i) {
            Array.set(args, i, gestures[i].getWrappedInstance());
        }
        invoke(performGestureMethod, wrappedInstance, args);
    }

    public void click(Point point) {
        performGesture(new PointerGesture(point).pause(0L));
    }

    public void longClick(Point point) {
        performGesture(new PointerGesture(point).pause(ViewConfiguration.getLongPressTimeout()));
    }
}
