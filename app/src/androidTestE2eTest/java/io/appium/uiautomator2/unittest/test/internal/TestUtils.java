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
import android.content.Intent;

import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.test.Config;
import io.appium.uiautomator2.utils.Device;

import static android.os.SystemClock.elapsedRealtime;
import static android.os.SystemClock.sleep;
import static io.appium.uiautomator2.unittest.test.Config.APP_LAUNCH_TIMEOUT;
import static io.appium.uiautomator2.unittest.test.Config.DEFAULT_POLLING_INTERVAL;
import static io.appium.uiautomator2.unittest.test.Config.IMPLICIT_TIMEOUT;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.source;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getName;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.w3c.ElementConstants.JWP_ELEMENT_ID_KEY_NAME;
import static io.appium.uiautomator2.utils.w3c.ElementConstants.W3C_ELEMENT_ID_KEY_NAME;

@SuppressWarnings("JavaDoc")
public class TestUtils {

    private static final int APP_LAUNCH_RETRIES_COUNT = 3;
    private static final String PM_GRANT_COMMAND = "pm grant %s %s";

    public static void waitForSeconds(int seconds) {
        sleep(seconds * 1000);
    }

    public static void waitForMillis(int millis) {
        sleep(millis);
    }

    private static void waitForAppToLaunch(String appPackage) throws JSONException {
        long start = elapsedRealtime();
        boolean waitStatus;
        do {
            Device.waitForIdle();
            waitStatus = getUiDevice().wait(Until.hasObject(
                    androidx.test.uiautomator.By.pkg(appPackage).depth(0)),
                    IMPLICIT_TIMEOUT);
            if (waitStatus) {
                return;
            }
            Logger.error("Unable to find AUT. Is it System UI or AUT ARN on arm emu?");
            Logger.debug("Page sources:" + source().getValue());
            Response response = findElement(By.xpath("//*[@text='Close app' or @text='Wait' or " +
                    "@text='OK' or @text='Open app again']"));
            if (response.isSuccessful()) {
                click(response.getElementId());
            }
            waitForMillis(DEFAULT_POLLING_INTERVAL);
        } while (elapsedRealtime() - start < APP_LAUNCH_TIMEOUT);
        throw new TimeoutException("app to launch");
    }

    public static Response waitForElement(By by) {
        return waitForElement(by, null);
    }

    public static Response waitForElement(By by, String context) {
        final long start = elapsedRealtime();
        Response response;
        do {
            try {
              response = (context == null) ? findElement(by) : findElement(by, context);
              if (response.isSuccessful()) {
                  return response;
              }
            } catch (Exception e) {
              //
            }
            waitForMillis(DEFAULT_POLLING_INTERVAL);
        } while (elapsedRealtime() - start < Config.EXPLICIT_TIMEOUT);
        throw new TimeoutException("element located by " + by);
    }

    public static Response waitForElementInvisibility(String elementId) {
        final long start = elapsedRealtime();
        Response response;
        do {
            try {
                response = getName(elementId);
                if (!response.isSuccessful()) {
                    return response;
                }
            } catch (Exception e) {
                // ignore stale reference, etc. errors
            }
            waitForMillis(DEFAULT_POLLING_INTERVAL);
        } while (elapsedRealtime() - start < Config.EXPLICIT_TIMEOUT);
        throw new TimeoutException("invisibility of element " + elementId);
    }

    protected static void startActivity(Context ctx, String activity) throws JSONException {
        final String fullActivityName = Config.APP_PKG + activity;
        int retriesCount = 0;
        while (retriesCount < APP_LAUNCH_RETRIES_COUNT) {
            try {
                Intent intent = new Intent().setClassName(Config.APP_PKG, fullActivityName)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.stopService(intent);
                ctx.startActivity(intent);
                Logger.info("Waiting for app to launch:" + fullActivityName);
                waitForAppToLaunch(Config.APP_PKG);
                return;
            } catch (TimeoutException e) {
                Logger.error("App launch retries count:" + retriesCount, e);
            }
            retriesCount++;
        }
        throw new TimeoutException("Unable to launch app");
    }

    public static void grantPermission(final Context context, final String permission) throws
            IOException {
        Logger.info(String.format("Granting permission '%s' to %s", permission, context
                .getPackageName()));
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).executeShellCommand(
                String.format(PM_GRANT_COMMAND, context.getPackageName(), permission));
    }

    /**
     * prepares the JSON Object
     *
     * @param by        Element locator
     * @param contextId Search context id
     * @return {@link By} converted to JSON object
     */
    public static JSONObject convertByToJson(By by, String contextId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("context", contextId);
            final String strategy;
            if (by instanceof By.ByAccessibilityId) {
                strategy = "accessibility id";
            } else if (by instanceof By.ByClass) {
                strategy = "class name";
            } else if (by instanceof By.ById) {
                strategy = "id";
            } else if (by instanceof By.ByXPath) {
                strategy = "xpath";
            } else if (by instanceof By.ByAndroidUiAutomator || by instanceof By.ByText) {
                strategy = "-android uiautomator";
            } else {
                strategy = "unsupported";
            }
            jsonObject.put("strategy", strategy);
            jsonObject.put("selector", by.getElementLocator());
        } catch (JSONException e) {
            throw new IllegalArgumentException("Unable to form JSON Object", e);
        }
        return jsonObject;
    }

    /**
     * return JSONObjects count in a JSONArray
     *
     * @param jsonArray
     * @return count of JSONObjects
     */
    public static int getJsonObjectCountInJsonArray(JSONArray jsonArray) {
        int count = 0;
        for (int i = 0; i < jsonArray.length(); i++, count++) {
            try {
                jsonArray.getJSONObject(i);
            } catch (JSONException ignore) {
            }
        }
        return count;
    }

    @Nullable
    public static String extractElementId(JSONObject obj) {
        Iterator<String> keysIterator = obj.keys();
        while (keysIterator.hasNext()) {
            String key = keysIterator.next();
            if ((key.equalsIgnoreCase(JWP_ELEMENT_ID_KEY_NAME) ||
                    key.equalsIgnoreCase(W3C_ELEMENT_ID_KEY_NAME))
                    && (obj.opt(key) instanceof String)) {
                return (String) obj.opt(key);
            }
        }
        return null;
    }
}
