package io.appium.uiautomator2.model.api.gestures;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class PinchModel extends BaseModel {
    public ElementModel origin;
    public RectModel area;
    @RequiredField
    public Float percent;
    public Integer speed;
}
