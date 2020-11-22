package io.appium.uiautomator2.handler;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;

public class GetElementAttribute extends SafeRequestHandler {

    public GetElementAttribute(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        String id = getElementId(request);
        String attributeName = getNameAttribute(request);
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement element = session.getElementsCache().getElementFromCache(id);
        String attribute = element.getAttribute(attributeName);
        return new AppiumResponse(getSessionId(request), attribute);
    }

}
