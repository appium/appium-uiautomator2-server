package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.model.api.ElementModel;
import io.appium.uiautomator2.model.api.FlickByOffsetModel;
import io.appium.uiautomator2.model.api.FlickBySpeedModel;

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
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class Flick extends SafeRequestHandler {

    public Flick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Point start = new Point(0.5, 0.5);
        Point end = new Point();
        double steps;
        final String elementId = toModel(request, ElementModel.class).getUnifiedId();
        if (elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
            start = element.getAbsolutePosition(start);
            FlickByOffsetModel model = toModel(request, FlickByOffsetModel.class);
            final Integer xoffset = model.xoffset;
            final Integer yoffset = model.yoffset;
            final int speed = model.speed;

            steps = 1250.0 / speed + 1;
            end.x = start.x + xoffset;
            end.y = start.y + yoffset;
        } else {
            FlickBySpeedModel model = toModel(request, FlickBySpeedModel.class);
            final Integer xSpeed = model.xspeed;
            final Integer ySpeed = model.yspeed;

            final double speed = Math.min(1250.0, Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
            steps = 1250.0 / speed + 1;

            start = PositionHelper.getDeviceAbsPos(start);
            end = calculateEndPoint(start, xSpeed, ySpeed);
        }

        steps = Math.abs(steps);
        Logger.debug("Flicking from " + start.toString() + " to " + end.toString()
                + " with steps: " + (int) steps);
        final boolean res = getUiDevice().swipe(start.x.intValue(), start.y.intValue(),
                end.x.intValue(), end.y.intValue(), (int) steps);

        if (res) {
            return new AppiumResponse(getSessionId(request));
        }
        throw new InvalidElementStateException("Flick did not complete successfully");
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
