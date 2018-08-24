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

package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.utils.Logger;

public class TouchUp extends TouchEvent {

    public TouchUp(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public boolean executeTouchEvent() throws UiAutomator2Exception {
        printEventDebugLine("TouchUp");
        try {
            return UiAutomatorBridge.getInstance().getInteractionController().touchUp(clickX, clickY);
        } catch (Exception e) {
            Logger.error("Problem invoking touchUp: " + e);
            return false;
        }
    }
}
