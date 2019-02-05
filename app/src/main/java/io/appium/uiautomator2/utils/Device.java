package io.appium.uiautomator2.utils;

import android.os.RemoteException;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.UiObject2Element;
import io.appium.uiautomator2.model.UiObjectElement;

public abstract class Device {

    public static UiDevice getUiDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public static AndroidElement getAndroidElement(String id, Object element, By by)
            throws UiAutomator2Exception {
        if (element instanceof UiObject2) {
            return new UiObject2Element(id, (UiObject2) element, by);
        } else if (element instanceof UiObject) {
            return new UiObjectElement(id, (UiObject) element, by);
        } else {
            throw new UiAutomator2Exception("Unknown Element type: " + element.getClass().getName());
        }
    }

    public static void wake() throws RemoteException {
        getUiDevice().wakeUp();
    }

    public static void scrollToElement(UiSelector selector, int maxSwipes)
            throws UiObjectNotFoundException {
        UiScrollable uiScrollable = new UiScrollable(new UiSelector().scrollable(true).instance(0));
        String uiScrollableClassName = uiScrollable.getClassName();
        String hScrollViewClassName = android.widget.HorizontalScrollView.class.getName();
        int defaultMaxSwipes = uiScrollable.getMaxSearchSwipes();

        if (java.util.Objects.equals(uiScrollableClassName, hScrollViewClassName)) {
            uiScrollable.setAsHorizontalList();
        }

        if (maxSwipes > 0) {
            uiScrollable.setMaxSearchSwipes(maxSwipes);
        }

        try {
            if (!uiScrollable.scrollIntoView(selector)) {
                throw new UiObjectNotFoundException("Cannot scroll to the element.");
            }
        }
        finally {
            // The number of search swipes is held in a static property of the UiScrollable class.
            // Whenever a non-default number of search swipes is used during the scroll, we must
            // always restore the setting after the operation.
            uiScrollable.setMaxSearchSwipes(defaultMaxSwipes);
        }
    }

    public static boolean back() {
        return getUiDevice().pressBack();
    }

    /**
     * reason for explicit method, in some cases google UiAutomator2 throwing exception
     * while calling waitForIdle() which is causing appium UiAutomator2 server to fall in
     * unexpected behaviour.
     * for more info please refer
     * https://code.google.com/p/android/issues/detail?id=73297
     */
    public static void waitForIdle() {
        try {
            getUiDevice().waitForIdle();
        } catch (Exception e) {
            Logger.error("Unable wait for AUT to idle");
        }
    }

    public static void waitForIdle(long timeInMS) {
        try {
            getUiDevice().waitForIdle(timeInMS);
        } catch (Exception e) {
            Logger.error(String.format("Unable wait %s for AUT to idle", timeInMS));
        }
    }
}
