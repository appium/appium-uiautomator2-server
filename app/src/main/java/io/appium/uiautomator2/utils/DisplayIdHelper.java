package io.appium.uiautomator2.utils;

import android.annotation.SuppressLint;
import android.view.Display;

import java.lang.reflect.Method;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

public class DisplayIdHelper {
    /**
     * Attempts to find the physical display ID that corresponds to the given Display.
     *
     * @param display android.view.Display instance
     * @return matching physical display ID, or -1 if not found
     */
    public static long getPhysicalDisplayId(Display display) {
        long physicalDisplayId = tryGetUsingAddress(display);

        return physicalDisplayId;
    }

    @SuppressLint("BlockedPrivateApi")
    private static long tryGetUsingAddress(Display display) {
        try {
            // Method is marked as public with @hide in AOSP https://cs.android.com/android/platform/superproject/main/+/main:frameworks/base/core/java/android/view/Display.java;l=836?q=Display
            Method getAddress = ReflectionUtils.getMethod(display.getClass(), "getAddress");

            Object address = getAddress.invoke(display);

            Method getPhysicalDisplayId = ReflectionUtils.getMethod(address.getClass(), "getPhysicalDisplayId");

            long physicalDisplayId = (long) getPhysicalDisplayId.invoke(address);

            return physicalDisplayId;

        } catch (UiAutomator2Exception e) {
            Logger.error("Required method not found", e);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
            Logger.error("Reflection access error", e);
        } catch (Exception e) {
            Logger.error("Unexpected error while resolving physical display ID", e);
        }

        return -1;
    }

}
