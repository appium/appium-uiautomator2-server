/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils;

import androidx.annotation.Nullable;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import java.util.Objects;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.UiObject2Element;
import io.appium.uiautomator2.model.UiObjectElement;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.WaitForIdleTimeout;

public abstract class Device {
    public static UiDevice getUiDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public static AndroidElement getAndroidElement(String id, Object element, boolean isSingleMatch,
                                                   @Nullable By by, @Nullable String contextId) {
        if (element instanceof UiObject2) {
            return new UiObject2Element(id, (UiObject2) element, isSingleMatch, by, contextId);
        } else if (element instanceof UiObject) {
            return new UiObjectElement(id, (UiObject) element, isSingleMatch, by, contextId);
        } else {
            throw new UiAutomator2Exception("Unknown Element type: " + element.getClass().getName());
        }
    }

    public static AndroidElement getAndroidElement(String id, Object element, boolean isSingleMatch,
                                                   @Nullable By by) {
        return getAndroidElement(id, element, isSingleMatch, by, null);
    }

    public static AndroidElement getAndroidElement(String id, Object element, boolean isSingleMatch)
            throws UiAutomator2Exception {
        return getAndroidElement(id, element, isSingleMatch, null, null);
    }

    public static void scrollToElement(@Nullable UiScrollable origin, UiSelector selector,
                                       @Nullable Integer maxSwipes) throws UiObjectNotFoundException {
        UiScrollable scrollableOrigin = origin == null
                ? new UiScrollable(new UiSelector().scrollable(true).instance(0))
                : origin;
        Logger.debug(String.format("Using %s as scrolling origin", scrollableOrigin.getSelector()));
        String hScrollViewClassName = android.widget.HorizontalScrollView.class.getName();
        if (Objects.equals(scrollableOrigin.getClassName(), hScrollViewClassName)) {
            scrollableOrigin.setAsHorizontalList();
        }

        int originalMaxSwipes = scrollableOrigin.getMaxSearchSwipes();
        if (maxSwipes != null && maxSwipes > 0) {
            scrollableOrigin.setMaxSearchSwipes(maxSwipes);
        }
        try {
            if (!scrollableOrigin.scrollIntoView(selector)) {
                throw new UiObjectNotFoundException(String.format("Cannot scroll to %s", selector));
            }
        } finally {
            // The number of search swipes is held in a static property of the UiScrollable class.
            // Whenever a non-default number of search swipes is used during the scroll, we must
            // always restore the setting after the operation.
            scrollableOrigin.setMaxSearchSwipes(originalMaxSwipes);
        }
    }

    public static boolean back() {
        return getUiDevice().pressBack();
    }

    public static void waitForIdle() {
        long timeoutMs = ((WaitForIdleTimeout) Settings.WAIT_FOR_IDLE_TIMEOUT.getSetting()).getValue();
        if (timeoutMs <= 0) {
            Logger.info("Idle timeout is not greater than zero. Skipping the wait");
            return;
        }

        Logger.info(String.format("Waiting up to %sms for the device to idle", timeoutMs));
        try {
            /*
             * In some cases UiAutomator2 framework is throwing an exception
             * while calling UiDevice.waitForIdle(), which causes the server to unexpectedly fail.
             * For more info please refer https://code.google.com/p/android/issues/detail?id=73297
             */
            getUiDevice().waitForIdle(timeoutMs);
        } catch (Exception e) {
            Logger.error(String.format("Unable to wait %sms for the device to idle", timeoutMs), e);
        }
    }
}
