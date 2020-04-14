package io.appium.uiautomator2.handler;

import androidx.annotation.VisibleForTesting;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.utils.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * This method return settings
 */
public class GetSettings extends SafeRequestHandler {

    public GetSettings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.debug("Get settings:");
        return new AppiumResponse(getSessionId(request), getPayload());
    }

    @VisibleForTesting
    public Map<String, Object> getPayload() {
        Map<String, Object> result = new HashMap<>();
        for (Settings value : Settings.values()) {
            try {
                @SuppressWarnings("rawtypes")
                ISetting setting = value.getSetting();
                result.put(setting.getName(), setting.getValue());
            } catch (IllegalArgumentException e) {
                Logger.error("No Setting: " + value.toString() + " : " + e);
            }
        }
        return result;
    }
}
