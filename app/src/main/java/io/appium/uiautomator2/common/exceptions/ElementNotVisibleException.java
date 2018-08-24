package io.appium.uiautomator2.common.exceptions;

public class ElementNotVisibleException extends UiAutomator2Exception {
    private static final long serialVersionUID = -5320098709492050871L;

    public ElementNotVisibleException(String message) {
        super(message);
    }

    public ElementNotVisibleException(Throwable t) {
        super(t);
    }

    public ElementNotVisibleException(String message, Throwable t) {
        super(message, t);
    }
}
