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

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Configurator;

import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.ServerConfig;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.unittest.test.Config;
import io.netty.channel.ConnectTimeoutException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElement;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElementInvisibility;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.createSession;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.deleteSession;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.updateSetting;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {

    private static ServerInstrumentation serverInstrumentation;
    private static Context ctx;
    @Rule
    public TestWatcher watcher = new TestWatcher();

    /**
     * start io.appium.uiautomator2.server and launch the application main activity
     */
    @BeforeClass
    public static void beforeStartServer() throws InterruptedException,
            JSONException, IOException {
        if (serverInstrumentation != null) {
            return;
        }
        assertNotNull(getUiDevice());
        ctx = InstrumentationRegistry.getInstrumentation().getContext();
        serverInstrumentation = ServerInstrumentation.getInstance(ctx, ServerConfig
                .getServerPort());
        Logger.info("Starting Server");
        serverInstrumentation.startServer();
        Client.waitForNettyStatus(NettyStatus.ONLINE);
        createSession();
        Configurator.getInstance().setWaitForSelectorTimeout(0);
        Configurator.getInstance().setWaitForIdleTimeout(50000);
        TestUtils.grantPermission(getTargetContext(), READ_EXTERNAL_STORAGE);
        TestUtils.grantPermission(getTargetContext(), WRITE_EXTERNAL_STORAGE);
    }

    @AfterClass
    public static void stopSever() throws InterruptedException, ConnectTimeoutException {
        deleteSession();
        if (serverInstrumentation == null) {
            return;
        }
        serverInstrumentation.stopServer();
        Client.waitForNettyStatus(NettyStatus.OFFLINE);
        serverInstrumentation = null;
    }

    @Before
    public void launchAUT() throws InterruptedException, JSONException {
        updateSetting(Settings.ENABLE_NOTIFICATION_LISTENER.toString(), false);
        startActivity(Config.APP_NAME);
        waitForElement(By.accessibilityId("Accessibility"));
    }

    protected void startActivity(String activity) throws JSONException {
        TestUtils.startActivity(ctx, activity);
    }

    protected void clickAndWaitForStaleness(String elementId) throws JSONException {
        click(elementId);
        waitForElementInvisibility(elementId);
    }

}
