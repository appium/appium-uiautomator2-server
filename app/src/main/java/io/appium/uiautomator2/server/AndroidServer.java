package io.appium.uiautomator2.server;

import io.appium.uiautomator2.http.HttpServer;
import io.appium.uiautomator2.utils.Logger;


public class AndroidServer {
    private final HttpServer webServer;

    public AndroidServer(int port) {
        webServer = new HttpServer(port);
        init();
        Logger.info("AndroidServer created on port " + port);
    }

    protected void init() {
        webServer.addHandler(new AppiumServlet());
    }

    public void start() {
        webServer.start();
    }


    public void stop() {
        webServer.stop();
    }

    public int getPort() {
        return webServer.getPort();
    }
}
