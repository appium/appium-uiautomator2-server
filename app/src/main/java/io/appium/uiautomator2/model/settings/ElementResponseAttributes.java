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

public class ElementResponseAttributes extends AbstractSetting<String> {
    private static final String SETTING_NAME = "elementResponseAttributes";
    private static final String DEFAULT_ATTRIBUTES = "name,text";
    private String value = DEFAULT_ATTRIBUTES;

    public ElementResponseAttributes() {
        super(String.class, SETTING_NAME);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_ATTRIBUTES;
    }

    public String[] asArray() {
        return value.split(",");
    }

    @Override
    protected void apply(String elementResponseAttributes) {
        value = elementResponseAttributes;
    }
}
