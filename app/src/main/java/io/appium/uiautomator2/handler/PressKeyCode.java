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

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.InteractionUtils.injectEventSync;
import static io.appium.uiautomator2.utils.JSONUtils.readInteger;

public class PressKeyCode extends SafeRequestHandler {
    public PressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Logger.info("Calling PressKeyCode... ");
        final JSONObject payload = getPayload(request);
        final int keyCode = readInteger(payload, "keycode");
        Integer metaState = readInteger(payload, "metastate", false);
        final Integer flags = readInteger(payload, "flags", false);

        boolean isSuccessful;
        if (flags == null) {
            if (metaState == null) {
                isSuccessful = getUiDevice().pressKeyCode(keyCode);
            } else {
                isSuccessful = getUiDevice().pressKeyCode(keyCode, metaState);
            }
        } else {
            metaState = metaState == null ? 0 : metaState;
            long downTime = SystemClock.uptimeMillis();
            isSuccessful = injectEventSync(new KeyEvent(downTime, downTime,
                    KeyEvent.ACTION_DOWN, keyCode, 0, metaState,
                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
            isSuccessful &= injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                    KeyEvent.ACTION_UP, keyCode, 0, metaState,
                    KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags));
        }
        if (!isSuccessful) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, String.format(
                    "Cannot generate key press event for key code %s", keyCode));
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

}
