package io.appium.uiautomator2.handler;

import android.graphics.Rect;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;

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
        final JSONObject result = new JSONObject();

        for (Settings value : Settings.values()) {
            try {
                settingsJson(result, value);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
    }

    private JSONObject settingsJson(JSONObject result, Settings setting) throws JSONException {
        result.put(setting.toString(), settingValue(setting));
        return result;
    }

    private String settingValue(Settings setting) {
        switch (setting) {
            case keyInjectionDelay:
                return Long.toString(Configurator.getInstance().getKeyInjectionDelay());
            case waitForIdleTimeout:
                return Long.toString(Configurator.getInstance().getWaitForIdleTimeout());
            case waitForSelectorTimeout:
                return Long.toString(Configurator.getInstance().getWaitForSelectorTimeout());
            case actionAcknowledgmentTimeout:
                return Long.toString(Configurator.getInstance().getActionAcknowledgmentTimeout());
            case scrollAcknowledgmentTimeout:
                return Long.toString(Configurator.getInstance().getScrollAcknowledgmentTimeout());
            default:
                // TODO: raise InvalidArgumentException
                return "no settings";
        }
    }
}
