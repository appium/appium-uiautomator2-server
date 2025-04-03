package io.appium.uiautomator2.model.settings;

import android.view.Display;

public class CurrentDisplayId extends AbstractSetting<Integer> {
    private static final String SETTING_NAME = "currentDisplayId";
    private static final Integer DEFAULT_VALUE = Display.DEFAULT_DISPLAY;
    private Integer value = DEFAULT_VALUE;

    public CurrentDisplayId() {
        super(Integer.class, SETTING_NAME);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    protected void apply(Integer currentDisplayId) {
        value = currentDisplayId;
    }
}
