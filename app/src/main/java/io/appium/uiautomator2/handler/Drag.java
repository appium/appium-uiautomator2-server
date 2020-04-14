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

import io.appium.uiautomator2.model.api.DragModel;

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
import static io.appium.uiautomator2.utils.ModelValidators.requireDouble;
import static io.appium.uiautomator2.utils.ModelValidators.requireInteger;

public class Drag extends SafeRequestHandler {
    public Drag(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        // DragArguments is created on each execute which prevents leaking state
        // across executions.
        final DragArguments dragArgs = new DragArguments(request);
        return dragArgs.el == null ? drag(dragArgs, request) : dragElement(dragArgs, request);
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

        public DragArguments(final IHttpRequest request) {
            DragModel model = toModel(request, DragModel.class);
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();

            if (model.elementId != null) {
                el = session.getKnownElements().getElementFromCache(model.elementId);
            }
            if (model.destElId != null) {
                destEl = session.getKnownElements().getElementFromCache(model.destElId);
            }

            start = new Point(requireDouble(model, "startX"), requireDouble(model, "startY"));
            end = new Point(requireDouble(model, "endX"), requireDouble(model, "endY"));
            steps = requireInteger(model, "steps");
        }
    }
}
