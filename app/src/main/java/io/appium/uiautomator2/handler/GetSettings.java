package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.Configurator;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.CompressedLayoutHierarchy;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.model.Session.CAP_ELEMENT_RESPONSE_FIELDS;

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

    private Object settingValue(Settings setting) {
        switch (setting) {
            case keyInjectionDelay:
                return Configurator.getInstance().getKeyInjectionDelay();
            case waitForIdleTimeout:
                return Configurator.getInstance().getWaitForIdleTimeout();
            case waitForSelectorTimeout:
                return Configurator.getInstance().getWaitForSelectorTimeout();
            case actionAcknowledgmentTimeout:
                return Configurator.getInstance().getActionAcknowledgmentTimeout();
            case scrollAcknowledgmentTimeout:
                return Configurator.getInstance().getScrollAcknowledgmentTimeout();
            case enableNotificationListener:
                return NotificationListener.getInstance().isListening;
            case shouldUseCompactResponses:
                return Session.shouldUseCompactResponses();
            case ignoreUnimportantViews:
                return CompressedLayoutHierarchy.getCompressedLayoutHierarchySetting();
            case allowInvisibleElements:
                Object allowInvisibleElements = Session.capabilities.get(AllowInvisibleElements.SETTING_NAME);
                return allowInvisibleElements != null && (boolean) allowInvisibleElements;
            case elementResponseFields:
                return Session.capabilities.containsKey(CAP_ELEMENT_RESPONSE_FIELDS);
            default:
                // TODO: raise InvalidArgumentException
                return "no settings";
        }
    }
}
