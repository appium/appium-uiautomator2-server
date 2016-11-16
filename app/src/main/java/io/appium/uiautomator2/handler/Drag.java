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

public class Drag extends SafeRequestHandler {
    public Drag(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {
        // DragArguments is created on each execute which prevents leaking state
        // across executions.
        final DragArguments dragArgs;
        try {
            dragArgs = new DragArguments(request);
            if (getPayload(request).has("elementId")) {
                return dragElement(dragArgs, request);
            } else {
                return drag(dragArgs, request);
            }
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
    }

    private AppiumResponse drag(final DragArguments dragArgs, final IHttpRequest request) {
        Point absStartPos;
        Point absEndPos;

        try {
            absStartPos = PositionHelper.getDeviceAbsPos(dragArgs.start);
            absEndPos = PositionHelper.getDeviceAbsPos(dragArgs.end);
        } catch (final InvalidCoordinatesException e) {
            Logger.error("The coordinates provided to an interactions operation are invalid. ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_COORDINATES, e);
        } catch (final UiObjectNotFoundException e) {
            Logger.error("Element not found: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }

        Logger.debug("Dragging from " + absStartPos.toString() + " to "
                + absEndPos.toString() + " with steps: " + dragArgs.steps.toString());
        final boolean res = getUiDevice().drag(absStartPos.x.intValue(),
                absStartPos.y.intValue(), absEndPos.x.intValue(),
                absEndPos.y.intValue(), dragArgs.steps);
        if (!res) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Drag did not complete successfully");
        }
        return new AppiumResponse(getSessionId(request), res);
    }

    private AppiumResponse dragElement(final DragArguments dragArgs, final IHttpRequest request) {
        Point absEndPos;

        if (dragArgs.destEl == null) {
            try {
                absEndPos = PositionHelper.getDeviceAbsPos(dragArgs.end);
            } catch (final InvalidCoordinatesException e) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
            } catch (final UiObjectNotFoundException e) {
                return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
            }

            Logger.debug("Dragging the element with id " + dragArgs.el.getId()
                    + " to " + absEndPos.toString() + " with steps: "
                    + dragArgs.steps.toString());
            try {
                final boolean res = dragArgs.el.dragTo(absEndPos.x.intValue(),
                        absEndPos.y.intValue(), dragArgs.steps);
                if (!res) {
                    return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Drag did not complete successfully");
                } else {
                    return new AppiumResponse(getSessionId(request), res);
                }
            } catch (final UiObjectNotFoundException e) {
                Logger.error("Drag did not complete successfully. Element not found: ", e);
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            } catch (InvalidCoordinatesException e) {
                Logger.error("The coordinates provided to an interactions operation are invalid. ", e);
                return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_COORDINATES, e);
            }
        } else {

            Logger.debug("Dragging the element with id " + dragArgs.el.getId()
                    + " to destination element with id " + dragArgs.destEl.getId()
                    + " with steps: " + dragArgs.steps);
            try {
                final boolean res = dragArgs.el.dragTo(dragArgs.destEl.getUiObject(),
                        dragArgs.steps);
                if (!res) {
                    return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Drag did not complete successfully");
                } else {
                    return new AppiumResponse(getSessionId(request), res);
                }
            } catch (final UiObjectNotFoundException e) {
                Logger.error("Drag did not complete successfully. Element not found: ", e);
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            } catch (InvalidCoordinatesException e) {
                Logger.error("The coordinates provided to an interactions operation are invalid. ", e);
                return new AppiumResponse(getSessionId(request), WDStatus.INVALID_ELEMENT_COORDINATES, e);
            }
        }

    }

    private class DragArguments {

        public final Point start;
        public final Point end;
        public final Integer steps;
        public AndroidElement el;
        public AndroidElement destEl;

        public DragArguments(final IHttpRequest request) throws JSONException {

            JSONObject payload = getPayload(request);

            if (payload.has("elementId")) {
                String id = payload.getString("elementId");
                el = KnownElements.getElementFromCache(id);
            }
            if (payload.has("destElId")) {
                String id = payload.getString("destElId");
                destEl = KnownElements.getElementFromCache(id);
            }

            start = new Point(payload.get("startX"), payload.get("startY"));
            end = new Point(payload.get("endX"), payload.get("endY"));
            steps = (Integer) payload.get("steps");
        }
    }
}
