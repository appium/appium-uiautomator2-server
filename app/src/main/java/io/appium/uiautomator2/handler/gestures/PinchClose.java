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

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.gestures.PinchModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class PinchClose extends SafeRequestHandler {

    public PinchClose(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        PinchModel pinchModel = toModel(request, PinchModel.class);
        final String elementId = pinchModel.origin == null ? null : pinchModel.origin.getUnifiedId();
        if (elementId == null) {
            if (pinchModel.area == null) {
                throw new IllegalArgumentException("The pinch area coordinates must be provided if " +
                        "element is not set");
            }
            CustomUiDevice.getInstance().getGestureController()
                    .pinchClose(pinchModel.area.toNativeRect(), pinchModel.percent, pinchModel.speed);
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getElementsCache().get(elementId);
            element.pinchClose(pinchModel.percent, pinchModel.speed);
        }

        return new AppiumResponse(getSessionId(request));
    }
}
