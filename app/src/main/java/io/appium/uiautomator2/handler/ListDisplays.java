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

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.app.Service;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.DisplayModel;
import io.appium.uiautomator2.model.api.DisplayMetricsModel;
import io.appium.uiautomator2.utils.DisplayIdHelper;

/**
 * Get list of all displays.
 */
public class ListDisplays extends SafeRequestHandler {
    public ListDisplays(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        return new AppiumResponse(getSessionId(request), listDisplays(getDisplayManager()));
    }

    private List<DisplayModel> listDisplays(@Nullable DisplayManager displayManager) {
        if (displayManager == null) {
            return Collections.emptyList();
        }

        return Arrays.stream(displayManager.getDisplays())
                .map(this::createDisplayModel)
                .collect(Collectors.toList());
    }

    @Nullable
    private DisplayManager getDisplayManager() {
        return (DisplayManager) getInstrumentation().getTargetContext()
                .getSystemService(Service.DISPLAY_SERVICE);
    }

    private DisplayModel createDisplayModel(Display display) {
        int displayId = display.getDisplayId();
        Long physicalIdLong = DisplayIdHelper.getPhysicalDisplayId(display);
        String physicalId = physicalIdLong != null ? String.valueOf(physicalIdLong) : null;
        boolean isDefault = displayId == Display.DEFAULT_DISPLAY;

        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return new DisplayModel(
          displayId,
          physicalId,
          new DisplayMetricsModel(metrics),
          isDefault
        );
    }
}

