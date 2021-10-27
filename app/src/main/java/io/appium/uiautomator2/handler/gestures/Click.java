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

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

import android.graphics.Point;
import android.graphics.Rect;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.ClickModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class Click extends SafeRequestHandler {

    public Click(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ClickModel clickModel = toModel(request, ClickModel.class);
        String elementId = clickModel.origin == null ? null : clickModel.origin.getUnifiedId();
        if (elementId == null) {
            if (clickModel.offset == null) {
                throw new IllegalArgumentException("Click offset coordinates must be provided " +
                        "if element is not set");
            }
            CustomUiDevice.getInstance().getGestureController().click(
                    clickModel.offset.toNativePoint()
            );
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getElementsCache().get(elementId);
            if (clickModel.offset == null) {
                element.click();
            } else {
                Rect bounds = element.getBounds();
                Point location = new Point(
                        bounds.left + clickModel.offset.x.intValue(),
                        bounds.top + clickModel.offset.y.intValue()
                );
                CustomUiDevice.getInstance().getGestureController().click(location);
            }
        }

        return new AppiumResponse(getSessionId(request));
    }
}
