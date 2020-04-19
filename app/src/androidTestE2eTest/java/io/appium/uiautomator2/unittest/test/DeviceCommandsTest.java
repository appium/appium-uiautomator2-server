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
package io.appium.uiautomator2.unittest.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;

import io.appium.uiautomator2.unittest.test.internal.TestUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.test.uiautomator.UiDevice;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;
import io.appium.uiautomator2.unittest.test.internal.SkipHeadlessDevices;
import io.appium.uiautomator2.utils.Device;

import static io.appium.uiautomator2.unittest.test.internal.TestUtils.getJsonObjectCountInJsonArray;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElement;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElementInvisibility;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElements;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.getDeviceSize;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.getInfo;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.getScreenOrientation;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.getSettings;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.rotateScreen;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.screenshot;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollToElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollToText;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollToClassName;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.setRotation;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.updateSetting;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.updateSettings;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getAttribute;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getText;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.sendKeys;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class DeviceCommandsTest extends BaseTest {
    /**
     * Test for findElement
     *
     * While working on PR #340 to: `appium:appium-uiautomator2-server` we were
     * seeing this test fail consistently on API-26. Ultimately we confirmed
     * that it was a test-ordering issue. By prefixing the test-case w/ `"zzz"`
     * we forced it to be run first which produced consistent result(s)
     *
     * TODO: Figure out test state ordering issue
     */
    @Test
    public void zzzFindElementTest() {
        By by = By.xpath("//*[@text='API Demos']");
        Response response = findElement(by);
        assertTrue(by + " should be found", response.isSuccessful());

        by = By.xpath("//hierarchy//*[@text='API Demos']");
        response = findElement(by);
        assertTrue(by + " should be found", response.isSuccessful());

        by = By.xpath("//hierarchy");
        response = findElement(by);
        assertFalse(by + " should not be found", response.isSuccessful());

        by = By.xpath("//*[@resource-id='android:id/action_bar']");
        response = findElement(by);
        assertTrue(response.isSuccessful());

        by = By.xpath("(//*[@class='android.widget.TextView'])[3]");
        response = findElement(by);
        response = getAttribute(response.getElementId(), "text");
        assertEquals("Accessibility", response.getValue());

        by = By.xpath("//*[@resource-id='android:id/content']" +
                "//*[@resource-id='android:id/text1'][5]");
        response = findElement(by);
        response = getAttribute(response.getElementId(), "text");
        assertEquals("Content", response.getValue());
    }

    /**
     * test to find element using "-android automator" property
     */
    @Test
    public void findElementUsingUiAutomatorTest() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        By androidUiAutomator = By.androidUiAutomator("new UiSelector().text(\"Radio Group\");" +
                "new UiScrollable(new UiSelector().resourceId(\"android:id/list\"))" +
                ".scrollIntoView(new UiSelector().text(\"Radio Group\"));");
        response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
        click(response.getElementId());

        response = waitForElementInvisibility(response.getElementId());
        assertFalse(androidUiAutomator + " should not be found", response.isSuccessful());
    }

    /**
     * test to find elements using "-android automator" property
     */
    @Test
    public void findElementsUsingUiAutomatorTest() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        By by = By.androidUiAutomator("resourceId(\"android:id/text1\")");
        response = findElements(by);
        assertTrue(by + " should be found", response.isSuccessful());

        JSONArray elements = response.getValue();
        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in views screen should at least > 5, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 5);
    }

    /**
     * Test for findElements
     */
    @Test
    public void findElementsTest() {
        By by = By.className("android.widget.TextView");
        Response response = findElements(by);
        assertTrue(by + " should be found", response.isSuccessful());
        JSONArray elements = response.getValue();
        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in Home launch screen should at least > 5, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 5);
    }

    /**
     * Test for Device size
     *
     * @throws JSONException
     */
    @Test
    public void getDeviceSizeTest() throws JSONException {
        JSONObject value = getDeviceSize().getValue();
        int height = value.getInt("height");
        int width = value.getInt("width");
        assertTrue("device window height is " + height + ", which is not expected", height > 200);
        assertTrue("device window width is " + width + ", which is not expected", width > 100);
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationLandscapeTest() throws JSONException {
        Response response = rotateScreen("LANDSCAPE");
        assertTrue(response.isSuccessful());
        Device.waitForIdle();
        assertEquals("LANDSCAPE", getScreenOrientation());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationPortraitTest() throws JSONException {
        Response response = rotateScreen("PORTRAIT");
        assertTrue(response.isSuccessful());
        Device.waitForIdle();
        assertEquals("PORTRAIT", getScreenOrientation());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationLandscapeRightTest() throws JSONException {
        /*
          LANDSCAPE RIGHT
         */
        JSONObject rotateMap = new JSONObject().put("x", 0).put("y", 0)
                .put("z", 90);
        Response response = setRotation(rotateMap);
        Device.waitForIdle();
        assertTrue(response.isSuccessful());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationPortraitUpsideDownTest() throws JSONException {
        /*
          PORTRAIT UPSIDE DOWN
         */
        JSONObject rotateMap = new JSONObject().put("x", 0).put("y", 0)
                .put("z", 180);
        Response response = setRotation(rotateMap);
        Device.waitForIdle();
        assertTrue(response.isSuccessful());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationPortraitMapTest() throws JSONException {
        /*
          PORTRAIT
         */
        JSONObject rotateMap = new JSONObject().put("x", 0).put("y", 0)
                .put("z", 0);
        Response response = setRotation(rotateMap);
        Device.waitForIdle();
        assertTrue(response.isSuccessful());
    }

    /**
     * performs screen rotation
     *
     * @throws JSONException
     */
    @Test
    public void screenRotationInvalidTest() throws JSONException {
        /*
          INVALID MAP
         */
        JSONObject rotateMap = new JSONObject().put("x", 0).put("y", 0)
                .put("z", 10);
        Response response = setRotation(rotateMap);
        assertFalse(response.isSuccessful());
    }

    @Test
    public void findElementWithContextId1() {
        //parent element - By.androidUiAutomator (UiObject)
        Response response = findElement(By.androidUiAutomator("new UiSelector().resourceId" +
                "(\"android:id/list\")"));
        String contextId = response.getElementId();

        //child  element - By.className (UiObject2)
        response = findElement(By.className("android.widget.TextView"), contextId);
        response = getText(response.getElementId());
        assertEquals("Access'ibility", response.getValue());
    }

    @Test
    public void findElementWithContextId2() {
        //parent element - By.className  (UiObject2)
        Response response = findElement(By.className("android.widget.ListView"));
        String contextId = response.getElementId();

        //child  element - By.className (UiObject2)
        response = findElement(By.className("android.widget.TextView"), contextId);
        response = getText(response.getElementId());
        assertEquals("Access'ibility", response.getValue());
    }

    @Test
    public void findElementWithContextId3() {
        //parent element - By.className  (UiObject2)
        Response response = findElement(By.className("android.widget.ListView"));
        String contextId = response.getElementId();

        //child element - By.xpath  (UiObject2)
        response = findElement(By.xpath("//*[@class='android.widget.TextView'][2]"), contextId);
        response = getText(response.getElementId());
        assertEquals("Accessibility", response.getValue());
    }

    @Test
    public void findElementWithContextId4() {
        //parent element - By.className  (UiObject2)
        Response response = findElement(By.className("android.widget.ListView"));
        String contextId = response.getElementId();

        //child element - By.xpath  (UiObject2)
        response = findElement(By.xpath("//hierarchy//*[@class='android.widget.TextView'][2]"),
                contextId);
        response = getText(response.getElementId());
        assertEquals("Accessibility", response.getValue());
    }

    @Test
    public void findElementWithContextId5() {
        //parent element - By.className  (UiObject2)
        Response response = waitForElement(By.className("android.widget.ListView"));
        String contextId = response.getElementId();

        //child  element - By.androidUiAutomator (UiObject)
        response = waitForElement(By.androidUiAutomator("new UiSelector().text(\"Animation\");"),
                contextId);
        response = getText(response.getElementId());
        assertEquals("Animation", response.getValue());
    }

    @Test
    public void findElementWithContextId6() {
        //parent element - By.xpath
        Response response = findElement(By.xpath("(//hierarchy//*[@class='android.widget.FrameLayout'])[3]"));
        String contextId = response.getElementId();

        //child element - By.xpath  (UiObject2)
        response = findElement(By.xpath("(//*[@class='android.widget.TextView'])[2]"), contextId);
        response = getText(response.getElementId());
        assertEquals("Accessibility", response.getValue());
    }

    @Test
    public void findElementWithContextId7() {
        //parent element - By.androidUiAutomator (UiObject)
        Response response = findElement(By.androidUiAutomator("new UiSelector()"
                + ".resourceId(\"android:id/list\");"));
        String contextId = response.getElementId();

        //child element - By.xpath  (UiObject2)
        response = findElement(By.xpath("//*[@class='android.widget.TextView'][2]"), contextId);
        response = getText(response.getElementId());
        assertEquals("Accessibility", response.getValue());
    }

    @Test
    public void findElementWithContextId8() throws JSONException {
        Response response = findElement(By.accessibilityId("Animation"));
        clickAndWaitForStaleness(response.getElementId());
        response = findElement(By.accessibilityId("Events"));
        clickAndWaitForStaleness(response.getElementId());
    }

    @Test
    public void findElementWithContextId9() {
        //parent element - By.xpath (UiObject2)
        Response response = waitForElement(By.xpath("//*[@class='android.widget.ListView']"));
        String contextId = response.getElementId();

        //child  element - By.androidUiAutomator (UiObject)
        response = waitForElement(By.androidUiAutomator("new UiSelector().className(\"android.widget" +
                ".TextView\")"), contextId);
        response = getText(response.getElementId());
        assertEquals("Access'ibility", response.getValue());
    }

    @Test
    public void findElementWithAttributes() throws JSONException {
        scrollToText("Views");
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        By by = By.accessibilityId("Buttons");
        response = findElement(by);
        String elementId = response.getElementId();
        assertTrue(response.isSuccessful());

        response = getAttribute(elementId, "clickable");
        assertEquals("true", response.getValue());

        response = getAttribute(elementId, "enabled");
        assertEquals("true", response.getValue());
    }

    @Test
    public void findElementWithClassName() {
        Response response = findElement(By.className("android.widget.TextView"));
        response = getText(response.getElementId());
        assertEquals("API Demos", response.getValue());
    }

    @Test
    public void findElementsWithAttribute() throws JSONException {
        Response response = findElement(By.accessibilityId("Accessibility"));
        clickAndWaitForStaleness(response.getElementId());

        response = findElements(By.xpath("//*[@enabled='true' and @class='android.widget" +
                ".TextView']"));

        JSONArray elements = response.getValue();
        int elementCount = getJsonObjectCountInJsonArray(elements);
        assertTrue("Elements Count in views screen should at least > 4, " +
                "in all variants of screen sizes, but actual: " + elementCount, elementCount > 4);
        List<String> expectedTexts = Arrays.asList("API Demos", "Accessibility Node Provider",
                "Accessibility Node Querying", "Accessibility Service");
        for (int i = 0; i < 4; i++) {
            String elementId = TestUtils.extractElementId(elements.getJSONObject(i));
            assertEquals(expectedTexts.get(i), getText(elementId).getValue());
        }
    }

    @Test
    public void toastVerificationTest1() throws JSONException {
        startActivity(".view.PopupMenu1");

        Response response = waitForElement(By.accessibilityId("Make a Popup!"));
        click(response.getElementId());
        response = waitForElement(By.xpath(".//*[@text='Search']"));
        click(response.getElementId());

        By by = By.xpath("//*[@text='Clicked popup menu item Search']");
        response = waitForElement(by);
        response = getText(response.getElementId());
        assertEquals("Clicked popup menu item Search", response.getValue());
        assertTrue(response.isSuccessful());
    }

    @Test
    public void toastVerificationTest2() throws JSONException {
        startActivity(".view.PopupMenu1");

        Response response = waitForElement(By.accessibilityId("Make a Popup!"));
        click(response.getElementId());
        response = waitForElement(By.xpath(".//*[@text='Add']"));
        click(response.getElementId());

        By by = By.xpath("//*[contains(@text,'Clicked popup menu item Add')]");
        response = waitForElement(by);
        assertTrue(response.isSuccessful());
        response = getText(response.getElementId());
        assertEquals("Clicked popup menu item Add", response.getValue());
    }

    @Test
    public void toastVerificationTest3() throws JSONException {
        startActivity(".view.PopupMenu1");

        Response response = waitForElement(By.accessibilityId("Make a Popup!"));
        click(response.getElementId());
        response = waitForElement(By.xpath(".//*[@text='Edit']"));
        click(response.getElementId());

        By by = By.xpath("//*[@text='Clicked popup menu item Edit']");
        response = waitForElement(by);
        assertTrue(response.isSuccessful());
        response = getText(response.getElementId());
        assertEquals("Clicked popup menu item Edit", response.getValue());
    }

    @Test
    public void toastVerificationTest4() throws JSONException {
        startActivity(".view.PopupMenu1");

        Response response = waitForElement(By.accessibilityId("Make a Popup!"));
        click(response.getElementId());
        response = waitForElement(By.xpath(".//*[@text='Edit']"));
        click(response.getElementId());

        By by = By.xpath("//*[@text='Clicked popup menu item Edit']");
        response = waitForElement(by);
        assertTrue(response.isSuccessful());

        response = waitForElement(By.xpath(".//*[@text='Share']"));
        click(response.getElementId());

        by = By.xpath("//*[@text='Clicked popup menu item Share']");
        response = waitForElement(by);
        assertTrue(response.isSuccessful());
        response = getText(response.getElementId());
        assertEquals("Clicked popup menu item Share", response.getValue());
    }

    /**
     * Performs scrolling through a horizontal list to an element specified by its text.
     *
     * @throws JSONException
     */
    @Test
    public void scrollHorizontalListTest() throws JSONException {
        startActivity(".view.Tabs5"); // Page with horizontally-scrollable tabs
        waitForElement(By.id("android:id/tabs"));

        // On devices with Android API Level 23 and earlier, the tab labels are displayed
        // in all-caps, whereas their actual text is rather like 'Tab 13', 'Tab 26', etc.
        // The arguments of methods findElement() and scrollToText() are case-sensitive,
        // therefore we need to use different sets of inputs for different target devices.
        int apiLevel = (int) getField(UiDevice.class, "API_LEVEL_ACTUAL", getUiDevice());

        String[] items = apiLevel < 24
            ? new String[] { "Tab 13", "Tab 26", "Tab 5" }  // up to Android 6.0 Marshmallow
            : new String[] { "TAB 13", "TAB 26", "TAB 5" }; // Android 7.0 Nougat and later

        for (String item : items) {
            assertFalse(String.format(
                "Item '%s' should not be found in the horizontal list.", item),
                findElement(By.text(item)).isSuccessful());

            assertTrue(String.format(
                "Scroll-to-text should have succeeded for item '%s'.", item),
                scrollToText(item).isSuccessful());

            assertTrue(String.format(
                "Item '%s' should now be found in the horizontal list.", item),
                findElement(By.text(item)).isSuccessful());
        }
    }

    /**
     * Performs scrolling through a very long list to an element specified by its text.
     *
     * @throws JSONException
     */
    @Test
    @Ignore("The run-time of this test is ~8.5 minutes, which might fail automated build jobs " +
            "that have timeout of 10 minutes. To verify this scenario, run the test locally.")
    public void scrollVeryLongListSuccessfully() throws JSONException {
        startActivity(".view.List1"); // A very long list (500+ items).
        waitForElement(By.id("android:id/list"));

        String[] items = {
            "Zanetti Parmigiano Reggiano", // at the very end of the list
            "Abbaye de Belloc",            // at the very beginning of the list
            "Grafton Village Cheddar",     // somewhere half-way through the list
            "Yorkshire Blue"               // almost at the end of the list
        };

        for (String item : items) {
            assertFalse(String.format(
                "Item '%s' should not be found in the list.", item),
                findElement(By.text(item)).isSuccessful());

            assertTrue(String.format(
                "Scroll-to-text should have succeeded for item '%s'.", item),
                scrollToText(item, 150).isSuccessful());

            assertTrue(String.format(
                "Item '%s' should now be found in the list.", item),
                findElement(By.text(item)).isSuccessful());
        }
    }

    /**
     * Attempts scrolling through a very long list with a limited number of swipes.
     *
     * @throws JSONException
     */
    @Test
    public void scrollVeryLongListUnsuccessfully() throws JSONException {
        startActivity(".view.List1"); // A very long list (500+ items).
        waitForElement(By.id("android:id/list"));

        // Attempt to scroll to the item that is at the end of the very long list.
        // This cannot be done with 10 swipes, hence it should fail.
        assertFalse(
            "Scroll-to-text should not have succeeded.",
            scrollToText("Zanetti Parmigiano Reggiano", 10).isSuccessful());

        // Attempt to scroll back to the item that is at the beginning of the list.
        // This will need at least 10 swipes, but now we allow only 5. Thus, it should fail.
        assertFalse(
            "Scroll-to-text should not have succeeded.",
            scrollToText("Abbaye de Belloc", 5).isSuccessful());
    }

    /**
     * Performs scrolling to an element specified by its class name.
     *
     * @throws JSONException
     */
    @Test
    public void scrollByClassNameTest() throws JSONException {
        startActivity(".preference.PreferencesFromCode");
        waitForElement(By.id("android:id/list"));

        // This page contains GUI elements of different classes (text, checkbox, switch).
        // However, in the portrait mode, all elements fit on one page. We need to rotate
        // the screen to the landscape mode to shorten the visible part of the list.
        String defaultOrientation = getScreenOrientation();
        rotateScreen("LANDSCAPE");

        try {
            String badClassName = "non.existent.ClassName";

            assertFalse(
                "Scroll-by-class-name should fail for non-existent element.",
                scrollToClassName(badClassName).isSuccessful());

            // At this point, we are at the end of the list, and the Switch element is off the screen.

            String goodClassName = android.widget.Switch.class.getName();

            assertFalse(
            "Element of type '" + goodClassName + "' should not be found in the list.",
            findElement(By.className(goodClassName)).isSuccessful());

            assertTrue(
                "Scroll-to-class-name should have succeeded.",
                scrollToClassName(goodClassName).isSuccessful());

            assertTrue(
                "Element of type '" + goodClassName + "' should now be found in the list.",
                findElement(By.className(goodClassName)).isSuccessful());
        } finally {
            rotateScreen(defaultOrientation);
        }
    }

    /**
     * Performs scrolling to an element identified by a UiSelector specification.
     *
     * @throws JSONException
     */
    @Test
    public void scrollByUiSelectorTest() throws JSONException {
        startActivity(".preference.PreferencesFromCode");
        waitForElement(By.id("android:id/list"));

        String uiSelectorSpec = "new UiSelector()" +
                ".classNameMatches(\"android.widget.RelativeLayout\")" +
                ".childSelector(new UiSelector().textStartsWith(\"Parent checkbox\"))";

        By by = By.androidUiAutomator(uiSelectorSpec);

        assertTrue(
            "Scroll-to-element should have succeeded.",
            scrollToElement(uiSelectorSpec).isSuccessful());

        assertTrue(
            by + " should be found in the list.",
            findElement(by).isSuccessful());
    }

    @Test
    @SkipHeadlessDevices
    public void screenshotTest() throws JSONException {
        Response response = findElement(By.accessibilityId("Accessibility"));
        clickAndWaitForStaleness(response.getElementId());
        waitForElement(By.accessibilityId("Custom View"));

        response = screenshot();
        String value = response.getValue();
        byte[] bytes = Base64.decode(value, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        assertNotNull(bitmap);
    }

    @Test
    public void shouldBeAbleToUpdateSettings() throws JSONException {
        Response response = getSettings();
        JSONObject defaultSettings = response.getValue();
        Map<String, Object> settings = new HashMap<>();
        settings.put("actionAcknowledgmentTimeout", 123);
        settings.put("allowInvisibleElements", true);
        settings.put("ignoreUnimportantViews", true);
        settings.put("elementResponseAttributes", "text");
        settings.put("enableNotificationListener", false);
        settings.put("keyInjectionDelay", 10);
        settings.put("scrollAcknowledgmentTimeout", 300);
        settings.put("shouldUseCompactResponses", false);
        settings.put("waitForIdleTimeout", 50001);
        settings.put("waitForSelectorTimeout", 10);
        settings.put("shutdownOnPowerDisconnect", false);
        try {
            for (Map.Entry<String, Object> entry : settings.entrySet()) {
                updateSetting(entry.getKey(), entry.getValue());
            }
            response = getSettings();
            JSONObject jsonObject = response.getValue();
            for (Map.Entry<String, Object> entry : settings.entrySet()) {
                assertEquals(entry.getValue(), jsonObject.get(entry.getKey()));
            }
        } finally {
            updateSettings(defaultSettings);
        }
    }

    @Test
    public void shouldBeAbleToFindElementViaUiSelectorWithQuotesInParams() throws JSONException {
        startActivity(".view.TextFields");
        Response response = waitForElement(By.id("io.appium.android.apis:id/edit"));
        sendKeys(response.getElementId(), "Use a \"tel:\" URL");
        By androidUiAutomator = By.androidUiAutomator("new UiSelector().textContains(" +
                "\"Use a \\\"tel:\\\" URL\");");
        response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void shouldBeAbleToFindElementViaUiSelectorWithParenthesesInParams() throws JSONException {
        startActivity(".view.TextFields");
        Response response = waitForElement(By.id("io.appium.android.apis:id/edit"));
        sendKeys(response.getElementId(), "(415)");
        By androidUiAutomator = By.androidUiAutomator("new UiSelector().textContains(" +
                "\"(415)\");");
        response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void shouldBeAbleToFindElementViaUiSelectorWithCommasInParams() throws JSONException {
        startActivity(".view.TextFields");
        Response response = waitForElement(By.id("io.appium.android.apis:id/edit"));
        sendKeys(response.getElementId(), "Expressway, Suite 400, Austin");
        By androidUiAutomator = By.androidUiAutomator("new UiSelector().text(" +
                "\"Expressway, Suite 400, Austin\");");
        response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void shouldBeAbleToFindElementViaUiScrollableScrollIntoView() throws JSONException {
        startActivity(".view.List1");
        waitForElement(By.id("android:id/list"));
        By androidUiAutomator = By.androidUiAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"android:id/list\"))" +
                        ".scrollIntoView(new UiSelector().text(\"Beer Cheese\"));");
        Response response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void shouldBeAbleToFindElementViaUiScrollableScrollTextIntoView() throws JSONException {
        startActivity(".view.List1");
        waitForElement(By.id("android:id/list"));
        By androidUiAutomator = By.androidUiAutomator(
                "new UiScrollable(new UiSelector().resourceId(\"android:id/list\"))" +
                        ".scrollTextIntoView(\"Beer Cheese\");");
        Response response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void shouldBeAbleToFindElementViaUiScrollableGetChildByDescription() throws JSONException {
        startActivity(".view.ScrollBar1");
        waitForElement(By.accessibilityId("Lorem ipsum dolor sit amet."));
        By androidUiAutomator = By.androidUiAutomator(
                " new UiScrollable (new UiSelector().className (android.widget.ScrollView))" +
                        ".getChildByDescription ( new UiSelector() " +
                        ".className( android.widget.TextView ),\"Lorem ipsum dolor sit amet.\"," +
                        "true ) ; ");
        Response response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    @Test
    public void scrollIntoViewShouldNotThrowExceptionIfScrollableDoesNotExist() throws
            JSONException {
        startActivity(".view.ScrollBar1");
        waitForElement(By.accessibilityId("Lorem ipsum dolor sit amet."));
        By androidUiAutomator = By.androidUiAutomator(
                "new UiScrollable(new UiSelector().text(\"test\"))" +
                        ".scrollIntoView(new UiSelector().text(\"Lorem ipsum dolor sit amet.\"))");
        Response response = findElement(androidUiAutomator);
        assertTrue(androidUiAutomator + " should be found", response.isSuccessful());
    }

    // @Test
    // @RootRequired
    // public void shouldShutdownServerOnPowerDisconnect() throws IOException, JSONException {
    //     try {
    //         UiDevice.getInstance(getInstrumentation()).executeShellCommand(
    //                 "su 0 am broadcast -a android.intent.action.ACTION_POWER_DISCONNECTED " +
    //                         "io.appium.uiautomator2.e2etest &");
    //         waitForNettyStatus(NettyStatus.OFFLINE);
    //         assertTrue(serverInstrumentation.isServerStopped());
    //     } finally {
    //         serverInstrumentation = null;
    //         startServer();
    //     }
    // }

    @Test
    public void shouldExtractDeviceInformation() throws JSONException {
        JSONObject info = getInfo().getValue();
        assertEquals(info.getString("manufacturer"), Build.MANUFACTURER);
        assertEquals(info.getString("platformVersion"), Build.VERSION.RELEASE);
        assertTrue(info.getJSONArray("networks").length() > 0);
    }
}
