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

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.DoubleClickModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class DoubleClick extends SafeRequestHandler {

    public DoubleClick(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        DoubleClickModel doubleClickModel = toModel(request, DoubleClickModel.class);
        final String elementId = doubleClickModel.origin == null ? null : doubleClickModel.origin.getUnifiedId();
        if (elementId == null) {
            if (doubleClickModel.offset == null) {
                throw new IllegalArgumentException("Double click offset coordinates must be provided " +
                        "if element is not set");
            }
            CustomUiDevice.getInstance().getGestureController().doubleClick(
                    doubleClickModel.offset.toNativePoint()
            );
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getElementsCache().get(elementId);
            if (doubleClickModel.offset == null) {
                element.doubleClick();
            } else {
                Rect bounds = element.getBounds();
                Point location = new Point(bounds.left + doubleClickModel.offset.x.intValue(),
                        bounds.top + doubleClickModel.offset.y.intValue());
                CustomUiDevice.getInstance().getGestureController().doubleClick(location);
            }
        }

        return new AppiumResponse(getSessionId(request));
    }
}
