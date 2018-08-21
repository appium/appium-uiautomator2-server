package io.appium.uiautomator2.model.internal;

import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiAutomationElement;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class AccessibilityHelpers {
    public static final long AX_ROOT_RETRIEVAL_TIMEOUT = 10000;
    private static final boolean MULTI_WINDOW = false;

    public static UiAutomationElement uiTreeWithToastElement(AccessibilityNodeInfo root) {
        return UiAutomationElement.rebuildForNewRoot(root,
                NotificationListener.getInstance().getToastMessage());
    }

    public static UiAutomationElement uiTreeWithRootElement(AccessibilityNodeInfo root) {
        return UiAutomationElement.rebuildForNewRoot(root, null);
    }

    public static AccessibilityNodeInfo getRootAccessibilityNodeInActiveWindow() throws UiAutomator2Exception {
        return getRootAccessibilityNodeInActiveWindow(AX_ROOT_RETRIEVAL_TIMEOUT);
    }

    public static AccessibilityNodeInfo getRootAccessibilityNodeInActiveWindow(long timeoutMillis)
            throws UiAutomator2Exception {
        Device.waitForIdle();
        // This call invokes `AccessibilityInteractionClient.getInstance().clearCache();` method
        // which resets the internal accessibility cache
        //noinspection EmptyCatchBlock
        try {
            UiAutomatorBridge.getInstance().getUiAutomation().setServiceInfo(null);
        } catch (Exception ign) {}

        long end = SystemClock.uptimeMillis() + timeoutMillis;
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
                return root;
            }
        }
        throw new UiAutomator2Exception(String.format(
                "Timed out after %d milliseconds waiting for root AccessibilityNodeInfo",
                timeoutMillis));
    }

    /**
     * Returns a list containing the root {@link AccessibilityNodeInfo}s for each active window
     */
    public static AccessibilityNodeInfo[] getWindowRoots(@Nullable AccessibilityNodeInfo activeWindowRoot)
            throws UiAutomator2Exception {
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
            AccessibilityNodeInfo node = activeWindowRoot == null ?
                    getRootAccessibilityNodeInActiveWindow() : activeWindowRoot;
            if (node == null) {
                throw new UiAutomator2Exception("Unable to get Root in Active window," +
                        " ERROR: null root node returned by UiTestAutomationBridge.");
            }
            ret.add(node);
        }
        return ret.toArray(new AccessibilityNodeInfo[ret.size()]);
    }
}
