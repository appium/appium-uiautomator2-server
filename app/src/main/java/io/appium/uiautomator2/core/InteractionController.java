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

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

import static io.appium.uiautomator2.utils.ReflectionUtils.getMethod;
import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;

public class InteractionController {

    private static final String CLASS_INTERACTION_CONTROLLER = "androidx.test.uiautomator.InteractionController";
    private static final String METHOD_INJECT_EVENT_SYNC = "injectEventSync";
    private final Object interactionController;

    public InteractionController(Object interactionController) {
        this.interactionController = interactionController;
    }

    public boolean injectEventSync(final InputEvent event) throws UiAutomator2Exception {
        return (Boolean) invoke(getMethod(CLASS_INTERACTION_CONTROLLER,
                METHOD_INJECT_EVENT_SYNC, InputEvent.class), interactionController, event);
    }
}
