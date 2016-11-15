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

import android.app.UiAutomation;
import android.support.test.uiautomator.UiDevice;
import android.view.Display;
import android.view.InputEvent;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class UiAutomatorBridge {

    private static final String CLASS_UI_AUTOMATOR_BRIDGE = "android.support.test.uiautomator.UiAutomatorBridge";

    private static final String FIELD_UI_AUTOMATOR_BRIDGE = "mUiAutomationBridge";
    private static final String FIELD_QUERY_CONTROLLER = "mQueryController";
    private static final String FIELD_INTERACTION_CONTROLLER = "mInteractionController";
    private static final String FIELD_UI_AUTOMATOR = "mUiAutomation";

    private static final String METHOD_GET_DEFAULT_DISPLAY = "getDefaultDisplay";
    private static final String METHOD_INJECT_INPUT_EVENT = "injectInputEvent";

    private static UiAutomatorBridge INSTANCE = new UiAutomatorBridge();

    private final Object uiAutomatorBridge;

    public UiAutomatorBridge() {
        try {

            this.uiAutomatorBridge = getField(UiDevice.class, FIELD_UI_AUTOMATOR_BRIDGE, Device.getUiDevice());
        } catch (Error error) {
            Logger.error("ERROR", error);
            throw error;
        } catch (UiAutomator2Exception error) {
            Logger.error("ERROR", error);
            throw new Error(error);
        }
    }

    public static UiAutomatorBridge getInstance() {
        return INSTANCE;
    }

    public InteractionController getInteractionController() throws UiAutomator2Exception {
        return new InteractionController(getField(CLASS_UI_AUTOMATOR_BRIDGE, FIELD_INTERACTION_CONTROLLER, uiAutomatorBridge));
    }

    public QueryController getQueryController() throws UiAutomator2Exception {
        return new QueryController(getField(CLASS_UI_AUTOMATOR_BRIDGE, FIELD_QUERY_CONTROLLER, uiAutomatorBridge));
    }

    public UiAutomation getUiAutomation() {
        return (UiAutomation)getField(CLASS_UI_AUTOMATOR_BRIDGE, FIELD_UI_AUTOMATOR, uiAutomatorBridge);
    }

    public Display getDefaultDisplay() throws UiAutomator2Exception {
        return (Display) invoke(method(CLASS_UI_AUTOMATOR_BRIDGE, METHOD_GET_DEFAULT_DISPLAY), uiAutomatorBridge);
    }

    public boolean injectInputEvent(InputEvent event, boolean sync) throws UiAutomator2Exception {
        return (Boolean) invoke(method(CLASS_UI_AUTOMATOR_BRIDGE, METHOD_INJECT_INPUT_EVENT, InputEvent.class, boolean.class), uiAutomatorBridge, event, sync);
    }
}
