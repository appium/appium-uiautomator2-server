package io.appium.uiautomator2.model;


import java.util.UUID;


public class AppiumUiAutomatorDriver {

    private static AppiumUiAutomatorDriver instance;
    private Session session;

    private AppiumUiAutomatorDriver() {
    }

    public static synchronized AppiumUiAutomatorDriver getInstance() {
        if (instance == null) {
            instance = new AppiumUiAutomatorDriver();
        }
        return instance;
    }

    public String initializeSession() {

        if (this.session != null) {
            session.getKnownElements().clear();
            return session.getSessionId();
        }
        this.session = new Session(UUID.randomUUID().toString());
        return session.getSessionId();
    }

    public Session getSession() {
        return session;
    }
}

