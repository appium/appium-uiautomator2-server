package io.appium.uiautomator2.handler;

import android.os.RemoteException;
import android.support.test.uiautomator.UiDevice;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.OrientationEnum;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

public class RotateScreen extends SafeRequestHandler {

    public RotateScreen(String mappedUri) {
        super(mappedUri);
    }

    @Override
    public AppiumResponse safeHandle(IHttpRequest request) {

        try {
            JSONObject payload = getPayload(request);
            String orientation = payload.getString("orientation");
            return handleRotation(request, orientation);
        } catch (RemoteException e) {
            Logger.error("Exception while rotating Screen ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        } catch (JSONException e) {
            Logger.error("Exception while reading JSON: ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.JSON_DECODER_ERROR, e);
        } catch (InterruptedException e) {
            Logger.error("Exception while rotating Screen ", e);
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, e);
        }
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
        final UiDevice d = Device.getUiDevice();
        OrientationEnum desired;
        OrientationEnum current = OrientationEnum.fromInteger(d.getDisplayRotation());

        Logger.debug("Desired orientation: " + orientation);
        Logger.debug("Current rotation: " + current);

        if (orientation.equalsIgnoreCase("LANDSCAPE")) {
            switch (current) {
                case ROTATION_0:
                    d.setOrientationRight();
                    desired = OrientationEnum.ROTATION_270;
                    break;
                case ROTATION_180:
                    d.setOrientationLeft();
                    desired = OrientationEnum.ROTATION_270;
                    break;
                default:
                    return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Already in landscape mode.");
            }
        } else {
            switch (current) {
                case ROTATION_90:
                case ROTATION_270:
                    d.setOrientationNatural();
                    desired = OrientationEnum.ROTATION_0;
                    break;
                default:
                    return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Already in portrait mode.");
            }
        }
        current = OrientationEnum.fromInteger(d.getDisplayRotation());
        // If the orientation has not changed,
        // busy wait until the TIMEOUT has expired
        final int TIMEOUT = 2000;
        final long then = System.currentTimeMillis();
        long now = then;
        while (current != desired && now - then < TIMEOUT) {
            Thread.sleep(100);
            now = System.currentTimeMillis();
            current = OrientationEnum.fromInteger(d.getDisplayRotation());
        }
        if (current != desired) {
            return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, "Set the orientation, but app refused to rotate.");
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, "Rotation (" + orientation + ") successful.");
    }
}
