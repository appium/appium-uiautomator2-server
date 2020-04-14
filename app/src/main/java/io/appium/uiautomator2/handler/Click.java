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

import io.appium.uiautomator2.model.api.CoordinatesModel;
import io.appium.uiautomator2.model.api.ElementIdModel;

import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.NoSuchElementException;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.Point;
import io.appium.uiautomator2.utils.PositionHelper;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.ModelValidators.requireDouble;

public class Click extends SafeRequestHandler {

    public Click(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        final String elementId = toModel(request, ElementIdModel.class).getUnifiedId();
        if (elementId != null) {
            Logger.info("Click element command");
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            AndroidElement element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new NoSuchElementException();
            }
            element.click();
        } else {
            Logger.info("tap command");
            CoordinatesModel coordinates = toModel(request, CoordinatesModel.class);
            Point coords = new Point(
                    requireDouble(coordinates, "x"),
                    requireDouble(coordinates, "y"));
            coords = PositionHelper.getDeviceAbsPos(coords);
            if (!getUiDevice().click(coords.x.intValue(), coords.y.intValue())) {
                throw new InvalidElementStateException(
                        String.format("Click failed at (%s, %s) coordinates",
                                coords.x.intValue(), coords.y.intValue()));
            }
        }
        Device.waitForIdle();
        return new AppiumResponse(getSessionId(request));
    }
}
