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

import android.os.RemoteException;

import android.os.SystemClock;
import io.appium.uiautomator2.model.api.OrientationModel;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.ScreenOrientation;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.Device.waitForOrientationSync;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class SetOrientation extends SafeRequestHandler {
    public SetOrientation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        OrientationModel model = toModel(request, OrientationModel.class);
        ScreenOrientation current = ScreenOrientation.current();
        ScreenOrientation desired = null;
        try {
            if (model.orientation.equalsIgnoreCase(ScreenOrientation.LANDSCAPE)) {
                switch (current) {
                    case ROTATION_0:
                        getUiDevice().setOrientationRight();
                        desired = ScreenOrientation.ROTATION_270;
                        break;
                    case ROTATION_180:
                        getUiDevice().setOrientationLeft();
                        desired = ScreenOrientation.ROTATION_270;
                        break;
                }
            } else {
                switch (current) {
                    case ROTATION_90:
                    case ROTATION_270:
                        getUiDevice().setOrientationNatural();
                        desired = ScreenOrientation.ROTATION_0;
                        break;
                }
            }
        } catch (RemoteException e) {
            throw new UiAutomator2Exception("Cannot perform screen rotation", e);
        }
        current = desired == null ? current : waitForOrientationSync(desired);
        return new AppiumResponse(getSessionId(request), current.toString());
    }
}
