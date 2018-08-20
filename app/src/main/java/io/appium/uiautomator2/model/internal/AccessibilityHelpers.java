package io.appium.uiautomator2.model.internal;

import android.os.SystemClock;
import android.view.accessibility.AccessibilityNodeInfo;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiAutomationElement;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class AccessibilityHelpers {
    public static final long AX_ROOT_RETRIEVAL_TIMEOUT = 10000;

    public static UiAutomationElement refreshUiElementTree() {
        return UiAutomationElement.newRootElement(getRootAccessibilityNodeInActiveWindow(),
                NotificationListener.getInstance().getToastMessage());
    }

    public static UiAutomationElement refreshUiElementTree(AccessibilityNodeInfo nodeInfo) {
        return UiAutomationElement.newRootElement(nodeInfo, null /*Toast Messages*/);
    }

    public static AccessibilityNodeInfo getRootAccessibilityNodeInActiveWindow() throws UiAutomator2Exception {
        return getRootAccessibilityNodeInActiveWindow(AX_ROOT_RETRIEVAL_TIMEOUT);
    }

    public static AccessibilityNodeInfo getRootAccessibilityNodeInActiveWindow(long timeoutMillis)
            throws UiAutomator2Exception {
        Device.waitForIdle();

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
                return root;
            }
        }
        throw new UiAutomator2Exception(String.format(
                "Timed out after %d milliseconds waiting for root AccessibilityNodeInfo",
                timeoutMillis));
    }
}
