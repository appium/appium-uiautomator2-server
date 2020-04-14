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

import io.appium.uiautomator2.model.api.RotationModel;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.OrientationEnum;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class RotateScreen extends SafeRequestHandler {

    public RotateScreen(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        RotationModel model = toModel(request, RotationModel.class);
        try {
            if (model.orientation != null) {
                return handleRotation(request, model.orientation);
            }

            if (model.x != null && model.y != null && model.z != null) {
                return handleRotation(request, model.x, model.y, model.z);
            }

            throw new InvalidArgumentException("Unable to Rotate Device, Unsupported arguments");
        } catch (RemoteException | InterruptedException e) {
            throw new UiAutomator2Exception("Cannot perform screen rotation", e);
        }
    }

    private AppiumResponse handleRotation(IHttpRequest request, int x, int y, int z)
            throws InvalidCoordinatesException, InterruptedException {
        if (x != 0 || y != 0 || !(z == 0 || z == 90 || z == 180 || z == 270)) {
            throw new InvalidCoordinatesException(
                    "Unable to Rotate Device. Invalid rotation, valid params x=0, y=0, z=(0 or 90 or 180 or 270)");
        }
        OrientationEnum current = OrientationEnum.fromInteger(getUiDevice().getDisplayRotation());
        OrientationEnum desired = OrientationEnum.fromInteger(z / 90);
        if (current.equals(desired)) {
            return new AppiumResponse(getSessionId(request), current.getOrientation());
        }

        switch (desired) {
            case ROTATION_0:
            case ROTATION_90:
            case ROTATION_180:
            case ROTATION_270:
                CustomUiDevice.getInstance()
                        .getInstrumentation()
                        .getUiAutomation()
                        .setRotation(desired.getValue());
                break;
            default:
                throw new InvalidCoordinatesException(String.format(
                        "Unable to Rotate Device. Invalid desired orientation value '%s'", desired));
        }

        return verifyRotation(request, desired);
    }

    /**
     * Set the desired rotation
     *
     * @param orientation The rotation desired (LANDSCAPE or PORTRAIT)
     * @return {@link AppiumResponse}
     * @throws RemoteException
     * @throws InterruptedException
     */
    private AppiumResponse handleRotation(IHttpRequest request, final String orientation)
            throws RemoteException, InterruptedException {
        OrientationEnum desired;
        OrientationEnum current = OrientationEnum.fromInteger(getUiDevice().getDisplayRotation());

        Logger.debug("Desired orientation: " + orientation);
        Logger.debug("Current rotation: " + current);

        if (orientation.equalsIgnoreCase("LANDSCAPE")) {
            switch (current) {
                case ROTATION_0:
                    getUiDevice().setOrientationRight();
                    desired = OrientationEnum.ROTATION_270;
                    break;
                case ROTATION_180:
                    getUiDevice().setOrientationLeft();
                    desired = OrientationEnum.ROTATION_270;
                    break;
                default:
                    return new AppiumResponse(getSessionId(request), current.getOrientation());
            }
        } else {
            switch (current) {
                case ROTATION_90:
                case ROTATION_270:
                    getUiDevice().setOrientationNatural();
                    desired = OrientationEnum.ROTATION_0;
                    break;
                default:
                    return new AppiumResponse(getSessionId(request), current.getOrientation());
            }
        }

        return verifyRotation(request, desired);
    }

    private AppiumResponse verifyRotation(IHttpRequest request, OrientationEnum desired) throws InterruptedException {
        OrientationEnum current = OrientationEnum.fromInteger(getUiDevice().getDisplayRotation());
        // If the orientation has not changed,
        // busy wait until the TIMEOUT has expired
        final int TIMEOUT = 2000;
        final long then = System.currentTimeMillis();
        long now = then;
        while (!current.equals(desired) && now - then < TIMEOUT) {
            Thread.sleep(100);
            now = System.currentTimeMillis();
            current = OrientationEnum.fromInteger(getUiDevice().getDisplayRotation());
        }
        if (current != desired) {
            throw new InvalidElementStateException("Set the orientation, but app refused to rotate");
        }
        return new AppiumResponse(getSessionId(request), current.getOrientation());
    }
}
