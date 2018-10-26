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
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiAutomationElement;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ReflectionUtils.clearAccessibilityCache;

public class AXWindowHelpers {
    public static final long AX_ROOT_RETRIEVAL_TIMEOUT = 10000;
    private static final boolean MULTI_WINDOW = false;
    private static AccessibilityNodeInfo currentActiveWindowRoot = null;

    public static void refreshRootAXNode() throws UiAutomator2Exception {
        Device.waitForIdle();
        clearAccessibilityCache();

        long end = SystemClock.uptimeMillis() + AX_ROOT_RETRIEVAL_TIMEOUT;
        while (end > SystemClock.uptimeMillis()) {
            AccessibilityNodeInfo root = null;
            try {
                root = UiAutomatorBridge.getInstance().getQueryController().getAccessibilityRootNode();
            } catch (Exception e) {
                /*
                 * Sometimes getAccessibilityRootNode() throws
                 * "java.lang.IllegalStateException: Cannot perform this action on a sealed instance."
                 * Ignore it and try to re-get root node.
                 */
                Logger.debug(String.format("'%s' exception was caught while invoking " +
                        "getRootAccessibilityNodeInActiveWindow() - ignoring it", e.getMessage()));
            }
            if (root != null) {
                UiAutomationElement.rebuildForNewRoot(root,
                        NotificationListener.getInstance().getToastMessage());
                currentActiveWindowRoot = root;
                return;
            }
        }
        throw new UiAutomator2Exception(String.format(
                "Timed out after %d milliseconds waiting for root AccessibilityNodeInfo",
                AX_ROOT_RETRIEVAL_TIMEOUT));
    }

    /**
     * Returns a list containing the root {@link AccessibilityNodeInfo}s for each active window
     */
    public static AccessibilityNodeInfo[] getWindowRoots() throws UiAutomator2Exception {
        List<AccessibilityNodeInfo> ret = new ArrayList<>();
        /*
         * TODO: MULTI_WINDOW is disabled, UIAutomatorViewer captures active window properties and
         * end users always relay on UIAutomatorViewer while writing tests.
         * If we enable MULTI_WINDOW it effects end users.
         * https://code.google.com/p/android/issues/detail?id=207569
         */
        if (CustomUiDevice.getInstance().getApiLevelActual() >= Build.VERSION_CODES.LOLLIPOP && MULTI_WINDOW) {
            // Support multi-window searches for API level 21 and up
            for (AccessibilityWindowInfo window : CustomUiDevice.getInstance().getInstrumentation()
                    .getUiAutomation().getWindows()) {
                AccessibilityNodeInfo root = window.getRoot();

                if (root == null) {
                    Logger.debug(String.format("Skipping null root node for window: %s", window.toString()));
                    continue;
                }
                ret.add(root);
            }
            // Prior to API level 21 we can only access the active window
        } else {
            AccessibilityNodeInfo node = currentActiveWindowRoot();
            if (node == null) {
                throw new UiAutomator2Exception("Unable to get Root in Active window," +
                        " ERROR: null root node returned by UiTestAutomationBridge.");
            }
            ret.add(node);
        }
        return ret.toArray(new AccessibilityNodeInfo[ret.size()]);
    }

    public static synchronized AccessibilityNodeInfo currentActiveWindowRoot() {
        if (currentActiveWindowRoot == null) {
            refreshRootAXNode();
        }
        return currentActiveWindowRoot;
    }
}
