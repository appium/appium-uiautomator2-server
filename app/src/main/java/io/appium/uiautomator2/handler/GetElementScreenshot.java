package io.appium.uiautomator2.handler;

import android.graphics.Rect;
import android.support.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.ScreenshotHelper;

public class GetElementScreenshot extends SafeRequestHandler {

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
            final String result = ScreenshotHelper.takeScreenshot(elementRect);
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
        } catch (UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (CropScreenshotException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.ELEMENT_NOT_VISIBLE, e);
        } catch (Exception e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }
}
