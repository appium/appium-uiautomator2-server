package io.appium.uiautomator2.common.exceptions;

public class StaleElementReferenceException extends UiAutomator2Exception {
    private static final long serialVersionUID = -5835005031770654071L;

    public StaleElementReferenceException(String message) {
        super(message);
    }

    public StaleElementReferenceException(Throwable t) {
        super(t);
    }

    public StaleElementReferenceException(String message, Throwable t) {
        super(message, t);
    }
}
