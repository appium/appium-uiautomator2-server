package io.appium.uiautomator2.handler;

import android.graphics.Rect;

import io.appium.uiautomator2.model.api.LocationModel;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

public class Location extends SafeRequestHandler {
    public Location(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        String id = getElementId(request);
        AndroidElement element = session.getKnownElements().getElementFromCache(id);
        if (element == null) {
            throw new ElementNotFoundException();
        }
        final Rect bounds = element.getBounds();
        Logger.info("Element found at location " + "(" + bounds.left + "," + bounds.top + ")");
        return new AppiumResponse(getSessionId(request), new LocationModel(bounds.left, bounds.top));

    }
}


