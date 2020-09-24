package io.appium.uiautomator2.model.api.gestures;

import androidx.test.uiautomator.Direction;

import java.util.Arrays;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.ElementModel;

public class SwipeModel extends BaseModel {
    public ElementModel origin;
    public RectModel area;
    @RequiredField
    public String direction;
    @RequiredField
    public Float percent;
    public Integer speed;

    public SwipeModel() {}

    public Direction getDirection() {
        try {
            return Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException(
                    String.format("Swipe direction must be one of %s", Arrays.toString(Direction.values())));
        }
    }
}
