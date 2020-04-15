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

import android.graphics.Rect;
import android.os.Build;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.google.gson.JsonSyntaxException;
import io.appium.uiautomator2.model.api.ElementModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CGestureModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CItemModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;

import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_DURATION_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_ORIGIN_POINTER;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_ORIGIN_VIEWPORT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY_DOWN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY_UP;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_PAUSE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_DOWN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_MOVE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_UP;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_VALUE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_NONE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_POINTER;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.EVENT_INJECTION_DELAY_MS;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_LEFT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_MIDDLE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_RIGHT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.PARAMETERS_KEY_POINTER_TYPE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_MOUSE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_PEN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_TOUCH;

public class ActionsTokenizer {
    private ActionTokens tokenizedActions;

    private static long alignDuration(long ms) {
        if (ms <= 0) {
            return 0;
        }
        if (ms < EVENT_INJECTION_DELAY_MS) {
            return EVENT_INJECTION_DELAY_MS;
        }
        final long modResult = ms % EVENT_INJECTION_DELAY_MS;
        return modResult == 0 ? ms : ms + EVENT_INJECTION_DELAY_MS - modResult;
    }

    private static List<W3CItemModel> filterActionsByType(final List<W3CItemModel> items,
                                                          final String type) {
        final List<W3CItemModel> result = new ArrayList<>();
        for (final W3CItemModel item : items) {
            if (type.equals(item.type)) {
                result.add(item);
            }
        }
        return result;
    }

    private static int extractButton(final W3CGestureModel gesture, final int toolType) {
        if (toolType == MotionEvent.TOOL_TYPE_FINGER) {
            // Ignore button code conversion for the unsupported tool type
            return gesture.button == null ? 0 : gesture.button;
        }

        int button = gesture.button == null ? MOUSE_BUTTON_LEFT : gesture.button;
        // W3C button codes are different from Android constants. Converting...
        switch (button) {
            case MOUSE_BUTTON_LEFT:
                if (toolType == MotionEvent.TOOL_TYPE_STYLUS && Build.VERSION.SDK_INT >= 23) {
                    return MotionEvent.BUTTON_STYLUS_PRIMARY;
                }
                return MotionEvent.BUTTON_PRIMARY;
            case MOUSE_BUTTON_MIDDLE:
                return MotionEvent.BUTTON_TERTIARY;
            case MOUSE_BUTTON_RIGHT:
                if (toolType == MotionEvent.TOOL_TYPE_STYLUS && Build.VERSION.SDK_INT >= 23) {
                    return MotionEvent.BUTTON_STYLUS_SECONDARY;
                }
                return MotionEvent.BUTTON_SECONDARY;
        }
        return button;
    }


    private static long extractDuration(final W3CItemModel item, final W3CGestureModel gesture) {
        if (gesture.duration == null) {
            throw new ActionsParseException(String.format(
                    "Missing %s key for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, gesture, item.id));
        }
        if (gesture.duration < 0) {
            throw new ActionsParseException(String.format(
                    "%s key cannot be negative for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, gesture, item.id));
        }
        return gesture.duration;
    }

    @Nullable
    private Long findEntryPointDeltaForSecondaryAction(long timeDeltaMs) {
        Long result = null;
        int upDownBalance = 0;
        for (int i = 0; i < tokenizedActions.size(); ++i) {
            final long currentDelta = tokenizedActions.timeDeltaAt(i);
            if (currentDelta > timeDeltaMs) {
                break;
            }

            final List<InputEventParams> allParams = tokenizedActions.eventsAt(currentDelta);
            if (allParams == null || allParams.isEmpty()) {
                continue;
            }

            for (InputEventParams params : allParams) {
                if (!(params instanceof MotionInputEventParams)) {
                    continue;
                }
                final MotionInputEventParams motionParams = (MotionInputEventParams) params;
                if (motionParams.actionCode == MotionEvent.ACTION_DOWN) {
                    if (upDownBalance == 0) {
                        result = currentDelta;
                    }
                    upDownBalance++;
                } else if (motionParams.actionCode == MotionEvent.ACTION_UP) {
                    upDownBalance--;
                }
            }
        }

        return upDownBalance > 1 ? result : null;
    }

    private static int actionToToolType(final W3CItemModel item) {
        if (item.parameters != null) {
            if (item.parameters.pointerType != null) {
                switch (item.parameters.pointerType) {
                    case POINTER_TYPE_MOUSE:
                        return MotionEvent.TOOL_TYPE_MOUSE;
                    case POINTER_TYPE_PEN:
                        return MotionEvent.TOOL_TYPE_STYLUS;
                    case POINTER_TYPE_TOUCH:
                        return MotionEvent.TOOL_TYPE_FINGER;
                    default:
                        // use default
                        break;
                }
            }
        }
        return MotionEvent.TOOL_TYPE_FINGER;
    }

    private static MotionEvent.PointerCoords extractElementCoordinates(
            final String actionId, final W3CGestureModel gesture, final Object originValue) {
        String elementId = null;
        if (originValue instanceof String) {
            elementId = (String) originValue;
        } else if (originValue instanceof Map) {
            // It's how this is defined in WebDriver source:
            //
            // if isinstance(origin, WebElement):
            //    action["origin"] = {"element-6066-11e4-a52e-4f735466cecf": origin.id}
            //noinspection unchecked
            elementId = new ElementModel((Map<String, Object>) originValue).getUnifiedId();
        }
        if (elementId == null) {
            throw new ActionsParseException(String.format(
                    "An unknown element '%s' is set for action item '%s' of action '%s'",
                    originValue, gesture, actionId));
        }
        final MotionEvent.PointerCoords result = new MotionEvent.PointerCoords();
        Rect bounds;
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        try {
            final AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            //noinspection ConstantConditions
            bounds = element.getBounds();
            if (bounds.width() == 0 || bounds.height() == 0) {
                throw new ActionsParseException(String.format(
                        "The element with id '%s' has zero width/height in the action item '%s' of action '%s'",
                        elementId, gesture, actionId));
            }
        } catch (NullPointerException | UiObjectNotFoundException e) {
            throw new ActionsParseException(String.format(
                    "An unknown element id '%s' is set for the action item '%s' of action '%s'",
                    elementId, gesture, actionId));
        }
        // https://w3c.github.io/webdriver/webdriver-spec.html#pointer-actions
        // > Let x element and y element be the result of calculating the in-view center point of element.
        result.x = bounds.left + bounds.width() / 2.0f;
        result.y = bounds.top + bounds.height() / 2.0f;
        if (gesture.x != null) {
            result.x += gesture.x.floatValue();
            // TODO: Shall we throw an exception if result.x is outside of bounds rect?
        }
        if (gesture.y != null) {
            result.y += gesture.y.floatValue();
            // TODO: Shall we throw an exception if result.y is outside of bounds rect?
        }
        return result;
    }

    private static MotionEvent.PointerCoords extractCoordinates(final String actionId,
                                                                final List<W3CGestureModel> gestures,
                                                                final int itemIdx) {
        if (itemIdx < 0) {
            throw new ActionsParseException(String.format(
                    "The first item of action '%s' cannot define HOVER move, " +
                            "because its start coordinates are not set", actionId));
        }
        final W3CGestureModel gesture = gestures.get(itemIdx);
        if (!ACTION_ITEM_TYPE_POINTER_MOVE.equals(gesture.type)) {
            if (itemIdx > 0) {
                return extractCoordinates(actionId, gestures, itemIdx - 1);
            }
            throw new ActionsParseException(String.format(
                    "Action item '%s' of action '%s' should be preceded with at least one item " +
                            "with coordinates", gesture, actionId));
        }
        Object origin = gesture.origin == null ? ACTION_ITEM_ORIGIN_VIEWPORT : gesture.origin;
        final MotionEvent.PointerCoords result = new MotionEvent.PointerCoords();
        result.size = gesture.size == null ? 1 : gesture.size.floatValue();
        result.pressure = gesture.pressure == null ? 1 : gesture.pressure.floatValue();
        if (origin instanceof String) {
            if (origin.equals(ACTION_ITEM_ORIGIN_VIEWPORT)) {
                if (gesture.x == null || gesture.y == null) {
                    throw new ActionsParseException(String.format(
                            "Both coordinates must be be set for action item '%s' of action '%s'",
                            gesture, actionId));
                }
                result.x = gesture.x.floatValue();
                result.y = gesture.y.floatValue();
                return result;
            } else if (origin.equals(ACTION_ITEM_ORIGIN_POINTER)) {
                if (itemIdx > 0) {
                    final MotionEvent.PointerCoords recentCoords = extractCoordinates(actionId, gestures, itemIdx - 1);
                    result.x = recentCoords.x;
                    result.y = recentCoords.y;
                    if (gesture.x != null) {
                        result.x += gesture.x.floatValue();
                    }
                    if (gesture.y != null) {
                        result.y += gesture.y.floatValue();
                    }
                    return result;
                }
                throw new ActionsParseException(String.format(
                        "Action item '%s' of action '%s' should be preceded with at least one item " +
                                "containing absolute coordinates", gesture, actionId));
            }
        }
        return extractElementCoordinates(actionId, gesture, origin);
    }

    private void recordEventParams(long timeDeltaMs, @Nullable final InputEventParams newParam) {
        if (newParam instanceof MotionInputEventParams) {
            final MotionInputEventParams motionParams = (MotionInputEventParams) newParam;
            if (motionParams.actionCode == MotionEvent.ACTION_UP
                    || motionParams.actionCode == MotionEvent.ACTION_DOWN
                    || motionParams.actionCode == MotionEvent.ACTION_MOVE) {
                Long entryPointDelta = findEntryPointDeltaForSecondaryAction(timeDeltaMs);
                if (entryPointDelta != null) {
                    // The entry point for secondary up/down and move actions
                    // will always be the timestamp
                    // of the very first touch down action in the chain
                    motionParams.startDelta = entryPointDelta;
                }
            }
        }
        tokenizedActions.addEventAt(timeDeltaMs, newParam);
    }

    private void applyEmptyActionToEventsMapping(final W3CItemModel item) {
        final List<W3CGestureModel> gestures = item.actions;
        long timeDelta = 0;
        for (final W3CGestureModel gesture : gestures) {
            if (!ACTION_ITEM_TYPE_PAUSE.equals(gesture.type)) {
                throw new ActionsParseException(String.format(
                        "Unexpected action item %s '%s' in action with id '%s'",
                        ACTION_ITEM_TYPE_KEY, gesture.type, item.id));
            }
            timeDelta += alignDuration(extractDuration(item, gesture));
            recordEventParams(timeDelta, null);
        }
    }

    private void applyKeyActionToEventsMapping(final W3CItemModel item) {
        final List<W3CGestureModel> gestures = item.actions;
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        for (final W3CGestureModel gesture : gestures) {
            switch (gesture.type) {
                case ACTION_ITEM_TYPE_PAUSE:
                    timeDelta += alignDuration(extractDuration(item, gesture));
                    recordEventParams(timeDelta, null);
                    break;
                case ACTION_ITEM_TYPE_KEY_DOWN:
                    chainEntryPointDelta = timeDelta;
                case ACTION_ITEM_TYPE_KEY_UP:
                    if (gesture.value == null) {
                        throw new ActionsParseException(String.format(
                                "Missing %s key for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, gesture, item.id));
                    }
                    if (gesture.value.isEmpty()) {
                        throw new ActionsParseException(String.format(
                                "%s key cannot be empty for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, gesture, item.id));
                    }
                    final KeyInputEventParams evtParams = new KeyInputEventParams(
                            chainEntryPointDelta,
                            gesture.type.equals(ACTION_ITEM_TYPE_KEY_DOWN)
                                    ? KeyEvent.ACTION_DOWN
                                    : KeyEvent.ACTION_UP,
                            gesture.value.codePointAt(0)
                    );
                    recordEventParams(timeDelta, evtParams);
                    chainEntryPointDelta = timeDelta;
                    break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, gesture.type, item.id));
            }
        }
    }

    private static void assertPointersCount(int toolType, int pointerIndex) {
        if (toolType == MotionEvent.TOOL_TYPE_MOUSE && pointerIndex > 0) {
            throw new ActionsParseException(
                    String.format("No more that one simultaneous pointer is supported for %s %s",
                            PARAMETERS_KEY_POINTER_TYPE, POINTER_TYPE_MOUSE));
        }
        if (toolType == MotionEvent.TOOL_TYPE_STYLUS && pointerIndex > 0) {
            throw new ActionsParseException(
                    String.format("No more that one simultaneous pointer is supported for %s %s",
                            PARAMETERS_KEY_POINTER_TYPE, POINTER_TYPE_PEN));
        }
    }

    private void applyPointerActionToEventsMapping(final W3CItemModel item, final int pointerIndex) {
        final String actionId = item.id;
        final MotionEvent.PointerProperties props = new MotionEvent.PointerProperties();
        props.id = pointerIndex;
        props.toolType = actionToToolType(item);
        assertPointersCount(props.toolType, pointerIndex);

        final boolean isToolTypeMouse = props.toolType == MotionEvent.TOOL_TYPE_MOUSE;
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        long recentUpDelta = -1;
        long recentDownDelta = -1;
        boolean isPointerDown = false;
        boolean isHovering = false;
        int recentButton = 0;
        final List<W3CGestureModel> gestures = item.actions;
        for (int actionItemIdx = 0; actionItemIdx < gestures.size(); actionItemIdx++) {
            final W3CGestureModel gesture = gestures.get(actionItemIdx);
            switch (gesture.type) {
                case ACTION_ITEM_TYPE_PAUSE: {
                    timeDelta += alignDuration(extractDuration(item, gesture));
                    recordEventParams(timeDelta, null);
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_DOWN: {
                    if (isPointerDown || recentDownDelta == timeDelta) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform two or more '%s' actions without a pause between them at " +
                                        "%sms in '%s' chain", gesture.type, timeDelta, actionId));
                    }

                    chainEntryPointDelta = timeDelta;
                    recentButton = extractButton(gesture, props.toolType);
                    recordEventParams(timeDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_DOWN,
                            extractCoordinates(actionId, gestures, actionItemIdx), recentButton, props));
                    isPointerDown = true;
                    recentDownDelta = timeDelta;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_UP: {
                    if (!isPointerDown) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform '%s' action without performing '%s' first at " +
                                        "%sms in '%s' chain", gesture.type, ACTION_ITEM_TYPE_POINTER_DOWN, timeDelta, actionId));
                    }
                    if (recentUpDelta == timeDelta) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform two or more '%s' actions without a pause between them at " +
                                        "%sms in '%s' chain", gesture.type, timeDelta, actionId));
                    }

                    recentButton = extractButton(gesture, props.toolType);
                    recordEventParams(timeDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_UP,
                            extractCoordinates(actionId, gestures, actionItemIdx), recentButton, props));
                    isPointerDown = false;
                    recentButton = 0;
                    chainEntryPointDelta = timeDelta;
                    recentUpDelta = timeDelta;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_MOVE: {
                    long duration = alignDuration(extractDuration(item, gesture));
                    if (duration < EVENT_INJECTION_DELAY_MS) {
                        break;
                    }
                    if (actionItemIdx == 0) {
                        // Selenium client sets the default move duration
                        // to 250 ms, but it won't work if this is the very first
                        // action item, since gesture start coordinate is undefined.
                        // It would be better to set the default duration to zero.
                        timeDelta += duration;
                        recordEventParams(timeDelta, null);
                        break;
                    }
                    int actionCode = !isPointerDown && isToolTypeMouse
                            ? MotionEvent.ACTION_HOVER_MOVE
                            : MotionEvent.ACTION_MOVE;
                    final MotionEvent.PointerCoords startCoordinates = extractCoordinates(actionId, gestures, actionItemIdx - 1);
                    final MotionEvent.PointerCoords endCoordinates = extractCoordinates(actionId, gestures, actionItemIdx);

                    final long startDelta = timeDelta;
                    final long firstActionDelta = recentDownDelta == startDelta || recentUpDelta == startDelta
                            ? startDelta + EVENT_INJECTION_DELAY_MS : startDelta;
                    long stepsCount = (startDelta + duration - firstActionDelta) / EVENT_INJECTION_DELAY_MS;
                    if (actionCode == MotionEvent.ACTION_HOVER_MOVE && !isHovering) {
                        recordEventParams(firstActionDelta, new MotionInputEventParams(
                                firstActionDelta, MotionEvent.ACTION_HOVER_ENTER, startCoordinates, 0, props));
                        isHovering = true;
                        if (stepsCount < 3) {
                            // Hover gesture should also include enter and exit
                            // events, so we must book enough time for these
                            stepsCount = 3;
                            duration = EVENT_INJECTION_DELAY_MS * 3;
                        }
                    } else {
                        recordEventParams(firstActionDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_MOVE,
                                stepsCount <= 1 ? endCoordinates : startCoordinates, recentButton, props));
                    }
                    timeDelta = firstActionDelta + EVENT_INJECTION_DELAY_MS;

                    for (long step = 2; step <= stepsCount; step++) {
                        final MotionEvent.PointerCoords currentCoordinates = new MotionEvent.PointerCoords();
                        currentCoordinates.x = startCoordinates.x + (endCoordinates.x - startCoordinates.x) / stepsCount * step;
                        currentCoordinates.y = startCoordinates.y + (endCoordinates.y - startCoordinates.y) / stepsCount * step;
                        if (step == stepsCount && isHovering) {
                            recordEventParams(timeDelta, new MotionInputEventParams(
                                    firstActionDelta, MotionEvent.ACTION_HOVER_EXIT, endCoordinates, 0, props));
                            isHovering = false;
                        } else {
                            recordEventParams(timeDelta, new MotionInputEventParams(
                                    chainEntryPointDelta, actionCode, currentCoordinates, recentButton, props));
                        }
                        timeDelta += EVENT_INJECTION_DELAY_MS;
                    }
                    timeDelta = startDelta + duration;
                }
                break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, gesture.type, actionId));
            }
        }
    }

    /**
     * Parses actions JSON and transforms it into timeflow mapping,
     * where keys are timestamps in milliseconds and values are lists
     * of corresponding action properties. All events on this timeflow are
     * aligned by 5ms interval
     *
     * @param preprocessedItems a valid W3C actions chain
     * @return tokenized chain of events
     * @throws JsonSyntaxException if the given json has invalid format
     * @throws ActionsParseException if the given actions chain cannot be tokenized properly
     */
    public ActionTokens tokenize(List<W3CItemModel> preprocessedItems) {
        tokenizedActions = new ActionTokens();

        final List<W3CItemModel> emptyActions = filterActionsByType(preprocessedItems, ACTION_TYPE_NONE);
        for (final W3CItemModel emptyAction : emptyActions) {
            applyEmptyActionToEventsMapping(emptyAction);
        }

        final List<W3CItemModel> keyInputActions = filterActionsByType(preprocessedItems, ACTION_TYPE_KEY);
        for (final W3CItemModel keyAction : keyInputActions) {
            applyKeyActionToEventsMapping(keyAction);
        }

        final List<W3CItemModel> pointerActions = filterActionsByType(preprocessedItems, ACTION_TYPE_POINTER);
        for (int pointerIdx = 0; pointerIdx < pointerActions.size(); pointerIdx++) {
            applyPointerActionToEventsMapping(pointerActions.get(pointerIdx), pointerIdx);
        }

        return tokenizedActions;
    }
}
