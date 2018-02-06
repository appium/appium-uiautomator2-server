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

public class Logger {

    private static final String TAG = "appium-e2e-tests";

    /**
     * Logger error
     */
    public static void error(String message) {
        android.util.Log.e(TAG, message);
    }

    /**
     * Logger error
     */
    public static void error(String message, Throwable throwable) {
        android.util.Log.e(TAG, message, throwable);
    }

    /**
     * Logger info
     */
    public static void info(String message) {
        android.util.Log.i(TAG, message);
    }

    /**
     * Logger debug
     */
    public static void debug(String message) {
        android.util.Log.d(TAG, message);
    }
}
