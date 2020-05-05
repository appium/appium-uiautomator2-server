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

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import io.appium.uiautomator2.model.api.ElementModel;
import io.appium.uiautomator2.model.api.ElementRectModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InvalidClassException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.NoSuchAttributeException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.core.AccessibilityNodeInfoHelpers;
import io.appium.uiautomator2.core.EventRegister;
import io.appium.uiautomator2.core.ReturningRunnable;
import io.appium.uiautomator2.core.UiObjectChildGenerator;
import io.appium.uiautomator2.model.AccessibilityScrollData;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.UiObject2Element;

import static io.appium.uiautomator2.model.internal.CustomUiDevice.getInstance;
import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static io.appium.uiautomator2.utils.ReflectionUtils.method;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToString;
import static io.appium.uiautomator2.utils.StringHelpers.toNonNullString;
import static io.appium.uiautomator2.utils.StringHelpers.toNullableString;

public abstract class ElementHelpers {
    private static Method findAccessibilityNodeInfo;
    // these constants are magic numbers experimentally determined to minimize flakiness in generating
    // last scroll data used in getting the 'contentSize' attribute.
    // TODO see whether anchoring these to time and screen size is more reliable across devices
    private static final int MINI_SWIPE_STEPS = 10;
    private static final int MINI_SWIPE_PIXELS = 200;
    // https://android.googlesource.com/platform/frameworks/testing/+/master/uiautomator/library/core-src/com/android/uiautomator/core/UiScrollable.java#635
    private static final double SWIPE_DEAD_ZONE_PCT = 0.1;

    private static AccessibilityNodeInfo elementToNode(Object element) {
        AccessibilityNodeInfo result = null;
        try {
            result = (AccessibilityNodeInfo) findAccessibilityNodeInfo.invoke(element, 5000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Remove all duplicate elements from the provided list
     *
     * @param elements - elements to remove duplicates from
     * @return a new list with duplicates removed
     */
    public static List<Object> dedupe(List<Object> elements) {
        try {
            findAccessibilityNodeInfo = method(UiObject.class, "findAccessibilityNodeInfo", long.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Object> result = new ArrayList<>();
        List<AccessibilityNodeInfo> nodes = new ArrayList<>();

        for (Object element : elements) {
            AccessibilityNodeInfo node = elementToNode(element);
            if (!nodes.contains(node)) {
                nodes.add(node);
                result.add(element);
            }
        }

        return result;
    }

    /**
     * Set text of an element
     *
     * @param element - target element
     * @param text    - desired text
     * @return true if the text has been successfully set
     */
    public static boolean setText(final Object element, @Nullable final String text) {
        // Per framework convention, setText(null) means clearing it
        String textToSend = toNonNullString(text);
        AccessibilityNodeInfo nodeInfo = AccessibilityNodeInfoGetter.fromUiObject(element);
        if (nodeInfo == null) {
            throw new ElementNotFoundException();
        }

        /*
         * Execute ACTION_SET_PROGRESS action (introduced in API level 24)
         * if element has range info and text can be converted to float.
         * Falling back to element.setText() if something goes wrong.
         */
        if (nodeInfo.getRangeInfo() != null && Build.VERSION.SDK_INT >= 24) {
            Logger.debug("Element has range info.");
            try {
                if (AccessibilityNodeInfoHelpers.setProgressValue(nodeInfo,
                        Float.parseFloat(Objects.requireNonNull(text)))) {
                    return true;
                }
            } catch (NumberFormatException e) {
                Logger.debug(String.format("Can not convert \"%s\" to float.", text));
            }
            Logger.debug("Unable to perform ACTION_SET_PROGRESS action. " +
                    "Falling back to element.setText()");
        }

        /*
         * Below Android 7.0 (API level 24) calling setText() throws
         * `IndexOutOfBoundsException: setSpan (x ... x) ends beyond length y`
         * if text length is greater than getMaxTextLength()
         */
        if (Build.VERSION.SDK_INT < 24) {
            textToSend = AccessibilityNodeInfoHelpers.truncateTextToMaxLength(nodeInfo, textToSend);
        }

        Logger.debug("Sending text to element: " + textToSend);
        Bundle args = new Bundle();
        args.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, textToSend);
        return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, args);
    }

    public static AndroidElement findElement(final BySelector ui2BySelector) throws UiAutomator2Exception {
        Object ui2Object = getInstance().findObject(ui2BySelector);
        if (ui2Object == null) {
            throw new ElementNotFoundException();
        }
        return getAndroidElement(UUID.randomUUID().toString(), ui2Object, true);
    }

    public static NoSuchAttributeException generateNoAttributeException(@Nullable String attributeName) {
        return new NoSuchAttributeException(String.format("'%s' attribute is unknown for the element. " +
                        "Only the following attributes are supported: %s", attributeName,
                Arrays.toString(Attribute.exposableAliases())));
    }

    @NonNull
    public static String getText(Object element) throws UiObjectNotFoundException {
        //noinspection ConstantConditions
        return getText(element, true);
    }

    @Nullable
    public static String getText(Object element, boolean replaceNull) throws UiObjectNotFoundException {
        if (element instanceof UiObject2) {
            /*
             * If the given element is TOAST element, we can't perform any operation on {@link UiObject2} as it
             * not formed with valid AccessibilityNodeInfo, Instead we are using custom created AccessibilityNodeInfo of
             * TOAST Element to retrieve the Text.
             */
            AccessibilityNodeInfo nodeInfo = (AccessibilityNodeInfo) getField(UiObject2.class,
                    "mCachedNode", element);
            if (nodeInfo != null && Objects.equals(nodeInfo.getClassName(), Toast.class.getName())) {
                return charSequenceToString(nodeInfo.getText(), replaceNull);
            }
        }

        return AccessibilityNodeInfoHelpers.getText(AccessibilityNodeInfoGetter.fromUiObject(element), replaceNull);
    }

    public static String getContentSize(AndroidElement element) throws UiObjectNotFoundException {
        Rect boundsRect = element.getBounds();
        ContentSize contentSize = new ContentSize(boundsRect);
        try {
            contentSize.touchPadding = getTouchPadding(element);
        } catch (ReflectiveOperationException e) {
            throw new UiAutomator2Exception(e);
        }
        contentSize.scrollableOffset = getScrollableOffset(element);
        return contentSize.toString();
    }

    private static Rect getElementBoundsInScreen(AndroidElement element) {
        return getElementBoundsInScreen(element.getUiObject());
    }

    private static Rect getElementBoundsInScreen(Object uiObject) {
        Logger.debug("Getting bounds in screen for an AndroidElement");
        AccessibilityNodeInfo nodeInfo = null;

        try {
            nodeInfo = AccessibilityNodeInfoGetter.fromUiObjectDefaultTimeout(uiObject);
        } catch (UiAutomator2Exception ignored) {
        }

        if (nodeInfo == null) {
            throw new UiAutomator2Exception("Could not find accessibility node info for the view");
        }

        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        Logger.debug("Bounds were: " + rect);
        return rect;
    }

    private static int getScrollableOffset(AndroidElement uiScrollable) {
        // get the bounds of the scrollable view
        Rect bounds = getElementBoundsInScreen(uiScrollable);

        // now scroll a bit back and forth in the view to populate the lastScrollData we need
        int x1 = bounds.centerX();
        int y1 = bounds.centerY() + MINI_SWIPE_PIXELS;
        int x2 = x1;
        int y2 = y1 - (MINI_SWIPE_PIXELS * 2);
        int yMargin = (int) Math.floor(bounds.height() * SWIPE_DEAD_ZONE_PCT);

        // ensure that our xs and ys are within the bounds of the element
        if (y1 > bounds.height()) {
            y1 = bounds.height() - yMargin;
        }
        if (y2 < 0) {
            y2 = yMargin;
        }

        Session session = AppiumUIA2Driver.getInstance().getSession();
        AccessibilityScrollData lastScrollData = null;
        Logger.debug("Doing a mini swipe-and-back in the scrollable view to generate scroll data");
        swipe(x1, y1, x2, y2);
        lastScrollData = session.getLastScrollData();
        if (lastScrollData == null) {
            // if we didn't get scroll data from the down swipe, try to get it from the up swipe
            swipe(x2, y2, x1, y1);
            lastScrollData = session.getLastScrollData();
        } else {
            // otherwise just do a reset swipe without worrying about scroll data, to avoid it
            // failing because of flakiness
            getUiDevice().swipe(x2, y2, x1, y1, MINI_SWIPE_STEPS);
        }


        if (lastScrollData == null) {
            throw new UiAutomator2Exception("Could not retrieve accessibility scroll data; unable to determine scrollable offset");
        }

        // if we're in some views, like ScrollViews, we get x/y values directly, and we can simply return
        if (lastScrollData.getMaxScrollY() != -1) {
            return lastScrollData.getMaxScrollY();
        }

        // in other views, like List or Grid, we get item counts and indexes, and we have to turn
        // that into pixels by doing some math
        if (lastScrollData.getItemCount() == -1) {
            throw new UiAutomator2Exception("Did not get either scrollY or itemCount from accessibility scroll data");
        }

        return getScrollableOffsetByItemCount(uiScrollable, lastScrollData.getItemCount());
    }

    private static int getScrollableOffsetByItemCount(AndroidElement uiScrollable, int itemCount) {
        Logger.debug("Figuring out scrollableOffset via item count of " + itemCount);
        Object scrollObject = uiScrollable.getUiObject();
        Rect scrollBounds = getElementBoundsInScreen(uiScrollable);

        // here we loop through the children and get their bounds until the height differs, then
        // regardless of whether we have a list or a grid, we'll know the height of an item/row
        try {
            int itemsPerRow = 0;
            int rowHeight = 0;
            int lastExaminedItemY = Integer.MIN_VALUE; // initialize to something impossibly negative
            int numRowsExamined = 0;
            int numRowsToExamine = 3; // examine a few rows since the top ones often have bad offsets
            Object lastExaminedItem = null;

            UiObjectChildGenerator gen = new UiObjectChildGenerator(scrollObject);
            for (Object item : gen) {
                if (item == null) {
                    throw new UiObjectNotFoundException("Could not get child of scrollview");
                }

                Rect itemBounds = getElementBoundsInScreen(item);

                ++itemsPerRow;
                lastExaminedItem = item;

                if (lastExaminedItemY != Integer.MIN_VALUE && itemBounds.top > lastExaminedItemY) {
                    ++numRowsExamined;
                    rowHeight = itemBounds.top - lastExaminedItemY;
                    if (numRowsExamined >= numRowsToExamine) {
                        break;
                    }
                    // reset itemsPerRow as we examine another row; don't want it to overaccumulate
                    itemsPerRow = 0;
                }

                lastExaminedItemY = itemBounds.top;
            }

            if (lastExaminedItem == null) {
                throw new UiObjectNotFoundException("Could not find any children of the scrollview to get offset from");
            }
            Logger.debug("Determined there were " + itemsPerRow + " items per row");

            if (itemsPerRow == 0) {
                // Need to exit. Other case we will get an ArithmeticException
                return 0;
            }

            @SuppressWarnings("IntegerDivisionInFloatingPointContext")
            int numRows = (int) Math.floor(itemCount / itemsPerRow);
            if (itemCount % itemsPerRow > 0) {
                // we might have an additional part-row
                ++numRows;
            }
            int totalHeight = numRows * rowHeight;
            int scrollableOffset = totalHeight - scrollBounds.height();
            Logger.debug("Determined there were " + numRows + " rows of height " +
                    rowHeight + ", for a total height of " + totalHeight + " and scroll offset " +
                    "of " + scrollableOffset);
            return scrollableOffset;
        } catch (UiObjectNotFoundException ignore) {
        } catch (InvalidClassException e) {
            Logger.error("Programming error, tried to build a UiObjectChildGenerator with wrong type");
        }

        // there were no child items we could find, so assume no offset
        return 0;
    }

    private static int getTouchPadding(AndroidElement element) throws UiObjectNotFoundException, ReflectiveOperationException {
        final UiObject2 uiObject2 = element instanceof UiObject2Element
                ? getUiDevice().findObject(By.clazz(((UiObject2) element.getUiObject()).getClassName()))
                : getUiDevice().findObject(By.clazz(((UiObject) element.getUiObject()).getClassName()));
        Field gestureField = uiObject2.getClass().getDeclaredField("mGestures");
        gestureField.setAccessible(true);
        Object gestureObject = gestureField.get(uiObject2);

        Field viewConfigField = gestureObject.getClass().getDeclaredField("mViewConfig");
        viewConfigField.setAccessible(true);
        Object viewConfigObject = viewConfigField.get(gestureObject);

        Method getScaledPagingTouchSlopMethod = viewConfigObject.getClass().getDeclaredMethod("getScaledPagingTouchSlop");
        getScaledPagingTouchSlopMethod.setAccessible(true);
        int touchPadding = (int) getScaledPagingTouchSlopMethod.invoke(viewConfigObject);

        return touchPadding / 2;
    }

    private static boolean swipe(final int startX, final int startY, final int endX, final int endY) {
        Logger.debug(String.format("Swiping from [%s, %s] to [%s, %s]", startX, startY, endX, endY));
        return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                setResult(getUiDevice().swipe(startX, startY, endX, endY, MINI_SWIPE_STEPS));
            }
        });
    }

    private static class ContentSize {
        private final int width;
        private final int height;
        private final int top;
        private final int left;
        private int scrollableOffset;
        private int touchPadding;

        ContentSize(Rect rect) {
            width = rect.width();
            height = rect.height();
            top = rect.top;
            left = rect.left;
        }

        @Override
        public String toString() {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("width", width);
                jsonObject.put("height", height);
                jsonObject.put("top", top);
                jsonObject.put("left", left);
                jsonObject.put("scrollableOffset", scrollableOffset);
                jsonObject.put("touchPadding", touchPadding);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }
    }
}
