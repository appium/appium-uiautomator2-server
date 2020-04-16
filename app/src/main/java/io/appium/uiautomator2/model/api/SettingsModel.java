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

package io.appium.uiautomator2.model.api;

import io.appium.uiautomator2.model.RequiredField;

import java.util.HashMap;
import java.util.Map;

public class SettingsModel extends BaseModel {
    @RequiredField
    public Map<String, Object> settings;

    public SettingsModel() {}

    public SettingsModel(Object ...args) {
        if (args.length % 2 != 0) {
            throw new IllegalArgumentException("There must be even count of arguments");
        }

        this.settings = new HashMap<>();
        for (int i = 0; i < args.length; i += 2) {
            this.settings.put((String) args[i], args[i + 1]);
        }
    }
}
