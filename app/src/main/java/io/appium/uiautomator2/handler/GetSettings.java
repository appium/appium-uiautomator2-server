package io.appium.uiautomator2.handler;

import android.support.annotation.VisibleForTesting;
import android.support.test.uiautomator.Configurator;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.settings.ActionAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.CompressedLayoutHierarchy;
import io.appium.uiautomator2.model.settings.ElementResponseAttributes;
import io.appium.uiautomator2.model.settings.EnableNotificationListener;
import io.appium.uiautomator2.model.settings.KeyInjectionDelay;
import io.appium.uiautomator2.model.settings.ScrollAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShouldUseCompactResponses;
import io.appium.uiautomator2.model.settings.WaitForIdleTimeout;
import io.appium.uiautomator2.model.settings.WaitForSelectorTimeout;
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
                result.put(value.toString(), settingValue(value));
            } catch (IllegalArgumentException e) {
                Logger.error("No Setting: " + value.toString() + " : " + e);
            }
        }
        return result;
    }

    private Object settingValue(Settings setting) {
        switch (setting) {
            case keyInjectionDelay:
                return KeyInjectionDelay.getTime();
            case waitForIdleTimeout:
                return WaitForIdleTimeout.getTime();
            case waitForSelectorTimeout:
                return WaitForSelectorTimeout.getTime();
            case actionAcknowledgmentTimeout:
                return ActionAcknowledgmentTimeout.getTime();
            case scrollAcknowledgmentTimeout:
                return ScrollAcknowledgmentTimeout.getTime();
            case enableNotificationListener:
                return EnableNotificationListener.isEnabled();
            case shouldUseCompactResponses:
                return ShouldUseCompactResponses.isEnabled();
            case ignoreUnimportantViews:
                return CompressedLayoutHierarchy.isEnabled();
            case allowInvisibleElements:
                return AllowInvisibleElements.isEnabled();
            case elementResponseAttributes:
                return ElementResponseAttributes.isEnabled();
            default:
                throw new IllegalArgumentException();
        }
    }
}
