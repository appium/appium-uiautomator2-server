package io.appium.uiautomator2.model.settings;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Logger;

public abstract class AbstractSetting<T> implements ISetting {

    private Class<T> valueType;

    public AbstractSetting(Class<T> valueType) {
        this.valueType = valueType;
    }

    public void updateSetting(Object value) {
        Logger.debug(String.format("Set the %s to %s", getSettingName(), String.valueOf(value)));
        T convertedValue = convertValue(value);
        try {
            apply(convertedValue);
        } catch (Exception e) {
            Logger.error(String.format("Unable to update the setting %s: %s", getSettingName(), e.getMessage()));
        }
    }

    public abstract String getSettingName();

    public Class<T> getValueType() {
        return valueType;
    }

    protected abstract void apply(T value);

    private T convertValue(Object value) {
        if (!valueType.isInstance(value)) {
            String errorMsg = String.format("Invalid setting value type. Got: %s. Expected: %s.",
                    value.getClass().getName(), valueType.getName());
            throw new UiAutomator2Exception(errorMsg);
        }
        return valueType.cast(value);
    }
}
