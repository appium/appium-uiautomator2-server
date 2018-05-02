package io.appium.uiautomator2.handler;

import android.os.SystemClock;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;

public class LongPressKeyCode extends SafeRequestHandler {

    public LongPressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        InteractionController interactionController = UiAutomatorBridge.getInstance()
                .getInteractionController();

        final JSONObject payload = getPayload(request);
        final Object kc = payload.get("keycode");
        final Object ms = payload.has("metastate") ? payload.get("metastate") : null;

        final Integer keyCode;
        if (kc instanceof Integer) {
            keyCode = (Integer) kc;
        } else if (kc instanceof String) {
            keyCode = Integer.parseInt((String) kc);
        } else {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    "Keycode of type " + kc.getClass() + "not supported.");
        }

        final Integer metaState;
        if (ms instanceof Integer) {
            metaState = (Integer) ms;
        } else if (ms instanceof String) {
            metaState = Integer.parseInt((String) ms);
        } else {
            metaState = 0;
        }

        final long eventTime = SystemClock.uptimeMillis();
        // Send an initial down event
        final KeyEvent downEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN,
                keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, 0, InputDevice.SOURCE_KEYBOARD);
        boolean isInjectionSuccessful = interactionController.injectEventSync(downEvent);
        // https://android.googlesource.com/platform/frameworks/base.git/+/9d83b4783c33f1fafc43f367503e129e5a5047fa%5E%21/#F0
        final KeyEvent repeatEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN,
                keyCode, 1, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, KeyEvent.FLAG_LONG_PRESS, InputDevice.SOURCE_KEYBOARD);
        isInjectionSuccessful = interactionController.injectEventSync(repeatEvent)
                && isInjectionSuccessful;
        // Finally, send the up event
        final KeyEvent upEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP,
                keyCode, 0, metaState, KeyCharacterMap.VIRTUAL_KEYBOARD,
                0, 0, InputDevice.SOURCE_KEYBOARD);
        isInjectionSuccessful = interactionController.injectEventSync(upEvent)
                && isInjectionSuccessful;
        if (!isInjectionSuccessful) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    "Cannot inject long press event for key code " + keyCode);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }
}
