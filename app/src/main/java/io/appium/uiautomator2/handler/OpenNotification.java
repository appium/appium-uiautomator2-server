package io.appium.uiautomator2.handler;


import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class OpenNotification extends SafeRequestHandler {

    public OpenNotification(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        if (Device.getUiDevice().openNotification()) {
            Logger.info("Opened Notification");
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
        }
        Logger.info("Unable to Open Notification");
        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Device failed to open notifications.");
    }

}
