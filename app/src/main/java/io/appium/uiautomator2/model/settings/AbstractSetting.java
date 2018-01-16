/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.model.settings;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Logger;

public abstract class AbstractSetting<T> implements ISetting {

    private final Class<T> valueType;

    public AbstractSetting(Class<T> valueType) {
        this.valueType = valueType;
    }

    public void updateSetting(Object value) {
        Logger.debug(String.format("Set the %s to %s", getSettingName(), String.valueOf(value)));
        T convertedValue = convertValue(value);
        try {
            apply(convertedValue);
        } catch (Exception e) {
            Logger.error(String.format("Unable to update the setting %s: %s", getSettingName(), e.toString()));
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
