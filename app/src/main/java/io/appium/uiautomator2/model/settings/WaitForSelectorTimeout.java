package io.appium.uiautomator2.model.settings;

import android.support.test.uiautomator.Configurator;

public class WaitForSelectorTimeout extends AbstractSetting<Integer> {

    public static final String SETTING_NAME = "waitForSelectorTimeout";

    public WaitForSelectorTimeout() {
        super(Integer.class);
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

    @Override
    protected void apply(Integer timeout) {
        Configurator.getInstance().setWaitForSelectorTimeout(timeout);
    }
}
