package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;

/**
 * This method return first visible element inside provided element
 */
public class FirstVisibleView extends SafeRequestHandler {

    public FirstVisibleView(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Logger.info("Get first visible element inside provided element");
        String elementId = getElementId(request);

        AndroidElement element = KnownElements.getElementFromCache(elementId);
        if (element == null) {
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }
        try {
            KnownElements ke = new KnownElements();
            Object firstObject = null;
            if (element.getUiObject() instanceof UiObject) {
                UiObject uiObject = (UiObject) element.getUiObject();
                Logger.debug("Container for first visible is a uiobject; looping through children");
                for (int i = 0; i < uiObject.getChildCount(); i++) {
                    UiObject object = uiObject.getChild(new UiSelector().index(i));
                    if (object.exists()) {
                        firstObject = object;
                        break;
                    }
                }
            } else {
                UiObject2 uiObject = (UiObject2) element.getUiObject();
                Logger.debug("Container for first visible is a uiobject2; looping through children");
                List<UiObject2> childObjects = uiObject.getChildren();
                if (childObjects.isEmpty()) {
                    throw new UiObjectNotFoundException("Could not get children for container object");
                }
                for (UiObject2 childObject : childObjects) {
                    try {
                        if (AccessibilityNodeInfoGetter.fromUiObject(childObject) != null) {
                            firstObject = childObject;
                            break;
                        }
                    } catch (UiAutomator2Exception ignored) {
                    }
                }
            }

            if (firstObject == null) {
                Logger.error("No visible child was found for element");
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            }

            String id = UUID.randomUUID().toString();
            AndroidElement androidElement = getAndroidElement(id, firstObject, null);
            ke.add(androidElement);
            JSONObject result = new JSONObject();
            result.put("ELEMENT", id);
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
        } catch (UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch(StaleObjectException e){
            Logger.error("Stale Element Exception: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.STALE_ELEMENT_REFERENCE, e);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        }
    }
}
