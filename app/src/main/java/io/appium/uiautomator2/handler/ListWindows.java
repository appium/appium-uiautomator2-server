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
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

import android.app.Service;
import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.SparseArray;
import android.view.Display;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.appium.uiautomator2.common.exceptions.TakeScreenshotException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.ListWindowsModel;
import io.appium.uiautomator2.model.api.WindowModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.DisplayIdHelper;
import io.appium.uiautomator2.utils.GlobMatcher;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.ScreenshotHelper;
import io.appium.uiautomator2.utils.StringHelpers;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

/**
 * Get list of windows on all displays.
 * For Android API 30+ (R), uses getWindowsOnAllDisplays().
 * For older APIs, uses getWindows().
 */
public class ListWindows extends SafeRequestHandler {
    public ListWindows(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ListWindowsModel model = toModel(request, ListWindowsModel.class);
        DisplayManager displayManager = getDisplayManager();
        List<WindowModel> windows = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                ? listWindowsOnApiLevel30(displayManager)
                : listWindowsOnApiLevelBelow30(displayManager);

        if (model.filters != null && !model.filters.isEmpty()) {
            windows = filterWindows(windows, model.filters);
        }

        return new AppiumResponse(getSessionId(request), windows);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private List<WindowModel> listWindowsOnApiLevel30(@Nullable DisplayManager displayManager) {
        SparseArray<List<AccessibilityWindowInfo>> windowsOnAllDisplays = CustomUiDevice
                .getInstance()
                .getUiAutomation()
                .getWindowsOnAllDisplays();

        return IntStream.range(0, windowsOnAllDisplays.size())
                .mapToObj(windowsOnAllDisplays::keyAt)
                .filter(displayId -> windowsOnAllDisplays.get(displayId) != null)
                .flatMap(displayId -> {
                    Long physicalDisplayId = getPhysicalDisplayId(displayManager, displayId);
                    return windowsOnAllDisplays.get(displayId).stream()
                            .map(window -> createWindowModel(window, displayId, physicalDisplayId));
                })
                .collect(Collectors.toList());
    }

    private List<WindowModel> listWindowsOnApiLevelBelow30(@Nullable DisplayManager displayManager) {
        List<AccessibilityWindowInfo> windowList = CustomUiDevice
                .getInstance()
                .getUiAutomation()
                .getWindows();

        // For API < 30, getWindows() only returns windows from the default display
        int displayId = Display.DEFAULT_DISPLAY;
        Long physicalDisplayId = getPhysicalDisplayId(displayManager, displayId);

        return windowList.stream()
                .map(window -> createWindowModel(window, displayId, physicalDisplayId))
                .collect(Collectors.toList());
    }

    @Nullable
    private DisplayManager getDisplayManager() {
        return (DisplayManager) getInstrumentation().getTargetContext()
                .getSystemService(Service.DISPLAY_SERVICE);
    }

    @Nullable
    private Long getPhysicalDisplayId(@Nullable DisplayManager displayManager, int displayId) {
        if (displayManager == null) {
            return null;
        }
        Display display = displayManager.getDisplay(displayId);
        return display != null ? DisplayIdHelper.getPhysicalDisplayId(display) : null;
    }

    private WindowModel createWindowModel(
        AccessibilityWindowInfo window,
        int displayId,
        @Nullable Long physicalDisplayId
    ) {
        Integer windowId = window.getId();
        Rect bounds = new Rect();
        window.getBoundsInScreen(bounds);

        String packageName = null;
        AccessibilityNodeInfo root = window.getRoot();
        if (root != null) {
            packageName = StringHelpers.charSequenceToNullableString(root.getPackageName());
        }

        String screenshot = takeWindowScreenshot(window);

        return new WindowModel(windowId, displayId, physicalDisplayId, bounds, packageName, screenshot);
    }

    @Nullable
    private String takeWindowScreenshot(AccessibilityWindowInfo windowInfo) {
        // Screenshot API is only available from API 34
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return null;
        }

        try {
            Window window = getWindowFromAccessibilityWindowInfo(windowInfo);
            if (window == null) {
                return null;
            }

            UiAutomation uiAutomation = CustomUiDevice.getInstance().getUiAutomation();
            Bitmap screenshot = uiAutomation.takeScreenshot(window);

            if (screenshot == null) {
                return null;
            }

            try {
                return ScreenshotHelper.bitmapToBase64Png(screenshot);
            } catch (TakeScreenshotException e) {
                Logger.debug("Failed to convert screenshot to base64", e);
                return null;
            } finally {
                screenshot.recycle();
            }
        } catch (Exception e) {
            Logger.debug("Failed to take window screenshot", e);
            return null;
        }
    }

    @Nullable
    private Window getWindowFromAccessibilityWindowInfo(AccessibilityWindowInfo windowInfo) {
        try {
            // AccessibilityWindowInfo has a private field "mWindow" that contains the Window object
            Object window = getField(AccessibilityWindowInfo.class, "mWindow", windowInfo);
            return window instanceof Window ? (Window) window : null;
        } catch (Exception e) {
            Logger.debug("Failed to get Window from AccessibilityWindowInfo", e);
            return null;
        }
    }

    private List<WindowModel> filterWindows(List<WindowModel> windows, Map<String, Object> filters) {
        return windows.stream()
                .filter(window -> matchesFilters(window, filters))
                .collect(Collectors.toList());
    }

    private boolean matchesFilters(WindowModel window, Map<String, Object> filters) {
        // Apply AND logic: all provided filters must match
        return filters.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .allMatch(entry -> matchesFilter(window, entry.getKey(), entry.getValue()));
    }

    private boolean matchesFilter(WindowModel window, String key, Object value) {
        switch (key) {
            case "packageName":
                return matchesPackageName(window.packageName, value);
            case "windowId":
                return matchesInteger(window.windowId, value);
            case "displayId":
                return matchesInteger(window.displayId, value);
            case "physicalDisplayId":
                return matchesLong(window.physicalDisplayId, value);
            default:
                Logger.debug(String.format("Unknown filter key: %s", key));
                return true; // Unknown filters don't exclude the window
        }
    }

    private boolean matchesPackageName(String packageName, Object filterValue) {
        if (packageName == null) {
            return false;
        }

        String filterStr = filterValue.toString();
        // Support wildcard matching using GlobMatcher
        return GlobMatcher.matches(filterStr, packageName);
    }

    private boolean matchesInteger(Integer value, Object filterValue) {
        if (value == null) {
            return false;
        }

        if (filterValue instanceof Number) {
            return value.equals(((Number) filterValue).intValue());
        }

        try {
            int filterInt = Integer.parseInt(filterValue.toString());
            return value.equals(filterInt);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean matchesLong(Long value, Object filterValue) {
        if (value == null) {
            return false;
        }

        if (filterValue instanceof Number) {
            return value.equals(((Number) filterValue).longValue());
        }

        try {
            long filterLong = Long.parseLong(filterValue.toString());
            return value.equals(filterLong);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

