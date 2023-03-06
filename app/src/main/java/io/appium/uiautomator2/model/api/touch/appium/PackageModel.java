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

package io.appium.uiautomator2.model.api.touch.appium;

import javax.annotation.Nullable;

public class PackageModel {
    private final @Nullable
    String packageName;
    private final @Nullable
    String packageActivity;
    private final @Nullable
    String appName;

    public PackageModel(
            String packageName,
            String packageActivity,
            String appName) {
        this.packageName = packageName;
        this.packageActivity = packageActivity;
        this.appName = appName;
    }

}
