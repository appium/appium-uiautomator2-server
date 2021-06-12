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
package io.appium.uiautomator2.unittest.test;

import io.appium.uiautomator2.server.ServerConfig;

public class Config {
    public static final Long NETTY_STATUS_TIMEOUT = 32_000L;
    public static final Long EXPLICIT_TIMEOUT = 16_000L;
    public static final Long IMPLICIT_TIMEOUT = 8_000L;
    public static final Long APP_LAUNCH_TIMEOUT = 32_000L;
    public static final int DEFAULT_POLLING_INTERVAL = 300;
    public static final String APP_PKG = "io.appium.android.apis";
    public static final String APP_NAME = ".ApiDemos";
    public static final String HOST = "http://localhost:" + ServerConfig.getServerPort();
}
