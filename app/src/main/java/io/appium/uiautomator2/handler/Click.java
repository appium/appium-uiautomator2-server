package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.utils.w3c.W3CElementUtils;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.NoSuchElementException;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
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
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException, UiObjectNotFoundException {
        JSONObject payload = toJSON(request);
        final String elementId = W3CElementUtils.extractElementId(payload);
        if (elementId != null) {
            Logger.info("Click element command");
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new NoSuchElementException();
            }
            element.click();
        } else {
            Logger.info("tap command");
            Point coords = new Point(Double.parseDouble(payload.get("x").toString()),
                    Double.parseDouble(payload.get("y").toString()));
            coords = PositionHelper.getDeviceAbsPos(coords);
            if (!getUiDevice().click(coords.x.intValue(), coords.y.intValue())) {
                throw new InvalidElementStateException(
                        String.format("Click failed at (%s, %s) coordinates",
                        coords.x.intValue(), coords.y.intValue()));
            }
        }
        Device.waitForIdle();
        return new AppiumResponse(getSessionId(request));
    }
}
