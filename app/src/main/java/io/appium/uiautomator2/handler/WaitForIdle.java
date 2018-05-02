package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;

public class WaitForIdle extends SafeRequestHandler {

    public WaitForIdle(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Integer timeout = 1000;
        JSONObject payload = getPayload(request);
        if (payload.has("timeout")) {
            timeout = Integer.parseInt(payload.getString("timeout"));
        }
        Device.waitForIdle(timeout);
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Device waited");
    }
}
