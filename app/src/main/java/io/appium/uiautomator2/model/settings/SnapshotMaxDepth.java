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

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;

public class SnapshotMaxDepth extends AbstractSetting<Integer> {
    public static final String SETTING_NAME = "snapshotMaxDepth";
    // Set DEFAULT_VALUE as 70 to avoid StackOverflow from infinite recursion
    // https://github.com/appium/appium/issues/12545
    // https://github.com/appium/appium/issues/12892
    private static final int DEFAULT_VALUE = 70;
    private static final int MIN_DEPTH = 10;
    private static final int MAX_DEPTH = 500;
    private Integer value = DEFAULT_VALUE;

    public SnapshotMaxDepth() {
        super(Integer.class, SETTING_NAME);
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    protected void apply(Integer value) {
        if (value == null || value < MIN_DEPTH || value > MAX_DEPTH) {
            throw new InvalidArgumentException(String.format(
                "Invalid %s value specified, must be in range %s..%s. %s was given",
                SETTING_NAME,
                MIN_DEPTH,
                MAX_DEPTH,
                value
            ));
        }
        this.value = value;
    }
}
