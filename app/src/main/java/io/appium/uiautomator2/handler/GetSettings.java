package io.appium.uiautomator2.handler;

import android.support.annotation.VisibleForTesting;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

/**
 * This method return settings
 */
public class GetSettings extends SafeRequestHandler {

    public GetSettings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.debug("Get settings:");

        try {
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, getPayload());
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        }
    }

    @VisibleForTesting
    public JSONObject getPayload() throws JSONException {
        final JSONObject result = new JSONObject();
        for (Settings value : Settings.values()) {
            try {
                ISetting setting = value.getSetting();
                result.put(setting.getName(), setting.getValue());
            } catch (IllegalArgumentException e) {
                Logger.error("No Setting: " + value.toString() + " : " + e);
            }
        }
        return result;
    }
}
