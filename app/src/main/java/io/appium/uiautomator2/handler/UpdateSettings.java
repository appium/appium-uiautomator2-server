package io.appium.uiautomator2.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import io.appium.uiautomator2.common.exceptions.UnsupportedSettingException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.ActionAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.ElementResponseAttributes;
import io.appium.uiautomator2.model.settings.EnableNotificationListener;
import io.appium.uiautomator2.model.settings.CompressedLayoutHierarchy;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.KeyInjectionDelay;
import io.appium.uiautomator2.model.settings.ScrollAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.ShouldUseCompactResponses;
import io.appium.uiautomator2.model.settings.WaitForIdleTimeout;
import io.appium.uiautomator2.model.settings.WaitForSelectorTimeout;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class UpdateSettings extends SafeRequestHandler {

    private static final Map<String, Class<? extends ISetting>> SETTINGS = new HashMap<String, Class<? extends ISetting>>() {
        {
            put(AllowInvisibleElements.SETTING_NAME, AllowInvisibleElements.class);
            put(CompressedLayoutHierarchy.SETTING_NAME, CompressedLayoutHierarchy.class);
            put(WaitForIdleTimeout.SETTING_NAME, WaitForIdleTimeout.class);
            put(EnableNotificationListener.SETTING_NAME, EnableNotificationListener.class);
            put(WaitForSelectorTimeout.SETTING_NAME, WaitForSelectorTimeout.class);
            put(KeyInjectionDelay.SETTING_NAME, KeyInjectionDelay.class);
            put(ActionAcknowledgmentTimeout.SETTING_NAME, ActionAcknowledgmentTimeout.class);
            put(ScrollAcknowledgmentTimeout.SETTING_NAME, ScrollAcknowledgmentTimeout.class);
            put(ElementResponseAttributes.SETTING_NAME, ElementResponseAttributes.class);
            put(ShouldUseCompactResponses.SETTING_NAME, ShouldUseCompactResponses.class);
        }
    };

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
                setting.updateSetting(settingValue);
                Session.capabilities.put(settingName, settingValue);
            }
        } catch (Exception e) {
            Logger.error("error settings " + e.getMessage());
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    public ISetting getSetting(String settingName) throws UnsupportedSettingException, IllegalAccessException, InstantiationException {
        if (!SETTINGS.containsKey(settingName)) {
            throw new UnsupportedSettingException(settingName);
        }
        return SETTINGS.get(settingName).newInstance();
    }
}
