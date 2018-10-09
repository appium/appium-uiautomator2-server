package io.appium.uiautomator2.common.exceptions;

public class NoAttributeFoundException extends UiAutomator2Exception {
    private static final long serialVersionUID = -4526232656079801503L;

    private final String attributeName;

    public String getAttributeName() {
        return attributeName;
    }

    public NoAttributeFoundException(String message, String attr) {
        super(message);
        this.attributeName = attr;
    }
}
