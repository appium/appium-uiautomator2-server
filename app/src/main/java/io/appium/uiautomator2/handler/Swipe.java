package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.UiObjectNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class Swipe extends SafeRequestHandler {

    public Swipe(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        Point absStartPos, absEndPos;
        final boolean isSwipePerformed;
        try {
            final SwipeArguments swipeArgs;
            JSONObject payload = getPayload(request);
            Logger.info("JSON Payload : ", payload.toString());
            swipeArgs = new SwipeArguments(request);

            if (payload.has("elementId")) {
                absStartPos = swipeArgs.element.getAbsolutePosition(swipeArgs.start);
                absEndPos = swipeArgs.element.getAbsolutePosition(swipeArgs.end);
                Logger.debug("Swiping the element with ElementId " + swipeArgs.element.getId()
                        + " to " + absEndPos.toString() + " with steps: "
                        + swipeArgs.steps.toString());
            } else {
                absStartPos = PositionHelper.getDeviceAbsPos(swipeArgs.start);
                absEndPos = PositionHelper.getDeviceAbsPos(swipeArgs.end);
                Logger.debug("Swiping On Device from " + absStartPos.toString() + " to "
                        + absEndPos.toString() + " with steps: " + swipeArgs.steps.toString());
            }

            isSwipePerformed = getUiDevice().swipe(absStartPos.x.intValue(),
                    absStartPos.y.intValue(), absEndPos.x.intValue(),
                    absEndPos.y.intValue(), swipeArgs.steps);
            if (!isSwipePerformed) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Swipe did not complete successfully");
            } else {
                return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, isSwipePerformed);
            }

        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (final UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        } catch (final InvalidCoordinatesException e) {
            Logger.error("The coordinates provided to an interactions operation are invalid. ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_COORDINATES, e);
        }
    }

    public class SwipeArguments {
        public final Point start;
        public final Point end;
        public final Integer steps;
        public AndroidElement element;

        public SwipeArguments(final IHttpRequest request) throws JSONException {
            JSONObject payload = getPayload(request);
            if (payload.has("elementId")) {
                Logger.info("Payload has elementId" + payload);
                String id = payload.getString("elementId");
                element = KnownElements.getElementFromCache(id);
            }
            start = new Point(payload.get("startX"), payload.get("startY"));
            end = new Point(payload.get("endX"), payload.get("endY"));
            steps = (Integer) payload.get("steps");
        }
    }
}
