package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Point;

import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class LongClickModel extends BaseModel {
    public ElementModel origin;
    public Double x;
    public Double y;
    public Double duration;

    public LongClickModel() {}

    public Point toNativePoint() {
        return new Point(x.intValue(), y.intValue());
    }
}
