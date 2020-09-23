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

package io.appium.uiautomator2.handler.gestures;

import android.graphics.Point;
import android.graphics.Rect;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.DragModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class Drag extends SafeRequestHandler {

    public Drag(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        DragModel dragModel = toModel(request, DragModel.class);
        final String elementId = dragModel.origin == null ? null : dragModel.origin.getUnifiedId();
        if (elementId == null) {
            CustomUiDevice.getInstance().getGestureController().drag(
                    dragModel.getNativeStartPoint(), dragModel.getNativeEndPoint(), dragModel.speed);
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
            if (dragModel.startX == null && dragModel.startY == null) {
                element.drag(dragModel.getEndPoint(), dragModel.speed);
            } else if (dragModel.startX != null && dragModel.startY != null) {
                Rect bounds = element.getBounds();
                Point start = new Point(bounds.left + dragModel.startX.intValue(),
                        bounds.top + dragModel.startY.intValue());
                CustomUiDevice.getInstance().getGestureController().drag(start, dragModel.getNativeEndPoint(),
                        dragModel.speed);
            } else {
                throw new IllegalArgumentException("Both startX and startY coordinates must be set");
            }
        }

        return new AppiumResponse(getSessionId(request));
    }
}
