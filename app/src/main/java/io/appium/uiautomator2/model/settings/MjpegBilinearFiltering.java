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
 * Controls whether or not to apply bilinear filtering to source screenshot
 * resize algorithm. Enabling this flag may improve the quality of the resulting
 * scaled bitmap, but will introduce a [small] performance hit.
 *
 * Type: `Boolean`
 * Acceptable values: `false`|`true`
 * Default value: `false`
 */
public class MjpegBilinearFiltering extends AbstractSetting<Boolean> {
    public static final String SETTING_NAME = "mjpegBilinearFiltering";

    public MjpegBilinearFiltering() {
        super(Boolean.class, SETTING_NAME);
    }

    @Override
    public Boolean getValue() {
        return ServerConfig.isMjpegBilinearFiltering();
    }

    @Override
    protected void apply(Boolean value) {
        if (value == null) {
            throw new InvalidArgumentException(String.format(
                "Invalid %s value specified, must be false|true. null was given",
                SETTING_NAME));
        }
        ServerConfig.setMjpegBilinearFiltering(value);
    }
}
