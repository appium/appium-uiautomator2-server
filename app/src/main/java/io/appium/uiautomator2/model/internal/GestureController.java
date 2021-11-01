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
import android.os.SystemClock;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.EventCondition;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

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

    private static UiDevice getDevice() {
        return UiDevice.getInstance(getInstrumentation());
    }

    private class GestureRunnable implements Runnable {
        private PointerGesture[] mGestures;

        public GestureRunnable(PointerGesture[] gestures) {
            mGestures = gestures;
        }

        @Override
        public void run() {
            performGesture(mGestures);
        }
    }

    private <R> R performGestureAndWait(EventCondition<R> condition, long timeout, PointerGesture... gestures) {
        return getDevice().performActionAndWait(new GestureRunnable(gestures), condition, timeout);
    }

    public void click(Point point) {
        performGesture(new PointerGesture(point).pause(0L));
    }

    public void doubleClick(Point point) {
        performGesture(new PointerGesture(point).pause(0L));
        SystemClock.sleep(ViewConfiguration.getDoubleTapTimeout() / 2);
        performGesture(new PointerGesture(point).pause(0L));
    }

    public void longClick(Point point, @Nullable Long durationMs) {
        long duration = durationMs == null ? ViewConfiguration.getLongPressTimeout() : durationMs;
        if (duration < 0) {
            throw new IllegalArgumentException("Long click duration cannot be negative");
        }
        performGesture(new PointerGesture(point).pause(duration));
    }

    private static int checkSpeed(int speed) {
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative");
        }
        return speed;
    }

    private static float checkPercent(float percent) {
        if (percent < 0.0f || percent > 1.0f) {
            throw new IllegalArgumentException("Percent must be between 0.0f and 1.0f");
        }
        return percent;
    }

    public void drag(Point start, Point end, @Nullable Integer speed) {
        int dragSpeed = speed == null ? Gestures.getDefaultDragSpeed() : checkSpeed(speed);
        performGesture(gestures.drag(start, end, dragSpeed));
    }

    public void pinchClose(Rect area, float percent, @Nullable Integer speed) {
        int pinchSpeed = speed == null ? Gestures.getDefaultPinchSpeed() : checkSpeed(speed);
        performGesture(gestures.pinchClose(area, checkPercent(percent), pinchSpeed));
    }

    public void pinchOpen(Rect area, float percent, @Nullable Integer speed) {
        int pinchSpeed = speed == null ? Gestures.getDefaultPinchSpeed() : checkSpeed(speed);
        performGesture(gestures.pinchOpen(area, checkPercent(percent), pinchSpeed));
    }

    public void swipe(Rect area, Direction direction, float percent, @Nullable Integer speed) {
        int swipeSpeed = speed == null ? Gestures.getDefaultSwipeSpeed() : checkSpeed(speed);
        performGesture(gestures.swipe(area, direction, checkPercent(percent), swipeSpeed));
    }

    public boolean scroll(Rect area, Direction direction, float percent, @Nullable Integer speed) {
        if (percent < 0.0f) {
            throw new IllegalArgumentException("Percent must be greater than 0.0f");
        }

        Direction swipeDirection = Direction.reverse(direction);
        int scrollSpeed = speed == null ? Gestures.getDefaultScrollSpeed() : checkSpeed(speed);
        float swipePercent = percent;
        while (swipePercent > 0.0f) {
            float segment = Math.min(swipePercent, 1.0f);
            PointerGesture swipe = gestures.swipe(area, swipeDirection, segment, scrollSpeed).pause(250);

            // Perform the gesture and return early if we reached the end
            if (performGestureAndWait(Until.scrollFinished(direction), Gestures.getScrollTimeout(), swipe)) {
                return false;
            }

            swipePercent -= 1.0f;
        }
        // We never reached the end
        return true;
    }

    public boolean fling(Rect area, Direction direction, @Nullable Integer speed) {
        ViewConfiguration vc = ViewConfiguration.get(getInstrumentation().getTargetContext());
        int minVelocity = vc.getScaledMinimumFlingVelocity();
        int flingSpeed = speed == null ? Gestures.getDefaultFlingSpeed() : speed;
        if (flingSpeed < minVelocity) {
            throw new IllegalArgumentException(String.format(
                    "Speed %s is less than the minimum fling velocity %s", speed, minVelocity));
        }

        // To fling, we swipe in the opposite direction
        Direction swipeDirection = Direction.reverse(direction);
        PointerGesture swipe = gestures.swipe(area, swipeDirection, 1.0f, flingSpeed);

        // Perform the gesture and return true if we did not reach the end
        return !performGestureAndWait(Until.scrollFinished(direction), Gestures.getFlingTimeout(), swipe);
    }
}
