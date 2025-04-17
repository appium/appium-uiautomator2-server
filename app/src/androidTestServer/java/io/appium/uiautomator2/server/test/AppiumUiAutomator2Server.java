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
package io.appium.uiautomator2.server.test;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.appium.uiautomator2.test.BuildConfig;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.utils.Logger;

@RunWith(AndroidJUnit4.class)
public class AppiumUiAutomator2Server {
    /**
     * Starts the server on the device.
     * !!! This class is the main entry point for UIA2 driver package.
     * !!! Do not rename or move it unless you know what you are doing.
     */
    @Test
    public void startServer() throws InterruptedException {
        Logger.info( ">>>Entry point start<<<");
        Logger.info(buildConfigSerializer());
        ServerInstrumentation.getInstance().start();
        CountDownLatch shutdownLatch = ServerInstrumentation.getInstance().getShutdownLatch();
        if (shutdownLatch != null) {
            shutdownLatch.await();
        }
        Logger.info(">>>Entry point finish<<<");
    }
    /**
     * Serializes all static fields of the BuildConfig class into a JSON string.
     *
     * <p>This method uses reflection to collect static field values from the BuildConfig class
     * and converts them into a JSON format using Gson library.</p>
     *
     * @return JSON string representation of BuildConfig's static fields
     * @throws RuntimeException if BuildConfig class cannot be found or field access is denied
     *
     * @apiNote The BuildConfig class name is hardcoded to "io.appium.uiautomator2.test.BuildConfig".
     *          Modify the class name if used in different packages.
     */
    public static String buildConfigSerializer() {
        final String buildConfigClassName = "io.appium.uiautomator2.test.BuildConfig";
        final Map<String, Object> fieldMap = new HashMap<>();

        try {
            Class<?> buildConfigClass = Class.forName(buildConfigClassName);

            for (Field field : buildConfigClass.getDeclaredFields()) {
                if (Modifier.isStatic(field.getModifiers())) {
                    try {
                        field.setAccessible(true);
                        Object value = field.get(null);
                        fieldMap.put(field.getName(), value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Field access denied for: " + field.getName(), e);
                    }
                }
            }
            return new Gson().toJson(fieldMap);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("BuildConfig class not found: " + buildConfigClassName, e);
        }
    }
}
