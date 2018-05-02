package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class PressKeyCode extends SafeRequestHandler {
    public PressKeyCode(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        try {
            Logger.info("Calling PressKeyCode... ");
            JSONObject payload = getPayload(request);
            Object kc = payload.get("keycode");
            Integer keyCode;
            if (kc instanceof Integer) {
                keyCode = (Integer) kc;
            } else if (kc instanceof String) {
                keyCode = Integer.parseInt((String) kc);
            } else {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Keycode of type " + kc.getClass() + "not supported.");
            }
            Integer metaState;
            if (payload.has("metastate") && payload.get("metastate") != JSONObject.NULL) {
                metaState = (Integer) payload.get("metastate");
                getUiDevice().pressKeyCode(keyCode, metaState);
            } else {
                getUiDevice().pressKeyCode(keyCode);
            }
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
        } catch (JSONException e) {
            Logger.error("Unable to PressKeyCode:" + e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        }
    }

}
