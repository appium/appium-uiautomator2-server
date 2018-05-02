package io.appium.uiautomator2.handler;

import android.os.Environment;

import java.io.File;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class GetDataDir extends SafeRequestHandler {

    File dataDirectory;

    public GetDataDir(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        dataDirectory = Environment.getDataDirectory();
        Logger.info("data directory at " + dataDirectory);
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, dataDirectory);
    }
}
