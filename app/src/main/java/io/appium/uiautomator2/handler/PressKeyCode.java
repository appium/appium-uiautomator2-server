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

import android.os.SystemClock;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import io.appium.uiautomator2.model.api.KeyCodeModel;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class PressKeyCode extends SafeRequestHandler {
    public PressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Calling PressKeyCode... ");
        final KeyCodeModel model = toModel(request, KeyCodeModel.class);
        final int keyCode = model.keycode;
        Integer metaState = model.metastate;
        Integer flags = model.flags;

        boolean isSuccessful;
        if (flags == null) {
            if (metaState == null) {
                isSuccessful = getUiDevice().pressKeyCode(keyCode);
            } else {
                isSuccessful = getUiDevice().pressKeyCode(keyCode, metaState);
            }
        } else {
            final InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
            metaState = metaState == null ? 0 : metaState;
            long downTime = SystemClock.uptimeMillis();
            isSuccessful = interactionController.injectEventSync(new KeyEvent(downTime, downTime,
                            KeyEvent.ACTION_DOWN, keyCode, 0, metaState,
                            KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
            isSuccessful &= interactionController.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                            KeyEvent.ACTION_UP, keyCode, 0, metaState,
                            KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
        }
        if (!isSuccessful) {
            throw new InvalidElementStateException(String.format(
                    "Cannot generate key press event for key code %s", keyCode));
        }
        return new AppiumResponse(getSessionId(request));
    }

}
