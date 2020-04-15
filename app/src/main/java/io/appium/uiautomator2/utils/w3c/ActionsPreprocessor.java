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

import io.appium.uiautomator2.model.api.touch.w3c.W3CGestureModel;
import io.appium.uiautomator2.model.api.touch.w3c.W3CItemModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_ITEM_TYPE_POINTER_CANCEL;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_ID;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_KEY_TYPE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_KEY;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_NONE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.ACTION_TYPE_POINTER;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.KEY_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.NONE_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.PARAMETERS_KEY_POINTER_TYPE;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_ITEM_TYPES;
import static io.appium.uiautomator2.utils.w3c.ActionsConstants.POINTER_TYPES;

public class ActionsPreprocessor {
    private static List<W3CGestureModel> preprocessActionItems(final String actionId,
                                                            final String actionType,
                                                            final List<W3CGestureModel> actionItems) {
        final List<W3CGestureModel> processedItems = new ArrayList<>();
        boolean shouldSkipNextItem = false;
        for (int i = actionItems.size() - 1; i >= 0; i--) {
            final W3CGestureModel actionItem = actionItems.get(i);
            if (actionItem.type == null) {
                throw new ActionsParseException(
                        String.format("All items of '%s' action must have the %s key set",
                                actionId, ACTION_ITEM_TYPE_KEY));
            }
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
            if (!allowedItemTypes.contains(actionItem.type)) {
                throw new ActionsParseException(String.format(
                        "Only %s item type values are supported for action type '%s'. " +
                                "'%s' is passed instead for action '%s'",
                        allowedItemTypes, actionType, actionItem.type, actionId));
            }

            if (actionItem.type.equals(ACTION_ITEM_TYPE_POINTER_CANCEL)) {
                shouldSkipNextItem = true;
                continue;
            }
            if (shouldSkipNextItem) {
                shouldSkipNextItem = false;
                continue;
            }

            processedItems.add(actionItem);
        }

        final List<W3CGestureModel> result = new ArrayList<>();
        for (int i = processedItems.size() - 1; i >= 0; i--) {
            result.add(processedItems.get(i));
        }
        return result;
    }

    public List<W3CItemModel> preprocess(List<W3CItemModel> actions) {
        final List<String> actionIds = new ArrayList<>();
        final Set<String> pointerTypes = new HashSet<>();
        for (final W3CItemModel action : actions) {
            if (action.id == null) {
                throw new ActionsParseException(
                        String.format("All actions must have the %s key set", ACTION_KEY_ID));
            }
            final String actionId = action.id;
            if (actionIds.contains(actionId)) {
                throw new ActionsParseException(
                        String.format("The action %s '%s' has one one or more duplicates",
                                ACTION_KEY_ID, actionId));
            }

            actionIds.add(actionId);
            if (action.type == null) {
                throw new ActionsParseException(
                        String.format("'%s' action must have the %s key set",
                                actionId, ACTION_KEY_TYPE));
            }
            final String actionType = action.type;
            if (!ACTION_TYPES.contains(actionType)) {
                throw new ActionsParseException(String.format(
                        "Only %s values are supported for %s key. "
                                + "'%s' is passed instead for action '%s'",
                        ACTION_TYPES, ACTION_KEY_TYPE, actionType, actionId));
            }

            if (action.parameters != null) {
                if (action.parameters.pointerType != null) {
                    if (!POINTER_TYPES.contains(action.parameters.pointerType)) {
                        throw new ActionsParseException(String.format(
                                "Only %s values are supported for %s key. " +
                                        "'%s' is passed instead for action '%s'",
                                POINTER_TYPES, PARAMETERS_KEY_POINTER_TYPE,
                                action.parameters.pointerType, actionId));
                    }
                    pointerTypes.add(action.parameters.pointerType);
                    if (!actionType.equals(ACTION_TYPE_POINTER)) {
                        throw new ActionsParseException(String.format(
                                "%s parameter is only supported for action type '%s' in '%s' action",
                                PARAMETERS_KEY_POINTER_TYPE, ACTION_TYPE_POINTER, actionId));
                    }
                }
            }

            if (action.items == null) {
                throw new ActionsParseException(String.format(
                        "'%s' action should contain at least one item", actionId));
            }
            action.items = preprocessActionItems(actionId, actionType, action.items);
        }
        if (pointerTypes.size() > 1) {
            throw new ActionsParseException(String.format(
                    "It is only allowed to use one pointer type simultaneously. And you have %s: %s",
                    pointerTypes.size(), pointerTypes));
        }
        return actions;
    }
}
