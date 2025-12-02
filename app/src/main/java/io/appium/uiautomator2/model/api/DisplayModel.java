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

public class DisplayModel {
    public int id;
    /**
     * Physical display ID as a string to avoid JavaScript number precision issues.
     * JavaScript's Number.MAX_SAFE_INTEGER is 2^53 - 1, while Java Long can hold values up to 2^63 - 1.
     * Serializing large Long values as numbers in JSON can cause precision loss when parsed in Node.js.
     */
    @Nullable
    public String physicalId;
    public DisplayMetricsModel metrics;
    public boolean isDefault;
    public String name;

    public DisplayModel() {}

    public DisplayModel(
        int id,
        @Nullable String physicalId,
        DisplayMetricsModel metrics,
        boolean isDefault,
        String name
    ) {
        this.id = id;
        this.physicalId = physicalId;
        this.metrics = metrics;
        this.isDefault = isDefault;
        this.name = name;
    }
}

