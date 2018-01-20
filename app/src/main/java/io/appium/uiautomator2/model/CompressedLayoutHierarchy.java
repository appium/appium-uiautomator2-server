package io.appium.uiautomator2.model;

import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class CompressedLayoutHierarchy {
    public static final String  SETTING_NAME = "ignoreUnimportantViews";

    public static void updateSetting(boolean compressLayout) {
        try {
            Device.getUiDevice().setCompressedLayoutHeirarchy(compressLayout);
            Logger.info("Set the Compressed Layout Hierarchy");
        } catch (Exception e) {
            Logger.error("error setting compressLayoutHierarchy " + e.getMessage());
        }
    }
}
