package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class Drag extends SafeRequestHandler {
    public Drag(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        // DragArguments is created on each execute which prevents leaking state
        // across executions.
        final DragArguments dragArgs = new DragArguments(request);
        if (toJSON(request).has("elementId")) {
            return dragElement(dragArgs, request);
        }
        return drag(dragArgs, request);
    }

    private AppiumResponse drag(final DragArguments dragArgs, final IHttpRequest request) {
        Point absStartPos = PositionHelper.getDeviceAbsPos(dragArgs.start);
        Point absEndPos = PositionHelper.getDeviceAbsPos(dragArgs.end);

        Logger.debug("Dragging from " + absStartPos.toString() + " to "
                + absEndPos.toString() + " with steps: " + dragArgs.steps.toString());
        final boolean res = getUiDevice().drag(absStartPos.x.intValue(),
                absStartPos.y.intValue(), absEndPos.x.intValue(),
                absEndPos.y.intValue(), dragArgs.steps);
        if (!res) {
            throw new InvalidElementStateException("Drag did not complete successfully");
        }
        return new AppiumResponse(getSessionId(request));
    }

    private AppiumResponse dragElement(final DragArguments dragArgs, final IHttpRequest request) {
        Point absEndPos;

        if (dragArgs.destEl == null) {
            absEndPos = PositionHelper.getDeviceAbsPos(dragArgs.end);

            Logger.debug("Dragging the element with id " + dragArgs.el.getId()
                    + " to " + absEndPos.toString() + " with steps: "
                    + dragArgs.steps.toString());
            try {
                final boolean res = dragArgs.el.dragTo(absEndPos.x.intValue(),
                        absEndPos.y.intValue(), dragArgs.steps);
                if (!res) {
                    throw new InvalidElementStateException("Drag did not complete successfully");
                }
                return new AppiumResponse(getSessionId(request));
            } catch (final UiObjectNotFoundException e) {
                throw new ElementNotFoundException("Drag did not complete successfully. Element not found", e);
            }
        } else {

            Logger.debug("Dragging the element with id " + dragArgs.el.getId()
                    + " to destination element with id " + dragArgs.destEl.getId()
                    + " with steps: " + dragArgs.steps);
            try {
                final boolean res = dragArgs.el.dragTo(dragArgs.destEl.getUiObject(), dragArgs.steps);
                if (!res) {
                    throw new InvalidElementStateException("Drag did not complete successfully");
                }
                return new AppiumResponse(getSessionId(request));
            } catch (final UiObjectNotFoundException e) {
                throw new ElementNotFoundException("Drag did not complete successfully. Element not found", e);
            }
        }

    }

    private static class DragArguments {

        public final Point start;
        public final Point end;
        public final Integer steps;
        public AndroidElement el;
        public AndroidElement destEl;

        public DragArguments(final IHttpRequest request) throws JSONException {

            JSONObject payload = toJSON(request);
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();

            if (payload.has("elementId")) {
                String id = payload.getString("elementId");
                el = session.getKnownElements().getElementFromCache(id);
            }
            if (payload.has("destElId")) {
                String id = payload.getString("destElId");
                destEl = session.getKnownElements().getElementFromCache(id);
            }

            start = new Point(payload.get("startX"), payload.get("startY"));
            end = new Point(payload.get("endX"), payload.get("endY"));
            steps = (Integer) payload.get("steps");
        }
    }
}
