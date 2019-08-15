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

import io.netty.handler.codec.http.HttpResponseStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;
import io.appium.uiautomator2.utils.Device;

import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForElementInvisibility;
import static io.appium.uiautomator2.unittest.test.internal.TestUtils.waitForSeconds;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollToText;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getLocation;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getText;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.drag;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.flickOnElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.flickOnPosition;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.longClick;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.multiPointerGesture;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.swipe;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.tap;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.touchDown;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.touchMove;
import static io.appium.uiautomator2.unittest.test.internal.commands.GestureCommands.touchUp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("JavaDoc")
public class GestureCommandsTest extends BaseTest {

    /**
     * test to perform drag and drop
     *
     * @throws JSONException
     */
    @Test
    public void dragAndDropTest() throws JSONException {
        startActivity(".view.DragAndDropDemo");
        Response response = findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        String srcElementId = response.getElementId();
        response = getLocation(srcElementId);
        JSONObject srcLocation = response.getValue();
        int startX = srcLocation.getInt("x");
        int startY = srcLocation.getInt("y");

        response = findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
        String destElementId = response.getElementId();
        response = getLocation(srcElementId);
        JSONObject destLocation = response.getValue();
        int endX = destLocation.getInt("x");
        int endY = destLocation.getInt("y");

        JSONObject dragBody = new JSONObject();
        dragBody.put("elementId", srcElementId);
        dragBody.put("destElId", destElementId);
        dragBody.put("startX", startX);
        dragBody.put("startY", startY);
        dragBody.put("endX", endX);
        dragBody.put("endY", endY);
        dragBody.put("steps", 1000);

        response = drag(dragBody);
        assertTrue(response.isSuccessful());

        response = findElement(By.id("io.appium.android.apis:id/drag_result_text"));
        response = getText(response.getElementId());
        assertEquals("Dropped!", response.getValue());
    }

    @Test
    public void tapTest() throws JSONException {
        By by = By.accessibilityId("Accessibility");
        Response response = findElement(by);
        assertTrue(by + " should be found", response.isSuccessful());
        String elementId = response.getElementId();
        response = getLocation(elementId);
        JSONObject json = response.getValue();
        int x = json.getInt("x");
        int y = json.getInt("y");
        assertTrue("element location y coordinate is zero(0), which is not expected", y > 0);

        response = tap(x + 5, y + 5);
        assertTrue(response.isSuccessful());
        response = waitForElementInvisibility(elementId);
        assertFalse(by + " found, which not expected", response.isSuccessful());
    }

    /**
     * Test for flick on device screen
     *
     * @throws JSONException
     */
    @Test
    public void flickTest() throws JSONException {
        Response response = flickOnPosition();
        assertTrue(response.isSuccessful());
    }

    /**
     * Performs multi pointer touch actions
     *
     * @throws InterruptedException
     * @throws JSONException
     */
    @Test
    public void multiPointerGestureTest() throws JSONException {
        JSONArray actions = new JSONArray();
        startActivity(".view.ChronometerDemo");

        Response response = findElement(By.id("io.appium.android.apis:id/start"));
        click(response.getElementId());
        waitForSeconds(2);

        response = findElement(By.id("io.appium.android.apis:id/chronometer"));
        response = getText(response.getElementId());
        assertNotEquals("Initial format: 00:00", response.getValue());

        response = findElement(By.id("io.appium.android.apis:id/stop"));
        response = getLocation(response.getElementId());
        JSONObject location = response.getValue();
        JSONArray action1 = new JSONArray();
        action1.put(new JSONObject().put("time", 0.05).put("touch", location));

        response = findElement(By.id("io.appium.android.apis:id/reset"));
        response = getLocation(response.getElementId());
        location = response.getValue();
        JSONArray action2 = new JSONArray();
        action2.put(new JSONObject().put("time", 0.05).put("touch", location));

        /*
          actions, e.g.:
          [
          [{"time": 0.005, "touch": {"y": 705, "x": 540 }}],
          [{"time": 0.005, "touch": {"y": 561, "x": 540 }}]
          ]
         */
        actions.put(action1).put(action2);

        response = multiPointerGesture((new JSONObject().put("actions", actions)));
        assertTrue(response.isSuccessful());

        response = findElement(By.id("io.appium.android.apis:id/chronometer"));
        response = getText(response.getElementId());
        assertEquals("Initial format: 00:00", response.getValue());
    }

    /**
     * Swipes on the screen from Focus to Buttons
     *
     * @throws JSONException
     */
    @Test
    public void swipeTest() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        response = findElement(By.accessibilityId("Custom"));
        response = getLocation(response.getElementId());
        JSONObject json = response.getValue();
        int x1 = json.getInt("x");
        int y1 = json.getInt("y");

        response = findElement(By.accessibilityId("Auto Complete"));
        response = getLocation(response.getElementId());
        json = response.getValue();
        int x2 = json.getInt("x");
        int y2 = json.getInt("y");

        swipe(x1, y1, x2, y2, 100);

        //After Swipe
        response = findElement(By.accessibilityId("Auto Complete"));

        // swipe performed hence the 'Buttons' element was not found on the screen
        assertEquals(response.code(), HttpResponseStatus.NOT_FOUND.code());
    }

    /**
     * Test for flick on element
     *
     * @throws JSONException
     */
    @Test
    public void flickOnElementTest() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        response = findElement(By.id("android:id/list"));
        response = flickOnElement(response.getElementId());
        assertTrue(response.isSuccessful());

        response = findElement(By.accessibilityId("Animation"));
        assertEquals(response.code(), HttpResponseStatus.NOT_FOUND.code());
    }

    /**
     * Performs long click action on the element
     *
     * @throws JSONException
     */
    @Test
    public void touchLongClickTest() throws JSONException {
        By by = By.accessibilityId("Accessibility");
        Response response = findElement(by);
        assertTrue(by + " should be found", response.isSuccessful());
        longClick(response.getElementId());

        response = waitForElementInvisibility(response.getElementId());
        assertEquals(response.code(), HttpResponseStatus.NOT_FOUND.code());
    }

    @Test
    public void touchActionsTest() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());

        String upElement = findElement(By.accessibilityId("Auto Complete"))
                .getElementId();
        String downElement = findElement(By.accessibilityId("Controls")).getElementId();
        Device.waitForIdle();
        touchDown(downElement);
        touchMove(upElement);
        touchUp(downElement);
        response = findElement(By.accessibilityId("Auto Complete"));
        assertEquals(response.code(), HttpResponseStatus.NOT_FOUND.code());
    }
}
