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

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Range;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiDevice;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.SimpleBoundsCalculation;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToString;

/**
 * This class contains static helper methods to work with {@link AccessibilityNodeInfo}
 */
public class AccessibilityNodeInfoHelpers {
    // https://github.com/appium/appium/issues/12892
    private final static int MAX_DEPTH = 70;

    @Nullable
    public static Range<Integer> getSelectionRange(@Nullable AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return null;
        }

        int selectionStart = nodeInfo.getTextSelectionStart();
        int selectionEnd = nodeInfo.getTextSelectionEnd();
        if (selectionStart >= 0 && selectionStart != selectionEnd) {
            return new Range<>(selectionStart, selectionEnd);
        }
        return null;
    }

    @Nullable
    public static String getPackageName(@Nullable AccessibilityNodeInfo nodeInfo) {
        return nodeInfo == null ? null : charSequenceToNullableString(nodeInfo.getPackageName());
    }

    public static boolean isPassword(@Nullable AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null && nodeInfo.isPassword();
    }

    public static boolean isVisible(@Nullable AccessibilityNodeInfo nodeInfo) {
        return nodeInfo != null && nodeInfo.isVisibleToUser();
    }

    @Nullable
    public static String getText(@Nullable AccessibilityNodeInfo nodeInfo, boolean replaceNull) {
        if (nodeInfo == null) {
            return replaceNull ? "" : null;
        }

        if (nodeInfo.getRangeInfo() != null) {
            return Float.toString(nodeInfo.getRangeInfo().getCurrent());
        }
        return charSequenceToString(nodeInfo.getText(), replaceNull);
    }

    /**
     * Returns the node's bounds clipped to the size of the display
     *
     * @return Empty Rect if node is null, else a Rect containing visible bounds
     */
    public static Rect getBounds(@Nullable AccessibilityNodeInfo node) {
        Rect rect = new Rect();
        if (node == null) {
            return rect;
        }
        if (((SimpleBoundsCalculation) Settings.SIMPLE_BOUNDS_CALCULATION.getSetting()).getValue()) {
            node.getBoundsInScreen(rect);
            return rect;
        }

        UiDevice uiDevice = getUiDevice();
        Rect screenRect = new Rect(0, 0, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight());
        return getBounds(node, screenRect, 0);
    }

    /**
     * Returns the node's bounds clipped to the size of the display, limited by the MAX_DEPTH
     * The implementation is borrowed from `getVisibleBounds` method of `UiObject2` class
     *
     * @return Empty rect if node is null, else a Rect containing visible bounds
     */
    @SuppressLint("CheckResult")
    private static Rect getBounds(@Nullable AccessibilityNodeInfo node, Rect displayRect, int depth) {
        Rect ret = new Rect();
        if (node == null) {
            return ret;
        }

        // Get the object bounds in screen coordinates
        node.getBoundsInScreen(ret);

        // Trim any portion of the bounds that are not on the screen
        ret.intersect(displayRect);

        // Trim any portion of the bounds that are outside the window
        Rect window = new Rect();
        if (node.getWindow() != null) {
            node.getWindow().getBoundsInScreen(window);
            ret.intersect(window);
        }

        // Find the visible bounds of our first scrollable ancestor
        AccessibilityNodeInfo ancestor;
        int currentDepth = depth;
        for (ancestor = node.getParent(); ancestor != null && ++currentDepth < MAX_DEPTH; ancestor = ancestor.getParent()) {
            // If this ancestor is scrollable
            if (ancestor.isScrollable()) {
                // Trim any portion of the bounds that are hidden by the non-visible portion of our
                // ancestor
                Rect ancestorRect = getBounds(ancestor, displayRect, currentDepth);
                ret.intersect(ancestorRect);
                break;
            }
        }

        return ret;
    }

    /**
     * Perform accessibility action ACTION_SET_PROGRESS on the node
     *
     * @param value desired progress value
     * @throws InvalidElementStateException if there was a failure while setting the progress value
     */
    @TargetApi(Build.VERSION_CODES.N)
    public static void setProgressValue(final AccessibilityNodeInfo node, final float value) {
        if (!node.getActionList().contains(AccessibilityAction.ACTION_SET_PROGRESS)) {
            throw new InvalidElementStateException(
                    String.format("The element '%s' does not support ACTION_SET_PROGRESS. " +
                            "Did you interact with the correct element?", node));
        }

        float valueToSet = value;
        AccessibilityNodeInfo.RangeInfo rangeInfo = node.getRangeInfo();
        if (rangeInfo != null) {
            if (value < rangeInfo.getMin()) {
                valueToSet = rangeInfo.getMin();
            }
            if (value > rangeInfo.getMax()) {
                valueToSet = rangeInfo.getMax();
            }
        }
        final Bundle args = new Bundle();
        args.putFloat(AccessibilityNodeInfo.ACTION_ARGUMENT_PROGRESS_VALUE, valueToSet);
        if (!node.performAction(AccessibilityAction.ACTION_SET_PROGRESS.getId(), args)) {
            throw new InvalidElementStateException(
                    String.format("ACTION_SET_PROGRESS has failed on the element '%s'. " +
                            "Did you interact with the correct element?", node));
        }
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
