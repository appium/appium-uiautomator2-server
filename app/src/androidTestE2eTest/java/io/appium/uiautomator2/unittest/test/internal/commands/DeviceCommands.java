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
package io.appium.uiautomator2.unittest.test.internal.commands;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.test.Config;
import io.appium.uiautomator2.unittest.test.internal.Client;
import io.appium.uiautomator2.unittest.test.internal.Response;
import io.appium.uiautomator2.unittest.test.internal.TestUtils;
import io.appium.uiautomator2.utils.Logger;

import static android.os.SystemClock.elapsedRealtime;

@SuppressWarnings("JavaDoc")
public class DeviceCommands {

    /**
     * finds the element using By selector
     *
     * @param by Element locator
     * @return Response from UiAutomator2 server
     */
    public static Response findElement(By by) {
        return findElement(by, "");
    }

    /**
     * finds the element using By selector
     *
     * @param by        Element locator
     * @param contextId Context id
     * @return Response from UiAutomator2 server
     */
    public static Response findElement(By by, String contextId) {
        final long start = elapsedRealtime();
        JSONObject json = TestUtils.convertByToJson(by, contextId);
        Response response;
        do {
            response = Client.post("/element", json);
            Logger.info("Find element response: " + response);
            if (response.isSuccessful()) {
                return response;
            }
            TestUtils.waitForMillis(Config.DEFAULT_POLLING_INTERVAL);
        } while (elapsedRealtime() - start < Config.IMPLICIT_TIMEOUT);
        return response;
    }

    /**
     * finds the elements using By selector
     *
     * @param by Element locator
     * @return Response from UiAutomator2 server
     */
    public static Response findElements(By by) {
        JSONObject json = TestUtils.convertByToJson(by, "");
        return Client.post("/elements", json);
    }

    /**
     * Finds the height and width of screen
     *
     * @return Response from UiAutomator2 server
     */
    public static Response getDeviceSize() {
        Response response = Client.get("/window/current/size");
        Logger.info("Device window Size response:" + response);
        return response;
    }

    /**
     * performs screen rotation
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response rotateScreen(String orientation) throws JSONException {
        JSONObject postBody = new JSONObject().put("orientation", orientation);
        return Client.post("/orientation", postBody);
    }

    /**
     * return screen orientation
     *
     * @return Response from UiAutomator2 server
     */
    public static String getScreenOrientation() {
        Response response = Client.get("/orientation");
        return response.getValue();
    }

    /**
     * return rotation
     *
     * @return Response from UiAutomator2 server
     */
    public static JSONObject getRotation() {
        Response response = Client.get("/rotation");
        return response.getValue();
    }

    /**
     * return rotation
     *
     * @return Response from UiAutomator2 server
     */
    public static Response setRotation(JSONObject rotateMap) {
        return Client.post("/rotation", rotateMap);
    }

    public static Response source() {
        return Client.get("/source");
    }

    public static Response createSession() throws JSONException {
        JSONObject caps = new JSONObject();
        caps.put("capabilities", new JSONObject());
        return Client.post(Config.HOST + "/wd/hub", "/session", caps);
    }

    public static Response deleteSession() {
        return Client.delete();
    }

    /**
     * return the appStrings
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response appStrings() {
        JSONObject jsonObject = new JSONObject();
        return Client.post("/appium/app/strings", jsonObject);
    }

    /**
     * update setting
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response updateSetting(String settingName, Object settingValue) throws
            JSONException {
        JSONObject postBody = new JSONObject();
        postBody.put("settings", new JSONObject().put(settingName, settingValue));
        return Client.post("/appium/settings", postBody);
    }

    /**
     * update settings
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response updateSettings(JSONObject settings) throws
            JSONException {
        return Client.post("/appium/settings", new JSONObject().put("settings",settings));
    }

    /**
     * return settings
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response getSettings() {
        return Client.get("/appium/settings");
    }

    /**
     * retrieve device information
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response getInfo() {
        return Client.get("/appium/device/info");
    }

    /**
     * Performs scroll to an element displaying the given text.
     * The default maximum number of swipes will be used during the element search.
     *
     * @param text
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToText(String text) throws JSONException {
        return scrollToText(text, 0);
    }

    /**
     * Performs scroll to an element displaying the given text.
     * The given maximum number of swipes will be used during the element search.
     *
     * @param text
     * @param maxSwipes
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToText(String text, int maxSwipes) throws JSONException {
        String textEscaped = text.replace("\"", "\\\"");
        String uiSelectorSpec = String.format("new UiSelector().text(\"%s\")", textEscaped);

        return scrollToElement(uiSelectorSpec, maxSwipes);
    }

    /**
     * performs scroll to an element with the given accessibility id
     *
     * @param scrollToId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToAccessibilityId(String scrollToId) throws JSONException {
        JSONObject postBody = new JSONObject();
        postBody.put("cmd", "action");
        postBody.put("action", "find");

        JSONObject params = new JSONObject();
        params.put("strategy", "accessibility id");
        params.put("selector", scrollToId);
        params.put("context",  "");
        params.put("multiple", false);

        postBody.put("params", params);
        return Client.post("/touch/scroll", postBody);
    }

    /**
     * Performs scroll to an element identified by the given UiSelector specification.
     * The default maximum number of swipes will be used during the element search.
     *
     * @param className
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToClassName(String className) throws JSONException {
        JSONObject postBody = new JSONObject();
        postBody.put("cmd", "action");
        postBody.put("action", "find");

        JSONObject params = new JSONObject();
        params.put("strategy", "class name");
        params.put("selector", className);
        params.put("context",  "");
        params.put("multiple", false);

        postBody.put("params", params);
        return Client.post("/touch/scroll", postBody);
    }

    /**
     * Performs scroll to an element identified by the given UiSelector specification.
     * The given maximum number of swipes will be used during the element search.
     *
     * @param uiSelectorSpec
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToElement(String uiSelectorSpec) throws JSONException {
        return scrollToElement(uiSelectorSpec, 0);
    }

    /**
     * Performs scroll to an element identified by the given UiSelector specification.
     *
     * @param uiSelectorSpec
     * @param maxSwipes
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scrollToElement(String uiSelectorSpec, int maxSwipes) throws JSONException {
        JSONObject postBody = new JSONObject();
        postBody.put("cmd", "action");
        postBody.put("action", "find");

        JSONObject params = new JSONObject();
        params.put("strategy", "-android uiautomator");
        params.put("selector",  uiSelectorSpec);
        params.put("context",  "");
        params.put("multiple", false);

        if(maxSwipes > 0) {
            params.put("maxSwipes", maxSwipes);
        }

        postBody.put("params", params);
        return Client.post("/touch/scroll", postBody);
    }

    /**
     * Get device screenshot
     *
     * @return Base64-encoded screenshot string
     */
    public static Response screenshot() {
        return Client.get("/screenshot");
    }

    /**
     * Accepts an on-screen alert
     *
     * @param buttonLabel optional button label to click on
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response acceptAlert(@Nullable String buttonLabel) throws JSONException {
        final JSONObject payload = new JSONObject();
        if (buttonLabel != null) {
            payload.put("buttonLabel", buttonLabel);
        }
        return Client.post("/alert/accept", payload);
    }

    /**
     * Dismisses an on-screen alert
     *
     * @param buttonLabel optional button label to click on
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response dismissAlert(@Nullable String buttonLabel) throws JSONException {
        final JSONObject payload = new JSONObject();
        if (buttonLabel != null) {
            payload.put("buttonLabel", buttonLabel);
        }
        return Client.post("/alert/dismiss", payload);
    }

    /**
     * Gets the text content of an on-screen alert
     *
     * @return Response from UiAutomator2 server
     */
    public static Response getAlertText() {
        return Client.get("/alert/text");
    }

    /**
     * Performs W3C action
     *
     * @param actions valid W3C actions list
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response performActions(JSONArray actions) throws JSONException {
        JSONObject payload = new JSONObject();
        payload.put("actions", actions);
        return Client.post("/actions", payload);
    }
}
