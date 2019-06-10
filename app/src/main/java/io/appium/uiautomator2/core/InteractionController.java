/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appium.uiautomator2.core;

import android.view.InputEvent;
import android.view.MotionEvent.PointerCoords;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class InteractionController {

    public static final String METHOD_PERFORM_MULTI_POINTER_GESTURE = "performMultiPointerGesture";
    private static final String CLASS_INTERACTION_CONTROLLER = "androidx.test.uiautomator.InteractionController";
    private static final String METHOD_SEND_KEY = "sendKey";
    private static final String METHOD_INJECT_EVENT_SYNC = "injectEventSync";
    private static final String METHOD_TOUCH_DOWN = "touchDown";
    private static final String METHOD_TOUCH_UP = "touchUp";
    private static final String METHOD_TOUCH_MOVE = "touchMove";
    private static final String TRACK_SCROLL_EVENT_CAP = "trackScrollEvents";
    private final Object interactionController;

    public InteractionController(Object interactionController) {
        this.interactionController = interactionController;
    }

    public boolean sendKey(int keyCode, int metaState) throws UiAutomator2Exception {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER, METHOD_SEND_KEY, int.class, int.class),
                interactionController, keyCode, metaState);
    }

    public boolean injectEventSync(final InputEvent event, boolean shouldRegister) throws UiAutomator2Exception {
        if (!shouldRegister) {
            return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER,
                    METHOD_INJECT_EVENT_SYNC, InputEvent.class), interactionController, event);
        }
        return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                Boolean result = (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER,
                        METHOD_INJECT_EVENT_SYNC, InputEvent.class), interactionController, event);
                setResult(result);
            }
        });
    }

    public boolean injectEventSync(final InputEvent event) throws UiAutomator2Exception {
        return injectEventSync(event, true);
    }

    public boolean shouldTrackScrollEvents() {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        Boolean trackScrollEvents = true;
        if (session.hasCapability(TRACK_SCROLL_EVENT_CAP)) {
          try {
              trackScrollEvents = (Boolean) session.getCapability(TRACK_SCROLL_EVENT_CAP);
              Logger.error("Capability '" + TRACK_SCROLL_EVENT_CAP + "': " + trackScrollEvents);
          } catch (Exception e) {
              Logger.error("Could not set '" + TRACK_SCROLL_EVENT_CAP + "' from capability: ", e);
              trackScrollEvents = true;
          }
        }

        return trackScrollEvents;
    }

    private boolean doTouchDown(final int x, final int y) {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER,
                METHOD_TOUCH_DOWN, int.class, int.class), interactionController, x, y);
    }

    public boolean touchDown(final int x, final int y) throws UiAutomator2Exception {
        if (shouldTrackScrollEvents()) {
            return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
                @Override
                public void run() {
                    setResult(doTouchDown(x, y));
                }
            });
        } else {
            return doTouchDown(x, y);
        }
    }

    private boolean doTouchUp(final int x, final int y) {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER, METHOD_TOUCH_UP,
                int.class, int.class), interactionController, x, y);
    }

    public boolean touchUp(final int x, final int y) throws UiAutomator2Exception {
        if (shouldTrackScrollEvents()) {
            return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
                @Override
                public void run() {
                    setResult(doTouchUp(x, y));
                }
            });
      } else {
        return doTouchUp(x, y);
      }
    }

    private boolean doTouchMove(final int x, final int y) {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER,
                METHOD_TOUCH_MOVE, int.class, int.class), interactionController, x, y);
    }

    public boolean touchMove(final int x, final int y) throws UiAutomator2Exception {
        if (shouldTrackScrollEvents()) {
            return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
                @Override
                public void run() {;
                    setResult(doTouchMove(x, y));
                }
            });
        } else {
            return doTouchMove(x, y);
        }
    }

    private boolean doPerformMultiPointerGesture(final PointerCoords[][] pcs) {
        return (Boolean) invoke(method(CLASS_INTERACTION_CONTROLLER,
                METHOD_PERFORM_MULTI_POINTER_GESTURE, PointerCoords[][].class),
                interactionController, (Object) pcs);
    }

    public Boolean performMultiPointerGesture(final PointerCoords[][] pcs) throws UiAutomator2Exception {
        if (shouldTrackScrollEvents()) {
            return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
                @Override
                public void run() {
                    setResult(doPerformMultiPointerGesture(pcs));
                }
            });
        } else {
            return doPerformMultiPointerGesture(pcs);
        }
    }
}
