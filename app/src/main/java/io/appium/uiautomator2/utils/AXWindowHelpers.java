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

import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.EnableMultiWindows;
import io.appium.uiautomator2.model.settings.EnableTopmostWindowFromActivePackage;
import io.appium.uiautomator2.model.settings.Settings;

public class AXWindowHelpers {
    private static final long AX_ROOT_RETRIEVAL_TIMEOUT_MS = 10000;
    private static final long AX_ROOT_RETRIEVAL_INTERVAL_MS = 250;
    private static final Map<String, AccessibilityNodeInfo[]> CACHED_WINDOW_ROOTS = new HashMap<>();

    public static void resetAccessibilityCache() {
        Device.waitForIdle();
        clearAccessibilityCache();
        synchronized (CACHED_WINDOW_ROOTS) {
            CACHED_WINDOW_ROOTS.clear();
        }
    }

    public static AccessibilityNodeInfo[] getCachedWindowRoots() {
        boolean shouldRetrieveAllWindowRoots = Settings.get(EnableMultiWindows.class).getValue();
        // Multi-window retrieval is needed to search the topmost window from active package.
        boolean shouldRetrieveTopmostWindowRootFromActivePackage = Settings.get(
                EnableTopmostWindowFromActivePackage.class
        ).getValue();
        String cacheKey = makeCacheKey(
                shouldRetrieveAllWindowRoots,
                shouldRetrieveTopmostWindowRootFromActivePackage
        );
        synchronized (CACHED_WINDOW_ROOTS) {
            if (CACHED_WINDOW_ROOTS.containsKey(cacheKey)) {
                return CACHED_WINDOW_ROOTS.get(cacheKey);
            }

            // Either one of above settings has changed or we did not have cached windows yet
            CACHED_WINDOW_ROOTS.clear();
            AccessibilityNodeInfo[] newRoots = retrieveWindowRoots(
                    shouldRetrieveAllWindowRoots,
                    shouldRetrieveTopmostWindowRootFromActivePackage
            );
            CACHED_WINDOW_ROOTS.put(cacheKey, newRoots);
            return newRoots;
        }
    }

    private static AccessibilityNodeInfo[] retrieveWindowRoots(
            boolean shouldRetrieveAllWindowRoots,
            boolean shouldRetrieveTopmostWindowRootFromActivePackage
    ) {
        if (shouldRetrieveAllWindowRoots) {
            return getWindowRoots();
        }
        return new AccessibilityNodeInfo[] {
            shouldRetrieveTopmostWindowRootFromActivePackage
                    ? getTopmostWindowRootFromActivePackage()
                    : getActiveWindowRoot()
        };
    }

    private static AccessibilityNodeInfo getActiveWindowRoot() {
        long start = SystemClock.uptimeMillis();
        while (SystemClock.uptimeMillis() - start < AX_ROOT_RETRIEVAL_TIMEOUT_MS) {
            try {
                AccessibilityNodeInfo rootNode = UiAutomatorBridge.getInstance()
                        .getUiAutomation()
                        .getRootInActiveWindow();
                if (rootNode != null) {
                    return rootNode;
                }
            } catch (Exception e) {
                Logger.info("An exception was caught while looking for " +
                        "the root of the active window. Ignoring it", e);
            }
            SystemClock.sleep(AX_ROOT_RETRIEVAL_INTERVAL_MS);
        }
        throw new UiAutomator2Exception(String.format(
                "Timed out after %dms waiting for the root AccessibilityNodeInfo in the active window. " +
                        "Make sure the active window is not constantly hogging the main UI thread " +
                        "(e.g. the application is being idle long enough), so the accessibility " +
                        "manager could do its work", SystemClock.uptimeMillis() - start));
    }

    private static List<AccessibilityWindowInfo> getWindows() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return CustomUiDevice.getInstance().getUiAutomation().getWindows();
        }

        int currentDisplayId = UiAutomatorBridge.getInstance().getCurrentDisplayId();

        SparseArray<List<AccessibilityWindowInfo>> windowsOnAllDisplays = CustomUiDevice
                .getInstance()
                .getUiAutomation()
                .getWindowsOnAllDisplays();

        if (!windowsOnAllDisplays.contains(currentDisplayId)) {
            return Collections.emptyList();
        }

        return windowsOnAllDisplays.get(currentDisplayId);
    }

    private static AccessibilityNodeInfo[] getWindowRoots() {
        return getWindows().stream()
                .map(window -> {
                    AccessibilityNodeInfo root = window.getRoot();
                    if (root == null) {
                        Logger.info(String.format("Skipping null root node for window: %s", window));
                    }
                    return root;
                })
                .filter(Objects::nonNull)
                .toArray(AccessibilityNodeInfo[]::new);
    }

    private static AccessibilityNodeInfo getTopmostWindowRootFromActivePackage() {
        CharSequence activeRootPackageName = Objects.requireNonNull(getActiveWindowRoot().getPackageName());

        List<AccessibilityWindowInfo> windows = getWindows();
        windows.sort((w1, w2) -> {
            return Integer.compare(w2.getLayer(), w1.getLayer()); // descending order
        });
        return windows.stream()
                .map(AccessibilityWindowInfo::getRoot)
                .filter(Objects::nonNull)
                .filter(root -> Objects.equals(root.getPackageName(), activeRootPackageName))
                .findFirst()
                .orElseThrow(() -> new UiAutomator2Exception(
                        String.format("Unable to find the active topmost window associated with %s package",
                                activeRootPackageName)));
    }

    /**
     * Clears the in-process Accessibility cache, removing any stale references. Because the
     * AccessibilityInteractionClient singleton stores copies of AccessibilityNodeInfo instances,
     * calls to public APIs such as `recycle` do not guarantee cached references get updated. See
     * the android.view.accessibility AIC and ANI source code for more information.
     */
    private static void clearAccessibilityCache() {
        try {
            // This call invokes `AccessibilityInteractionClient.getInstance().clearCache();` method
            UiAutomatorBridge.getInstance().getUiAutomation().setServiceInfo(null);
        } catch (NullPointerException npe) {
            // it is fine
            // ignore
        } catch (Exception e) {
            Logger.warn("Failed to clear Accessibility Node cache", e);
        }
    }

    private static String makeCacheKey(
            boolean shouldRetrieveAllWindowRoots,
            boolean shouldRetrieveTopmostWindowRootFromActivePackage
    ) {
        return String.format(
                "%s:%s",
                shouldRetrieveAllWindowRoots,
                shouldRetrieveTopmostWindowRootFromActivePackage
        );
    }
}
