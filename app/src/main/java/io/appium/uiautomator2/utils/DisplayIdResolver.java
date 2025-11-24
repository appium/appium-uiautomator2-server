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

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.view.Display;

import androidx.test.core.app.ApplicationProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;

public class DisplayIdResolver {
    private static final String TAG = DisplayIdResolver.class.getSimpleName();
    private static final Map<Integer, Integer> displayIndexToIdCache = new ConcurrentHashMap<>();
    private static final Map<Integer, Integer> displayIdToIndexCache = new ConcurrentHashMap<>();
    private static final Map<Integer, Long> displayIndexToPhysicalIdCache = new ConcurrentHashMap<>();

    /**
     * Resolves a display index (0 = main, 1+ = secondary) to display ID.
     *
     * @param displayIndex Appium display index
     * @return Android display ID
     */
    public static int resolveDisplayId(int displayIndex) {
        if (displayIndexToIdCache.containsKey(displayIndex)) {
            return displayIndexToIdCache.get(displayIndex);
        }

        Context context = ApplicationProvider.getApplicationContext();
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = dm.getDisplays();

        if (displayIndex < 0 || displayIndex >= displays.length) {
            throw new InvalidArgumentException(String.format(
                    "Invalid display index %d. Device has %d display(s).", displayIndex, displays.length));
        }

        Display display = displays[displayIndex];
        int displayId = display.getDisplayId();

        Logger.info(TAG, String.format("Caching display index=%d -> displayId=%d, name=%s",
                displayIndex, displayId, display.getName()));

        displayIndexToIdCache.put(displayIndex, displayId);
        displayIdToIndexCache.put(displayId, displayIndex);
        return displayId;
    }

    /**
     * Resolves a display ID to display index.
     *
     * @param displayId Android display ID
     * @return Appium display index
     */
    public static int resolveDisplayIndex(int displayId) {
        if (displayIdToIndexCache.containsKey(displayId)) {
            return displayIdToIndexCache.get(displayId);
        }

        Context context = ApplicationProvider.getApplicationContext();
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = dm.getDisplays();

        for (int displayIndex = 0; displayIndex < displays.length; displayIndex++) {
            Display display = displays[displayIndex];
            if (display.getDisplayId() == displayId) {
                Logger.info(TAG, String.format("Caching displayId=%d -> display index=%d, name=%s",
                        displayId, displayIndex, display.getName()));

                displayIdToIndexCache.put(displayId, displayIndex);
                displayIndexToIdCache.put(displayIndex, displayId);
                return displayIndex;
            }
        }

        throw new InvalidArgumentException(String.format(
                "Invalid display ID %d. Device has %d display(s).", displayId, displays.length));
    }

    /**
     * Resolves a display index to physical display ID (for screenshot commands).
     *
     * @param displayIndex Appium display index
     * @return Android physical display ID
     */
    public static Long resolvePhysicalDisplayId(int displayIndex) {
        if (displayIndexToPhysicalIdCache.containsKey(displayIndex)) {
            return displayIndexToPhysicalIdCache.get(displayIndex);
        }

        Context context = ApplicationProvider.getApplicationContext();
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = dm.getDisplays();

        if (displayIndex < 0 || displayIndex >= displays.length) {
            throw new InvalidArgumentException(String.format(
                    "Invalid display index %d. Device has %d display(s).", displayIndex, displays.length));
        }

        Display display = displays[displayIndex];
        Long physicalDisplayId = DisplayIdHelper.getPhysicalDisplayId(display);

        if (physicalDisplayId == null) {
            physicalDisplayId = (long) display.getDisplayId();
            Logger.debug(TAG, String.format("Cannot get physical display ID for display index %d, using display ID as fallback", displayIndex));
        }

        Logger.info(TAG, String.format("Caching display index=%d -> physicalDisplayId=%d, name=%s",
                displayIndex, physicalDisplayId, display.getName()));

        displayIndexToPhysicalIdCache.put(displayIndex, physicalDisplayId);
        return physicalDisplayId;
    }

    /**
     * Gets the number of available displays.
     *
     * @return number of displays
     */
    public static int getDisplayCount() {
        Context context = ApplicationProvider.getApplicationContext();
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        return dm.getDisplays().length;
    }

    /**
     * Clears the display ID cache. Useful for test or display hot-swap.
     */
    public static void clearCache() {
        displayIndexToIdCache.clear();
        displayIdToIndexCache.clear();
        displayIndexToPhysicalIdCache.clear();
        Logger.info(TAG, "DisplayIdResolver cache cleared");
    }
}