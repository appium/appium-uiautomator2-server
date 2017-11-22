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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActionsConstants {
    public static final String ACTION_KEY_TYPE = "type";
    public static final String ACTION_TYPE_POINTER = "pointer";
    public static final String ACTION_TYPE_KEY = "key";
    public static final String ACTION_TYPE_NONE = "none";
    public static final List<String> ACTION_TYPES = Arrays.asList(ACTION_TYPE_POINTER,
            ACTION_TYPE_KEY, ACTION_TYPE_NONE);

    public static final String ACTION_KEY_ID = "id";

    public static final String ACTION_KEY_PARAMETERS = "parameters";
    public static final String PARAMETERS_KEY_POINTER_TYPE = "pointerType";
    public static final String POINTER_TYPE_MOUSE = "mouse";
    public static final String POINTER_TYPE_PEN = "pen";
    public static final String POINTER_TYPE_TOUCH = "touch";
    public static final List<String> POINTER_TYPES = Arrays.asList(POINTER_TYPE_MOUSE,
            POINTER_TYPE_PEN, POINTER_TYPE_TOUCH);

    public static final String ACTION_KEY_ACTIONS = "actions";

    public static final String ACTION_ITEM_TYPE_KEY = "type";
    public static final String ACTION_ITEM_TYPE_POINTER_MOVE = "pointerMove";
    public static final String ACTION_ITEM_TYPE_POINTER_UP = "pointerUp";
    public static final String ACTION_ITEM_TYPE_POINTER_DOWN = "pointerDown";
    public static final String ACTION_ITEM_TYPE_POINTER_CANCEL = "pointerCancel";
    public static final String ACTION_ITEM_TYPE_PAUSE = "pause";
    public static final String ACTION_ITEM_TYPE_KEY_UP = "keyUp";
    public static final String ACTION_ITEM_TYPE_KEY_DOWN = "keyDown";
    public static final List<String> POINTER_ITEM_TYPES = Arrays.asList(
            ACTION_ITEM_TYPE_POINTER_MOVE, ACTION_ITEM_TYPE_POINTER_UP,
            ACTION_ITEM_TYPE_POINTER_DOWN, ACTION_ITEM_TYPE_POINTER_CANCEL, ACTION_ITEM_TYPE_PAUSE);
    public static final List<String> KEY_ITEM_TYPES = Arrays.asList(
            ACTION_ITEM_TYPE_KEY_UP, ACTION_ITEM_TYPE_KEY_DOWN, ACTION_ITEM_TYPE_PAUSE);
    public static final List<String> NONE_ITEM_TYPES =
            Collections.singletonList(ACTION_ITEM_TYPE_PAUSE);

    public static final String ACTION_ITEM_VALUE_KEY = "value";
    public static final String ACTION_ITEM_PRESSURE_KEY = "pressure";
    public static final String ACTION_ITEM_SIZE_KEY = "size";
    public static final String ACTION_ITEM_BUTTON_KEY = "button";
    public static final String ACTION_ITEM_DURATION_KEY = "duration";

    public static final int MOUSE_BUTTON_LEFT = 0;
    public static final int MOUSE_BUTTON_MIDDLE = 1;
    public static final int MOUSE_BUTTON_RIGHT = 2;

    public static final String ACTION_ITEM_ORIGIN_KEY = "origin";
    public static final String ACTION_ITEM_ORIGIN_VIEWPORT = "viewport";
    public static final String ACTION_ITEM_ORIGIN_POINTER = "pointer";

    public static final String ACTION_ITEM_X_KEY = "x";
    public static final String ACTION_ITEM_Y_KEY = "y";

    public static final int MOTION_EVENT_INJECTION_DELAY_MS = 5;
}
