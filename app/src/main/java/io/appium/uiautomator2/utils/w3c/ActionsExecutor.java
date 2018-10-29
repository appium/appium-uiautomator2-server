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

import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.InteractionUtils.injectEventSync;
import static io.appium.uiautomator2.utils.w3c.ActionHelpers.normalizeSequence;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.EVENT_INJECTION_DELAY_MS;

public class ActionsExecutor {
    private final KeyCharacterMap keyCharacterMap;
    private static final List<Integer> HOVERING_ACTIONS = Arrays.asList(
            MotionEvent.ACTION_HOVER_ENTER, MotionEvent.ACTION_HOVER_EXIT, MotionEvent.ACTION_HOVER_MOVE
    );
    private final ActionTokens actionTokens;

    public ActionsExecutor(ActionTokens actionTokens) {
        this.actionTokens = actionTokens;
        this.keyCharacterMap = KeyCharacterMap.load(KeyCharacterMap.VIRTUAL_KEYBOARD);
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
            for (KeyEvent event : events) {
                if (event.getAction() == keyAction) {
                    final KeyEvent keyEvent = new KeyEvent(startTimestamp + eventParam.startDelta,
                            SystemClock.uptimeMillis(), keyAction, event.getKeyCode(), 0,
                            event.getMetaState() | metaKeysToState(depressedMetaKeys),
                            KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0);
                    result &= injectEventSync(keyEvent);
                    Logger.debug(String.format("[%s (%s)] Synthesized: %s", startTimestamp + eventParam.startDelta,
                            result ? "success" : "fail", keyEvent.toString()));
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

        final KeyEvent keyEvent = new KeyEvent(startTimestamp + eventParam.startDelta,
                SystemClock.uptimeMillis(), keyAction, w3CKeyCode.getAndroidCodePoint(), 0,
                metaKeysToState(depressedMetaKeys), KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0);
        result = injectEventSync(keyEvent);
        Logger.debug(String.format("[%s (%s)] Synthesized: %s", startTimestamp + eventParam.startDelta,
                result ? "success" : "fail", keyEvent.toString()));
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
            case MotionEvent.TOOL_TYPE_FINGER:
                return InputDevice.SOURCE_TOUCHSCREEN;
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

    private static int getActionsCount(@SuppressWarnings("SameParameterValue") int action,
                                       List<MotionInputEventParams> motionEventsParams) {
        int result = 0;
        for (MotionInputEventParams params : motionEventsParams) {
            if (params.actionCode == action) {
                result++;
            }
        }
        return result;
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
        int pointersCount = nonHoveringProps.length - getActionsCount(MotionEvent.ACTION_DOWN, events);
        boolean result = true;
        boolean isMoveActionTriggered = false;
        for (final MotionInputEventParams event : normalizeSequence(events)) {
            final int actionCode = event.actionCode;
            MotionEvent synthesizedEvent = null;
            switch (actionCode) {
                case MotionEvent.ACTION_DOWN: {
                    final int action = ++pointersCount <= 1
                            ? MotionEvent.ACTION_DOWN
                            : getPointerAction(MotionEvent.ACTION_POINTER_DOWN, event.properties.id);
                    synthesizedEvent = MotionEvent.obtain(startTimestamp + event.startDelta,
                            SystemClock.uptimeMillis(), action, pointersCount, nonHoveringProps, nonHoveringCoords,
                            metaKeysToState(depressedMetaKeys), event.button,
                            1, 1, 0, 0, inputSource, 0);
                }
                break;
                case MotionEvent.ACTION_UP: {
                    final int action = pointersCount <= 1
                            ? MotionEvent.ACTION_UP
                            : getPointerAction(MotionEvent.ACTION_POINTER_UP, event.properties.id);
                    synthesizedEvent = MotionEvent.obtain(startTimestamp + event.startDelta,
                            SystemClock.uptimeMillis(), action, pointersCount--, nonHoveringProps, nonHoveringCoords,
                            metaKeysToState(depressedMetaKeys), event.button,
                            1, 1, 0, 0, inputSource, 0);
                }
                break;
                case MotionEvent.ACTION_MOVE: {
                    if (isMoveActionTriggered) {
                        break;
                    }
                    synthesizedEvent = MotionEvent.obtain(startTimestamp + event.startDelta,
                            SystemClock.uptimeMillis(), actionCode, pointersCount, nonHoveringProps, nonHoveringCoords,
                            metaKeysToState(depressedMetaKeys), event.button,
                            1, 1, 0, 0, inputSource, 0);
                    isMoveActionTriggered = true;
                }
                break;
                case MotionEvent.ACTION_HOVER_ENTER:
                case MotionEvent.ACTION_HOVER_EXIT:
                case MotionEvent.ACTION_HOVER_MOVE: {
                    synthesizedEvent = MotionEvent.obtain(startTimestamp + event.startDelta,
                            SystemClock.uptimeMillis(), actionCode, 1, hoveringProps, hoveringCoords,
                            metaKeysToState(depressedMetaKeys), 0,
                            1, 1, 0, 0, inputSource, 0);
                }
                break;
                default:
                    // do nothing
                    break;
            } // switch
            if (synthesizedEvent != null) {
                result &= injectEventSync(synthesizedEvent);
                Logger.debug(String.format("[%s (%s)] Synthesized %s", synthesizedEvent.getDownTime(),
                        result ? "success" : "fail", synthesizedEvent.toString()));
            }
        }
        return result;
    }

    public boolean execute() {
        if (actionTokens.isEmpty()) {
            return true;
        }

        boolean result = true;
        final Set<Integer> depressedMetaKeys = new HashSet<>();
        final long startTimestamp = SystemClock.uptimeMillis();
        for (long currentDelta = 0; currentDelta <= actionTokens.maxTimeDelta(); currentDelta += EVENT_INJECTION_DELAY_MS) {
            final List<InputEventParams> events = actionTokens.eventsAt(currentDelta);
            if (events == null || events.isEmpty()) {
                SystemClock.sleep(EVENT_INJECTION_DELAY_MS);
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
                result &= executeKeyEvents(keyEvents, startTimestamp, depressedMetaKeys);
            }
            if (!motionEvents.isEmpty()) {
                result &= executeMotionEvents(motionEvents, startTimestamp, depressedMetaKeys);
            }

            SystemClock.sleep(EVENT_INJECTION_DELAY_MS);
        }
        return result;
    }
}
