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
import android.support.annotation.Nullable;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.view.KeyEvent;
import android.view.MotionEvent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;

import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_BUTTON_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_DURATION_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_ORIGIN_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_ORIGIN_POINTER;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_ORIGIN_VIEWPORT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_PRESSURE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_SIZE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY_DOWN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY_UP;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_PAUSE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_DOWN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_MOVE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_UP;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_VALUE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_X_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_Y_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_ACTIONS;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_ID;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_PARAMETERS;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_TYPE;
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

    private static List<JSONObject> filterActionsByType(final JSONArray actions,
                                                        final String type) throws JSONException {
        final List<JSONObject> result = new ArrayList<>();
        for (int i = 0; i < actions.length(); i++) {
            final JSONObject action = actions.getJSONObject(i);
            final String actionType = action.getString(ACTION_KEY_TYPE);
            if (actionType.equals(type)) {
                result.add(action);
            }
        }
        return result;
    }

    private static int extractButton(final JSONObject actionItem, final int toolType)
            throws JSONException {
        if (toolType == MotionEvent.TOOL_TYPE_FINGER) {
            // Ignore button code conversion for the unsupported tool type
            if (actionItem.has(ACTION_ITEM_BUTTON_KEY)) {
                return actionItem.getInt(ACTION_ITEM_BUTTON_KEY);
            }
            return 0;
        }

        int button = MOUSE_BUTTON_LEFT;
        if (actionItem.has(ACTION_ITEM_BUTTON_KEY)) {
            button = actionItem.getInt(ACTION_ITEM_BUTTON_KEY);
        }
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


    private static long extractDuration(final JSONObject action,
                                        final JSONObject actionItem) throws JSONException {
        if (!actionItem.has(ACTION_ITEM_DURATION_KEY)) {
            throw new ActionsParseException(String.format(
                    "Missing %s key for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, actionItem, action.getString(ACTION_KEY_ID)));
        }
        final long duration = actionItem.getLong(ACTION_ITEM_DURATION_KEY);
        if (duration < 0) {
            throw new ActionsParseException(String.format(
                    "%s key cannot be negative for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, actionItem, action.getString(ACTION_KEY_ID)));
        }
        return duration;
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

    private static int actionToToolType(final JSONObject action) throws JSONException {
        if (action.has(ACTION_KEY_PARAMETERS)) {
            final JSONObject params = action.getJSONObject(ACTION_KEY_PARAMETERS);
            if (params.has(PARAMETERS_KEY_POINTER_TYPE)) {
                switch (params.getString(PARAMETERS_KEY_POINTER_TYPE)) {
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
            final String actionId, final JSONObject actionItem, final Object originValue)
            throws JSONException {
        String elementId = null;
        if (originValue instanceof String) {
            elementId = (String) originValue;
        } else if (originValue instanceof JSONObject) {
            // It's how this is defined in WebDriver source:
            //
            // if isinstance(origin, WebElement):
            //    action["origin"] = {"element-6066-11e4-a52e-4f735466cecf": origin.id}
            final Iterator<String> keys = ((JSONObject) originValue).keys();
            if (keys.hasNext()) {
                final String name = keys.next();
                if (name.toLowerCase().startsWith("element")) {
                    elementId = String.valueOf(((JSONObject) originValue).get(name));
                }
            }
        }
        if (elementId == null) {
            throw new ActionsParseException(String.format(
                    "An unknown element '%s' is set for action item '%s' of action '%s'",
                    originValue, actionItem, actionId));
        }
        final MotionEvent.PointerCoords result = new MotionEvent.PointerCoords();
        Rect bounds;
        try {
            final AndroidElement element = KnownElements.getElementFromCache(elementId);
            //noinspection ConstantConditions
            bounds = element.getBounds();
            if (bounds.width() == 0 || bounds.height() == 0) {
                throw new ActionsParseException(String.format(
                        "The element with id '%s' has zero width/height in the action item '%s' of action '%s'",
                        elementId, actionItem, actionId));
            }
        } catch (NullPointerException | UiObjectNotFoundException e) {
            throw new ActionsParseException(String.format(
                    "An unknown element id '%s' is set for the action item '%s' of action '%s'",
                    elementId, actionItem, actionId));
        }
        // https://w3c.github.io/webdriver/webdriver-spec.html#pointer-actions
        // > Let x element and y element be the result of calculating the in-view center point of element.
        result.x = bounds.left + bounds.width() / 2;
        result.y = bounds.top + bounds.height() / 2;
        if (actionItem.has(ACTION_ITEM_X_KEY)) {
            result.x += (float) actionItem.getDouble(ACTION_ITEM_X_KEY);
            // TODO: Shall we throw an exception if result.x is outside of bounds rect?
        }
        if (actionItem.has(ACTION_ITEM_Y_KEY)) {
            result.y += (float) actionItem.getDouble(ACTION_ITEM_Y_KEY);
            // TODO: Shall we throw an exception if result.y is outside of bounds rect?
        }
        return result;
    }

    private static MotionEvent.PointerCoords extractCoordinates(final String actionId, final JSONArray allItems,
                                                                final int itemIdx) throws JSONException {
        if (itemIdx < 0) {
            throw new ActionsParseException(String.format(
                    "The first item of action '%s' cannot define HOVER move, " +
                            "because its start coordinates are not set", actionId));
        }
        final JSONObject actionItem = allItems.getJSONObject(itemIdx);
        final String actionType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
        if (!actionType.equals(ACTION_ITEM_TYPE_POINTER_MOVE)) {
            if (itemIdx > 0) {
                return extractCoordinates(actionId, allItems, itemIdx - 1);
            }
            throw new ActionsParseException(String.format(
                    "Action item '%s' of action '%s' should be preceded with at least one item " +
                            "with coordinates", actionItem, actionId));
        }
        Object origin = ACTION_ITEM_ORIGIN_VIEWPORT;
        if (actionItem.has(ACTION_ITEM_ORIGIN_KEY)) {
            origin = actionItem.get(ACTION_ITEM_ORIGIN_KEY);
        }
        final MotionEvent.PointerCoords result = new MotionEvent.PointerCoords();
        result.size = actionItem.has(ACTION_ITEM_SIZE_KEY) ?
                (float) actionItem.getDouble(ACTION_ITEM_SIZE_KEY) : 1;
        result.pressure = actionItem.has(ACTION_ITEM_PRESSURE_KEY) ?
                (float) actionItem.getDouble(ACTION_ITEM_PRESSURE_KEY) : 1;
        if (origin instanceof String) {
            if (origin.equals(ACTION_ITEM_ORIGIN_VIEWPORT)) {
                if (!actionItem.has(ACTION_ITEM_X_KEY) || !actionItem.has(ACTION_ITEM_Y_KEY)) {
                    throw new ActionsParseException(String.format(
                            "Both coordinates must be be set for action item '%s' of action '%s'",
                            actionItem, actionId));
                }
                result.x = (float) actionItem.getDouble(ACTION_ITEM_X_KEY);
                result.y = (float) actionItem.getDouble(ACTION_ITEM_Y_KEY);
                return result;
            } else if (origin.equals(ACTION_ITEM_ORIGIN_POINTER)) {
                if (itemIdx > 0) {
                    final MotionEvent.PointerCoords recentCoords = extractCoordinates(actionId, allItems, itemIdx - 1);
                    result.x = recentCoords.x;
                    result.y = recentCoords.y;
                    if (actionItem.has(ACTION_ITEM_X_KEY)) {
                        result.x += (float) actionItem.getDouble(ACTION_ITEM_X_KEY);
                    }
                    if (actionItem.has(ACTION_ITEM_Y_KEY)) {
                        result.y += (float) actionItem.getDouble(ACTION_ITEM_Y_KEY);
                    }
                    return result;
                }
                throw new ActionsParseException(String.format(
                        "Action item '%s' of action '%s' should be preceded with at least one item " +
                                "containing absolute coordinates", actionItem, actionId));
            }
        }
        return extractElementCoordinates(actionId, actionItem, origin);
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

    private void applyEmptyActionToEventsMapping(final JSONObject action) throws JSONException {
        final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
        long timeDelta = 0;
        for (int i = 0; i < actionItems.length(); i++) {
            final JSONObject actionItem = actionItems.getJSONObject(i);
            final String itemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            if (!itemType.equals(ACTION_ITEM_TYPE_PAUSE)) {
                throw new ActionsParseException(String.format(
                        "Unexpected action item %s '%s' in action with id '%s'",
                        ACTION_ITEM_TYPE_KEY, itemType, action.getString(ACTION_KEY_ID)));
            }
            timeDelta += alignDuration(extractDuration(action, actionItem));
            recordEventParams(timeDelta, null);
        }
    }

    private void applyKeyActionToEventsMapping(final JSONObject action) throws JSONException {
        final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        for (int i = 0; i < actionItems.length(); i++) {
            final JSONObject actionItem = actionItems.getJSONObject(i);
            final String itemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            switch (itemType) {
                case ACTION_ITEM_TYPE_PAUSE:
                    timeDelta += alignDuration(extractDuration(action, actionItem));
                    recordEventParams(timeDelta, null);
                    break;
                case ACTION_ITEM_TYPE_KEY_DOWN:
                    chainEntryPointDelta = timeDelta;
                case ACTION_ITEM_TYPE_KEY_UP:
                    if (!actionItem.has(ACTION_ITEM_VALUE_KEY)) {
                        throw new ActionsParseException(String.format(
                                "Missing %s key for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, actionItem, action.getString(ACTION_KEY_ID)));
                    }
                    final String value = actionItem.getString(ACTION_ITEM_VALUE_KEY);
                    if (value.isEmpty()) {
                        throw new ActionsParseException(String.format(
                                "%s key cannot be empty for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, actionItem, action.getString(ACTION_KEY_ID)));
                    }
                    final KeyInputEventParams evtParams = new KeyInputEventParams(
                            chainEntryPointDelta, itemType.equals(ACTION_ITEM_TYPE_KEY_DOWN) ?
                            KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, value.codePointAt(0)
                    );
                    recordEventParams(timeDelta, evtParams);
                    chainEntryPointDelta = timeDelta;
                    break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, itemType, action.getString(ACTION_KEY_ID)));
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

    private void applyPointerActionToEventsMapping(
            final JSONObject action, final int pointerIndex) throws JSONException {
        final String actionId = action.getString(ACTION_KEY_ID);
        final MotionEvent.PointerProperties props = new MotionEvent.PointerProperties();
        props.id = pointerIndex;
        props.toolType = actionToToolType(action);
        assertPointersCount(props.toolType, pointerIndex);

        final boolean isToolTypeMouse = props.toolType == MotionEvent.TOOL_TYPE_MOUSE;
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        long recentUpDelta = -1;
        long recentDownDelta = -1;
        boolean isPointerDown = false;
        int recentButton = 0;
        final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
        for (int actionItemIdx = 0; actionItemIdx < actionItems.length(); actionItemIdx++) {
            final JSONObject actionItem = actionItems.getJSONObject(actionItemIdx);
            final String itemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            switch (itemType) {
                case ACTION_ITEM_TYPE_PAUSE: {
                    timeDelta += alignDuration(extractDuration(action, actionItem));
                    recordEventParams(timeDelta, null);
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_DOWN: {
                    if (isPointerDown || recentDownDelta == timeDelta) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform two or more '%s' actions without a pause between them at " +
                                        "%sms in '%s' chain", itemType, timeDelta, actionId));
                    }

                    chainEntryPointDelta = timeDelta;
                    recentButton = extractButton(actionItem, props.toolType);
                    recordEventParams(timeDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_DOWN,
                            extractCoordinates(actionId, actionItems, actionItemIdx), recentButton, props));
                    isPointerDown = true;
                    recentDownDelta = timeDelta;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_UP: {
                    if (!isPointerDown) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform '%s' action without performing '%s' first at " +
                                        "%sms in '%s' chain", itemType, ACTION_ITEM_TYPE_POINTER_DOWN, timeDelta, actionId));
                    }
                    if (recentUpDelta == timeDelta) {
                        throw new ActionsParseException(String.format(
                                "You cannot perform two or more '%s' actions without a pause between them at " +
                                        "%sms in '%s' chain", itemType, timeDelta, actionId));
                    }

                    recentButton = extractButton(actionItem, props.toolType);
                    recordEventParams(timeDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_UP,
                            extractCoordinates(actionId, actionItems, actionItemIdx), recentButton, props));
                    isPointerDown = false;
                    recentButton = 0;
                    chainEntryPointDelta = timeDelta;
                    recentUpDelta = timeDelta;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_MOVE: {
                    final long duration = alignDuration(extractDuration(action, actionItem));
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
                    int actionCode = MotionEvent.ACTION_MOVE;
                    final MotionEvent.PointerCoords startCoordinates = extractCoordinates(actionId, actionItems, actionItemIdx - 1);
                    final MotionEvent.PointerCoords endCoordinates = extractCoordinates(actionId, actionItems, actionItemIdx);

                    final long startDelta = timeDelta;
                    final long firstActionDelta = recentDownDelta == startDelta || recentUpDelta == startDelta
                            ? startDelta + EVENT_INJECTION_DELAY_MS : startDelta;
                    final long stepsCount = (startDelta + duration - firstActionDelta) / EVENT_INJECTION_DELAY_MS;
                    if (!isPointerDown && isToolTypeMouse) {
                        if (duration <= EVENT_INJECTION_DELAY_MS * 3) {
                            // Hover gesture should also include enter and exit
                            // events, so we must book enough time for these
                            timeDelta += duration;
                            recordEventParams(timeDelta, null);
                            break;
                        }
                        recordEventParams(firstActionDelta, new MotionInputEventParams(
                                firstActionDelta, MotionEvent.ACTION_HOVER_ENTER, startCoordinates, 0, props));
                        actionCode = MotionEvent.ACTION_HOVER_MOVE;
                    } else {
                        recordEventParams(firstActionDelta, new MotionInputEventParams(chainEntryPointDelta, MotionEvent.ACTION_MOVE,
                                stepsCount <= 1 ? endCoordinates : startCoordinates, recentButton, props));
                    }
                    timeDelta = firstActionDelta + EVENT_INJECTION_DELAY_MS;

                    for (long step = 2; step <= stepsCount; step++) {
                        final MotionEvent.PointerCoords currentCoordinates = new MotionEvent.PointerCoords();
                        currentCoordinates.x = startCoordinates.x + (endCoordinates.x - startCoordinates.x) / stepsCount * step;
                        currentCoordinates.y = startCoordinates.y + (endCoordinates.y - startCoordinates.y) / stepsCount * step;
                        if (step == stepsCount && actionCode == MotionEvent.ACTION_HOVER_MOVE) {
                            recordEventParams(timeDelta, new MotionInputEventParams(
                                    firstActionDelta, MotionEvent.ACTION_HOVER_EXIT, endCoordinates, 0, props));
                        } else {
                            recordEventParams(timeDelta, new MotionInputEventParams(
                                    actionCode == MotionEvent.ACTION_HOVER_MOVE ? firstActionDelta : chainEntryPointDelta,
                                    actionCode, currentCoordinates, recentButton, props));
                        }
                        timeDelta += EVENT_INJECTION_DELAY_MS;
                    }
                    timeDelta = startDelta + duration;
                }
                break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, itemType, actionId));
            }
        }
    }

    /**
     * Parses actions JSON and transforms it into timeflow mapping,
     * where keys are timestamps in milliseconds and values are lists
     * of corresponding action properties. All events on this timeflow are
     * aligned by 5ms interval
     *
     * @param preprocessedActions a valid W3C actions chain
     * @return tokenized chain of events
     * @throws JSONException         if the given json has invalid format
     * @throws ActionsParseException if the given actions chain cannot be tokenized properly
     */
    public ActionTokens tokenize(JSONArray preprocessedActions) throws JSONException {
        tokenizedActions = new ActionTokens();

        final List<JSONObject> emptyActions = filterActionsByType(preprocessedActions, ACTION_TYPE_NONE);
        for (final JSONObject emptyAction : emptyActions) {
            applyEmptyActionToEventsMapping(emptyAction);
        }

        final List<JSONObject> keyInputActions = filterActionsByType(preprocessedActions, ACTION_TYPE_KEY);
        for (final JSONObject keyAction : keyInputActions) {
            applyKeyActionToEventsMapping(keyAction);
        }

        final List<JSONObject> pointerActions = filterActionsByType(preprocessedActions, ACTION_TYPE_POINTER);
        for (int pointerIdx = 0; pointerIdx < pointerActions.size(); pointerIdx++) {
            applyPointerActionToEventsMapping(pointerActions.get(pointerIdx), pointerIdx);
        }

        return tokenizedActions;
    }
}
