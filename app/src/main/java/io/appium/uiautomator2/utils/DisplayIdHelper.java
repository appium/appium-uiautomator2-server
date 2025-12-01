package io.appium.uiautomator2.utils;

import static io.appium.uiautomator2.utils.ReflectionUtils.*;

import android.annotation.SuppressLint;
import android.view.Display;

import androidx.annotation.Nullable;

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
            if (address == null) {
                // Emulators may return null.
                // Display address, or null if none.
                // https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/DisplayInfo.java;l=83-86;drc=61197364367c9e404c7da6900658f1b16c42d0da
                return null;
            }
            return (long) invoke(getMethod(address.getClass(), "getPhysicalDisplayId"), address);

        } catch (UiAutomator2Exception e) {
            Logger.error("Unable to retrieve physicalDisplayId", e);
        }

        return null;
    }

}
