package io.appium.uiautomator2.handler;

import android.os.RemoteException;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.common.exceptions.InvalidCoordinatesException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.OrientationEnum;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class RotateScreen extends SafeRequestHandler {


    public RotateScreen(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        JSONObject payload = getPayload(request);
        try {
            if (payload.has("orientation")) {
                String orientation = payload.getString("orientation");
                return handleRotation(request, orientation);
            }

            if (payload.has("x") && payload.has("y") && payload.has("z")) {
                int x = payload.getInt("x");
                int y = payload.getInt("y");
                int z = payload.getInt("z");
                return handleRotation(request, x, y, z);
            }

            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_COMMAND,
                    "Unable to Rotate Device, Unsupported arguments");
        } catch (RemoteException | InterruptedException e) {
            Logger.error("Exception while rotating Screen ", e);
            throw new UiAutomator2Exception(e);
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
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                    String.format("Already in %s mode", current.getOrientation()));
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
                    return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS,
                            "Already in landscape mode.");
            }
        } else {
            switch (current) {
                case ROTATION_90:
                case ROTATION_270:
                    getUiDevice().setOrientationNatural();
                    desired = OrientationEnum.ROTATION_0;
                    break;
                default:
                    return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Already in portrait mode.");
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
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Set the orientation, but app refused to rotate.");
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Rotation (" + current.getOrientation() + ") successful.");
    }
}
