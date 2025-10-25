package io.appium.uiautomator2.handler;

import androidx.annotation.VisibleForTesting;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.utils.Logger;

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
        return Arrays.stream(Settings.values())
                .collect(HashMap::new, (map, value) -> {
                    try {
                        @SuppressWarnings("rawtypes")
                        ISetting setting = value.getSetting();
                        map.put(setting.getName(), setting.getValue());
                    } catch (IllegalArgumentException e) {
                        Logger.error("No Setting: " + value, e);
                    }
                }, HashMap::putAll);
    }
}
