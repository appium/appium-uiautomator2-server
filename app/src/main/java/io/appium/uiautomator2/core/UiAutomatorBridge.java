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
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.test.uiautomator.UiDevice;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Device;

import static io.appium.uiautomator2.utils.ReflectionUtils.invoke;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;

public class UiAutomatorBridge {
    private static UiAutomatorBridge INSTANCE = null;

    private UiAutomatorBridge() { }

    public static synchronized UiAutomatorBridge getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UiAutomatorBridge();
        }
        return INSTANCE;
    }

    public InteractionController getInteractionController() throws UiAutomator2Exception {
        return new InteractionController(invoke(method(UiDevice.class, "getInteractionController"),
                Device.getUiDevice()));
    }

    public AccessibilityNodeInfo getAccessibilityRootNode() throws UiAutomator2Exception {
        Object queryController = invoke(method(UiDevice.class, "getQueryController"), Device.getUiDevice());
        return (AccessibilityNodeInfo) invoke(method(queryController.getClass(), "getRootNode"), queryController);
    }

    public UiAutomation getUiAutomation() {
        return (UiAutomation) invoke(method(UiDevice.class, "getUiAutomation"), Device.getUiDevice());
    }

    public Display getDefaultDisplay() throws UiAutomator2Exception {
        return (Display) invoke(method(UiDevice.class, "getDefaultDisplay"), Device.getUiDevice());
    }
}
