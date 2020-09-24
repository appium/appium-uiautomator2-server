package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Rect;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;

public class RectModel extends BaseModel {
    @RequiredField
    public Double top;
    @RequiredField
    public Double left;
    @RequiredField
    public Double width;
    @RequiredField
    public Double height;

    public RectModel() {}

    public Rect toNativeRect() {
        if (width < 0) {
            throw new IllegalArgumentException("Rectangle width must not be negative");
        }
        if (height < 0) {
            throw new IllegalArgumentException("Rectangle height must not be negative");
        }
        return new Rect(left.intValue(), top.intValue(),
                left.intValue() + width.intValue(), top.intValue() + height.intValue());
    }
}
