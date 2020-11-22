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
import io.appium.uiautomator2.model.api.gestures.ScrollModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class Scroll extends SafeRequestHandler {

    public Scroll(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ScrollModel scrollModel = toModel(request, ScrollModel.class);
        final String elementId = scrollModel.origin == null ? null : scrollModel.origin.getUnifiedId();
        final boolean result;
        if (elementId == null) {
            if (scrollModel.area == null) {
                throw new IllegalArgumentException("The scroll area coordinates must be provided if " +
                        "element is not set");
            }
            result = CustomUiDevice.getInstance().getGestureController()
                    .scroll(scrollModel.area.toNativeRect(), scrollModel.getDirection(), scrollModel.percent, scrollModel.speed);
        } else {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getElementsCache().get(elementId);
            result = element.scroll(scrollModel.getDirection(), scrollModel.percent, scrollModel.speed);
        }

        return new AppiumResponse(getSessionId(request), result);
    }
}
