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

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.Configurator;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.unittest.test.Config;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElement;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElementInvisibility;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.createSession;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.deleteSession;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElements;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {
    protected static ServerInstrumentation serverInstrumentation;
    private static Context ctx;

    @Rule
    public TestWatcher watcher = new TestWatcher();

    /**
     * start io.appium.uiautomator2.server and launch the application main activity
     */
    @BeforeClass
    public static void startServer() throws JSONException, IOException {
        if (serverInstrumentation != null) {
            return;
        }
        assertNotNull(getUiDevice());
        ctx = InstrumentationRegistry.getInstrumentation().getContext();
        serverInstrumentation = ServerInstrumentation.getInstance();
        Logger.info("Starting Server");
        serverInstrumentation.startServer();
        Client.waitForNettyStatus(NettyStatus.ONLINE);
        createSession();
        Configurator.getInstance().setWaitForSelectorTimeout(0);
        Configurator.getInstance().setWaitForIdleTimeout(50000);
        TestUtils.grantPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        TestUtils.grantPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
    }

    @AfterClass
    public static void stopSever() {
        deleteSession();
        if (serverInstrumentation == null) {
            return;
        }
        serverInstrumentation.stopServer();
        Client.waitForNettyStatus(NettyStatus.OFFLINE);
        serverInstrumentation = null;
    }

    @Before
    public void launchAUT() throws JSONException {
        dismissSystemAlert();
        startActivity(Config.APP_NAME);
        waitForElement(By.accessibilityId("Accessibility"));
    }

    protected void dismissSystemAlert() {
        String[] ids = {"android:id/button1", "android:id/aerr_wait"};
        for (String id : ids) {
            try {
                Logger.info("Checking for alert using id '" + id + "'");
                Response response = findElements(By.id(id));
                JSONArray elements = response.getValue();
                if (elements.length() == 0) {
                    continue;
                }

                String elementId = TestUtils.extractElementId(elements.getJSONObject(0));
                clickAndWaitForStaleness(elementId);
            } catch (Exception e) {
                Logger.error("Error getting alert: ", e);
            }
        }
    }

    protected void startActivity(String activity) throws JSONException {
        TestUtils.startActivity(ctx, activity);
    }

    protected void clickAndWaitForStaleness(String elementId) throws JSONException {
        if (elementId == null) {
            return;
        }

        click(elementId);
        waitForElementInvisibility(elementId);
    }
}
