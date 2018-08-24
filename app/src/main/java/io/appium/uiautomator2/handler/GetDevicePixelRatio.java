package io.appium.uiautomator2.handler;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class GetDevicePixelRatio extends SafeRequestHandler {

    public GetDevicePixelRatio(String mappedUri) {
        super(mappedUri);
    }

    private static float getDeviceScaleRatio(Instrumentation instrumentation) {
        WindowManager windowManager = (WindowManager) instrumentation.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Get device pixel ratio");
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Float ratio = getDeviceScaleRatio(instrumentation);
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, ratio);
    }
}