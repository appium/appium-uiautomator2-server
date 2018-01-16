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

import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.API.API_18;

public class CompressedLayoutHierarchy extends AbstractSetting<Boolean> {

    public static final String SETTING_NAME = "ignoreUnimportantViews";

    public CompressedLayoutHierarchy() {
        super(Boolean.class);
    }

    @Override
    protected void apply(Boolean compressLayout) {
        // setCompressedLayoutHeirarchy doesn't exist on API <= 17
        if (API_18) {
            Device.getUiDevice().setCompressedLayoutHeirarchy(compressLayout);
        } else {
            Logger.info("SetCompressedLayoutHeirarchy doesn't exist on API <= 17");
        }
    }

    @Override
    public String getSettingName() {
        return SETTING_NAME;
    }

}
