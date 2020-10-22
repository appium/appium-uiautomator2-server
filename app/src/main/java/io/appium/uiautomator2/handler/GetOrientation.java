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
import io.appium.uiautomator2.utils.Logger;

public class GetOrientation extends SafeRequestHandler {

    public GetOrientation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        ScreenOrientation orientation = ScreenOrientation.current();
        ScreenRotation rotation = ScreenRotation.current();
        if (orientation == null) {
            Logger.warn(String.format("The current screen orientation is unknown. " +
                    "Assuming it based on the current rotation value %s", rotation.name()));
            orientation = rotation == ScreenRotation.ROTATION_0 || rotation == ScreenRotation.ROTATION_180
                    ? ScreenOrientation.PORTRAIT
                    : ScreenOrientation.LANDSCAPE;
        }
        return new AppiumResponse(getSessionId(request), orientation.name());
    }
}
