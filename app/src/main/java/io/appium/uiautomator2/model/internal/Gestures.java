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

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.graphics.Point;
import android.graphics.Rect;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiObject2;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

import io.appium.uiautomator2.utils.ReflectionUtils;

public class Gestures {
    private final Class<?> wrappedClass;
    private final int displayId;

    Gestures(int displayId) {
        this.displayId = displayId;
        // https://androidx.tech/artifacts/test.uiautomator/uiautomator/2.3.0-beta01-source/androidx/test/uiautomator/Gestures.java.html
        this.wrappedClass = ReflectionUtils.getClass("androidx.test.uiautomator.Gestures");
    }

    public int getDisplayId() {
        return displayId;
    }

    public PointerGesture drag(Point start, Point end, int speed) {
        Method dragMethod = getMethod(
                wrappedClass, "drag",
                Point.class, Point.class, int.class, int.class
        );
        return new PointerGesture(
                invoke(dragMethod, wrappedClass, start, end, speed, displayId),
                displayId
        );
    }

    public PointerGesture[] pinchClose(Rect area, float percent, int speed) {
        Method pinchCloseMethod = getMethod(
                wrappedClass, "pinchClose",
                Rect.class, float.class, int.class, int.class
        );
        return toGesturesArray(
                invoke(pinchCloseMethod, wrappedClass, area, percent, speed, displayId)
        );
    }

    public PointerGesture[] pinchOpen(Rect area, float percent, int speed) {
        Method pinchOpenMethod = getMethod(
                wrappedClass, "pinchOpen",
                Rect.class, float.class, int.class, int.class
        );
        return toGesturesArray(
                invoke(pinchOpenMethod, wrappedClass, area, percent, speed, displayId)
        );
    }

    public PointerGesture swipe(Rect area, Direction direction, float percent, int speed) {
        Method swipeRectMethod = getMethod(
                wrappedClass, "swipeRect",
                Rect.class, Direction.class, float.class, int.class, int.class
        );
        return new PointerGesture(
                invoke(swipeRectMethod, wrappedClass, area, direction, percent, speed, displayId),
                displayId
        );
    }

    private PointerGesture[] toGesturesArray(Object result) {
        return java.util.stream.IntStream.range(0, Array.getLength(result))
                .mapToObj(i -> new PointerGesture(Array.get(result, i), displayId))
                .toArray(PointerGesture[]::new);
    }

    public static float getDisplayDensity() {
        return getInstrumentation().getTargetContext().getResources().getDisplayMetrics().density;
    }

    private static int getSpeedValue(String gestureName) {
        String fieldName = String.format("DEFAULT_%s_SPEED", gestureName.toUpperCase());
        return (int) getField(UiObject2.class, fieldName, null);
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
        // UiObject2.SCROLL_TIMEOUT
        return 1000L;
    }

    public static long getFlingTimeout() {
        // UiObject2.FLING_TIMEOUT
        return 5000L;
    }
}
