package io.appium.uiautomator2.utils;

import static io.appium.uiautomator2.utils.ReflectionUtils.*;

import android.annotation.SuppressLint;
import android.view.Display;

import androidx.annotation.Nullable;

import java.lang.reflect.Method;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

public class DisplayIdHelper {
    /**
     * Attempts to find the physical display ID that corresponds to the given Display.
     *
     * @param display android.view.Display instance
     * @return matching physical display ID, or null if not found
     */
    @Nullable
    public static Long getPhysicalDisplayId(Display display) {
        return tryGetUsingAddress(display);
    }

    @Nullable
    @SuppressLint("BlockedPrivateApi")
    private static Long tryGetUsingAddress(Display display) {
        try {
            // Method is marked as public with @hide in AOSP https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/Display.java;l=836?q=Display
            Object address = invoke(getMethod(display.getClass(), "getAddress"), display);
            long physicalDisplayId = (long) invoke(getMethod(address.getClass(), "getPhysicalDisplayId"), address);

            return physicalDisplayId;

        } catch (UiAutomator2Exception e) {
            Logger.error("Unable to retrieve physicalDisplayId", e);
        }

        return null;
    }

}
