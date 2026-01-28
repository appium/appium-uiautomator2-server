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

package io.appium.uiautomator2.handler;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.test.core.app.ApplicationProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.DisplayIdHelper;
import io.appium.uiautomator2.utils.Logger;

public class GetDisplayInfo extends SafeRequestHandler {
    private static final String TAG = GetDisplayInfo.class.getSimpleName();

    public GetDisplayInfo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info(TAG, "Getting display information");

        Context context = ApplicationProvider.getApplicationContext();
        DisplayManager dm = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = dm.getDisplays();

        List<Map<String, Object>> displayInfos = new ArrayList<>();
        for (int displayIndex = 0; displayIndex < displays.length; displayIndex++) {
            Display display = displays[displayIndex];

            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);

            Map<String, Object> displayInfo = new HashMap<>();
            // Appium display index (0, 1, 2...)
            displayInfo.put("displayIndex", displayIndex);

            // Android Display ID (DisplayManager's ID)
            int displayId = display.getDisplayId();
            displayInfo.put("displayId", displayId);

            // Physical display ID
            Long physicalDisplayId = DisplayIdHelper.getPhysicalDisplayId(display);
            if (physicalDisplayId != null) {
                displayInfo.put("physicalDisplayId", physicalDisplayId);
            } else {
                // If physical ID cannot be obtained, use display ID as fallback
                displayInfo.put("physicalDisplayId", (long) displayId);
                Logger.debug(TAG, String.format("Cannot get physical display ID for display index %d, using display ID as fallback", displayIndex));
            }

            // Basic display information
            displayInfo.put("width", displayMetrics.widthPixels);
            displayInfo.put("height", displayMetrics.heightPixels);
            displayInfo.put("isDefault", displayId == Display.DEFAULT_DISPLAY);
            displayInfo.put("name", display.getName() != null ? display.getName() : "Display " + displayIndex);

            displayInfos.add(displayInfo);

            Logger.info(TAG, String.format("Display index=%d, displayId=%d, physicalDisplayId=%s: %dx%d, name=%s",
                    displayIndex, displayId, displayInfo.get("physicalDisplayId"),
                    displayMetrics.widthPixels, displayMetrics.heightPixels,
                    display.getName()));
        }

        Logger.info(TAG, String.format("Found %d displays", displayInfos.size()));
        return new AppiumResponse(getSessionId(request), displayInfos);
    }
}