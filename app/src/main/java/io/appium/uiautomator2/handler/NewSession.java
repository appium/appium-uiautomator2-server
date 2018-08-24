package io.appium.uiautomator2.handler;


import org.json.JSONException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUiAutomatorDriver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class NewSession extends SafeRequestHandler {

    public NewSession(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Session.capabilities = getPayload(request, "desiredCapabilities");
        String sessionID = AppiumUiAutomatorDriver.getInstance().initializeSession();
        Logger.info("Session Created with SessionID:" + sessionID);
        return new AppiumResponse(sessionID, WDStatus.SUCCESS, "Created Session");
    }
}
