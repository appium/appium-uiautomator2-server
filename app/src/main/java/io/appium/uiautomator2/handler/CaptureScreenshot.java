package io.appium.uiautomator2.handler;

import android.app.UiAutomation;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;


public class CaptureScreenshot extends SafeRequestHandler {
    private static final UiAutomation uia = CustomUiDevice.getInstance()
            .getInstrumentation()
            .getUiAutomation();

    @Nullable
    private static String takeScreenshot() {
        final Bitmap screenshot = uia.takeScreenshot();
        if (screenshot == null) {
            return null;
        }
        try {
            final ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (!screenshot.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
                return null;
            }
            return Base64.encodeToString(stream.toByteArray(), Base64.DEFAULT);
        } finally {
            screenshot.recycle();
        }
    }

    public CaptureScreenshot(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Capture screenshot command");
        final String result = takeScreenshot();
        if (null == result) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                    "Failed to capture a screenshot. Does the current view have 'secure' flag set?");
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
    }
}
