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

import android.graphics.Rect;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.model.Point;
import io.appium.uiautomator2.unittest.test.internal.Client;
import io.appium.uiautomator2.unittest.test.internal.Response;

@SuppressWarnings("JavaDoc")
public class ElementCommands {

    private static final String W3C_ELEMENT_ID_KEY_NAME = "element-6066-11e4-a52e-4f735466cecf";

    private static JSONObject toOrigin(String elementId) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(W3C_ELEMENT_ID_KEY_NAME, elementId);
        return jsonObject;
    }

    private static JSONObject toPoint(Point point) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", point.x);
        jsonObject.put("y", point.y);
        return jsonObject;
    }

    private static JSONObject toArea(Rect rect) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("left", rect.left);
        jsonObject.put("top", rect.top);
        jsonObject.put("width", rect.width());
        jsonObject.put("height", rect.height());
        return jsonObject;
    }

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
     * performs long click gesture
     * POST /appium/gestures/long_click
     *
     * @param elementId
     * @param offset
     * @param duration
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response longClick(@Nullable String elementId, @Nullable Point offset,
                                     @Nullable Long duration) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (offset != null) {
            jsonObject.put("offset", toPoint(offset));
        }
        if (duration != null) {
            jsonObject.put("duration", duration);
        }
        return Client.post("/appium/gestures/long_click", jsonObject);
    }

    /**
     * performs drag gesture
     * POST /appium/gestures/drag
     *
     * @param elementId
     * @param start
     * @param end
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response drag(@Nullable String elementId, @Nullable Point start,
                                @Nullable Point end, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (start != null) {
            jsonObject.put("start", toPoint(start));
        }
        if (end != null) {
            jsonObject.put("end", toPoint(end));
        }
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/drag", jsonObject);
    }

    /**
     * performs fling gesture
     * POST /appium/gestures/fling
     *
     * @param elementId
     * @param area
     * @param direction
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response fling(@Nullable String elementId, @Nullable Rect area,
                                 String direction, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (area != null) {
            jsonObject.put("area", toArea(area));
        }
        jsonObject.put("direction", direction);
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/fling", jsonObject);
    }

    /**
     * performs pinch close gesture
     * POST /appium/gestures/pinch_close
     *
     * @param elementId
     * @param area
     * @param percent
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response pinchClose(@Nullable String elementId, @Nullable Rect area,
                                      float percent, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (area != null) {
            jsonObject.put("area", toArea(area));
        }
        jsonObject.put("percent", percent);
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/pinch_close", jsonObject);
    }

    /**
     * performs pinch open gesture
     * POST /appium/gestures/pinch_open
     *
     * @param elementId
     * @param area
     * @param percent
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response pinchOpen(@Nullable String elementId, @Nullable Rect area,
                                     float percent, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (area != null) {
            jsonObject.put("area", toArea(area));
        }
        jsonObject.put("percent", percent);
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/pinch_open", jsonObject);
    }

    /**
     * performs scroll gesture
     * POST /appium/gestures/scroll
     *
     * @param elementId
     * @param area
     * @param percent
     * @param direction
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response scroll(@Nullable String elementId, @Nullable Rect area,
                                  float percent, String direction, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (area != null) {
            jsonObject.put("area", toArea(area));
        }
        jsonObject.put("percent", percent);
        jsonObject.put("direction", direction);
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/scroll", jsonObject);
    }

    /**
     * performs swipe gesture
     * POST /appium/gestures/swipe
     *
     * @param elementId
     * @param area
     * @param percent
     * @param direction
     * @param speed
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response swipe(@Nullable String elementId, @Nullable Rect area,
                                 float percent, String direction, @Nullable Integer speed) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        if (elementId != null) {
            jsonObject.put("origin", toOrigin(elementId));
        }
        if (area != null) {
            jsonObject.put("area", toArea(area));
        }
        jsonObject.put("percent", percent);
        jsonObject.put("direction", direction);
        if (speed != null) {
            jsonObject.put("speed", speed);
        }
        return Client.post("/appium/gestures/swipe", jsonObject);
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
        jsonObject.put("text", text);
        jsonObject.put("replace", false);
        return Client.post("/element/" + elementId + "/value", jsonObject);
    }

    /**
     * Send a keycode with particular parameters
     *
     * @param keyCode   Android key code
     * @param metaState the state of meta keys
     * @param flags     KeyEvent flags
     * @return Response from UiAutomator2 server
     * @throws JSONException
     */
    public static Response pressKeyCode(int keyCode, int metaState, int flags) throws JSONException {
        JSONObject payload = new JSONObject();
        payload.put("keycode", keyCode);
        payload.put("metastate", metaState);
        payload.put("flags", flags);
        return Client.post("/appium/device/press_keycode", payload);
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
