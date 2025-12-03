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

import static io.appium.uiautomator2.utils.ReflectionUtils.*;

import android.annotation.SuppressLint;
import android.app.UiAutomation;
import android.os.ParcelFileDescriptor;
import android.view.Display;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class DisplayIdHelper {
    private static final Pattern VIRTUAL_DISPLAY_ID_PATTERN = Pattern.compile("Virtual Display (\\d+)");
    private static final Pattern DISPLAY_NAME_PATTERN = Pattern.compile("name=\"([^\"]+)\"");

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

    /**
     * Parses 'dumpsys SurfaceFlinger --displays' output to extract virtual display IDs mapped by display name.
     * This should be called once per listDisplays/listWindows operation to avoid multiple shell invocations.
     * Note: '--displays' option works over API Level 33 to print virtual displays. '--display-id' option prints
     * virtual displays only for API Level 36+, thus this logic currently uses '--displays'.
     *
     * @return Map of display name to virtual display ID (as string), empty map if parsing fails
     */
    public static Map<String, String> parseVirtualDisplays() {
        Map<String, String> virtualDisplayMap = new HashMap<>();
        UiAutomation automation = CustomUiDevice.getInstance().getUiAutomation();

        try (
                ParcelFileDescriptor pfd = automation.executeShellCommand("dumpsys SurfaceFlinger --displays");
                InputStream is = new FileInputStream(pfd.getFileDescriptor());
                BufferedReader br = new BufferedReader(new InputStreamReader(is))
        ) {
            String currentVirtualId = null;
            String line;
            while ((line = br.readLine()) != null) {
                Matcher idMatcher = VIRTUAL_DISPLAY_ID_PATTERN.matcher(line);
                if (idMatcher.find()) {
                    currentVirtualId = idMatcher.group(1);
                } else if (currentVirtualId != null) {
                    Matcher nameMatcher = DISPLAY_NAME_PATTERN.matcher(line);
                    if (nameMatcher.find()) {
                        String displayName = nameMatcher.group(1);
                        virtualDisplayMap.put(displayName, currentVirtualId);
                        currentVirtualId = null;
                    }
                }
            }
        } catch (Exception e) {
            Logger.debug("Unable to parse virtual displays from SurfaceFlinger", e);
        }

        return virtualDisplayMap;
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
