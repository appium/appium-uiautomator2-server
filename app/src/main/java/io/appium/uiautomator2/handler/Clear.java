package io.appium.uiautomator2.handler;

import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.NoSuchElementException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.uiautomator.By.focused;
import static io.appium.uiautomator2.utils.ElementHelpers.findElement;

public class Clear extends SafeRequestHandler {
    public Clear(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Logger.info("Clear element command");
        AndroidElement element;
        String elementId = getElementId(request);
        if (elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new NoSuchElementException();
            }
        } else {
            //perform action on focused element
            element = findElement(focused(true));
        }
        element.clear();
        return new AppiumResponse(getSessionId(request));
    }
}
