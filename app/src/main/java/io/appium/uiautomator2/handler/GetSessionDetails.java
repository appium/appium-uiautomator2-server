package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibilityScrollData;
import io.appium.uiautomator2.model.AppiumUiAutomatorDriver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.server.WDStatus;

public class GetSessionDetails extends SafeRequestHandler {
    public GetSessionDetails(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Session session = AppiumUiAutomatorDriver.getInstance().getSession();
        JSONObject result = new JSONObject();
        AccessibilityScrollData scrollData = session.getLastScrollData();
        JSONObject lastScrollData = null;
        if (scrollData != null) {
            lastScrollData = new JSONObject(scrollData.getAsMap());
        }
        result.put("lastScrollData", lastScrollData);
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
    }
}