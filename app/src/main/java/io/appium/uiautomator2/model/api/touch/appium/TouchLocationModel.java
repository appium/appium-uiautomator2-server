package io.appium.uiautomator2.model.api.touch.appium;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;

public class TouchLocationModel extends BaseModel {
    @RequiredField
    public Double x;
    @RequiredField
    public Double y;

    public TouchLocationModel() {}
}
