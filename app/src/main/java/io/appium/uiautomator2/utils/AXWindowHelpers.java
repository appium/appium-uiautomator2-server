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
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.EnableMultiWindows;
import io.appium.uiautomator2.model.settings.Settings;

public class AXWindowHelpers {
    private static final long AX_ROOT_RETRIEVAL_TIMEOUT_MS = 10000;
    private static AccessibilityNodeInfo[] cachedWindowRoots = null;

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

    public static void refreshAccessibilityCache() {
        Device.waitForIdle();
        clearAccessibilityCache();
        cachedWindowRoots = null;
    }

    private static AccessibilityNodeInfo getActiveWindowRoot() {
        long start = SystemClock.uptimeMillis();
        while (SystemClock.uptimeMillis() - start < AX_ROOT_RETRIEVAL_TIMEOUT_MS) {
            try {
                AccessibilityNodeInfo root = UiAutomatorBridge.getInstance().getAccessibilityRootNode();
                if (root != null) {
                    return root;
                }
            } catch (Exception e) {
                /*
                 * Sometimes getAccessibilityRootNode() throws
                 * "java.lang.IllegalStateException: Cannot perform this action on a sealed instance."
                 * Ignore it and try to re-get root node.
                 */
                Logger.info("An exception was caught while looking for " +
                        "the root of the active window. Ignoring it", e);
            }
        }
        throw new UiAutomator2Exception(String.format(
                "Timed out after %dms waiting for the root AccessibilityNodeInfo in the active window. " +
                        "Make sure the active window is not constantly hogging the main UI thread " +
                        "(e.g. the application is being idle long enough), so the accessibility " +
                        "manager could do its work", SystemClock.uptimeMillis() - start));
    }

    private static AccessibilityNodeInfo[] getWindowRoots() {
        List<AccessibilityNodeInfo> result = new ArrayList<>();
        List<AccessibilityWindowInfo> windows = CustomUiDevice.getInstance()
                .getInstrumentation()
                .getUiAutomation()
                .getWindows();
        for (AccessibilityWindowInfo window : windows) {
            AccessibilityNodeInfo root = window.getRoot();
            if (root == null) {
                Logger.info(String.format("Skipping null root node for window: %s", window.toString()));
                continue;
            }
            result.add(root);
        }
        return result.toArray(new AccessibilityNodeInfo[0]);
    }

    public static AccessibilityNodeInfo[] getCachedWindowRoots() {
        if (cachedWindowRoots == null) {
            // Multi-window searches are supported since API level 21
            boolean shouldRetrieveAllWindowRoots = CustomUiDevice.getInstance()
                    .getApiLevelActual() >= Build.VERSION_CODES.LOLLIPOP
                    && Settings.get(EnableMultiWindows.class).getValue();
            /*
             * ENABLE_MULTI_WINDOWS is disabled by default
             * because UIAutomatorViewer captures active window properties and
             * end users always rely on its output while writing their tests.
             * https://code.google.com/p/android/issues/detail?id=207569
             */
            cachedWindowRoots = shouldRetrieveAllWindowRoots
                    ? getWindowRoots()
                    : new AccessibilityNodeInfo[]{getActiveWindowRoot()};
        }
        return cachedWindowRoots;
    }
}
