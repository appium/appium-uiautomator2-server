package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class DeleteSession extends SafeRequestHandler {

    public DeleteSession(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Delete session command");
        String sessionId = getSessionId(request);
        NotificationListener.getInstance().stop();
        ServerInstrumentation.getInstance().stopServer();
        return new AppiumResponse(sessionId, WDStatus.SUCCESS, "Session deleted");
    }
}
