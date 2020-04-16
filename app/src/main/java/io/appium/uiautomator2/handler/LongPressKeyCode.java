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

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class LongPressKeyCode extends SafeRequestHandler {

    public LongPressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        final KeyCodeModel model = toModel(request, KeyCodeModel.class);
        final int keyCode = model.keycode;
        int metaState = model.metastate == null ? 0 : model.metastate;
        int flags = model.flags == null ? 0 : model.flags;

        final long downTime = SystemClock.uptimeMillis();
        final InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
        boolean isSuccessful = interactionController.injectEventSync(new KeyEvent(downTime, downTime,
                        KeyEvent.ACTION_DOWN, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                        0, flags));
        // https://android.googlesource.com/platform/frameworks/base.git/+/9d83b4783c33f1fafc43f367503e129e5a5047fa%5E%21/#F0
        isSuccessful &= interactionController.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_DOWN, keyCode, 1, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                        0, flags | KeyEvent.FLAG_LONG_PRESS));
        isSuccessful &= interactionController.injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                        KeyEvent.ACTION_UP, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                        0, flags));
        if (!isSuccessful) {
            throw new InvalidElementStateException("Cannot inject long press event for key code " + keyCode);
        }
        return new AppiumResponse(getSessionId(request));
    }
}
