package io.appium.uiautomator2.handler;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.OrientationEnum;
import io.appium.uiautomator2.model.api.RotationModel;
import io.appium.uiautomator2.utils.Device;

public class GetRotation extends SafeRequestHandler {

    public GetRotation(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
       int rotation = Device.getUiDevice().getDisplayRotation();
       return new AppiumResponse(getSessionId(request), getRotation(rotation));
    }

    private RotationModel getRotation(int orientation) {
        RotationModel result = new RotationModel();
        result.x = 0;
        result.y = 0;
        OrientationEnum orientationEnum = OrientationEnum.fromInteger(orientation);
        switch (orientationEnum) {
            case ROTATION_0:
                result.z = 0;
                break;
            case ROTATION_90:
                result.z = 90;
                break;
            case ROTATION_180:
                result.z = 180;
                break;
            case ROTATION_270:
                result.z = 270;
                break;
        }
        return result;
    }
}
