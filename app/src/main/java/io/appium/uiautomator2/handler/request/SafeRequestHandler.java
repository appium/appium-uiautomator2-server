package io.appium.uiautomator2.handler.request;

import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.MessageFormat;

import io.appium.uiautomator2.common.exceptions.CropScreenshotException;
import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.NoAlertOpenException;
import io.appium.uiautomator2.common.exceptions.NoAttributeFoundException;
import io.appium.uiautomator2.common.exceptions.NoSuchContextException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public abstract class SafeRequestHandler extends BaseRequestHandler {

    protected final String ELEMENT_ID_KEY_NAME = "element";

    public SafeRequestHandler(String mappedUri) {
        super(mappedUri);
    }


    protected String getIdOfKnownElement(IHttpRequest request, AndroidElement element) {
        return KnownElements.getIdOfElement(element);
    }

    protected AndroidElement getElementFromCache(IHttpRequest request, String id) {

        return KnownElements.getElementFromCache(id);
    }


    protected String[] extractKeysToSendFromPayload(IHttpRequest request) throws JSONException, UiAutomator2Exception {
        JSONArray valueArr = getPayload(request).getJSONArray("value");
        if (valueArr == null || valueArr.length() == 0) {
            throw new UiAutomator2Exception("No key to send to an element was found.");
        }

        String[] toReturn = new String[valueArr.length()];

        for (int i = 0; i < valueArr.length(); i++) {
            toReturn[i] = valueArr.getString(i);
        }

        return toReturn;
    }

    @Override
    public final AppiumResponse handle(IHttpRequest request) {
        try {
            return safeHandle(request);
        } catch (InvalidSelectorException e) {
            Logger.error("Invalid selector: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_SELECTOR, e);
        } catch (ElementNotFoundException | UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (UiSelectorSyntaxException e) {
            Logger.error("Unable to parse UiSelector: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_SELECTOR, e);
        } catch (CropScreenshotException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.ELEMENT_NOT_VISIBLE, e);
        } catch (InvalidElementStateException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_STATE, e);
        } catch (NoAlertOpenException e) {
            return new AppiumResponse(getSessionId(request), WDStatus.NO_ALERT_OPEN_ERROR, e);
        } catch (NoAttributeFoundException e) {
            Logger.error(MessageFormat.format(
                    "The requested attribute name '%s' is not supported.", e.getAttributeName()), e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_COMMAND, e);
        } catch (InvalidCoordinatesException e) {
            Logger.error("The coordinates provided to an interactions operation are invalid. ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_COORDINATES, e);
        } catch (NoSuchContextException e) {
            //TODO: update error code when w3c spec gets updated
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_WINDOW,
                    new UiAutomator2Exception("Invalid window handle was used: only 'NATIVE_APP' and 'WEBVIEW' are supported."));
        } catch (StaleObjectException e) {
            Logger.error("Stale Element Reference Exception: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.STALE_ELEMENT_REFERENCE, e);
        } catch (UnsupportedOperationException e) {
            Logger.error("Unsupported operation: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (NoClassDefFoundError e) {
            // This is a potentially interesting class path problem which should be returned to client.
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_COMMAND, e);
        } catch (Exception e) {
            // The advantage of catching general Exception here is that we can propagate the Exception to clients.
            Logger.error("Exception while handling action in: " + this.getClass().getName(), e);
            return AppiumResponse.forCatchAllError(getSessionId(request), e);
        } catch (Throwable e) {
            // Catching Errors seems like a bad idea in general but if we don't catch this, Netty will catch it anyway.
            // The advantage of catching it here is that we can propagate the Error to clients.
            Logger.error("Fatal error while handling action in: " + this.getClass().getName(), e);
            return AppiumResponse.forCatchAllError(getSessionId(request), e);
        }
    }
}
