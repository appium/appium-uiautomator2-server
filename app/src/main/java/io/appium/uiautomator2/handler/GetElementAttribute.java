package io.appium.uiautomator2.handler;

import android.graphics.Rect;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.view.accessibility.AccessibilityNodeInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InvalidClassException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import io.appium.uiautomator2.common.exceptions.NoAttributeFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.core.EventRegister;
import io.appium.uiautomator2.core.ReturningRunnable;
import io.appium.uiautomator2.core.UiObjectChildGenerator;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibilityScrollData;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUiAutomatorDriver;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.UiObject2Element;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class GetElementAttribute extends SafeRequestHandler {

    // these constants are magic numbers experimentally determined to minimize flakiness in generating
    // last scroll data used in getting the 'contentSize' attribute.
    // TODO see whether anchoring these to time and screen size is more reliable across devices
    private static int MINI_SWIPE_STEPS = 10;
    private static int MINI_SWIPE_PIXELS = 200;

    // https://android.googlesource.com/platform/frameworks/testing/+/master/uiautomator/library/core-src/com/android/uiautomator/core/UiScrollable.java#635
    private static double SWIPE_DEAD_ZONE_PCT = 0.1;

    public GetElementAttribute(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("get attribute of element command");
        String id = getElementId(request);
        String attributeName = getNameAttribute(request);
        AndroidElement element = KnownElements.getElementFromCache(id);
        if (element == null) {
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }
        try {
            if ("name".equals(attributeName) || "contentDescription".equals(attributeName)
                    || "text".equals(attributeName) || "className".equals(attributeName)
                    || "resourceId".equals(attributeName)) {
                String attribute = element.getStringAttribute(attributeName);
                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, attribute);
            } else if ("contentSize".equals(attributeName)) {
                Rect boundsRect = element.getBounds();
                ContentSize contentSize = new ContentSize(boundsRect);
                contentSize.touchPadding = getTouchPadding(element);
                contentSize.scrollableOffset = getScrollableOffset(element);

                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, contentSize.toString());
            } else {
                Boolean boolAttribute = element.getBoolAttribute(attributeName);
                // The result should be of type string according to
                // https://w3c.github.io/webdriver/webdriver-spec.html#get-element-attribute
                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                        boolAttribute.toString());
            }

        } catch (UiObjectNotFoundException e) {
            Logger.error(MessageFormat.format("Element not found while trying to get attribute '{0}'", attributeName), e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR);
        } catch (NoAttributeFoundException e) {
            Logger.error(MessageFormat.format("Requested attribute {0} not supported.", attributeName), e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_COMMAND, e);
        } catch(StaleObjectException e){
            Logger.error("Stale Element Exception: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.STALE_ELEMENT_REFERENCE, e);
        } catch (UiAutomator2Exception e) {
            Logger.error(MessageFormat.format("Unable to retrieve attribute {0}", attributeName), e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (ReflectiveOperationException e) {
            Logger.error("Can not access to method or field: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }

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

        Session session = AppiumUiAutomatorDriver.getInstance().getSession();
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

    private static int getScrollableOffsetByItemCount (AndroidElement uiScrollable, int itemCount) {
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

    private static boolean swipe(final int startX, final int startY, final int endX, final int endY) {
        Logger.debug(String.format("Swiping from [%d, %d] to [%d, %d]", startX, startY, endX, endY));
        return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                setResult(getUiDevice().swipe(startX, startY, endX, endY, MINI_SWIPE_STEPS));
            }
        });
    }

    private static Rect getElementBoundsInScreen(AndroidElement element) {
        return getElementBoundsInScreen(element.getUiObject());
    }

    private static Rect getElementBoundsInScreen(Object uiObject) {
        Logger.debug("Getting bounds in screen for an AndroidElement");
        AccessibilityNodeInfo nodeInfo = null;

        try {
            nodeInfo = AccessibilityNodeInfoGetter.fromUiObjectDefaultTimeout(uiObject);
        } catch (UiAutomator2Exception ignored) {}

        if (nodeInfo == null) {
            throw new UiAutomator2Exception("Could not find accessibility node info for the view");
        }

        Rect rect = new Rect();
        nodeInfo.getBoundsInScreen(rect);
        Logger.debug("Bounds were: " + rect);
        return rect;
    }

    private static int getTouchPadding(AndroidElement element) throws UiObjectNotFoundException, ReflectiveOperationException {
        UiObject2 uiObject2;
        if (element instanceof UiObject2Element) {
            uiObject2 = Device.getUiDevice().findObject(By.clazz(((UiObject2) element.getUiObject()).getClassName()));
        } else {
            uiObject2 = Device.getUiDevice().findObject(By.clazz(((UiObject) element.getUiObject()).getClassName()));
        }
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

    private static class ContentSize {

        int width;
        int height;
        int top;
        int left;
        int scrollableOffset;
        int touchPadding;

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
