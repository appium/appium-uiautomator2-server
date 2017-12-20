package io.appium.uiautomator2.handler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

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
            if (elementRect.isEmpty()) {
                Logger.error("Element is not visible");
                return new AppiumResponse(getSessionId(request), WDStatus.ELEMENT_NOT_VISIBLE);
            }
            final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
            final File outputFile = File.createTempFile("screenshot", ".png",
                    context.getCacheDir());
            try {
                if (!Device.getUiDevice().takeScreenshot(outputFile)) {
                    return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                            "Cannot capture a screenshot");
                }
                Logger.info("ScreenShot captured at location: " + outputFile.getAbsolutePath());
                Bitmap elementBmpScreenshot;
                try (FileInputStream fis = new FileInputStream(outputFile)) {
                    elementBmpScreenshot = Bitmap.createBitmap(BitmapFactory.decodeStream(fis),
                            elementRect.left, elementRect.top, elementRect.width(), elementRect.height());
                }
                final ByteArrayOutputStream elementPngScreenshot = new ByteArrayOutputStream();
                elementBmpScreenshot.compress(Bitmap.CompressFormat.PNG, 100, elementPngScreenshot);
                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                        Base64.encodeToString(elementPngScreenshot.toByteArray(), Base64.DEFAULT));
            } finally {
                //noinspection ResultOfMethodCallIgnored
                outputFile.delete();
            }
        } catch (UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (Exception e) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }
}
