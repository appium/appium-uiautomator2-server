package io.appium.uiautomator2.handler;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class GetElementScreenshot extends SafeRequestHandler {
    private static final UiAutomation uia = CustomUiDevice.getInstance()
            .getInstrumentation()
            .getUiAutomation();

    public GetElementScreenshot(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Capture screenshot of an element command");
        String id = getElementId(request);
        AndroidElement element = KnownElements.getElementFromCache(id);
        if (element == null) {
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }
        try {
            final Rect elementRect = element.getBounds();
            if (elementRect.isEmpty()) {
                Logger.error("Element is not visible");
                return new AppiumResponse(getSessionId(request), WDStatus.ELEMENT_NOT_VISIBLE);
            }
            final Bitmap screenshot = uia.takeScreenshot();
            if (screenshot == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                        "Failed to capture a screenshot. Does the current view have 'secure' flag set?");
            }
            try {
                final Rect screenRect = new Rect(0, 0,
                        screenshot.getWidth(), screenshot.getHeight());
                final Rect intersectionRect = new Rect();
                if (!intersectionRect.setIntersect(elementRect, screenRect)) {
                    Logger.error("Element is not visible inside the screen rect");
                    return new AppiumResponse(getSessionId(request), WDStatus.ELEMENT_NOT_VISIBLE);
                }

                final Bitmap elementScreenshot = Bitmap.createBitmap(screenshot,
                        intersectionRect.left, intersectionRect.top,
                        intersectionRect.width(), intersectionRect.height());
                try {
                    final ByteArrayOutputStream elementPngScreenshot = new ByteArrayOutputStream();
                    if (!elementScreenshot.compress(Bitmap.CompressFormat.PNG, 100,
                            elementPngScreenshot)) {
                        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                                "Element screenshot cannot be compressed to PNG format");
                    }
                    return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                            Base64.encodeToString(elementPngScreenshot.toByteArray(), Base64.DEFAULT));
                } finally {
                    elementScreenshot.recycle();
                }
            } finally {
                screenshot.recycle();
            }
        } catch (UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (Exception e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }
}
