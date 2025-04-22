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
        ServerInstrumentation.getInstance().start();
        CountDownLatch shutdownLatch = ServerInstrumentation.getInstance().getShutdownLatch();
        if (shutdownLatch != null) {
            shutdownLatch.await();
        }
        Logger.info(">>>Entry point finish<<<");
    }
}
