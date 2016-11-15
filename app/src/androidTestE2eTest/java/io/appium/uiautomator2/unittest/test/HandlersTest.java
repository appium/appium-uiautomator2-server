package io.appium.uiautomator2.unittest.test;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.Configurator;

import com.jayway.jsonpath.JsonPath;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import io.appium.uiautomator2.common.exceptions.SessionRemovedException;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.server.ServerConfig;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.unittest.test.TestHelper.getJsonObjectCountInJsonArray;
import static io.appium.uiautomator2.unittest.test.TestUtil.appStrings;
import static io.appium.uiautomator2.unittest.test.TestUtil.click;
import static io.appium.uiautomator2.unittest.test.TestUtil.createSession;
import static io.appium.uiautomator2.unittest.test.TestUtil.deleteSession;
import static io.appium.uiautomator2.unittest.test.TestUtil.drag;
import static io.appium.uiautomator2.unittest.test.TestUtil.findElement;
import static io.appium.uiautomator2.unittest.test.TestUtil.findElements;
import static io.appium.uiautomator2.unittest.test.TestUtil.flickOnElement;
import static io.appium.uiautomator2.unittest.test.TestUtil.flickOnPosition;
import static io.appium.uiautomator2.unittest.test.TestUtil.getAttribute;
import static io.appium.uiautomator2.unittest.test.TestUtil.getDeviceSize;
import static io.appium.uiautomator2.unittest.test.TestUtil.getLocation;
import static io.appium.uiautomator2.unittest.test.TestUtil.getName;
import static io.appium.uiautomator2.unittest.test.TestUtil.getRotation;
import static io.appium.uiautomator2.unittest.test.TestUtil.getScreenOrientation;
import static io.appium.uiautomator2.unittest.test.TestUtil.getSize;
import static io.appium.uiautomator2.unittest.test.TestUtil.getStringValueInJsonObject;
import static io.appium.uiautomator2.unittest.test.TestUtil.getText;
import static io.appium.uiautomator2.unittest.test.TestUtil.getValueInJsonObject;
import static io.appium.uiautomator2.unittest.test.TestUtil.isElementPresent;
import static io.appium.uiautomator2.unittest.test.TestUtil.longClick;
import static io.appium.uiautomator2.unittest.test.TestUtil.multiPointerGesture;
import static io.appium.uiautomator2.unittest.test.TestUtil.rotateScreen;
import static io.appium.uiautomator2.unittest.test.TestUtil.scrollTo;
import static io.appium.uiautomator2.unittest.test.TestUtil.sendKeys;
import static io.appium.uiautomator2.unittest.test.TestUtil.setRotation;
import static io.appium.uiautomator2.unittest.test.TestUtil.startActivity;
import static io.appium.uiautomator2.unittest.test.TestUtil.swipe;
import static io.appium.uiautomator2.unittest.test.TestUtil.tap;
import static io.appium.uiautomator2.unittest.test.TestUtil.touchDown;
import static io.appium.uiautomator2.unittest.test.TestUtil.touchMove;
import static io.appium.uiautomator2.unittest.test.TestUtil.touchUp;
import static io.appium.uiautomator2.unittest.test.TestUtil.waitForElement;
import static io.appium.uiautomator2.unittest.test.TestUtil.waitForElementInvisible;
import static io.appium.uiautomator2.unittest.test.TestUtil.waitForMilliSeconds;
import static io.appium.uiautomator2.unittest.test.TestUtil.waitForSeconds;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//TODO: need to remove explicit usage of waitForElement, once after configuring setWaitForSelectorTimeout() to driver instance
//reference link: https://developer.android.com/intl/es/reference/android/support/test/uiautomator/Configurator.html#setWaitForSelectorTimeout%28long%29

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class HandlersTest {
    private static final String testAppPkg = "io.appium.android.apis";
    private static final int SECOND = 1000;
    private static ServerInstrumentation serverInstrumentation;
    private static Context ctx;
    private String response;
    private String element;
    private String result;


    /**
     * start io.appium.uiautomator2.server and launch the application main activity
     *
     * @throws InterruptedException
     */
    @BeforeClass
    public static void beforeStartServer() throws InterruptedException, IOException, SessionRemovedException, JSONException {
        if (serverInstrumentation == null) {
            assertNotNull(getUiDevice());
            ctx = InstrumentationRegistry.getInstrumentation().getContext();
            serverInstrumentation = ServerInstrumentation.getInstance(ctx, ServerConfig.getServerPort());
            Logger.info("[AppiumUiAutomator2Server]", " Starting Server ");
            serverInstrumentation.startServer();
            TestHelper.waitForNetty();
            createSession();
            Configurator.getInstance().setWaitForSelectorTimeout(50000);
            Configurator.getInstance().setWaitForIdleTimeout(50000);
        }

    }

    @AfterClass
    public static void stopSever() throws InterruptedException {
        deleteSession();
        if (serverInstrumentation != null) {
            serverInstrumentation.stopServer();
        }
    }

    @Before
    public void launchAUT() throws InterruptedException, JSONException {
        Intent intent = new Intent().setClassName(testAppPkg, testAppPkg + ".ApiDemos").addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.stopService(intent);
        ctx.startActivity(intent);
        Logger.info("[AppiumUiAutomator2Server]", " waiting for app to launch ");

        TestHelper.waitForAppToLaunch(testAppPkg, 15 * SECOND);
        waitForElement(By.accessibilityId("Accessibility"), 10 * SECOND);
        getUiDevice().waitForIdle();
        Logger.info("Configurator.getInstance().getWaitForSelectorTimeout:" + Configurator.getInstance().getWaitForSelectorTimeout());
        element = findElement(By.accessibilityId("Accessibility"));
        assertTrue(By.accessibilityId("Accessibility") + " not found", isElementPresent(element));
    }

    /**
     * Test for click on element
     */
    @Test
    public void clickElementTest() throws JSONException {

        waitForElement(By.accessibilityId("Accessibility"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        Logger.info("[AppiumUiAutomator2Server]", " click element:" + element);
        assertTrue(By.accessibilityId("Accessibility") + "not found", isElementPresent(element));
        click(element);
        getUiDevice().waitForIdle();
        waitForElementInvisible(By.accessibilityId("Accessibility"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        assertFalse(By.accessibilityId("Accessibility") + " found", isElementPresent(element));
    }

    /**
     * test to perform drag and drop
     *
     * @throws JSONException
     * @throws InterruptedException
     */
    @Test
    public void dragAndDropTest() throws JSONException, InterruptedException {
        getUiDevice().waitForIdle();
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));
        waitForElement(By.xpath(".//*[@text='Drag and Drop']"), 10 * SECOND);
        click(findElement(By.xpath(".//*[@text='Drag and Drop']")));
        waitForElement(By.id("io.appium.android.apis:id/drag_dot_1"), 10 * SECOND);

        String srcElement = findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        String destElement = findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
        String srcLocationRes = getLocation(srcElement);
        String destLocationRes = getLocation(destElement);
        JSONObject srcLocation = new JSONObject(new JSONObject(srcLocationRes).get("value").toString());
        int startX = srcLocation.getInt("x");
        int startY = srcLocation.getInt("y");
        ;
        JSONObject destLocation = new JSONObject(new JSONObject(destLocationRes).get("value").toString());
        int endX = destLocation.getInt("x");
        int endY = destLocation.getInt("y");
        String srcElementId = new JSONObject(new JSONObject(srcElement).get("value").toString()).get("ELEMENT").toString();
        String destElementId = new JSONObject(new JSONObject(destElement).get("value").toString()).get("ELEMENT").toString();

        JSONObject dragBody = new JSONObject();
        dragBody.put("elementId", srcElementId);
        dragBody.put("destElId", destElementId);
        dragBody.put("startX", startX);
        dragBody.put("startY", startY);
        dragBody.put("endX", endX);
        dragBody.put("endY", endY);
        dragBody.put("steps", 1000);

        response = drag(dragBody.toString());
        boolean result = (Boolean) getValueInJsonObject(response, "value");
        assertTrue("Drag status from src to dest should be true. ", result);
    }

    @Test
    public void tapOnElement() throws JSONException {
        waitForElement(By.accessibilityId("Accessibility"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        assertTrue(By.accessibilityId("Accessibility") + "not found", isElementPresent(element));
        String response = getLocation(element);
        JSONObject json = new JSONObject(new JSONObject(response).get("value").toString());

        int x = JsonPath.compile("$.x").read(json.toString());
        int y = JsonPath.compile("$.y").read(json.toString());
        assertTrue("element location y coordinate is zero(0), which is not expected", y > 0);
        String tapResponse = tap(x, y);
        Boolean tapStatus = (Boolean) getValueInJsonObject(tapResponse, "value");
        assertTrue("Unable to tap on location: " + x + " " + y, tapStatus);
        element = findElement(By.accessibilityId("Accessibility"));
        assertFalse(By.accessibilityId("Accessibility") + " found, which not expected", isElementPresent(element));
    }

    /**
     * Test for findElement
     */
    @Test
    public void findElementTest() throws JSONException, InterruptedException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        response = findElement(By.xpath("//*[@text='API Demos']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='API Demos']") + "not found", isElementPresent(response));

        response = findElement(By.xpath("//hierarchy//*[@text='API Demos']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='API Demos']") + "not found", isElementPresent(response));

        response = findElement(By.xpath("//hierarchy"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertFalse(By.xpath("//*[@text='API Demos']") + " found", isElementPresent(response));

        response = findElement(By.xpath("//*[@resource-id='android:id/action_bar']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: " + response);
        result = getStringValueInJsonObject(response, "status");
        assertEquals(WDStatus.SUCCESS.code(), Integer.parseInt(result));

        response = findElement(By.xpath("(//*[@class='android.widget.TextView'][1])[2]"));
        Logger.info("[AppiumUiAutomator2Server]", "By.xpath:" + response);
        result = getAttribute(response, "text");
        assertEquals("Accessibility", getStringValueInJsonObject(result, "value"));

        response = findElement(By.xpath("//*[@resource-id='android:id/content']//*[@resource-id='android:id/text1'][4]"));
        Logger.info("[AppiumUiAutomator2Server]", "By.xpath:" + response);
        result = getAttribute(response, "text");
        assertEquals("Content", getStringValueInJsonObject(result, "value"));
    }

    /**
     * test to find element using "-android automator" property
     */
    @Test
    public void findElementUsingUiAutomatorTest() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        click(findElement(By.accessibilityId("Views")));

        By androidUiAutomator = By.androidUiAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"android:id/list\")).scrollIntoView("
                + "new UiSelector().text(\"Radio Group\"));");
        element = findElement(androidUiAutomator);


        Logger.info("[AppiumUiAutomator2Server]", " findElement By.androidUiAutomator: " + element);
        assertTrue(androidUiAutomator + "not found", isElementPresent(element));

        click(element);
        element = findElement(By.accessibilityId("Radio Group"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.accessibilityId: " + element);
        assertFalse(By.accessibilityId("Radio Group") + "not found", isElementPresent(element));
    }


    /**
     * test to find elements using "-android automator" property
     */
    @Test
    public void findElementsUsingUiAutomatorTest() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        click(findElement(By.accessibilityId("Views")));

        response = findElements(By.androidUiAutomator("resourceId(\"android:id/text1\")"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.androidUiAutomator: " + response);
        assertTrue(By.accessibilityId("Radio Group") + "not found", isElementPresent(response));

        JSONArray elements = new JSONArray(getStringValueInJsonObject(response, "value"));
        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in views screen should at least > 5, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 5);

    }

    /**
     * Test for findElements
     */
    @Test
    public void findElementsTest() throws JSONException, ClassNotFoundException {

        response = findElements(By.className("android.widget.TextView"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.className: " + response);
        assertTrue(By.className("android.widget.TextView") + "not found", isElementPresent(response));
        JSONArray elements = new JSONArray(getStringValueInJsonObject(response, "value"));
        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in Home launch screen should at least > 5, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 5);
    }

    /**
     * Test for get Attributes
     */
    @Test
    public void getAttributeTest() throws JSONException {
        element = findElement(By.accessibilityId("App"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.accessibilityId: " + element);

        result = getAttribute(element, "resourceId");
        Logger.info("[AppiumUiAutomator2Server]", " getAttribute: resourceId - " + result);
        assertEquals("android:id/text1", getStringValueInJsonObject(result, "value"));

        result = getAttribute(element, "contentDescription");
        assertEquals("App", getStringValueInJsonObject(result, "value"));

        result = getAttribute(element, "text");
        assertEquals("App", getStringValueInJsonObject(result, "value"));

        result = getAttribute(element, "className");
        assertEquals("android.widget.TextView", getStringValueInJsonObject(result, "value"));
    }

    /**
     * Test for getElement Text
     */
    @Test
    public void getTextTest() throws JSONException {
        element = findElement(By.id("android:id/text1"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: " + element);
        String elementTxt = getText(element);
        assertEquals("Accessibility", elementTxt);
    }

    /**
     * Test for send keys to element
     */

    @Test
    public void sendKeysTest() throws JSONException, InterruptedException {
        getUiDevice().waitForIdle();
        scrollTo("Views"); // Due to 'Views' option not visible on small screen

        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));

        waitForElement(By.accessibilityId("Controls"), 10 * SECOND);
        click(findElement(By.accessibilityId("Controls")));

        waitForElement(By.accessibilityId("1. Light Theme"), 10 * SECOND);
        click(findElement(By.accessibilityId("1. Light Theme")));

        waitForElement(By.id("io.appium.android.apis:id/edit"), 5 * SECOND);
        sendKeys(findElement(By.id("io.appium.android.apis:id/edit")), "Dummy Theme");
        String enteredText = getText(findElement(By.id("io.appium.android.apis:id/edit")));
        assertEquals("Dummy Theme", enteredText);
    }

    /**
     * Test for element name
     *
     * @throws JSONException
     */
    @Test
    public void getNameTest() throws JSONException {
        getUiDevice().waitForIdle();
        waitForElement(By.id("android:id/text1"), 5 * SECOND);
        String response = getName(findElement(By.id("android:id/text1")));
        assertEquals("Accessibility", getStringValueInJsonObject(response, "value"));
    }

    /**
     * Test for element size
     *
     * @throws JSONException
     */
    @Test
    public void getElementSizeTest() throws JSONException {
        getUiDevice().waitForIdle();
        waitForElement(By.id("android:id/text1"), 5 * SECOND);
        response = getSize(findElement(By.id("android:id/text1")));
        Integer height = JsonPath.compile("$.value.height").read(response);
        Integer width = JsonPath.compile("$.value.width").read(response);
        assertTrue("Element height is zero(0), which is not expected", height > 0);
        assertTrue("Element width is zero(0), which is not expected", width > 0);
    }

    /**
     * Test for Device size
     *
     * @throws JSONException
     */
    @Test
    public void getDeviceSizeTest() throws JSONException {
        getUiDevice().waitForIdle();
        response = getDeviceSize();
        Integer height = JsonPath.compile("$.value.height").read(response);
        Integer width = JsonPath.compile("$.value.width").read(response);
        assertTrue("device window height is zero(0), which is not expected", height > 479);
        assertTrue("device window width is zero(0), which is not expected", width > 319);
    }

    /**
     * Test for flick on element
     *
     * @throws JSONException
     */
    @Test
    public void flickOnElementTest() throws JSONException {
        getUiDevice().waitForIdle();
        waitForElement(By.id("android:id/text1"), 5 * SECOND);
        response = flickOnElement(findElement(By.id("android:id/text1")));
        assertTrue(JsonPath.compile("$.value").<Boolean>read(response));
    }

    /**
     * Test for flick on device screen
     *
     * @throws JSONException
     */
    @Test
    public void flickTest() throws JSONException {
        getUiDevice().waitForIdle();
        response = flickOnPosition();
        assertTrue(JsonPath.compile("$.value").<Boolean>read(response));
    }

    /**
     * getLocation will get the location of the element on the screen
     *
     * @throws JSONException
     * @throws InterruptedException
     */

    @Test
    public void getLocationTest() throws JSONException, InterruptedException {
        startActivity(ctx, "io.appium.android.apis", ".view.ChronometerDemo");
        waitForElement(By.id("io.appium.android.apis:id/start"), 10 * SECOND);
        element = findElement(By.id("io.appium.android.apis:id/start"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: " + element);
        String response = getLocation(element);
        JSONObject json = new JSONObject(new JSONObject(response).get("value").toString());

        int x = JsonPath.compile("$.x").read(json.toString());
        int y = JsonPath.compile("$.y").read(json.toString());
        assertTrue("element location x coordinate is zero(0), which is not expected", x > 0);
        assertTrue("element location y coordinate is zero(0), which is not expected", y > 0);
    }

    /**
     * Performs multi pointer touch actions
     *
     * @throws InterruptedException
     * @throws JSONException
     */
    @Test
    public void multiPointerGestureTest() throws InterruptedException, JSONException {
        JSONArray actions = new JSONArray();
        startActivity(ctx, "io.appium.android.apis", ".view.ChronometerDemo");
        waitForElement(By.id("io.appium.android.apis:id/start"), 10 * SECOND);
        click(findElement(By.id("io.appium.android.apis:id/start")));
        waitForSeconds(2 * SECOND);
        String elementTxt = getText(findElement(By.id("io.appium.android.apis:id/chronometer")));
        assertNotEquals("Initial format: 00:00", elementTxt);

        String stop = findElement(By.id("io.appium.android.apis:id/stop"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: " + stop);
        String response = getLocation(stop);
        JSONObject json = new JSONObject(new JSONObject(response).get("value").toString());
        int x = JsonPath.compile("$.x").read(json.toString());
        int y = JsonPath.compile("$.y").read(json.toString());
        JSONObject touch1 = new JSONObject().put("x", x).put("y", y);
        JSONArray action1 = new JSONArray();
        action1.put(new JSONObject().put("time", 0.05).put("touch", touch1));


        String reset = findElement(By.id("io.appium.android.apis:id/reset"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: " + reset);
        response = getLocation(reset);
        json = new JSONObject(new JSONObject(response).get("value").toString());
        x = JsonPath.compile("$.x").read(json.toString());
        y = JsonPath.compile("$.y").read(json.toString());
        JSONObject touch2 = new JSONObject().put("x", x).put("y", y);
        JSONArray action2 = new JSONArray();
        action2.put(new JSONObject().put("time", 0.05).put("touch", touch2));

        /**
         * actions, e.g.:
         * [
         * [{"time": 0.005, "touch": {"y": 705, "x": 540 }}],
         * [{"time": 0.005, "touch": {"y": 561, "x": 540 }}]
         * ]
         */
        actions.put(action1).put(action2);

        response = multiPointerGesture((new JSONObject().put("actions", actions)).toString());
        Logger.info("multi touch response: " + response);
        assertEquals("OK", getStringValueInJsonObject(response, "value"));

        elementTxt = getText(findElement(By.id("io.appium.android.apis:id/chronometer")));
        assertEquals("Initial format: 00:00", elementTxt);

    }

    /**
     * Swipes on the screen from Focus to Buttons
     *
     * @throws JSONException
     * @throws InterruptedException
     */
    @Test
    public void swipeTest() throws JSONException, InterruptedException {
        getUiDevice().waitForIdle();
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));
        waitForElement(By.accessibilityId("Custom"), 10 * SECOND);
        String startElement = findElement(By.accessibilityId("Custom"));
        String endElement = findElement(By.accessibilityId("Auto Complete"));

        //Before Swipe
        String startResponse = getLocation(startElement);
        JSONObject json = new JSONObject(new JSONObject(startResponse).get("value").toString());
        int x1 = JsonPath.compile("$.x").read(json.toString());
        int y1 = JsonPath.compile("$.y").read(json.toString());

        String endResponse = getLocation(endElement);
        json = new JSONObject(new JSONObject(endResponse).get("value").toString());
        int x2 = JsonPath.compile("$.x").read(json.toString());
        int y2 = JsonPath.compile("$.y").read(json.toString());

        swipe(x1, y1, x2, y2, 100);

        //After Swipe
        startElement = findElement(By.accessibilityId("Auto Complete"));
        String afterStatus = getStringValueInJsonObject(startElement, "status");

        // swipe performed hence the 'Buttons' element was not found on the screen
        assertEquals(WDStatus.NO_SUCH_ELEMENT.code(), Integer.parseInt(afterStatus));
    }

    /**
     * Performs long click action on the element
     *
     * @throws JSONException
     */
    @Test
    public void touchLongClickTest() throws JSONException {
        waitForElement(By.accessibilityId("Accessibility"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        Logger.info("[AppiumUiAutomator2Server]", "long click element:" + element);
        assertTrue(By.accessibilityId("Accessibility") + " not found", isElementPresent(element));
        longClick(element);
        getUiDevice().waitForIdle();
        waitForElementInvisible(By.accessibilityId("Accessibility"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        assertFalse(By.accessibilityId("Accessibility") + " found", isElementPresent(element));
    }

    /**
     * Performs Scroll to specified element
     *
     * @throws JSONException
     * @throws InterruptedException
     */
    @Test
    public void scrollTest() throws JSONException, InterruptedException {
        getUiDevice().waitForIdle();
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));
        String scrollToText = "Radio Group";
        element = findElement(By.accessibilityId(scrollToText));
        // Before Scroll 'Radio Group' Element was not found
        assertFalse(By.accessibilityId(scrollToText) + " found", isElementPresent(element));
        scrollTo(scrollToText);
        element = findElement(By.accessibilityId(scrollToText));
        // After Scroll Element was found
        assertTrue(By.accessibilityId(scrollToText) + " not found", isElementPresent(element));
    }

    /**
     * gets the length of the AppStrings
     *
     * @throws JSONException
     */
    @Test
    public void appStringsTest() throws JSONException {
        assertNotEquals(0, appStrings().length());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationTest() throws JSONException {
        getUiDevice().waitForIdle();

        rotateScreen("LANDSCAPE");
        assertEquals("LANDSCAPE", getScreenOrientation());

        rotateScreen("PORTRAIT");
        assertEquals("PORTRAIT", getScreenOrientation());

        /**
         * LANDSCAPE RIGHT
         */
        JSONObject rotateMap = new JSONObject().put("x", 0).put("y", 0).put("z", 90);
        String response = setRotation(rotateMap);
        String status = getStringValueInJsonObject(response, "status");
        assertEquals(WDStatus.SUCCESS.code(), Integer.parseInt(status));
        assertEquals(rotateMap.toString(), getRotation().toString());


        /**
         * PORTRAIT UPSIDE DOWN
         */
        rotateMap = new JSONObject().put("x", 0).put("y", 0).put("z", 180);
        response = setRotation(rotateMap);
        status = getStringValueInJsonObject(response, "status");
        assertEquals(WDStatus.SUCCESS.code(), Integer.parseInt(status));
        assertEquals(rotateMap.toString(), getRotation().toString());

        /**
         * PORTRAIT
         */
        rotateMap = new JSONObject().put("x", 0).put("y", 0).put("z", 0);
        response = setRotation(rotateMap);
        status = getStringValueInJsonObject(response, "status");
        assertEquals(WDStatus.SUCCESS.code(), Integer.parseInt(status));
        assertEquals(rotateMap.toString(), getRotation().toString());

        /**
         * INVALID MAP
         */
        rotateMap = new JSONObject().put("x", 0).put("y", 0).put("z", 10);
        response = setRotation(rotateMap);
        status = getStringValueInJsonObject(response, "status");
        assertEquals(WDStatus.UNKNOWN_COMMAND.code(), Integer.parseInt(status));
    }

    /**
     * Test to verify 500 HTTP Status code for unsuccessful request
     *
     * @throws JSONException
     * @throws IOException
     */
    @Test
    public void verify500HTTPStatusCode() throws JSONException, IOException {
        Response response = null;
        String responseBody = null;
        int responseCode;
        response = findElement(By.accessibilityId("invalid_ID"), response);

        responseBody = response.body().string();
        responseCode = response.code();
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: responseBody " + responseBody);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.id: responseCode " + responseCode);

        assertEquals("HTTP Status code for unsuccessful request should be '500'.", 500, responseCode);
        assertEquals("AppiumResponse status code for element not found should be '7'.", WDStatus.NO_SUCH_ELEMENT.code(), Integer.parseInt(getStringValueInJsonObject(responseBody, "status")));
        assertTrue("AppiumResponse value for element not found should contain 'An element could not be located'.", getStringValueInJsonObject(responseBody, "value").contains("An element could not be located"));
    }

    @Test
    public void touchActionsTest() throws JSONException {
        getUiDevice().waitForIdle();

        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));
        waitForElement(By.accessibilityId("Auto Complete"), 10 * SECOND);
        String downElement = findElement(By.accessibilityId("Controls"));
        String upElement = findElement(By.accessibilityId("Auto Complete"));

        touchDown(downElement);
        touchMove(upElement);
        touchUp(downElement);
        assertFalse(isElementPresent(findElement(By.accessibilityId("Auto Complete"))));
    }

    @Test
    public void findElementWithContextId() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);

        //parent element - By.androidUiAutomator (UiObject)
        response = findElement(By.androidUiAutomator("new UiSelector().resourceId(\"android:id/list\")"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.androidUiAutomator: " + response);
        String contextId = JsonPath.compile("$value.ELEMENT").read(response);
        //child  element - By.className (UiObject2)
        element = findElement(By.className("android.widget.TextView"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.className: " + element);
        String elementTxt = getText(element);
        assertEquals("Accessibility", elementTxt);

        //parent element - By.className  (UiObject2)
        response = findElement(By.className("android.widget.ListView"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.className: " + response);
        contextId = JsonPath.compile("$value.ELEMENT").read(response);
        //child  element - By.className (UiObject2)
        element = findElement(By.className("android.widget.TextView"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.className: " + element);
        elementTxt = getText(element);
        assertEquals("Accessibility", elementTxt);

        //child element - By.xpath  (UiObject2)
        element = findElement(By.xpath("//*[@class='android.widget.TextView'][2]"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + element);
        elementTxt = getText(element);
        assertEquals("Animation", elementTxt);

        //child element - By.xpath  (UiObject2)
        element = findElement(By.xpath("//hierarchy//*[@class='android.widget.TextView'][2]"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + element);
        elementTxt = getText(element);
        assertEquals("Animation", elementTxt);

        //child  element - By.androidUiAutomator (UiObject)
        element = findElement(By.androidUiAutomator("new UiSelector().text(\"Animation\");"), contextId);
        elementTxt = getText(element);
        assertEquals("Animation", elementTxt);

        //parent element - By.xpath
        response = findElement(By.xpath("//hierarchy//*[@class='android.widget.FrameLayout'][2]"));
        contextId = JsonPath.compile("$value.ELEMENT").read(response);
        //child element - By.xpath  (UiObject2)
        element = findElement(By.xpath("//*[@class='android.widget.TextView'][2]"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + element);
        elementTxt = getText(element);
        assertEquals("Animation", elementTxt);

        //parent element - By.androidUiAutomator (UiObject)
        response = findElement(By.androidUiAutomator("new UiSelector()"
                + ".resourceId(\"android:id/list\");"));
        contextId = JsonPath.compile("$value.ELEMENT").read(response);
        //child element - By.xpath  (UiObject2)
        element = findElement(By.xpath("//*[@class='android.widget.TextView'][2]"), contextId);
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + element);
        elementTxt = getText(element);
        assertEquals("Animation", elementTxt);

        click(findElement(By.accessibilityId("Animation")));
        getUiDevice().waitForIdle();
        waitForElement(By.accessibilityId("Events"), 5 * SECOND);
        click(findElement(By.accessibilityId("Events")));
        waitForElement(By.xpath("//*[@class='android.widget.LinearLayout'][3]"), 5 * SECOND);
        //parent element - By.xpath (UiObject2)
        response = findElement(By.xpath("//*[@class='android.widget.LinearLayout'][3]"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        contextId = JsonPath.compile("$value.ELEMENT").read(response);
        //child  element - By.androidUiAutomator (UiObject)
        element = findElement(By.androidUiAutomator("new UiSelector().className(\"android.widget.TextView\")"), contextId);
        elementTxt = getText(element);
        assertEquals("Animator Events:   ", elementTxt);
    }

    @Test
    public void findElementWithAttributes() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        element = findElement(By.xpath("//*[@enabled='true' and @scrollable='true']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.androidUiAutomator: " + element);
        assertTrue("//*[@enabled='true' and @scrollable='true'] not found", isElementPresent(element));

        result = getAttribute(element, "selected");
        assertEquals(false, (Boolean)getValueInJsonObject(result, "value"));

        result = getAttribute(element, "scrollable");
        assertEquals(true, (Boolean)getValueInJsonObject(result, "value"));

        result = getAttribute(element, "enabled");
        assertEquals(true, (Boolean)getValueInJsonObject(result, "value"));
    }

    @Test
    public void findElementWithClassName() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        element = findElement(By.className("android.widget.TextView"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.className: " + element);
        String elementTxt = getText(element);
        assertEquals("API Demos", elementTxt);
    }

    @Test
    public void findElementsWithAttribute() throws JSONException {
        waitForElement(By.xpath("//*[@text='API Demos']"), 5 * SECOND);
        element = findElement(By.accessibilityId("Accessibility"));
        click(element);
        waitForElement(By.xpath("//*[@text='Accessibility Node Provider']"), 5 * SECOND);
        response = findElements(By.xpath("//*[@enabled='true' and @class='android.widget.TextView']"));

        JSONArray elements = new JSONArray(getStringValueInJsonObject(response, "value"));

        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in views screen should at least > 4, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 4);

        assertEquals(getText(elements.get(0).toString()), "API Demos");
        assertEquals(getText(elements.get(1).toString()), "Accessibility Node Provider");
        assertEquals(getText(elements.get(2).toString()), "Accessibility Node Querying");
        assertEquals(getText(elements.get(3).toString()), "Accessibility Service");
    }

    @Test
    public void ToastVerificationTest() throws JSONException {
        getUiDevice().waitForIdle();
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
        waitForElement(By.accessibilityId("Views"), 10 * SECOND);
        click(findElement(By.accessibilityId("Views")));
        scrollTo("Popup Menu");
        waitForElement(By.accessibilityId("Popup Menu"), 10 * SECOND);
        click(findElement(By.accessibilityId("Popup Menu")));
        waitForElement(By.accessibilityId("Make a Popup!"), 10 * SECOND);
        click(findElement(By.accessibilityId("Make a Popup!")));
        waitForElement(By.xpath(".//*[@text='Search']"), 10 * SECOND);
        click(findElement(By.xpath(".//*[@text='Search']")));
        waitForMilliSeconds(500);
        response = findElement(By.xpath("//*[@text='Clicked popup menu item Search']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='Clicked popup menu item Search']") + "not found", isElementPresent(response));

        click(findElement(By.accessibilityId("Make a Popup!")));
        waitForElement(By.xpath(".//*[@text='Add']"), 10 * SECOND);
        click(findElement(By.xpath(".//*[@text='Add']")));
        waitForMilliSeconds(500);
        response = findElement(By.xpath("//*[@text='Clicked popup menu item Add']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='Clicked popup menu item Search Add']") + "not found", isElementPresent(response));

        click(findElement(By.accessibilityId("Make a Popup!")));
        waitForElement(By.xpath(".//*[@text='Edit']"), 10 * SECOND);
        click(findElement(By.xpath(".//*[@text='Edit']")));
        waitForMilliSeconds(500);
        response = findElement(By.xpath("//*[@text='Clicked popup menu item Edit']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='Clicked popup menu item Edit']") + "not found", isElementPresent(response));

        click(findElement(By.xpath(".//*[@text='Share']")));
        waitForMilliSeconds(1000);
        response = findElement(By.xpath("//*[@text='Clicked popup menu item Share']"));
        Logger.info("[AppiumUiAutomator2Server]", " findElement By.xpath: " + response);
        assertTrue(By.xpath("//*[@text='Clicked popup menu item Share']") + "not found", isElementPresent(response));
    }
}
