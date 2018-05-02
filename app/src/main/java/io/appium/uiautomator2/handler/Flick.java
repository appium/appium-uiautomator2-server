package io.appium.uiautomator2.handler;

import android.support.test.uiautomator.UiObjectNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

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

public class Flick extends SafeRequestHandler {

    public Flick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException,
            JSONException {
        Logger.info("Get Text of element command");
        Point start = new Point(0.5, 0.5);
        Point end = new Point();
        Double steps;
        JSONObject payload = getPayload(request);
        if (payload.has(ELEMENT_ID_KEY_NAME)) {
            String id = payload.getString(ELEMENT_ID_KEY_NAME);
            AndroidElement element = KnownElements.getElementFromCache(id);
            if (element == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            }
            start = element.getAbsolutePosition(start);
            final Integer xoffset = Integer.parseInt(payload.getString("xoffset"));
            final Integer yoffset = Integer.parseInt(payload.getString("yoffset"));
            final Integer speed = Integer.parseInt(payload.getString("speed"));

            steps = 1250.0 / speed + 1;
            end.x = start.x + xoffset;
            end.y = start.y + yoffset;

        } else {
            final Integer xSpeed = Integer.parseInt(payload.getString("xSpeed"));
            final Integer ySpeed = Integer.parseInt(payload.getString("ySpeed"));

            final Double speed = Math.min(1250.0,
                    Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
            steps = 1250.0 / speed + 1;

            start = PositionHelper.getDeviceAbsPos(start);
            end = calculateEndPoint(start, xSpeed, ySpeed);
        }

        steps = Math.abs(steps);
        Logger.debug("Flicking from " + start.toString() + " to " + end.toString()
                + " with steps: " + steps.intValue());
        final boolean res = getUiDevice().swipe(start.x.intValue(), start.y.intValue(),
                end.x.intValue(), end.y.intValue(), steps.intValue());

        if (res) {
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR,
                "Flick did not complete successfully");
    }

    private Point calculateEndPoint(final Point start, final Integer xSpeed,
                                    final Integer ySpeed) {
        final Point end = new Point();
        final double speedRatio = (double) xSpeed / ySpeed;
        double xOff;
        double yOff;

        final double value = Math.min(getUiDevice().getDisplayHeight(), getUiDevice().getDisplayWidth());

        if (speedRatio < 1) {
            yOff = value / 4;
            xOff = value / 4 * speedRatio;
        } else {
            xOff = value / 4;
            yOff = value / 4 / speedRatio;
        }

        xOff = Integer.signum(xSpeed) * xOff;
        yOff = Integer.signum(ySpeed) * yOff;

        end.x = start.x + xOff;
        end.y = start.y + yOff;
        return end;
    }
}
