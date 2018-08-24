package io.appium.uiautomator2.handler;

import android.os.RemoteException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.wake;

public class Wake extends SafeRequestHandler {

    public Wake(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        try {
            wake();
        } catch (RemoteException e) {
            Logger.error("Error waking up device");
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Wake up Device");
    }
}
