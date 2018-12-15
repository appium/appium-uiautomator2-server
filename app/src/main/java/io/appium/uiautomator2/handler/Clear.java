package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.uiautomator.By.focused;
import static io.appium.uiautomator2.utils.ElementHelpers.findElement;

public class Clear extends SafeRequestHandler {
    public Clear(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException,
            UiObjectNotFoundException {
        Logger.info("Clear element command");
        JSONObject payload = getPayload(request);
        AndroidElement element;
        if (payload.has("elementId")) {
            String id = payload.getString("elementId");
            element = KnownElements.getElementFromCache(id);
            if (element == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            }
        } else {
            //perform action on focused element
            try {
                element = findElement(focused(true));
            } catch (ClassNotFoundException e) {
                throw new UiAutomator2Exception(e);
            }
        }
        element.clear();
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Element Cleared");
    }
}
