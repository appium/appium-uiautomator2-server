package io.appium.uiautomator2.model.api.touch.w3c;

import io.appium.uiautomator2.model.api.BaseModel;

import java.util.List;

public class W3CItemModel implements BaseModel {
    public String type;
    public String id;
    public W3CItemParametersModel parameters;
    public List<W3CGestureModel> items;

    public W3CItemModel() {}
}
