package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Rect;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class PinchModel extends BaseModel {
    public ElementModel origin;
    public Double startX;
    public Double startY;
    public Double width;
    public Double height;
    @RequiredField
    public Float percent;
    public Integer speed;

    public PinchModel() {}

    public Rect getArea() {
        if (startX == null || startY == null) {
            throw new IllegalArgumentException("Both startX and startY coordinates of the pinch area must be set");
        }
        if (width == null || height == null) {
            throw new IllegalArgumentException("Both width and height of the pinch area must be set");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Both width and height of the swipe area must be greater than zero");
        }
        return new Rect(startX.intValue(), startY.intValue(),
                startX.intValue() + width.intValue(), startY.intValue() + height.intValue());
    }
}
