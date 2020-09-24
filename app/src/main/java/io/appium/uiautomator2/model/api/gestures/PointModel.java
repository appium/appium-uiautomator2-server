package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Point;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class PointModel extends BaseModel {
    @RequiredField
    public Double x;
    @RequiredField
    public Double y;

    public PointModel() {}

    public Point toNativePoint() {
        return new Point(x.intValue(), y.intValue());
    }

    public io.appium.uiautomator2.model.Point toPoint() {
        return new io.appium.uiautomator2.model.Point(x, y);
    }
}
