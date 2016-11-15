package io.appium.uiautomator2.unittest.test;

import android.content.Context;
import android.content.Intent;

import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static android.os.SystemClock.elapsedRealtime;
import static io.appium.uiautomator2.unittest.test.TestHelper.get;
import static io.appium.uiautomator2.unittest.test.TestHelper.post;
import static io.appium.uiautomator2.unittest.test.TestHelper.delete;
import static io.appium.uiautomator2.utils.Device.getUiDevice;

public class TestUtil {
    private static final String baseUrl = "/wd/hub/session/:sessionId";
    private static final int SECOND = 1000;

    /**
     * finds the element using By selector
     *
     * @param by
     * @return
     */
    public static String findElement(By by) {
        JSONObject json = new JSONObject();
        json = getJSon(by, json);
        String result = post(baseUrl + "/element", json.toString());
        Logger.info("findElement: " + result);
        return result;
    }

    /**
     * finds the element using By selector
     *
     * @param by
     * @return
     */
    public static String findElement(By by, String contextId) {
        JSONObject json = new JSONObject();
        json = getJSon(by, contextId, json);
        String result = post(baseUrl + "/element", json.toString());

        Logger.info("findElement: " + result);
        return result;
    }

    /**
     * finds the element using By selector
     *
     * @param by
     * @param response
     *
     * @return
     */
    public static Response findElement(By by, Response response) {
        JSONObject json = new JSONObject();
        json = getJSon(by, json);
        response = post(baseUrl + "/element", json.toString(), response);
        Logger.info("findElement: " + response.body().toString());
        return response;
    }

    /**
     * waits for the element for specific time
     *
     * @param by
     * @param TIME
     * @return
     */
    public static boolean waitForElement(By by, int TIME) {
        JSONObject jsonBody = new JSONObject();
        jsonBody = getJSon(by, jsonBody);
        long start = elapsedRealtime();
        boolean foundStatus = false;
        JSONObject jsonResponse;

        do {
            try {
                String response = post(baseUrl + "/element", jsonBody.toString());
                jsonResponse = new JSONObject(response);
                if (jsonResponse.getInt("status") == WDStatus.SUCCESS.code()) {
                    foundStatus = true;
                    break;
                }
            } catch (JSONException e) {
                // Retrying for element for given time
                Logger.error("Waiting for the element ..");
            }
        } while (!foundStatus && ((elapsedRealtime() - start) <= TIME));
        return foundStatus;
    }

    /**
     * waits for the element to invisible for specific time
     *
     * @param by
     * @param TIME
     * @return
     */
    public static boolean waitForElementInvisible(By by, int TIME) {
        JSONObject jsonBody = new JSONObject();
        jsonBody = getJSon(by, jsonBody);
        long start = elapsedRealtime();
        boolean foundStatus = true;
        JSONObject jsonResponse;

        do {
            try {
                String response = post(baseUrl + "/element", jsonBody.toString());
                jsonResponse = new JSONObject(response);
                if (jsonResponse.getInt("status") != WDStatus.SUCCESS.code()) {
                    foundStatus = false;
                    break;
                }
            } catch (JSONException e) {
                // Retrying for element for given time
                Logger.error("Waiting for the element ..");
            }
        } while (foundStatus && ((elapsedRealtime() - start) <= TIME));
        return foundStatus;
    }

    /**
     * finds the elements using By selector
     *
     * @param by
     * @return
     */
    public static String findElements(By by) {
        JSONObject json = new JSONObject();
        json = getJSon(by, json);
        return post(baseUrl + "/elements", json.toString());
    }

    /**
     * performs click on the given element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String click(String element) throws JSONException {
        String elementId;
        try {
            elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        } catch (JSONException jsonException) {
            throw jsonException;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("elementId", elementId);
        return post(baseUrl + "/element/" + elementId + "/click", jsonObject.toString());
    }

    public static String tap(int x, int y) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("x", x);
        jsonObject.put("y", y);
        return post(baseUrl + "/appium/tap", jsonObject.toString());
    }

    /**
     * Send Keys to the element
     *
     * @param element
     * @param text
     * @return
     * @throws JSONException
     */
    public static String sendKeys(String element, String text) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("elementId", elementId);
        jsonObject.put("text", text);
        jsonObject.put("replace", false);
        return post(baseUrl + "/element/" + elementId + "/value", jsonObject.toString());
    }

    public static String getStringValueInJsonObject(String element, String key) throws JSONException {
        return new JSONObject(element).getString(key);
    }

    public static Object getValueInJsonObject(String jsonString, String key) throws JSONException {
        return new JSONObject(jsonString).get(key);
    }

    /**
     * get the text from the element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String getText(String element) throws JSONException {
        String elementId ;
        JSONObject elementObject = new JSONObject(element);
        if(elementObject.has("value")) {
            elementId = elementObject.getJSONObject("value").getString("ELEMENT");
        } else {
            elementId = elementObject.getString("ELEMENT");
        }

        return getStringValueInJsonObject(get(baseUrl + "/element/" + elementId + "/text"), "value");
    }

    /**
     * get the text from the element
     *
     * @param element
     * @param response
     *
     * @return
     *
     * @throws JSONException
     */
    public static Response getText(String element, Response response) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        response = get(baseUrl + "/element/" + elementId + "/text", response);
        return response;
    }

    /**
     * returns the Attribute of element
     *
     * @param element
     * @param attribute
     * @return
     * @throws JSONException
     */
    public static String getAttribute(String element, String attribute) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        return get(baseUrl + "/element/" + elementId + "/attribute/" + attribute);
    }

    /**
     * get the content-desc from the element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String getName(String element) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        String response = get(baseUrl + "/element/" + elementId + "/name");
        Logger.info("Element name response:" + response);
        return response;
    }


    /**
     * Finds the height and width of element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String getSize(String element) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        String response = get(baseUrl + "/element/" + elementId + "/size");
        Logger.info("Element Size response:" + response);
        return response;
    }

    /**
     * Finds the height and width of screen
     *
     * @return
     * @throws JSONException
     */
    public static String getDeviceSize() throws JSONException {

        String response = get(baseUrl + "/window/current/size");
        Logger.info("Device window Size response:" + response);
        return response;
    }

    /**
     * Flick on the give element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String flickOnElement(String element) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("elementId", elementId);
        jsonObject.put("xoffset", 1);
        jsonObject.put("yoffset", 1);
        jsonObject.put("speed", 1000);
        String response = post(baseUrl + "/touch/flick", jsonObject.toString());
        Logger.info("Flick on element response:" + response);
        return response;
    }

    /**
     * Flick on given position
     *
     * @return
     * @throws JSONException
     */
    public static String flickOnPosition() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("xSpeed", 50);
        jsonObject.put("ySpeed", -180);
        String response = post(baseUrl + "/touch/flick", jsonObject.toString());
        Logger.info("Flick response:" + response);
        return response;
    }

    /**
     * prepares the JSON Object
     *
     * @param by
     * @param jsonObject
     * @return
     */
    public static JSONObject getJSon(By by, JSONObject jsonObject) {
        try {
            jsonObject.put("context", "");
            if (by instanceof By.ByAccessibilityId) {
                jsonObject.put("strategy", "accessibility id");
                jsonObject.put("selector", ((By.ByAccessibilityId) by).getElementLocator());
            } else if (by instanceof By.ByClass) {
                jsonObject.put("strategy", "class name");
                jsonObject.put("selector", ((By.ByClass) by).getElementLocator());
            } else if (by instanceof By.ById) {
                jsonObject.put("strategy", "id");
                jsonObject.put("selector", ((By.ById) by).getElementLocator());
            } else if (by instanceof By.ByXPath) {
                jsonObject.put("strategy", "xpath");
                jsonObject.put("selector", ((By.ByXPath) by).getElementLocator());
            } else if (by instanceof By.ByAndroidUiAutomator) {
                jsonObject.put("strategy", "-android uiautomator");
                jsonObject.put("selector", ((By.ByAndroidUiAutomator) by).getElementLocator());
            } else {
                throw new JSONException("Unable to create json object: " + by);
            }
        } catch (JSONException e) {
            Logger.error("Unable to form JSON Object: " + e);
        }
        return jsonObject;
    }

    /**
     * prepares the JSON Object
     *
     * @param by
     * @param contextId
     * @param jsonObject
     * @return
     */
    public static JSONObject getJSon(By by, String contextId, JSONObject jsonObject) {
        try {
            jsonObject = getJSon(by, jsonObject);
            jsonObject.put("context", contextId);
            return jsonObject;
        } catch (JSONException e) {
            Logger.error("Unable to form JSON Object: " + e);
        }
        return jsonObject;
    }

    /**
     * starts the activity
     *
     * @param ctx
     * @param packg
     * @param activity
     * @throws InterruptedException
     */
    public static void startActivity(Context ctx, String packg, String activity) throws InterruptedException {
        Intent intent = new Intent().setClassName(packg, packg + activity).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.stopService(intent);
        ctx.startActivity(intent);
        Logger.info("[AppiumUiAutomator2Server]", " waiting for activity to launch ");
        TestHelper.waitForAppToLaunch(packg, 15 * SECOND);
        getUiDevice().waitForIdle();
    }

    /**
     * return the element location on the screen
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String getLocation(String element) throws JSONException {
        String elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
        return get(baseUrl + "/element/" + elementId + "/location");
    }

    /**
     * performs swipe on the device screen
     *
     * @return
     * @throws JSONException
     */
    public static String swipe(int x1, int y1, int x2, int y2, int steps) throws JSONException {
        // swipe from (x1,y1) to (x2,y2)


        JSONObject swipeOpts = new JSONObject();
        swipeOpts.put("startX", x1);
        swipeOpts.put("startY", y1);
        swipeOpts.put("endX", x2);
        swipeOpts.put("endY", y2);
        swipeOpts.put("steps", steps);

        return post(baseUrl + "/touch/perform", swipeOpts.toString());
    }

    public static String touchDown(String element) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("params", getJsonForElement(element).toString());
        return post(baseUrl + "/touch/down", jsonObject.toString());
    }

    public static String touchUp(String element) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("params", getJsonForElement(element).toString());
        return post(baseUrl + "/touch/up", jsonObject.toString());
    }

    public static String touchMove(String element) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("params", getJsonForElement(element).toString());
        return post(baseUrl + "/touch/move", jsonObject.toString());
    }

    public static JSONObject getJsonForElement(String elementResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        String elementId = new JSONObject(elementResponse).getJSONObject("value").getString("ELEMENT");
        jsonObject.put("elementId", elementId);
        return jsonObject;
    }

    public static boolean isElementPresent(String elementResponse) throws JSONException {
        int status = new JSONObject(elementResponse).getInt("status");
        return status == 0;
    }
    /**
     * performs long click on the given element
     *
     * @param element
     * @return
     * @throws JSONException
     */
    public static String longClick(String element) throws JSONException {
        String elementId;
        JSONObject longClickJSON = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        try {
            elementId = new JSONObject(element).getJSONObject("value").getString("ELEMENT");
            longClickJSON.put("params", jsonObject.put("elementId", elementId));
        } catch (JSONException e) {
            throw new RuntimeException("Element not found", e);
        }
        return post(baseUrl + "/touch/longclick", longClickJSON.toString());
    }

    /**
     * perfroms scroll to the given text
     *
     * @param scrollToText
     * @return
     * @throws JSONException
     */
    public static String scrollTo(String scrollToText) throws JSONException {
        // TODO Create JSON object instead of below json string.Once the json is finalised from driver module
        String json = " {\"cmd\":\"action\",\"action\":\"find\",\"params\":{\"strategy\":\"-android uiautomator\",\"selector\":\"" +
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().descriptionContains(\\\"" + scrollToText + "\\\").instance(0));" +
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\\\"" + scrollToText + "\\\").instance(0));" +
                "\",\"context\":\"\",\"multiple\":false}}";
        JSONObject jsonObject = new JSONObject(json);
        return post(baseUrl + "/touch/scroll", jsonObject.toString());
    }

    /**
     * return the appStrings
     *
     * @return
     * @throws JSONException
     */
    public static String appStrings() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        return post(baseUrl + "/appium/app/strings", jsonObject.toString());
    }

    /**
     * performs screen rotation
     *
     * @return
     * @throws JSONException
     */
    public static String rotateScreen(String orientation) throws JSONException {
        JSONObject postBody = new JSONObject().put("orientation", orientation);

        return post(baseUrl + "/orientation", postBody.toString());
    }

    /**
     * return screen orientation
     *
     * @return
     * @throws JSONException
     */
    public static String getScreenOrientation() throws JSONException {
        String response = get(baseUrl + "/orientation");
        return new JSONObject(response).get("value").toString();
    }

    /**
     * return rotation
     *
     * @return
     * @throws JSONException
     */
    public static JSONObject getRotation() throws JSONException {
        String response = get(baseUrl + "/rotation");
        return new JSONObject(response).getJSONObject("value");
    }

    /**
     * return rotation
     *
     * @return
     * @throws JSONException
     */
    public static String setRotation(JSONObject rotateMap) throws JSONException {
        return post(baseUrl + "/rotation", rotateMap.toString());
    }

    public static String multiPointerGesture(String body) {
        return post(baseUrl + "/touch/multi/perform", body);
    }

    public static void waitForSeconds(int TIME) {
        try {
            if (TIME > 10 * SECOND) {
                TIME = 10 * SECOND;
            }
            Thread.sleep(TIME);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static void waitForMilliSeconds(int TIME_MS) {
        try {
            Thread.sleep(TIME_MS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static String drag(String dragBody) throws JSONException {
        return post(baseUrl + "/touch/drag", dragBody);
    }

    public static String source() {
        return  get(baseUrl + "/source");
    }

    public static String createSession() throws JSONException {
        return post("/wd/hub/session", new JSONObject().put("desiredCapabilities", new JSONObject()).toString());
    }

    public static String deleteSession(){
        return delete(baseUrl, new JSONObject().toString());
    }

}

