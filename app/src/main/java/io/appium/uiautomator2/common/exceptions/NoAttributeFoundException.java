package io.appium.uiautomator2.common.exceptions;

public class NoAttributeFoundException extends UiAutomator2Exception {
    private static final long serialVersionUID = -4526232656079801503L;

    /**
     * This exception is thrown when the element doesn't have the attribute searched
     * for.
     *
     * @param attr The attribute searched for.
     */

    public NoAttributeFoundException(String attr) {
        super("This element does not have the '" + attr + "' attribute");
    }

    public NoAttributeFoundException(Throwable t) {
        super(t);
    }

    public NoAttributeFoundException(String message, Throwable t) {
        super(message, t);
    }
}
