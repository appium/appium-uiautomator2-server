package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.ScreenOrientation;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;

public class GetScreenOrientation extends SafeRequestHandler {

    public GetScreenOrientation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ScreenOrientation orientation;
        int rotation = Device.getUiDevice().getDisplayRotation();
        if (rotation == 1 || rotation == 3) {
            orientation = ScreenOrientation.LANDSCAPE;
        } else {
            orientation = ScreenOrientation.PORTRAIT;
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, orientation);
    }
}
