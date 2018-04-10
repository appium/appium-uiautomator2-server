package io.appium.uiautomator2.handler;

import java.util.Map;
import java.util.Map.Entry;

import io.appium.uiautomator2.common.exceptions.UnsupportedSettingException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class UpdateSettings extends SafeRequestHandler {

    public UpdateSettings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        try {
            Map<String, Object> settings = getPayload(request, "settings");
            Logger.debug("Update settings: " + settings.toString());
            for (Entry<String, Object> entry : settings.entrySet()) {
                String settingName = entry.getKey();
                Object settingValue = entry.getValue();
                ISetting setting = getSetting(settingName);
                setting.update(settingValue);
                Session.capabilities.put(settingName, settingValue);
            }
        } catch (Exception e) {
            Logger.error("error settings " + e.getMessage());
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    public ISetting getSetting(String settingName) throws UnsupportedSettingException, IllegalAccessException, InstantiationException {
        for (Settings value : Settings.values()) {
            if (value.toString().equals(settingName)) {
                return value.getSetting();
            }
        }
        throw new UnsupportedSettingException(settingName);
    }
}
