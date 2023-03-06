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
    private String packageName;
    private String packageActivity;
    private String appName;

    public @Nullable String getAppName() {
        return appName;
    }

    public @Nullable String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public @Nullable String getPackageActivity() {
        return packageActivity;
    }

    public void setPackageActivity(String packageActivity) {
        this.packageActivity = packageActivity;
    }
}
