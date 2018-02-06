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
import io.appium.uiautomator2.utils.Logger;

@SuppressWarnings("JavaDoc")
public class GestureCommands {

    /**
     * Flick on given position
     *
     * @return Response from UiAutomator2 server
     */
    public static Response flickOnPosition() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xSpeed", 50);
        jsonObject.put("ySpeed", -180);

        Response response = Client.post("/touch/flick", jsonObject);
        Logger.info("Flick response:" + response);
        return response;
    }

    /**
     * performs swipe on the device screen
     *
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response swipe(int x1, int y1, int x2, int y2, int steps) throws JSONException {
        // swipe from (x1,y1) to (x2,y2)
        JSONObject swipeOpts = new JSONObject();
        swipeOpts.put("startX", x1);
        swipeOpts.put("startY", y1);
        swipeOpts.put("endX", x2);
        swipeOpts.put("endY", y2);
        swipeOpts.put("steps", steps);

        return Client.post("/touch/perform", swipeOpts);
    }

    public static Response multiPointerGesture(JSONObject body) {
        return Client.post("/touch/multi/perform", body);
    }

    public static Response drag(JSONObject dragBody) {
        return Client.post("/touch/drag", dragBody);
    }

    public static Response tap(int x, int y) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        return Client.post("/appium/tap", jsonObject);
    }

    /**
     * Flick on the give element
     * POST /touch/flick
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response flickOnElement(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("element", elementId);
        jsonObject.put("xoffset", 1);
        jsonObject.put("yoffset", -300);
        jsonObject.put("speed", 1000);
        return Client.post("/touch/flick", jsonObject);
    }

    /**
     * Touch down on element
     * POST /touch/down
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response touchDown(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject element = new JSONObject();
        element.put("element", elementId);
        jsonObject.put("params", element);
        return Client.post("/touch/down", jsonObject);
    }

    /**
     * Touch up on element
     * POST /touch/up
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response touchUp(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject element = new JSONObject();
        element.put("element", elementId);
        jsonObject.put("params", element);
        return Client.post("/touch/up", jsonObject);
    }

    /**
     * Move pointer to element
     * POST /touch/move
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response touchMove(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONObject element = new JSONObject();
        element.put("element", elementId);
        jsonObject.put("params", element);
        return Client.post("/touch/move", jsonObject);
    }

    /**
     * performs long click on the given element
     *
     * @param elementId
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response longClick(String elementId) throws JSONException {
        JSONObject longClickJSON = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        longClickJSON.put("params", jsonObject.put("element", elementId)
                .put("duration", 1000));
        return Client.post("/touch/longclick", longClickJSON);
    }
}
