package io.appium.uiautomator2.handler;

import java.util.Collections;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

@Deprecated
public class AppStrings extends SafeRequestHandler {
    public AppStrings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("The /app/strings endpoint is deprecated " +
                "and will be removed in a future release");
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, Collections.emptyMap());
    }
}
