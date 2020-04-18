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
 * Controls the quality of streamed screenshots. Where the best quality is 100
 * and the worst is 1. The greater the value is the more CPU time is needed to
 * encode a single bitmap into JPEG format. Usually a value between 25 and 90 is
 * fine for this setting. Greater values affect performance too much without
 * visible quality improvements and lower ones introduce visible distortions
 * into the resulting image.
 *
 * Type: `Integer`
 * Acceptable range: `1` to `100`
 * Default value: `50`
 */
public class MjpegServerScreenshotQuality extends AbstractSetting<Integer> {
    public static final String SETTING_NAME = "mjpegServerScreenshotQuality";

    public MjpegServerScreenshotQuality() {
        super(Integer.class, SETTING_NAME);
    }

    @Override
    public Integer getValue() {
        return ServerConfig.getMjpegServerScreenshotQuality();
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
        ServerConfig.setMjpegServerScreenshotQuality(value);
    }
}
