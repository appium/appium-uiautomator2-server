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
import android.util.LongSparseArray;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.MotionEvent.PointerProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_CANCEL;
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
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_NONE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_POINTER;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.KEY_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOTION_EVENT_INJECTION_DELAY_MS;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_LEFT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_MIDDLE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.MOUSE_BUTTON_RIGHT;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.NONE_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.PARAMETERS_KEY_POINTER_TYPE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_MOUSE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_PEN;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPE_TOUCH;

public class ActionsHelpers {
    private static JSONArray preprocessActionItems(final String actionId,
                                                   final String actionType,
                                                   final JSONArray actionItems) throws JSONException {
        final JSONArray processedItems = new JSONArray();

        boolean shouldSkipNextItem = false;
        for (int i = actionItems.length() - 1; i >= 0; i--) {
            final JSONObject actionItem = actionItems.getJSONObject(i);
            if (!actionItem.has(ACTION_ITEM_TYPE_KEY)) {
                throw new ActionsParseException(
                        String.format("All items of '%s' action must have the %s key set",
                                actionId, ACTION_ITEM_TYPE_KEY));
            }
            final String actionItemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            List<String> allowedItemTypes;
            switch (actionType) {
                case ACTION_TYPE_POINTER:
                    allowedItemTypes = POINTER_ITEM_TYPES;
                    break;
                case ACTION_TYPE_KEY:
                    allowedItemTypes = KEY_ITEM_TYPES;
                    break;
                case ACTION_TYPE_NONE:
                    allowedItemTypes = NONE_ITEM_TYPES;
                    break;
                default:
                    throw new ActionsParseException(
                            String.format("Unknown action type '%s' is set for '%s' action",
                                    actionType, actionId));
            }
            if (!allowedItemTypes.contains(actionItemType)) {
                throw new ActionsParseException(String.format(
                        "Only %s item type values are supported for action type '%s'. " +
                                "'%s' is passed instead for action '%s'",
                        allowedItemTypes, actionType, actionItemType, actionId));
            }

            if (actionItemType.equals(ACTION_ITEM_TYPE_POINTER_CANCEL)) {
                shouldSkipNextItem = true;
                continue;
            }
            if (shouldSkipNextItem) {
                shouldSkipNextItem = false;
                continue;
            }

            processedItems.put(actionItem);
        }

        final JSONArray result = new JSONArray();
        for (int i = processedItems.length() - 1; i >= 0; i--) {
            result.put(processedItems.getJSONObject(i));
        }
        return result;
    }

    private static PointerCoords extractElementCoordinates(
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
        final PointerCoords result = new PointerCoords();
        Rect bounds;
        try {
            final AndroidElement element = KnownElements.getElementFromCache(elementId);
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

    private static PointerCoords extractCoordinates(final String actionId, final JSONArray allItems,
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
        final PointerCoords result = new PointerCoords();
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
                    final PointerCoords recentCoords = extractCoordinates(actionId, allItems, itemIdx - 1);
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

    public static JSONArray preprocessActions(final JSONArray actions) throws JSONException {
        final List<String> actionIds = new ArrayList<>();
        for (int i = 0; i < actions.length(); i++) {
            final JSONObject action = actions.getJSONObject(i);

            if (!action.has(ACTION_KEY_ID)) {
                throw new ActionsParseException(
                        String.format("All actions must have the %s key set", ACTION_KEY_ID));
            }
            final String actionId = action.getString(ACTION_KEY_ID);
            if (actionIds.contains(actionId)) {
                throw new ActionsParseException(
                        String.format("The action %s '%s' has one one or more duplicates",
                                ACTION_KEY_ID, actionId));
            }

            actionIds.add(actionId);
            if (!action.has(ACTION_KEY_TYPE)) {
                throw new ActionsParseException(
                        String.format("'%s' action must have the %s key set",
                                actionId, ACTION_KEY_TYPE));
            }
            final String actionType = action.getString(ACTION_KEY_TYPE);
            if (!ACTION_TYPES.contains(actionType)) {
                throw new ActionsParseException(String.format(
                        "Only %s values are supported for %s key. "
                                + "'%s' is passed instead for action '%s'",
                        ACTION_TYPES, ACTION_KEY_TYPE, actionType, actionId));
            }

            if (action.has(ACTION_KEY_PARAMETERS)) {
                final JSONObject params = action.getJSONObject(ACTION_KEY_PARAMETERS);
                if (params.has(PARAMETERS_KEY_POINTER_TYPE)) {
                    final String pointerType = params.getString(PARAMETERS_KEY_POINTER_TYPE);
                    if (!POINTER_TYPES.contains(pointerType)) {
                        throw new ActionsParseException(String.format(
                                "Only %s values are supported for %s key. " +
                                        "'%s' is passed instead for action '%s'",
                                POINTER_TYPES, PARAMETERS_KEY_POINTER_TYPE,
                                pointerType, actionId));
                    }
                    if (!actionType.equals(ACTION_TYPE_POINTER)) {
                        throw new ActionsParseException(String.format(
                                "%s parameter is only supported for action type '%s' in '%s' action",
                                PARAMETERS_KEY_POINTER_TYPE, ACTION_TYPE_POINTER, actionId));
                    }
                }
            }

            if (!action.has(ACTION_KEY_ACTIONS)) {
                throw new ActionsParseException(String.format(
                        "'%s' action should contain at least one item", actionId));
            }
            final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
            action.put(ACTION_KEY_ACTIONS,
                    preprocessActionItems(actionId, actionType, actionItems));
        }
        return actions;
    }

    public static int getPointerAction(int motionEnvent, int index) {
        return motionEnvent + (index << MotionEvent.ACTION_POINTER_INDEX_SHIFT);
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
        return MotionEvent.TOOL_TYPE_MOUSE;
    }

    public static int toolTypeToInputSource(final int toolType) {
        switch (toolType) {
            case MotionEvent.TOOL_TYPE_MOUSE:
                return InputDevice.SOURCE_MOUSE;
            case MotionEvent.TOOL_TYPE_STYLUS:
                return InputDevice.SOURCE_STYLUS;
            case MotionEvent.TOOL_TYPE_FINGER:
                return InputDevice.SOURCE_TOUCHSCREEN;
            default:
                // use default
                break;
        }
        return InputDevice.SOURCE_MOUSE;
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

    private static void recordEventParams(final long timeDeltaMs,
                                          final LongSparseArray<List<InputEventParams>> mapping,
                                          @Nullable final InputEventParams newParams) {
        final List<InputEventParams> allParams = mapping.get(timeDeltaMs);
        if (allParams == null) {
            final List<InputEventParams> params = new ArrayList<>();
            if (newParams != null) {
                params.add(newParams);
            }
            mapping.put(timeDeltaMs, params);
        } else if (newParams != null) {
            allParams.add(newParams);
        }
    }

    private static MotionInputEventParams toMotionEventInputParams(
            final int actionCode, final PointerCoords coordinates, final int button,
            final PointerProperties properties, final long startDelta) {
        final MotionInputEventParams evtParams = new MotionInputEventParams();
        evtParams.actionCode = actionCode;
        evtParams.coordinates = coordinates;
        evtParams.button = button;
        evtParams.properties = properties;
        evtParams.startDelta = startDelta;
        return evtParams;
    }

    private static void applyPointerActionToEventsMapping(
            final JSONObject action, final int pointerIndex,
            final LongSparseArray<List<InputEventParams>> mapping) throws JSONException {
        final String actionId = action.getString(ACTION_KEY_ID);
        final PointerProperties props = new PointerProperties();
        props.id = pointerIndex;
        props.toolType = actionToToolType(action);
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        boolean isPointerDown = false;
        boolean isPointerHovering = false;
        int recentButton = 0;
        final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
        for (int i = 0; i < actionItems.length(); i++) {
            final JSONObject actionItem = actionItems.getJSONObject(i);
            final String itemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            switch (itemType) {
                case ACTION_ITEM_TYPE_PAUSE: {
                    timeDelta += extractDuration(action, actionItem);
                    recordEventParams(timeDelta, mapping, null);
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_DOWN: {
                    chainEntryPointDelta = timeDelta;
                    if (isPointerHovering) {
                        recordEventParams(timeDelta, mapping,
                                toMotionEventInputParams(MotionEvent.ACTION_HOVER_EXIT,
                                        extractCoordinates(actionId, actionItems, i),
                                        0, props, chainEntryPointDelta));
                        isPointerHovering = false;
                    }
                    recentButton = extractButton(actionItem, props.toolType);
                    recordEventParams(timeDelta, mapping, toMotionEventInputParams(
                            MotionEvent.ACTION_DOWN, extractCoordinates(actionId, actionItems, i),
                            recentButton, props, chainEntryPointDelta));
                    isPointerDown = true;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_UP: {
                    recentButton = extractButton(actionItem, props.toolType);
                    recordEventParams(timeDelta, mapping, toMotionEventInputParams(
                            MotionEvent.ACTION_UP, extractCoordinates(actionId, actionItems, i),
                            recentButton, props, chainEntryPointDelta));
                    isPointerDown = false;
                    recentButton = 0;
                    chainEntryPointDelta = timeDelta;
                }
                break;
                case ACTION_ITEM_TYPE_POINTER_MOVE: {
                    final long duration = extractDuration(action, actionItem);
                    if (duration < MOTION_EVENT_INJECTION_DELAY_MS) {
                        break;
                    }
                    if (i == 0) {
                        // FIXME: Selenium client sets the default move duration
                        // to 250 ms, but it won't work if this is the very first
                        // action item, since gesture start coordinate is undefined.
                        // It would be better to set the default duration to zero.
                        timeDelta += duration;
                        recordEventParams(timeDelta, mapping, null);
                        break;
                    }
                    int actionCode = MotionEvent.ACTION_MOVE;
                    final PointerCoords startCoordinates = extractCoordinates(actionId, actionItems, i - 1);
                    if (!isPointerDown) {
                        if (!isPointerHovering) {
                            recordEventParams(timeDelta, mapping, toMotionEventInputParams(
                                    MotionEvent.ACTION_HOVER_ENTER, startCoordinates,
                                    0, props, chainEntryPointDelta));
                            isPointerHovering = true;
                        }
                        actionCode = MotionEvent.ACTION_HOVER_MOVE;
                    }
                    // `stepsCount` is never going to be equal to zero, because of the
                    // `if (duration < MOTION_EVENT_INJECTION_DELAY_MS)` condition above
                    final long stepsCount = duration / MOTION_EVENT_INJECTION_DELAY_MS;
                    final PointerCoords endCoordinates = extractCoordinates(actionId, actionItems, i);
                    for (long step = 0; step < stepsCount; ++step) {
                        final PointerCoords currentCoordinates = new PointerCoords();
                        currentCoordinates.x = startCoordinates.x +
                                (endCoordinates.x - startCoordinates.x) / stepsCount * step;
                        currentCoordinates.y = startCoordinates.y +
                                (endCoordinates.y - startCoordinates.y) / stepsCount * step;
                        recordEventParams(timeDelta, mapping, toMotionEventInputParams(actionCode,
                                currentCoordinates, recentButton, props, chainEntryPointDelta));
                        timeDelta += MOTION_EVENT_INJECTION_DELAY_MS;
                    }
                }
                break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, itemType, action.getString(ACTION_KEY_ID)));
            }
        }
        if (isPointerHovering) {
            recordEventParams(timeDelta, mapping, toMotionEventInputParams(
                    MotionEvent.ACTION_HOVER_EXIT,
                    extractCoordinates(actionId, actionItems, actionItems.length() - 1),
                    0, props, chainEntryPointDelta));
            //noinspection UnusedAssignment
            isPointerHovering = false;
        }
    }

    private static long extractDuration(final JSONObject action,
                                        final JSONObject actionItem) throws JSONException {
        if (!actionItem.has(ACTION_ITEM_DURATION_KEY)) {
            throw new ActionsParseException(String.format(
                    "Missing %s key for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, action, action.getString(ACTION_KEY_ID)));
        }
        final long duration = actionItem.getLong(ACTION_ITEM_DURATION_KEY);
        if (duration < 0) {
            throw new ActionsParseException(String.format(
                    "%s key cannot be negative for action item '%s' of action with id '%s'",
                    ACTION_ITEM_DURATION_KEY, action, action.getString(ACTION_KEY_ID)));
        }
        return duration;
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

    private static void applyKeyActionToEventsMapping(
            final JSONObject action, final LongSparseArray<List<InputEventParams>> mapping)
            throws JSONException {
        final JSONArray actionItems = action.getJSONArray(ACTION_KEY_ACTIONS);
        long timeDelta = 0;
        long chainEntryPointDelta = 0;
        for (int i = 0; i < actionItems.length(); i++) {
            final JSONObject actionItem = actionItems.getJSONObject(i);
            final String itemType = actionItem.getString(ACTION_ITEM_TYPE_KEY);
            switch (itemType) {
                case ACTION_ITEM_TYPE_PAUSE:
                    timeDelta += extractDuration(action, actionItem);
                    recordEventParams(timeDelta, mapping, null);
                    break;
                case ACTION_ITEM_TYPE_KEY_DOWN:
                    chainEntryPointDelta = timeDelta;
                case ACTION_ITEM_TYPE_KEY_UP:
                    if (!actionItem.has(ACTION_ITEM_VALUE_KEY)) {
                        throw new ActionsParseException(String.format(
                                "Missing %s key for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, action, action.getString(ACTION_KEY_ID)));
                    }
                    final String value = actionItem.getString(ACTION_ITEM_VALUE_KEY);
                    if (value.isEmpty()) {
                        throw new ActionsParseException(String.format(
                                "%s key cannot be empty for action item '%s' of action with id '%s'",
                                ACTION_ITEM_VALUE_KEY, action, action.getString(ACTION_KEY_ID)));
                    }
                    final KeyInputEventParams evtParams = new KeyInputEventParams();
                    evtParams.keyCode = value.codePointAt(0);
                    evtParams.keyAction = itemType.equals(ACTION_ITEM_TYPE_KEY_DOWN) ?
                            KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP;
                    evtParams.startDelta = chainEntryPointDelta;
                    recordEventParams(timeDelta, mapping, evtParams);
                    chainEntryPointDelta = timeDelta;
                    break;
                default:
                    throw new ActionsParseException(String.format(
                            "Unexpected action item %s '%s' in action with id '%s'",
                            ACTION_ITEM_TYPE_KEY, itemType, action.getString(ACTION_KEY_ID)));
            }
        }
    }

    private static void applyEmptyActionToEventsMapping(
            final JSONObject action, final LongSparseArray<List<InputEventParams>> mapping)
            throws JSONException {
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
            timeDelta += extractDuration(action, actionItem);
            recordEventParams(timeDelta, mapping, null);
        }
    }

    public static LongSparseArray<List<InputEventParams>> actionsToInputEventsMapping(
            final JSONArray actions) throws JSONException {
        final LongSparseArray<List<InputEventParams>> result = new LongSparseArray<>();
        final List<JSONObject> pointerActions = filterActionsByType(actions, ACTION_TYPE_POINTER);
        for (int pointerIdx = 0; pointerIdx < pointerActions.size(); pointerIdx++) {
            applyPointerActionToEventsMapping(pointerActions.get(pointerIdx), pointerIdx, result);
        }
        final List<JSONObject> keyInputActions = filterActionsByType(actions, ACTION_TYPE_KEY);
        for (final JSONObject keyAction : keyInputActions) {
            applyKeyActionToEventsMapping(keyAction, result);
        }
        final List<JSONObject> emptyActions = filterActionsByType(actions, ACTION_TYPE_NONE);
        for (final JSONObject emptyAction : emptyActions) {
            applyEmptyActionToEventsMapping(emptyAction, result);
        }
        return result;
    }

    public static int metaKeysToState(final Set<Integer> metaKeys) {
        int result = 0;
        for (final int metaKey : metaKeys) {
            result |= metaKey;
        }
        return result;
    }

    public static abstract class InputEventParams {
        public long startDelta = 0;

        InputEventParams() {
        }
    }

    public static class KeyInputEventParams extends InputEventParams {
        public int keyAction;
        public int keyCode;

        KeyInputEventParams() {
            super();
        }
    }

    public static class MotionInputEventParams extends InputEventParams {
        public PointerProperties properties;
        public PointerCoords coordinates;
        public int actionCode;
        public int button = 0;

        MotionInputEventParams() {
            super();
        }
    }
}
