package io.appium.uiautomator2.model.settings;

import io.appium.uiautomator2.utils.Logger;

public class AllowInvisibleElements extends AbstractSetting<Boolean> {

    public static final String SETTING_NAME = "allowInvisibleElements";

    public AllowInvisibleElements() {
        super(Boolean.class);
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

    @Override
    protected void apply(Boolean allowInvisibleElements) {
        Logger.debug("Dummy setting.");
    }

}
