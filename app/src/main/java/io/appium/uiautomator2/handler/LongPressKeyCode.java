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

import static io.appium.uiautomator2.utils.InteractionUtils.injectEventSync;
import static io.appium.uiautomator2.utils.JSONUtils.readInteger;

public class LongPressKeyCode extends SafeRequestHandler {

    public LongPressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        final JSONObject payload = getPayload(request);
        final int keyCode = readInteger(payload, "keycode");
        Integer metaState = readInteger(payload, "metastate", false);
        metaState = metaState == null ? 0 : metaState;
        Integer flags = readInteger(payload, "flags", false);
        flags = flags == null ? 0 : flags;

        final long downTime = SystemClock.uptimeMillis();
        boolean isSuccessful = injectEventSync(new KeyEvent(downTime, downTime,
                KeyEvent.ACTION_DOWN, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, flags));
        // https://android.googlesource.com/platform/frameworks/base.git/+/9d83b4783c33f1fafc43f367503e129e5a5047fa%5E%21/#F0
        isSuccessful &= injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                KeyEvent.ACTION_DOWN, keyCode, 1, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, flags | KeyEvent.FLAG_LONG_PRESS));
        isSuccessful &= injectEventSync(new KeyEvent(downTime, SystemClock.uptimeMillis(),
                KeyEvent.ACTION_UP, keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, flags));
        if (!isSuccessful) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    "Cannot inject long press event for key code " + keyCode);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }
}
