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

import android.graphics.Rect;
import androidx.annotation.Nullable;

public class WindowModel {
    public Integer windowId;
    public Integer displayId;
    /**
     * Physical display ID as a string to avoid JavaScript number precision issues.
     * JavaScript's Number.MAX_SAFE_INTEGER is 2^53 - 1, while Java Long can hold values up to 2^63 - 1.
     * Serializing large Long values as numbers in JSON can cause precision loss when parsed in Node.js.
     */
    @Nullable
    public String physicalDisplayId;
    public ElementRectModel rect;
    @Nullable
    public String packageName;
    @Nullable
    public String screenshot;

    public WindowModel() {}

    public WindowModel(
        Integer windowId,
        Integer displayId,
        @Nullable String physicalDisplayId,
        Rect rect,
        @Nullable String packageName,
        @Nullable String screenshot
    ) {
        this.windowId = windowId;
        this.displayId = displayId;
        this.physicalDisplayId = physicalDisplayId;
        this.rect = new ElementRectModel(rect);
        this.packageName = packageName;
        this.screenshot = screenshot;
    }
}

