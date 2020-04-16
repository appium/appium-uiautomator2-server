package io.appium.uiautomator2.model.api.touch.w3c;

import io.appium.uiautomator2.model.RequiredField;
import io.appium.uiautomator2.model.api.BaseModel;

import java.util.List;

public class W3CItemModel extends BaseModel {
    @RequiredField
    public String type;
    @RequiredField
    public String id;
    public W3CItemParametersModel parameters;
    @RequiredField
    public List<W3CGestureModel> actions;

    public W3CItemModel() {}
}
