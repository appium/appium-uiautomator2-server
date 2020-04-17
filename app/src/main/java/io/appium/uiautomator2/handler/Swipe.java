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

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.model.api.SwipeModel;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.core.EventRegister;
import io.appium.uiautomator2.core.ReturningRunnable;
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

public class Swipe extends SafeRequestHandler {

    public Swipe(String mappedUri) {
        super(mappedUri);
    }

    private boolean executeSwipe(final Point start, final Point end, final int steps) {
        return EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                setResult(getUiDevice().swipe(
                        start.x.intValue(), start.y.intValue(),
                        end.x.intValue(), end.y.intValue(),
                        steps));
            }
        });
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        SwipeModel model = toModel(request, SwipeModel.class);

        Point absStartPos;
        Point absEndPos;
        if (model.elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(model.elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
            absStartPos = element.getAbsolutePosition(new Point(model.startX, model.startY));
            absEndPos = element.getAbsolutePosition(new Point(model.endX, model.endY));
            Logger.debug(String.format("Swiping the element %s from %s to %s in %s steps",
                    element.getId(), absStartPos.toString(), absEndPos.toString(),
                    model.steps));
        } else {
            absStartPos = PositionHelper.getDeviceAbsPos(new Point(model.startX, model.startY));
            absEndPos = PositionHelper.getDeviceAbsPos(new Point(model.endX, model.endY));
            Logger.debug(String.format("Swiping on device from %s to %s in %s steps",
                    absStartPos.toString(), absEndPos.toString(), model.steps));
        }

        if (!executeSwipe(absStartPos, absEndPos, model.steps)) {
            throw new InvalidElementStateException("Swipe action cannot be performed");
        }

        return new AppiumResponse(getSessionId(request));
    }
}
