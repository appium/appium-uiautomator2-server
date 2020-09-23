package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Point;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.ElementModel;

public class DragModel extends ElementModel {
    public Double startX;
    public Double startY;
    @RequiredField
    public Double endX;
    @RequiredField
    public Double endY;
    public Integer speed;

    public DragModel() {}

    public Point getNativeStartPoint() {
        return new Point(startX.intValue(), startY.intValue());
    }

    public Point getNativeEndPoint() {
        return new Point(endX.intValue(), endY.intValue());
    }

    public io.appium.uiautomator2.model.Point getEndPoint() {
        return new io.appium.uiautomator2.model.Point(endX, endY);
    }
}
