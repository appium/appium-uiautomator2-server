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
import io.appium.uiautomator2.server.ServerConfig;

/**
 * Controls the scaling factor of streamed screenshots. 100 means no scaling is
 * applied (e.g. 100% of the original size). The bigger image size is the more
 * CPU performance is needed to encode it.
 *
 * Type: `Integer`
 * Acceptable range: `1` to `100`
 * Default value: `50`
 */
public class MjpegScalingFactor extends AbstractSetting<Integer> {
    public static final String SETTING_NAME = "mjpegScalingFactor";

    public MjpegScalingFactor() {
        super(Integer.class, SETTING_NAME);
    }

    @Override
    public Integer getValue() {
        return ServerConfig.getMjpegScalingFactor();
    }

    @Override
    protected void apply(Integer value) {
        if (value == null || value < 1 || value > 100) {
            throw new InvalidArgumentException(String.format(
                "Invalid %s value specified, must be in range 1..100. %s was given",
                SETTING_NAME,
                value
            ));
        }
        ServerConfig.setMjpegScalingFactor(value);
    }
}
