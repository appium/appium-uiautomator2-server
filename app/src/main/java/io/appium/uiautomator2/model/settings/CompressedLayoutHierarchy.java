package io.appium.uiautomator2.model.settings;

import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.API.API_18;

public class CompressedLayoutHierarchy extends AbstractSetting<Boolean> {

    public static final String SETTING_NAME = "ignoreUnimportantViews";

    public CompressedLayoutHierarchy() {
        super(Boolean.class);
    }

    @Override
    protected void apply(Boolean compressLayout) {
        // setCompressedLayoutHeirarchy doesn't exist on API <= 17
        if (API_18) {
            Device.getUiDevice().setCompressedLayoutHeirarchy(compressLayout);
        } else {
            Logger.info("SetCompressedLayoutHeirarchy doesn't exist on API <= 17");
        }
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

}
