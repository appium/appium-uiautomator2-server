package io.appium.uiautomator2.model.settings;

import android.support.test.uiautomator.Configurator;

public class ActionAcknowledgmentTimeout extends AbstractSetting<Integer> {

    public static final String SETTING_NAME = "actionAcknowledgmentTimeout";

    public ActionAcknowledgmentTimeout() {
        super(Integer.class);
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

    @Override
    protected void apply(Integer timeout) {
        Configurator.getInstance().setActionAcknowledgmentTimeout(timeout);
    }
}
