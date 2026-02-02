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

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.KeyCodeModel;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

import java.lang.reflect.Field;

public class LongPressKeyCode extends SafeRequestHandler {

    public LongPressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    private void setDeviceIf(KeyEvent keyEvent) {
        try {
            // https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/view/KeyEvent.java#1865
            @SuppressLint("SoonBlockedPrivateApi") Field field = KeyEvent.class.getDeclaredField("mDeviceId");
            field.setAccessible(true);
            field.setInt(keyEvent, -1);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    @SuppressLint("SoonBlockedPrivateApi")
    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        final KeyCodeModel model = toModel(request, KeyCodeModel.class);
        final int keyCode = model.keycode;
        int metaState = model.metastate == null ? 0 : model.metastate;
        int flags = model.flags == null ? 0 : model.flags;

        final InteractionController interactionController = UiAutomatorBridge.getInstance().getInteractionController();
        final long now = SystemClock.uptimeMillis();
        // Send an initial down event
        final KeyEvent downEvent = new KeyEvent(
                now, now, KeyEvent.ACTION_DOWN, keyCode,
                0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags, InputDevice.SOURCE_KEYBOARD
        );
        setDeviceIf(downEvent);

        boolean isSuccessful = interactionController.injectEventSync(downEvent);
        // Send a repeat event. This will cause the FLAG_LONG_PRESS to be set.
        final KeyEvent repeatEvent = KeyEvent.changeTimeRepeat(downEvent, now, 1);
        isSuccessful &= interactionController.injectEventSync(repeatEvent);
        // Finally, send the up event
        final KeyEvent upEvent = new KeyEvent(
                now, now, KeyEvent.ACTION_UP, keyCode,
                0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, flags, InputDevice.SOURCE_KEYBOARD
        );
        setDeviceIf(upEvent);
        isSuccessful &= interactionController.injectEventSync(upEvent);
        if (!isSuccessful) {
            throw new InvalidElementStateException("Cannot inject long press event for key code " + keyCode);
        }
        return new AppiumResponse(getSessionId(request));
    }
}
