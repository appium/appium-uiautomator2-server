package io.appium.uiautomator2.handler;

import org.json.JSONException;

import java.util.Collections;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;

@Deprecated
public class AppStrings extends SafeRequestHandler {
    public AppStrings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, Collections.emptyMap());
    }
}
