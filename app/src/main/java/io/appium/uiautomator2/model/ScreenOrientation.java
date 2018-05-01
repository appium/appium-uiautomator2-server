package io.appium.uiautomator2.model;

/**
 * Represents possible screen orientations.
 */
public enum ScreenOrientation {
    LANDSCAPE("landscape"), PORTRAIT("portrait");

    private final String value;

    ScreenOrientation(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
