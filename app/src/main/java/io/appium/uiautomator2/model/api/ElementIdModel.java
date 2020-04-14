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

import androidx.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import io.appium.uiautomator2.model.AndroidElement;

import static io.appium.uiautomator2.utils.w3c.ElementConstants.JWP_ELEMENT_ID_KEY_NAME;
import static io.appium.uiautomator2.utils.w3c.ElementConstants.W3C_ELEMENT_ID_KEY_NAME;
import static org.apache.commons.lang.StringUtils.isBlank;

public class ElementIdModel implements BaseModel {
    @SerializedName(JWP_ELEMENT_ID_KEY_NAME)
    public String jwpElementId;
    @SerializedName(W3C_ELEMENT_ID_KEY_NAME)
    public String w3cElementId;

    public ElementIdModel() {}

    public ElementIdModel(AndroidElement source) {
        this.jwpElementId = source.getId();
        this.w3cElementId = source.getId();
    }

    @Nullable
    public String getUnifiedId() {
        return !isBlank(w3cElementId) ? w3cElementId : jwpElementId;
    }
}
