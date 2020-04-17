/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import io.appium.uiautomator2.model.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class Flick extends SafeRequestHandler {
    private static final double SPEED_MAGIC = 1250.0;

    public Flick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Point start = new Point(0.5, 0.5);
        Point end;
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
            if (model.speed == 0) {
                throw new IllegalArgumentException("The speed value cannot be zero");
            }

            steps = SPEED_MAGIC / model.speed + 1;
            end = new Point(start.x + model.xoffset, start.y + model.yoffset);
        } else {
            FlickBySpeedModel model = toModel(request, FlickBySpeedModel.class);
            if (model.xspeed == 0 && model.yspeed == 0) {
                throw new IllegalArgumentException("Both xspeed and yspeed cannot be zero");
            }

            final double speed = Math.min(SPEED_MAGIC,
                    Math.sqrt(model.xspeed * model.xspeed + model.yspeed * model.yspeed));
            steps = SPEED_MAGIC / speed + 1;
            start = PositionHelper.getDeviceAbsPos(start);
            end = calculateEndPoint(start, model.xspeed, model.yspeed);
        }

        steps = Math.abs(steps);
        Logger.debug(String.format("Flicking from %s to %s in %s steps",
                start.toString(), end.toString(), (int) steps));
        if (!performFlick(start, end, (int) steps)) {
            throw new InvalidElementStateException("Flick did not complete successfully");
        }

        return new AppiumResponse(getSessionId(request));
    }

    private boolean performFlick(Point start, Point end, int steps) {
        return getUiDevice().swipe(
                start.x.intValue(), start.y.intValue(),
                end.x.intValue(), end.y.intValue(), steps);
    }

    private Point calculateEndPoint(Point start, int xSpeed, int ySpeed) {
        final double speedRatio = 1.0 * xSpeed / ySpeed;
        final double minDimension = Math.min(getUiDevice().getDisplayHeight(), getUiDevice().getDisplayWidth());
        double xOffset;
        double yOffset;
        if (speedRatio < 1) {
            yOffset = minDimension / 4;
            xOffset = minDimension / 4 * speedRatio;
        } else {
            xOffset = minDimension / 4;
            yOffset = minDimension / 4 / speedRatio;
        }
        xOffset = Integer.signum(xSpeed) * xOffset;
        yOffset = Integer.signum(ySpeed) * yOffset;
        return new Point(start.x + xOffset, start.y + yOffset);
    }
}
