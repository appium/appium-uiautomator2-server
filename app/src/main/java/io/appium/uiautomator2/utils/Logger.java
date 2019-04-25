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

package io.appium.uiautomator2.utils;

public class Logger {
    public static final String TAG = "appium";

    private static String getString(Object... args) {
        StringBuilder content = new StringBuilder();

        for (Object arg : args) {
            if (arg != null) {
                content.append(arg.toString());
            }
        }

        return content.toString();
    }

    /**
     * Logger error
     */
    public static void error(Object... messages) {
        if (android.util.Log.isLoggable(TAG, android.util.Log.ERROR)) {
            android.util.Log.e(TAG, getString(messages));
        }
    }

    /**
     * Logger error
     */
    public static void error(String message, Throwable throwable) {
        if (android.util.Log.isLoggable(TAG, android.util.Log.ERROR)) {
            android.util.Log.e(TAG, getString(message), throwable);
        }
    }

    /**
     * Logger warning
     */
    public static void warn(Object... messages) {
        if (android.util.Log.isLoggable(TAG, android.util.Log.WARN)) {
            android.util.Log.w(TAG, getString(messages));
        }
    }

    /**
     * Logger info
     */
    public static void info(Object... messages) {
        if (android.util.Log.isLoggable(TAG, android.util.Log.INFO)) {
            android.util.Log.i(TAG, getString(messages));
        }
    }

    /**
     * Logger debug
     */
    public static void debug(Object... messages) {
        if (android.util.Log.isLoggable(TAG, android.util.Log.DEBUG)) {
            android.util.Log.d(TAG, getString(messages));
        }
    }
}
