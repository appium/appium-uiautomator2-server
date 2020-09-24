package io.appium.uiautomator2.model.api.gestures;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class DragModel extends BaseModel {
    public ElementModel origin;
    public PointModel start;
    @RequiredField
    public PointModel end;
    public Integer speed;

    public DragModel() {}
}
