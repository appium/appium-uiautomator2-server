package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.UiObjectNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class Click extends SafeRequestHandler {

    public Click(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException,
            UiObjectNotFoundException {
        JSONObject payload = getPayload(request);
        if (payload.has(ELEMENT_ID_KEY_NAME)) {
            Logger.info("Click element command");
            String id = payload.getString(ELEMENT_ID_KEY_NAME);
            AndroidElement element = KnownElements.getElementFromCache(id);
            if (element == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            }
            element.click();
        } else {
            Logger.info("tap command");
            Point coords = new Point(Double.parseDouble(payload.get("x").toString()),
                    Double.parseDouble(payload.get("y").toString()));
            coords = PositionHelper.getDeviceAbsPos(coords);
            final boolean res = getUiDevice().click(coords.x.intValue(), coords.y.intValue());
            return new AppiumResponse(getSessionId(request), res);
        }
        Device.waitForIdle();
        return new AppiumResponse(getSessionId(request), true);
    }
}
