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
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.ModelValidators.requireDouble;
import static io.appium.uiautomator2.utils.ModelValidators.requireInteger;

public class Swipe extends SafeRequestHandler {

    public Swipe(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        final Point absStartPos;
        final Point absEndPos;
        final boolean isSwipePerformed;
        final SwipeArguments swipeArgs;
        swipeArgs = new SwipeArguments(request);

        if (swipeArgs.element != null) {
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

        isSwipePerformed = EventRegister.runAndRegisterScrollEvents(new ReturningRunnable<Boolean>() {
            @Override
            public void run() {
                setResult(getUiDevice().swipe(absStartPos.x.intValue(),
                        absStartPos.y.intValue(), absEndPos.x.intValue(),
                        absEndPos.y.intValue(), swipeArgs.steps));

            }
        });
        if (isSwipePerformed) {
            return new AppiumResponse(getSessionId(request));
        }
        throw new InvalidElementStateException("Swipe did not complete successfully");
    }

    private static class SwipeArguments {
        public final Point start;
        public final Point end;
        public final Integer steps;
        public AndroidElement element;

        public SwipeArguments(final IHttpRequest request) {
            SwipeModel model = toModel(request, SwipeModel.class);
            if (model.elementId != null) {
                Logger.info("Payload has elementId: " + model.elementId);
                Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
                element = session.getKnownElements().getElementFromCache(model.elementId);
            }
            start = new Point(requireDouble(model, "startX"), requireDouble(model, "startY"));
            end = new Point(requireDouble(model, "endX"), requireDouble(model, "endY"));
            steps = requireInteger(model, "steps");
        }
    }
}
