package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.ScreenshotHelper;

public class CaptureScreenshot extends SafeRequestHandler {

    public CaptureScreenshot(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Capture screenshot command");
        try {
            final String result = ScreenshotHelper.takeScreenshot();
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
        } catch (Exception e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }
}
