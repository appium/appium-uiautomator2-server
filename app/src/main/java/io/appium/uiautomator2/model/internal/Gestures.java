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
import android.graphics.Rect;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject2;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class Gestures {
    private final Object wrappedInstance;

    Gestures(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public PointerGesture drag(Point start, Point end, int speed) {
        Method dragMethod = method(wrappedInstance.getClass(), "drag",
                Point.class, Point.class, int.class);
        return new PointerGesture(invoke(dragMethod, wrappedInstance, start, end, speed));
    }

    private static PointerGesture[] toGesturesArray(Object result) {
        List<PointerGesture> list = new ArrayList<>();
        for (int i = 0; i < Array.getLength(result); ++i) {
            list.add(new PointerGesture(Array.get(result, i)));
        }
        return list.toArray(new PointerGesture[0]);
    }

    public PointerGesture[] pinchClose(Rect area, float percent, int speed) {
        Method pinchCloseMethod = method(wrappedInstance.getClass(), "pinchClose",
                Rect.class, float.class, int.class);
        return toGesturesArray(invoke(pinchCloseMethod, wrappedInstance, area, percent, speed));
    }

    public PointerGesture[] pinchOpen(Rect area, float percent, int speed) {
        Method pinchOpenMethod = method(wrappedInstance.getClass(), "pinchOpen",
                Rect.class, float.class, int.class);
        return toGesturesArray(invoke(pinchOpenMethod, wrappedInstance, area, percent, speed));
    }

    public PointerGesture swipe(Rect area, Direction direction, float percent, int speed) {
        Method swipeRectMethod = method(wrappedInstance.getClass(), "swipeRect",
                Rect.class, Direction.class, float.class, int.class);
        return new PointerGesture(invoke(swipeRectMethod, wrappedInstance, area, direction, percent, speed));
    }

    public static float getDisplayDensity() {
        return InstrumentationRegistry.getInstrumentation().getContext()
                .getResources().getDisplayMetrics().density;
    }

    private static int getSpeedValue(String gestureName) {
        return (int) getField(String.format("DEFAULT_%s_SPEED", gestureName.toUpperCase()), UiObject2.class);
    }

    public static int getDefaultDragSpeed() {
        return (int) (getSpeedValue("drag") * getDisplayDensity());
    }

    public static int getDefaultSwipeSpeed() {
        return (int) (getSpeedValue("swipe") * getDisplayDensity());
    }

    public static int getDefaultScrollSpeed() {
        return (int) (getSpeedValue("scroll") * getDisplayDensity());
    }

    public static int getDefaultFlingSpeed() {
        return (int) (getSpeedValue("fling") * getDisplayDensity());
    }

    public static int getDefaultPinchSpeed() {
        return (int) (getSpeedValue("pinch") * getDisplayDensity());
    }

    public static long getScrollTimeout() {
        return (long) getField("SCROLL_TIMEOUT", UiObject2.class);
    }

    public static long getFlingTimeout() {
        return (long) getField("FLING_TIMEOUT", UiObject2.class);
    }
}
