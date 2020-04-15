package io.appium.uiautomator2.model.api.touch.w3c;

import io.appium.uiautomator2.model.api.BaseModel;
import io.appium.uiautomator2.model.api.touch.appium.TouchLocationModel;

public class W3CGestureModel implements BaseModel {
    public String type;
    public Integer duration;
    public String origin;
    public Double x;
    public Double y;
    public Integer button;
    public String value;

    public W3CGestureModel() {}
}
