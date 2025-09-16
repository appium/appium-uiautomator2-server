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

package io.appium.uiautomator2.utils.w3c;

import android.os.Build;
import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.LockSupport;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.settings.CurrentDisplayId;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.w3c.ActionHelpers.normalizeSequence;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.EVENT_INJECTION_DELAY_MS;

public class ActionsExecutor {
    private final KeyCharacterMap keyCharacterMap;
    private static final List<Integer> HOVERING_ACTIONS = Arrays.asList(
            MotionEvent.ACTION_HOVER_ENTER, MotionEvent.ACTION_HOVER_EXIT, MotionEvent.ACTION_HOVER_MOVE
    );
    private static final long NANOSECONDS_IN_MILLISECOND = 1000000;
    private final ActionTokens actionTokens;
    private final InteractionController interactionController;

    public ActionsExecutor(ActionTokens actionTokens) {
        this.actionTokens = actionTokens;
        this.keyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
        this.interactionController = UiAutomatorBridge.getInstance().getInteractionController();
    }

    private static MotionEvent.PointerProperties[] filterPointerProperties(
            final List<MotionInputEventParams> motionEventsParams, final boolean shouldHovering) {
        final List<MotionEvent.PointerProperties> result = new ArrayList<>();
        for (final MotionInputEventParams eventParams : motionEventsParams) {
            if (shouldHovering && HOVERING_ACTIONS.contains(eventParams.actionCode)
                    && eventParams.properties.toolType == MotionEvent.TOOL_TYPE_MOUSE) {
                result.add(eventParams.properties);
            } else if (!shouldHovering && !HOVERING_ACTIONS.contains(eventParams.actionCode)) {
                result.add(eventParams.properties);
            }
        }
        return result.toArray(new MotionEvent.PointerProperties[0]);
    }

    private static MotionEvent.PointerCoords[] filterPointerCoordinates(
            final List<MotionInputEventParams> motionEventsParams, final boolean shouldHovering) {
        final List<MotionEvent.PointerCoords> result = new ArrayList<>();
        for (final MotionInputEventParams eventParams : motionEventsParams) {
            if (shouldHovering && HOVERING_ACTIONS.contains(eventParams.actionCode) &&
                    eventParams.properties.toolType == MotionEvent.TOOL_TYPE_MOUSE) {
                result.add(eventParams.coordinates);
            } else if (!shouldHovering && !HOVERING_ACTIONS.contains(eventParams.actionCode)) {
                result.add(eventParams.coordinates);
            }
        }
        return result.toArray(new MotionEvent.PointerCoords[0]);
    }

    private static int metaKeysToState(final Set<Integer> metaKeys) {
        int result = 0;
        for (final int metaKey : metaKeys) {
            result |= metaKey;
        }
        return result;
    }

    private static void logEvent(Object event, long eventTime, boolean result) {
        Logger.info(String.format("[%s (%s)] Synthesized: %s", eventTime, result ? "success" : "fail", event.toString()));
        long currentTime = SystemClock.uptimeMillis();
        if (currentTime > eventTime + EVENT_INJECTION_DELAY_MS) {
            Logger.info(String.format("The event has been delayed for %sms", currentTime - eventTime));
        }
    }

    private boolean injectKeyEvent(KeyInputEventParams eventParam, long startTimestamp,
                                   Set<Integer> depressedMetaKeys) {
        final int keyCode = eventParam.keyCode;
        if (keyCode <= 0) {
            depressedMetaKeys.clear();
            return true;
        }
        final int keyAction = eventParam.keyAction;

        boolean result = true;
        final W3CKeyCode w3CKeyCode = W3CKeyCode.fromCodePoint(keyCode);
        if (w3CKeyCode == null) {
            final KeyEvent[] events = keyCharacterMap.getEvents(Character.toChars(keyCode));
            if (events == null) {
                throw new InvalidArgumentException(String.format(
                        "KeyCharacterMap.getEvents is unable to synthesize KeyEvent sequence out " +
                        "of '%s' key code. Consider applying a patch to UiAutomator2 server code or " +
                        "try to synthesize the necessary key event(s) for it manually", keyCode));
            }
            for (KeyEvent event : events) {
                if (event.getAction() == keyAction) {
                    final long eventTime = SystemClock.uptimeMillis();
                    final KeyEvent keyEvent = new KeyEvent(startTimestamp + eventParam.startDelta,
                            eventTime, keyAction, event.getKeyCode(), 0,
                            event.getMetaState() | metaKeysToState(depressedMetaKeys),
                            KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0);
                    result &= interactionController.injectEventSync(keyEvent);
                    logEvent(keyEvent, eventTime, result);
                }
            }
            return result;
        }

        final Integer metaCode = w3CKeyCode.toAndroidMetaKeyCode();
        if (metaCode != null) {
            if (keyAction == KeyEvent.ACTION_DOWN) {
                depressedMetaKeys.add(metaCode);
            } else {
                depressedMetaKeys.remove(metaCode);
            }
            return true;
        }

        final long eventTime = SystemClock.uptimeMillis();
        final KeyEvent keyEvent = new KeyEvent(startTimestamp + eventParam.startDelta,
                eventTime, keyAction, w3CKeyCode.getAndroidCodePoint(), 0,
                metaKeysToState(depressedMetaKeys), KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0);
        result = interactionController.injectEventSync(keyEvent);
        logEvent(keyEvent, eventTime, result);
        return result;
    }

    private boolean executeKeyEvents(List<KeyInputEventParams> events, long startTimestamp,
                                     Set<Integer> depressedMetaKeys) {
        boolean result = true;
        for (KeyInputEventParams event : events) {
            result &= injectKeyEvent(event, startTimestamp, depressedMetaKeys);
        }
        return result;
    }

    private static int toolTypeToInputSource(final int toolType) {
        switch (toolType) {
            case MotionEvent.TOOL_TYPE_MOUSE:
                return InputDevice.SOURCE_MOUSE;
            case MotionEvent.TOOL_TYPE_STYLUS:
                return InputDevice.SOURCE_STYLUS;
            default:
                return InputDevice.SOURCE_TOUCHSCREEN;
        }
    }

    private static int extractInputSource(List<MotionInputEventParams> events) {
        Set<Integer> result = new HashSet<>();
        for (MotionInputEventParams event : events) {
            result.add(toolTypeToInputSource(event.properties.toolType));
        }
        return result.iterator().next();
    }

    private static int getInitialPointersCount(List<MotionInputEventParams> events) {
        final Set<Integer> uniquePointerIds = new HashSet<>();
        for (MotionInputEventParams event : events) {
            if (event.actionCode == MotionEvent.ACTION_MOVE || event.actionCode == MotionEvent.ACTION_UP) {
                uniquePointerIds.add(event.properties.id);
            }
        }
        for (MotionInputEventParams event : events) {
            if (event.actionCode == MotionEvent.ACTION_DOWN) {
                uniquePointerIds.remove(event.properties.id);
            }
        }
        return uniquePointerIds.size();
    }

    private static int getPointerAction(int motionEvent, int index) {
        return motionEvent + (index << MotionEvent.ACTION_POINTER_INDEX_SHIFT);
    }

    private boolean executeMotionEvents(List<MotionInputEventParams> events, long startTimestamp, Set<Integer> depressedMetaKeys) {
        final MotionEvent.PointerProperties[] nonHoveringProps = filterPointerProperties(events, false);
        final MotionEvent.PointerProperties[] hoveringProps = filterPointerProperties(events, true);
        final MotionEvent.PointerCoords[] nonHoveringCoords = filterPointerCoordinates(events, false);
        final MotionEvent.PointerCoords[] hoveringCoords = filterPointerCoordinates(events, true);
        final int inputSource = extractInputSource(events);
        final int metaState = metaKeysToState(depressedMetaKeys);
        int pointersCount = getInitialPointersCount(events);
        boolean result = true;
        boolean isMoveActionTriggered = false;
        for (final MotionInputEventParams event : normalizeSequence(events)) {
            final int actionCode = event.actionCode;
            final long downTime = startTimestamp + event.startDelta;
            final long eventTime = SystemClock.uptimeMillis();
            MotionEvent synthesizedEvent = null;
            switch (actionCode) {
                case MotionEvent.ACTION_DOWN: {
                    final int action = ++pointersCount <= 1
                            ? MotionEvent.ACTION_DOWN
                            : getPointerAction(MotionEvent.ACTION_POINTER_DOWN, event.properties.id);
                    synthesizedEvent = MotionEvent.obtain(downTime, eventTime, action, pointersCount,
                            nonHoveringProps, nonHoveringCoords,
                            metaState, event.button, 1, 1, 0, 0, inputSource, 0);
                }
                break;
                case MotionEvent.ACTION_UP: {
                    final int action = pointersCount <= 1
                            ? MotionEvent.ACTION_UP
                            : getPointerAction(MotionEvent.ACTION_POINTER_UP, event.properties.id);
                    synthesizedEvent = MotionEvent.obtain(downTime, eventTime, action, pointersCount--,
                            nonHoveringProps, nonHoveringCoords,
                            metaState, event.button, 1, 1, 0, 0, inputSource, 0);
                }
                break;
                case MotionEvent.ACTION_MOVE: {
                    if (isMoveActionTriggered) {
                        break;
                    }
                    synthesizedEvent = MotionEvent.obtain(downTime, eventTime, actionCode, pointersCount,
                            nonHoveringProps, nonHoveringCoords,
                            metaState, event.button, 1, 1, 0, 0, inputSource, 0);
                    isMoveActionTriggered = true;
                }
                break;
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_HOVER_MOVE: {
                    synthesizedEvent = MotionEvent.obtain(downTime, eventTime, actionCode, 1,
                            hoveringProps, hoveringCoords,
                            metaState, 0, 1, 1, 0, 0, inputSource, 0);
                }
                break;
                default:
                    // do nothing
                    break;
            } // switch
            if (synthesizedEvent != null) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    final int displayId = Settings.get(CurrentDisplayId.class).getValue();
                    try {
                        // Method is marked as public with @hide in AOSP https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/MotionEvent.java;l=2419;drc=61197364367c9e404c7da6900658f1b16c42d0da;bpv=1;bpt=1?q=MotionEvent
                        invoke(getMethod(synthesizedEvent.getClass(), "setDisplayId", int.class), synthesizedEvent, displayId);
                    } catch (UiAutomator2Exception e) {
                        Logger.error("Unable to set displayId on motion event", e);
                    }
                }

                result &= interactionController.injectEventSync(synthesizedEvent);
                logEvent(synthesizedEvent, eventTime, result);
                synthesizedEvent.recycle();
            }
        }
        return result;
    }

    private static void sleepTillNextEvent(long nextEventTimestampNs) {
        // LockSupport.parkNanos is precise, unlike Thread.sleep
        LockSupport.parkNanos(nextEventTimestampNs - System.nanoTime());
    }

    public boolean execute() {
        if (actionTokens.isEmpty()) {
            return true;
        }

        boolean result = true;
        final Set<Integer> depressedMetaKeys = new HashSet<>();
        final long startTimestampMs = SystemClock.uptimeMillis();
        final long startTimestampNs = System.nanoTime();
        final long maxDeltaMs = actionTokens.maxTimeDelta();
        Logger.debug(String.format("Max actions chain time delta: %sms", maxDeltaMs));
        for (long currentDeltaMs = 0; currentDeltaMs <= maxDeltaMs; currentDeltaMs += EVENT_INJECTION_DELAY_MS) {
            final List<InputEventParams> events = actionTokens.eventsAt(currentDeltaMs);
            final long nextEventTimestampNs = (
                    startTimestampNs + (currentDeltaMs + EVENT_INJECTION_DELAY_MS) * NANOSECONDS_IN_MILLISECOND
            );
            if (events == null || events.isEmpty()) {
                sleepTillNextEvent(nextEventTimestampNs);
                continue;
            }

            final List<MotionInputEventParams> motionEvents = new ArrayList<>();
            final List<KeyInputEventParams> keyEvents = new ArrayList<>();
            for (final InputEventParams eventParam : events) {
                if (eventParam instanceof KeyInputEventParams) {
                    keyEvents.add((KeyInputEventParams) eventParam);
                } else if (eventParam instanceof MotionInputEventParams) {
                    motionEvents.add((MotionInputEventParams) eventParam);
                }
            }
            if (!keyEvents.isEmpty()) {
                result &= executeKeyEvents(keyEvents, startTimestampMs, depressedMetaKeys);
            }
            if (!motionEvents.isEmpty()) {
                result &= executeMotionEvents(motionEvents, startTimestampMs, depressedMetaKeys);
            }

            sleepTillNextEvent(nextEventTimestampNs);
        }
        return result;
    }
}
