package io.appium.uiautomator2.model.api.gestures;

import android.graphics.Rect;

import androidx.test.uiautomator.Direction;

import java.util.Arrays;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class ScrollModel extends BaseModel {
    public ElementModel origin;
    public Double startX;
    public Double startY;
    public Double width;
    public Double height;
    @RequiredField
    public String direction;
    @RequiredField
    public Float percent;
    public Integer speed;

    public ScrollModel() {}

    public Rect getArea() {
        if (startX == null || startY == null) {
            throw new IllegalArgumentException("Both startX and startY coordinates of the scroll area must be set");
        }
        if (width == null || height == null) {
            throw new IllegalArgumentException("Both width and height of the scroll area must be set");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Both width and height of the scroll area must be greater than zero");
        }
        return new Rect(startX.intValue(), startY.intValue(),
                startX.intValue() + width.intValue(), startY.intValue() + height.intValue());
    }

    public Direction getDirection() {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException(
                    String.format("Scroll direction must be one of %s", Arrays.toString(Direction.values())));
        }
    }
}
