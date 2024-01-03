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

import android.graphics.Rect;
import android.os.Build;
import android.os.SystemClock;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.Point;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;

import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.performActions;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollToText;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.drag;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.fling;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getText;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.longClick;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.pinchClose;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.pinchOpen;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.scroll;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.swipe;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ActionsCommandsTest extends BaseTest {
    private static final By DRAG_TEXT = By.id("io.appium.android.apis:id/drag_result_text");

    private static String dotIdByIdx(int idx) {
        return String.format("io.appium.android.apis:id/drag_dot_%d", idx);
    }

    private void verifyDragResult() {
        String dragLabel = "";
        long msStarted = SystemClock.currentThreadTimeMillis();
        do {
            Response response = findElement(DRAG_TEXT);
            try {
                dragLabel = getText(response.getElementId()).getValue();
                if (dragLabel.contains("Dropped")) {
                    return;
                }
            } catch (Exception e) {
                SystemClock.sleep(500);
            }
        } while (SystemClock.currentThreadTimeMillis() - msStarted <= 7000);
        fail(String.format("The drag result has an unexpected label: %s", dragLabel));
    }

    private void setupDragDropView() throws JSONException {
        scrollToText("Views"); // Due to 'Views' option not visible on small screen
        Response response = findElement(By.accessibilityId("Views"));
        clickAndWaitForStaleness(response.getElementId());
        response = findElement(By.accessibilityId("Drag and Drop"));
        clickAndWaitForStaleness(response.getElementId());
    }

    private void setupEditView() throws JSONException {
        Response response = findElement(By.accessibilityId("App"));
        clickAndWaitForStaleness(response.getElementId());
        response = findElement(By.accessibilityId("Alert Dialogs"));
        clickAndWaitForStaleness(response.getElementId());
        response = findElement(By.accessibilityId("Text Entry dialog"));
        clickAndWaitForStaleness(response.getElementId());
    }

    @Test
    public void verifyDragAndDropOnAnotherElement() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response dot2Response = findElement(By.id(dotIdByIdx(2)));
        final JSONArray actionsJson = new JSONArray(String.format("[ {" +
                "\"type\": \"pointer\"," +
                "\"id\": \"finger1\"," +
                "\"parameters\": {\"pointerType\": \"touch\"}," +
                "\"actions\": [" +
                "{\"type\": \"pointerMove\", \"duration\": 0, \"origin\": \"%s\", \"x\": 0, \"y\": 0}," +
                "{\"type\": \"pointerDown\"}," +
                "{\"type\": \"pause\", \"duration\": 1000}," +
                "{\"type\": \"pointerMove\", \"duration\": 3000, \"origin\": \"%s\", \"x\": 0, \"y\": 0}," +
                "{\"type\": \"pointerUp\"}]" +
                "} ]", dot1Response.getElementId(), dot2Response.getElementId()));
        Response actionsResponse = performActions(actionsJson);
        assertTrue(actionsResponse.isSuccessful());
        verifyDragResult();
    }

    @Test
    public void verifyTypingText() throws JSONException {
        setupEditView();

        Response edit = findElement(By.id("io.appium.android.apis:id/username_edit"));
        click(edit.getElementId());
        final JSONArray actionsJson = new JSONArray("[ {" +
                "\"type\": \"key\"," +
                "\"id\": \"keyboard\"," +
                "\"actions\": [" +
                "{\"type\": \"keyDown\", \"value\": \"h\"}," +
                "{\"type\": \"keyUp\", \"value\": \"h\"}," +
                "{\"type\": \"keyDown\", \"value\": \"i\"}," +
                "{\"type\": \"keyUp\", \"value\": \"i\"}]" +
                "} ]");
        Response actionsResponse = performActions(actionsJson);
        assertTrue(actionsResponse.isSuccessful());
        Response response = getText(edit.getElementId());
        assertThat((String) response.getValue(), equalTo("hi"));
    }

    @Test
    public void verifyLongClickGesture() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response longClickResponse = longClick(dot1Response.getElementId(), null, null);
        assertTrue(longClickResponse.isSuccessful());
        longClickResponse = longClick(dot1Response.getElementId(), new Point(1,1), null);
        assertTrue(longClickResponse.isSuccessful());
        longClickResponse = longClick(null, new Point(200,200), 2000L);
        assertTrue(longClickResponse.isSuccessful());
        // negative
        longClickResponse = longClick(null, null, 2000L);
        assertFalse(longClickResponse.isSuccessful());
        longClickResponse = longClick(dot1Response.getElementId(), null, -1L);
        assertFalse(longClickResponse.isSuccessful());
    }

    @Test
    public void verifyDragGesture() throws JSONException {
        setupDragDropView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // TODO: The test is unstable on API30
            return;
        }

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response dragResponse = drag(dot1Response.getElementId(), null, new Point(1,1), null);
        assertTrue(dragResponse.isSuccessful());
        dragResponse = drag(null, new Point(200,200), new Point(1,1), null);
        assertTrue(dragResponse.isSuccessful());
        dragResponse = drag(null, new Point(200,200), new Point(1,1), 200);
        assertTrue(dragResponse.isSuccessful());
        // negative
        dragResponse = drag(null, new Point(200,200), null, 200);
        assertFalse(dragResponse.isSuccessful());
        dragResponse = drag(null, null, new Point(1,1), 200);
        assertFalse(dragResponse.isSuccessful());
        dragResponse = drag(dot1Response.getElementId(), null, new Point(1,1), -1);
        assertFalse(dragResponse.isSuccessful());
    }

    @Test
    public void verifyFlingGesture() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response flingResponse = fling(dot1Response.getElementId(), null, "down", null);
        assertTrue(flingResponse.isSuccessful());
        flingResponse = fling(null, new Rect(200, 200, 300, 300), "left", null);
        assertTrue(flingResponse.isSuccessful());
        flingResponse = fling(null, new Rect(200, 200, 300, 300), "left", 1000);
        assertTrue(flingResponse.isSuccessful());
        // negative
        flingResponse = fling(null, new Rect(200, 200, 300, 300), "foo", null);
        assertFalse(flingResponse.isSuccessful());
        flingResponse = fling(null, null, "left", null);
        assertFalse(flingResponse.isSuccessful());
        flingResponse = fling(null, new Rect(200, 200, 300, 300), "left", -1);
        assertFalse(flingResponse.isSuccessful());
    }

    @Test
    public void verifyPinchCloseGesture() throws JSONException {
        setupDragDropView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // TODO: The test is unstable on API30
            return;
        }

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response pinchCloseResponse = pinchClose(dot1Response.getElementId(), null, 0.5f, null);
        assertTrue(pinchCloseResponse.isSuccessful());
        pinchCloseResponse = pinchClose(null, new Rect(200, 200, 300, 300), 0.5f, null);
        assertTrue(pinchCloseResponse.isSuccessful());
        pinchCloseResponse = pinchClose(null, new Rect(200, 200, 300, 300), 0.5f, 1000);
        assertTrue(pinchCloseResponse.isSuccessful());
        // negative
        pinchCloseResponse = pinchClose(null, new Rect(200, 200, 300, 300), -1f, null);
        assertFalse(pinchCloseResponse.isSuccessful());
        pinchCloseResponse = pinchClose(null, null, 0.5f, null);
        assertFalse(pinchCloseResponse.isSuccessful());
        pinchCloseResponse = pinchClose(null, new Rect(200, 200, 300, 300), 0.5f, -1);
        assertFalse(pinchCloseResponse.isSuccessful());
    }

    @Test
    public void verifyPinchOpenGesture() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response pinchOpenResponse = pinchOpen(dot1Response.getElementId(), null, 0.5f, null);
        assertTrue(pinchOpenResponse.isSuccessful());
        pinchOpenResponse = pinchOpen(null, new Rect(200, 200, 300, 300), 0.5f, null);
        assertTrue(pinchOpenResponse.isSuccessful());
        pinchOpenResponse = pinchOpen(null, new Rect(200, 200, 300, 300), 0.5f, 1000);
        assertTrue(pinchOpenResponse.isSuccessful());
        // negative
        pinchOpenResponse = pinchOpen(null, new Rect(200, 200, 300, 300), -1f, null);
        assertFalse(pinchOpenResponse.isSuccessful());
        pinchOpenResponse = pinchOpen(null, null, 0.5f, null);
        assertFalse(pinchOpenResponse.isSuccessful());
        pinchOpenResponse = pinchOpen(null, new Rect(200, 200, 300, 300), 0.5f, -1);
        assertFalse(pinchOpenResponse.isSuccessful());
    }

    @Test
    public void verifyScrollGesture() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response scrollResponse = scroll(dot1Response.getElementId(), null, 0.5f, "left", null);
        assertTrue(scrollResponse.isSuccessful());
        scrollResponse = scroll(null, new Rect(200, 200, 300, 300), 0.5f, "left", null);
        assertTrue(scrollResponse.isSuccessful());
        scrollResponse = scroll(null, new Rect(200, 200, 300, 300), 0.5f, "left",1000);
        assertTrue(scrollResponse.isSuccessful());
        // negative
        scrollResponse = scroll(null, new Rect(200, 200, 300, 300), -1f, "up", null);
        assertFalse(scrollResponse.isSuccessful());
        scrollResponse = scroll(null, null, 0.5f, "up",null);
        assertFalse(scrollResponse.isSuccessful());
        scrollResponse = scroll(null, new Rect(200, 200, 300, 300), 0.5f, "up",-1);
        assertFalse(scrollResponse.isSuccessful());
        scrollResponse = scroll(dot1Response.getElementId(), null, 0.5f, "foo", null);
        assertFalse(scrollResponse.isSuccessful());
    }

    @Test
    public void verifySwipeGesture() throws JSONException {
        setupDragDropView();

        Response dot1Response = findElement(By.id(dotIdByIdx(1)));
        Response swipeResponse = swipe(dot1Response.getElementId(), null, 0.5f, "left", null);
        assertTrue(swipeResponse.isSuccessful());
        swipeResponse = swipe(null, new Rect(200, 200, 300, 300), 0.5f, "left", null);
        assertTrue(swipeResponse.isSuccessful());
        swipeResponse = swipe(null, new Rect(200, 200, 300, 300), 0.5f, "left",1000);
        assertTrue(swipeResponse.isSuccessful());
        // negative
        swipeResponse = swipe(null, new Rect(200, 200, 300, 300), -1f, "up", null);
        assertFalse(swipeResponse.isSuccessful());
        swipeResponse = swipe(null, null, 0.5f, "up",null);
        assertFalse(swipeResponse.isSuccessful());
        swipeResponse = swipe(null, new Rect(200, 200, 300, 300), 0.5f, "up",-1);
        assertFalse(swipeResponse.isSuccessful());
        swipeResponse = swipe(dot1Response.getElementId(), null, 0.5f, "foo", null);
        assertFalse(swipeResponse.isSuccessful());
    }
}
