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

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.ScreenOrientation;
import io.appium.uiautomator2.model.api.RotationModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import java.util.Arrays;
import java.util.List;

import static io.appium.uiautomator2.utils.Device.waitForOrientationSync;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.abbreviate;

public class SetRotation extends SafeRequestHandler {
    private static final List<Integer> SUPPORTED_Z_VALUES = Arrays.asList(0, 90, 180, 270);

    public SetRotation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        RotationModel model = toModel(request, RotationModel.class);
        if (model.x != 0 || model.y != 0 || !SUPPORTED_Z_VALUES.contains(model.z)) {
            throw new InvalidCoordinatesException(String.format(
                    "Unable to Rotate Device. Invalid rotation (%s), valid params x=0, y=0, z=(0 or 90 or 180 or 270)",
                    abbreviate(request.body(), 300)));
        }
        ScreenOrientation current = ScreenOrientation.current();
        ScreenOrientation desired = ScreenOrientation.ofDegrees(model.z);
        if (desired != current) {
            CustomUiDevice.getInstance()
                    .getInstrumentation()
                    .getUiAutomation()
                    .setRotation(desired.ordinal());
            current = waitForOrientationSync(desired);
        }
        return new AppiumResponse(getSessionId(request), current.toString());
    }
}
