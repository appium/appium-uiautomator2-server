package io.appium.uiautomator2.handler.request;

import org.json.JSONException;

import androidx.test.uiautomator.StaleObjectException;
import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.Logger;

public abstract class SafeRequestHandler extends BaseRequestHandler {

    public SafeRequestHandler(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public final AppiumResponse handle(IHttpRequest request) {
        try {
            return safeHandle(request);
        } catch (UiObjectNotFoundException e) {
            return new AppiumResponse(getSessionId(request), new ElementNotFoundException(e));
        } catch (StaleObjectException e) {
            return new AppiumResponse(getSessionId(request), new StaleElementReferenceException(e));
        } catch (JSONException e) {
            return new AppiumResponse(getSessionId(request), new InvalidArgumentException(e));
        } catch (Throwable e) {
            // Catching Errors seems like a bad idea in general but if we don't catch this, Netty will catch it anyway.
            // The advantage of catching it here is that we can propagate the Error to clients.
            Logger.error("Error while handling action in: " + this.getClass().getName(), e);
            return new AppiumResponse(getSessionId(request), e);
        }
    }
}
