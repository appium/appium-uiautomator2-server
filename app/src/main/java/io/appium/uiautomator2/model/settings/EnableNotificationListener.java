package io.appium.uiautomator2.model.settings;

import io.appium.uiautomator2.model.NotificationListener;

public class EnableNotificationListener extends AbstractSetting<Boolean> {

    public static final String SETTING_NAME = "enableNotificationListener";

    public EnableNotificationListener() {
        super(Boolean.class);
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

    @Override
    protected void apply(Boolean enableNotificationListener) {
        if (enableNotificationListener) {
            NotificationListener.getInstance().start();
        } else {
            NotificationListener.getInstance().stop();
        }
    }

}
