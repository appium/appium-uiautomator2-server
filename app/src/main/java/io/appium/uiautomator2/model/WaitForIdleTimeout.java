package io.appium.uiautomator2.model;

import android.support.test.uiautomator.Configurator;

import io.appium.uiautomator2.utils.Logger;

public class WaitForIdleTimeout {

  public static final String SETTING_NAME = "waitForIdleTimeout";

  public static void updateSetting(int timeout) {
    try {
      Configurator.getInstance().setWaitForIdleTimeout(timeout);
      Logger.debug("Set waitForIdleTimeout to: " + timeout);
    } catch (Exception e) {
      Logger.error("Error setting waitForIdleTimeout " + e.getMessage());
    }
  }
}
