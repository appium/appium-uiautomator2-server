package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;

import static io.appium.uiautomator2.model.Session.NO_ID;

public class Status extends SafeRequestHandler {

    public Status(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        return new AppiumResponse(NO_ID, "Status Invoked");
    }
}
