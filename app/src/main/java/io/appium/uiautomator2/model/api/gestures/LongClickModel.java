package io.appium.uiautomator2.model.api.gestures;

import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class LongClickModel extends BaseModel {
    public ElementModel origin;
    public PointModel offset;
    public Double duration;
}
