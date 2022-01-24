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
package io.appium.uiautomator2.unittest.test.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.appium.uiautomator2.unittest.test.Config;

public class WebDriverSession {
    private WebDriverSession() {}

    private static WebDriverSession instance;

    public static synchronized WebDriverSession getInstance() {
        if (instance == null) {
            instance = new WebDriverSession();
        }
        return instance;
    }

    private String id;

    @Nullable
    public String getId() {
        return id;
    }

    @NonNull
    public String getBaseUrl() {
        if (null == getId()) {
            throw new IllegalStateException("Session id must be set");
        }

        return String.format("%s/session/%s", Config.HOST, this.getId());
    }

    public void setId(String value) {
        this.id = value;
    }
}
