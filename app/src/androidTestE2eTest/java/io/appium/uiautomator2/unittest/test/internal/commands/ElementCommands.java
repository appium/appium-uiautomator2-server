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

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.unittest.test.internal.Client;
import io.appium.uiautomator2.unittest.test.internal.Response;

@SuppressWarnings("JavaDoc")
public class ElementCommands {

    /**
     * performs click on the given element
     * POST /element/:elementId/click
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response click(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("element", elementId);
        return Client.post("/element/" + elementId + "/click", jsonObject);
    }

    /**
     * Send Keys to the element
     * POST /element/:elementId/value
     *
     * @param elementId
     * @param text
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response sendKeys(String elementId, String text) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("element", elementId);
        jsonObject.put("text", text);
        jsonObject.put("replace", false);
        return Client.post("/element/" + elementId + "/value", jsonObject);
    }

    /**
     * get the text from the element
     * GET /element/:elementId/text
     *
     * @param element
     * @return Response from UiAutomator2 server
     */
    public static Response getText(String element) {
        return Client.get("/element/" + element + "/text");
    }

    /**
     * returns the Attribute of element
     * GET /element/:elementId/attribute/:attribute
     *
     * @param elementId
     * @param attribute
     * @return Response from UiAutomator2 server
     */
    public static Response getAttribute(String elementId, String attribute) {
        return Client.get("/element/" + elementId + "/attribute/" + attribute);
    }

    /**
     * get the content-desc from the element
     * GET /element/:elementId/name
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     */
    public static Response getName(String elementId) {
        return Client.get("/element/" + elementId + "/name");
    }

    /**
     * Finds the height and width of element
     * GET /element/:elementId/size
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response getSize(String elementId) {
        return Client.get("/element/" + elementId + "/size");
    }

    /**
     * return the element location on the screen
     * GET /element/:elementId/location
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     */
    public static Response getLocation(String elementId) {
        return Client.get("/element/" + elementId + "/location");
    }

    /**
     * Get element screenshot
     *
     * @param elementId
     * @return Base64-encoded element screenshot string
     */
    public static Response screenshot(String elementId) {
        return Client.get("/element/" + elementId + "/screenshot");
    }
}
