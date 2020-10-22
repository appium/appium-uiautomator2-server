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

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.ScreenOrientation;
import io.appium.uiautomator2.model.ScreenRotation;
import io.appium.uiautomator2.model.api.OrientationModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.UseResourcesForOrientationDetection;

import static io.appium.uiautomator2.model.settings.Settings.USE_RESOURCES_FOR_ORIENTATION_DETECTION;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class SetOrientation extends SafeRequestHandler {
    public SetOrientation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        OrientationModel model = toModel(request, OrientationModel.class);
        ScreenRotation rotation = CustomUiDevice.getInstance()
                .setRotationSync(ScreenRotation.ofOrientation(model.orientation));
        String result = ((UseResourcesForOrientationDetection) USE_RESOURCES_FOR_ORIENTATION_DETECTION.getSetting()).getValue()
                ? ScreenOrientation.current().name()
                : rotation.toOrientation().name();
        return new AppiumResponse(getSessionId(request), result);
    }
}
