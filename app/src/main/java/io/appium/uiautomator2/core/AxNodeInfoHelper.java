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
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityWindowInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.Direction;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.internal.GestureController;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.SimpleBoundsCalculation;
import io.appium.uiautomator2.model.settings.SnapshotMaxDepth;
import io.appium.uiautomator2.utils.Logger;

import java.util.stream.IntStream;

import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToString;

/**
 * This class contains static helper methods to work with {@link AccessibilityNodeInfo}
 */
public class AxNodeInfoHelper {
    private static final long UNDEFINED_NODE_ID =
            (((long) Integer.MAX_VALUE) << 32) | Integer.MAX_VALUE;
    private static final int UNDEFINED_WINDOW_ID = -1;
    private static final float DEFAULT_GESTURE_MARGIN_PERCENT = 0.1f;
    private static final Margins mMargins = new PercentMargins(
            DEFAULT_GESTURE_MARGIN_PERCENT,
            DEFAULT_GESTURE_MARGIN_PERCENT,
            DEFAULT_GESTURE_MARGIN_PERCENT,
            DEFAULT_GESTURE_MARGIN_PERCENT
    );

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
        return mMargins.apply(bounds);
    }

    public static void click(AccessibilityNodeInfo node) {
        Rect bounds = getBounds(node);
        makeGestureController(node).click(getCenterPoint(bounds));
    }

    public static void doubleClick(AccessibilityNodeInfo node) {
        Rect bounds = getBounds(node);
        makeGestureController(node).doubleClick(getCenterPoint(bounds));
    }

    public static void longClick(AccessibilityNodeInfo node) {
        longClick(node, null);
    }

    public static void longClick(AccessibilityNodeInfo node, @Nullable Long durationMs) {
        Rect bounds = getBounds(node);
        makeGestureController(node).longClick(getCenterPoint(bounds), durationMs);
    }

    public static void drag(AccessibilityNodeInfo node, Point end) {
        drag(node, end, null);
    }

    public static void drag(AccessibilityNodeInfo node, Point end, @Nullable Integer speed) {
        Rect bounds = getBounds(node);
        makeGestureController(node).drag(getCenterPoint(bounds), end, speed);
    }

    public static void pinchClose(AccessibilityNodeInfo node, float percent) {
        pinchClose(node, percent, null);
    }

    public static void pinchClose(AccessibilityNodeInfo node, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        makeGestureController(node).pinchClose(bounds, percent, speed);
    }

    private static GestureController makeGestureController(AccessibilityNodeInfo node) {
        return CustomUiDevice.getInstance().getGestureController(getAxNodeDisplayId(node));
    }

    public static int getAxNodeDisplayId(AccessibilityNodeInfo node) {
        if (node != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            AccessibilityWindowInfo window = node.getWindow();
            if (window != null) {
                return window.getDisplayId();
            }
        }
        return Display.DEFAULT_DISPLAY;
    }

    public static void pinchOpen(AccessibilityNodeInfo node, float percent) {
        pinchOpen(node, percent, null);
    }

    public static void pinchOpen(AccessibilityNodeInfo node, float percent, @Nullable Integer speed) {
        Rect bounds = getBoundsForGestures(node);
        makeGestureController(node).pinchOpen(bounds, percent, speed);
    }

    public static void swipe(AccessibilityNodeInfo node, Direction direction, float percent) {
        swipe(node, direction, percent, null);
    }

    public static void swipe(
            AccessibilityNodeInfo node, Direction direction, float percent, @Nullable Integer speed
    ) {
        Rect bounds = getBoundsForGestures(node);
        makeGestureController(node).swipe(bounds, direction, percent, speed);
    }

    public static boolean scroll(AccessibilityNodeInfo node, Direction direction, float percent) {
        return scroll(node, direction, percent, null);
    }

    public static boolean scroll(
            AccessibilityNodeInfo node, Direction direction, float percent, @Nullable Integer speed
    ) {
        Rect bounds = getBoundsForGestures(node);
        return makeGestureController(node).scroll(bounds, direction, percent, speed);
    }

    public static boolean fling(AccessibilityNodeInfo node, Direction direction) {
        return fling(node, direction, null);
    }

    public static boolean fling(
            AccessibilityNodeInfo node, Direction direction, @Nullable Integer speed
    ) {
        Rect bounds = getBoundsForGestures(node);
        return makeGestureController(node).fling(bounds, direction, speed);
    }

    public static Rect getBounds(@Nullable AccessibilityNodeInfo node) {
        int displayId = AxNodeInfoHelper.getAxNodeDisplayId(node);
        final boolean isDisplayAccessible = CustomUiDevice.getInstance().getDisplayById(displayId) != null;
        Rect screen = null;
        if (isDisplayAccessible) {
            Point displaySize = CustomUiDevice.getInstance().getDisplaySize(displayId);
            screen = new Rect(0, 0, displaySize.x, displaySize.y);
        }
        if (node == null) {
            return screen == null ? new Rect() : screen;
        }
        return getVisibleBoundsInScreen(
                node,
                screen,
                Boolean.FALSE.equals(Settings.get(SimpleBoundsCalculation.class).getValue()),
                0
        );
    }

    @SuppressLint("CheckResult")
    private static Rect getVisibleBoundsInScreen(
            AccessibilityNodeInfo node, Rect displayRect, boolean trimScrollableParent, int depth
    ) {
        Rect nodeRect = new Rect();
        node.getBoundsInScreen(nodeRect);

        if (displayRect == null) {
            displayRect = new Rect();
        }
        nodeRect.intersect(displayRect);

        // Trim any portion of the bounds that are outside the window
        Rect bounds = new Rect();
        AccessibilityWindowInfo window = node.getWindow();
        if (window != null) {
            window.getBoundsInScreen(bounds);
            nodeRect.intersect(bounds);
        }

        // Trim the bounds into any scrollable ancestor, if required.
        if (trimScrollableParent) {
            for (AccessibilityNodeInfo ancestor = node.getParent();
                 ancestor != null;
                 ancestor = ancestor.getParent()
            ) {
                if (ancestor.isScrollable()) {
                    if (depth >= Settings.get(SnapshotMaxDepth.class).getValue()) {
                        break;
                    }
                    Rect ancestorRect = getVisibleBoundsInScreen(
                            ancestor, displayRect, true, depth + 1
                    );
                    nodeRect.intersect(ancestorRect);
                    break;
                }
            }
        }

        return nodeRect;
    }

    public static int calculateIndex(AccessibilityNodeInfo node) {
        AccessibilityNodeInfo parent = node.getParent();
        if (parent == null) {
            return 0;
        }
        return IntStream.range(0, parent.getChildCount())
                .filter(index -> node.equals(parent.getChild(index)))
                .findFirst()
                .orElse(0);
    }

    /**
     * Perform accessibility action ACTION_SET_PROGRESS on the node
     *
     * @param value desired progress value
     * @throws InvalidElementStateException if there was a failure while setting the progress value
     */
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

    private interface Margins {
        Rect apply(Rect bounds);
    }

    private static class PercentMargins implements Margins {
        float mLeft, mTop, mRight, mBottom;
        PercentMargins(float left, float top, float right, float bottom) {
            mLeft = left;
            mTop = top;
            mRight = right;
            mBottom = bottom;
        }

        @Override
        public Rect apply(Rect bounds) {
            return new Rect(bounds.left + (int) (bounds.width() * mLeft),
                    bounds.top + (int) (bounds.height() * mTop),
                    bounds.right - (int) (bounds.width() * mRight),
                    bounds.bottom - (int) (bounds.height() * mBottom));
        }
    }
}
