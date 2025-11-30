package io.appium.uiautomator2.model.settings;

import android.text.TextUtils;
import android.view.Display;

import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.core.UiAutomation;

public class CurrentDisplayId extends AbstractSetting<Integer> {
    private static final String SETTING_NAME = "currentDisplayId";
    private static final int DEFAULT_VALUE = Display.DEFAULT_DISPLAY;
    private int value = DEFAULT_VALUE;
    private boolean customized = false;

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

    public boolean isCustomized() {
        return customized;
    }

    @Override
    public boolean reset() {
        customized = false;
        return super.reset();
    }

    @Override
    protected void apply(Integer currentDisplayId) {
        List<Integer> displayIds = UiAutomation.getInstance().getDisplayIds();

        if (!displayIds.contains(currentDisplayId)) {
            String possibleValuesMessage = TextUtils.join(",", displayIds);

            throw new InvalidArgumentException(String.format(
                    "Invalid %s value specified, must be one of %s. %s was given",
                    SETTING_NAME,
                    possibleValuesMessage,
                    currentDisplayId
            ));
        }

        customized = true;
        value = currentDisplayId;
    }
}
