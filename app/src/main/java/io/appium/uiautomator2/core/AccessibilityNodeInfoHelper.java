/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appium.uiautomator2.core;

import android.os.Bundle;
import android.graphics.Rect;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;

import io.appium.uiautomator2.utils.Logger;

/**
 * This class contains static helper methods to work with {@link AccessibilityNodeInfo}
 */
public class AccessibilityNodeInfoHelper {

    /**
     * Returns the node's bounds clipped to the size of the display
     *
     * @param width  pixel width of the display
     * @param height pixel height of the display
     * @return null if node is null, else a Rect containing visible bounds
     */
    static Rect getVisibleBoundsInScreen(AccessibilityNodeInfo node, int width, int height) {
        if (node == null) {
            return null;
        }
        // targeted node's bounds
        Rect nodeRect = new Rect();
        node.getBoundsInScreen(nodeRect);
        Rect displayRect = new Rect();
        displayRect.top = 0;
        displayRect.left = 0;
        displayRect.right = width;
        displayRect.bottom = height;
        nodeRect.intersect(displayRect);
        return nodeRect;
    }

    /**
     * Perform accessibility action ACTION_SET_PROGRESS on the node
     *
     * @param value desired progress value
     * @return true if action performed successfully
     */
    public static boolean setProgressValue(final AccessibilityNodeInfo node, final float value) {
        if (!node.getActionList().contains(AccessibilityAction.ACTION_SET_PROGRESS)) {
            Logger.debug("The element does not support ACTION_SET_PROGRESS action.");
            return false;
        }
        Logger.debug(String.format(
                "Trying to perform ACTION_SET_PROGRESS accessibility action with value %s", value));
        final Bundle args = new Bundle();
        args.putFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, value);
        return node.performAction(AccessibilityAction.ACTION_SET_PROGRESS.getId(), args);
    }

    /**
     * Truncate text to max text length of the node
     *
     * @param text text to truncate
     * @return truncated text
     */
    public static String truncateTextToMaxLength(final AccessibilityNodeInfo node, final String text) {
        final int maxTextLength = node.getMaxTextLength();
        if (maxTextLength > 0 && text.length() > maxTextLength) {
            Logger.debug(String.format(
                    "The element has limited text length. Its text will be truncated to %s chars.",
                    maxTextLength));
            return text.substring(0, maxTextLength);
        }
        return text;
    }
}
