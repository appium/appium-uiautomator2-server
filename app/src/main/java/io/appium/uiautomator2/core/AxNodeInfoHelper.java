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
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;

import java.util.HashSet;
import java.util.Set;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.SimpleBoundsCalculation;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToString;

/**
 * This class contains static helper methods to work with {@link AccessibilityNodeInfo}
 */
public class AxNodeInfoHelper {
    // https://github.com/appium/appium/issues/12892
    private static final int MAX_DEPTH = 70;
    private static final long UNDEFINED_NODE_ID =
            (((long) Integer.MAX_VALUE) << 32) | Integer.MAX_VALUE;
    private static final int UNDEFINED_WINDOW_ID = -1;

    @Nullable
    public static String toUuid(AccessibilityNodeInfo info) {
        // mSourceNodeId and windowId properties define
        // the uniqueness of the particular AccessibilityNodeInfo instance
        long sourceNodeId = (Long) getField("mSourceNodeId", info);
        int windowId = info.getWindowId();
        if (sourceNodeId == UNDEFINED_NODE_ID || windowId == UNDEFINED_WINDOW_ID) {
            return null;
        }
        String sourceNodeIdHex = String.format("%016x", sourceNodeId);
        String windowIdHex = String.format("%016x", windowId);
        return String.format("%s-%s-%s-%s-%s",
                windowIdHex.substring(0, 8), windowIdHex.substring(8, 12), windowIdHex.substring(12, 16),
                sourceNodeIdHex.substring(0, 4), sourceNodeIdHex.substring(4, 16));
    }

    @Nullable
    public static Pair<Integer, Integer> getSelectionRange(@Nullable AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return null;
        }

        int selectionStart = nodeInfo.getTextSelectionStart();
        int selectionEnd = nodeInfo.getTextSelectionEnd();
        return selectionStart >= 0 && selectionStart != selectionEnd
            ? new Pair<>(selectionStart, selectionEnd)
            : null;
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

    private static Point getCenterPoint(Rect bounds) {
        return new Point(bounds.centerX(), bounds.centerY());
    }

    private static Rect getBoundsForGestures(AccessibilityNodeInfo node) {
        Rect bounds = getBounds(node);
        // The default margin values are copied from UiObject2 class:
        // private int mMarginLeft   = 5;
        // private int mMarginTop    = 5;
        // private int mMarginRight  = 5;
        // private int mMarginBottom = 5;
        bounds.left = bounds.left + 5;
        bounds.top = bounds.top + 5;
        bounds.right = bounds.right - 5;
        bounds.bottom = bounds.bottom - 5;
        return bounds;
    }

    public static void click(AccessibilityNodeInfo node) {
        Rect bounds = getBounds(node);
        CustomUiDevice.getInstance().getGestureController().click(getCenterPoint(bounds));
    }

    public static void doubleClick(AccessibilityNodeInfo node) {
        Rect bounds = getBounds(node);
        CustomUiDevice.getInstance().getGestureController().doubleClick(getCenterPoint(bounds));
    }

    public static void longClick(AccessibilityNodeInfo node) {
        longClick(node, null);
    }

    public static void longClick(AccessibilityNodeInfo node, @Nullable Long durationMs) {
        Rect bounds = getBounds(node);
        CustomUiDevice.getInstance().getGestureController().longClick(getCenterPoint(bounds), durationMs);
    }

    public static void drag(AccessibilityNodeInfo node, Point end) {
        drag(node, end, null);
    }

    public static void drag(AccessibilityNodeInfo node, Point end, @Nullable Integer speed) {
        Rect bounds = getBounds(node);
        CustomUiDevice.getInstance().getGestureController().drag(getCenterPoint(bounds), end, speed);
    }

    public static void pinchClose(AccessibilityNodeInfo node, float percent) {
        pinchClose(node, percent, null);
    }

    public static void pinchClose(AccessibilityNodeInfo node, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        CustomUiDevice.getInstance().getGestureController().pinchClose(bounds, percent, speed);
    }

    public static void pinchOpen(AccessibilityNodeInfo node, float percent) {
        pinchOpen(node, percent, null);
    }

    public static void pinchOpen(AccessibilityNodeInfo node, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        CustomUiDevice.getInstance().getGestureController().pinchOpen(bounds, percent, speed);
    }

    public static void swipe(AccessibilityNodeInfo node, Direction direction, float percent) {
        swipe(node, direction, percent, null);
    }

    public static void swipe(AccessibilityNodeInfo node, Direction direction, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        CustomUiDevice.getInstance().getGestureController().swipe(bounds, direction, percent, speed);
    }

    public static boolean scroll(AccessibilityNodeInfo node, Direction direction, float percent) {
        return scroll(node, direction, percent, null);
    }

    public static boolean scroll(AccessibilityNodeInfo node, Direction direction, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        return CustomUiDevice.getInstance().getGestureController().scroll(bounds, direction, percent, speed);
    }

    public static boolean fling(AccessibilityNodeInfo node, Direction direction) {
        return fling(node, direction, null);
    }

    public static boolean fling(AccessibilityNodeInfo node, Direction direction, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        return CustomUiDevice.getInstance().getGestureController().fling(bounds, direction, speed);
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
        if (Settings.get(SimpleBoundsCalculation.class).getValue()) {
            node.getBoundsInScreen(rect);
            return rect;
        }

        UiDevice uiDevice = getUiDevice();
        Rect screenRect = new Rect(0, 0, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight());
        return getBounds(node, screenRect, 0);
    }

    public static int calculateIndex(AccessibilityNodeInfo node) {
        AccessibilityNodeInfo parent = node.getParent();
        if (parent == null) {
            return 0;
        }
        for (int index = 0; index < parent.getChildCount(); ++index) {
            if (node.equals(parent.getChild(index))) {
                return index;
            }
        }
        return 0;
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
        int currentDepth = depth;
        Set<AccessibilityNodeInfo> ancestors = new HashSet<>();
        AccessibilityNodeInfo ancestor = node.getParent();
        // An erroneous situation is possible where node parent equals to the node itself
        while (++currentDepth < MAX_DEPTH && ancestor != null && !ancestors.contains(ancestor)) {
            // If this ancestor is scrollable
            if (ancestor.isScrollable()) {
                // Trim any portion of the bounds that are hidden by the non-visible portion of our
                // ancestor
                Rect ancestorRect = getBounds(ancestor, displayRect, currentDepth);
                ret.intersect(ancestorRect);
                return ret;
            }
            ancestors.add(ancestor);
            ancestor = ancestor.getParent();
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
