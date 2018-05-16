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

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;

import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.performActions;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.scrollTo;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.click;
import static io.appium.uiautomator2.unittest.test.internal.commands.ElementCommands.getText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActionsCommandsTest extends BaseTest {
    private static final By DRAG_TEXT = By.id("io.appium.android.apis:id/drag_result_text");

    private static String dotIdByIdx(int idx) {
        return String.format("io.appium.android.apis:id/drag_dot_%d", idx);
    }

    private void verifyDragResult(String expectedText) {
        Response response = findElement(DRAG_TEXT);
        response = getText(response.getElementId());
        assertThat((String) response.getValue(), containsString(expectedText));
    }

    private void setupDragDropView() throws JSONException {
        scrollTo("Views"); // Due to 'Views' option not visible on small screen
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
        assertThat(actionsResponse.getStatus(), equalTo(WDStatus.SUCCESS.code()));
        verifyDragResult("Dropped");
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
                "{\"type\": \"keyDown\", \"value\": \"\uE008\"}," +
                "{\"type\": \"keyDown\", \"value\": \"h\"}," +
                "{\"type\": \"keyUp\", \"value\": \"h\"}," +
                "{\"type\": \"keyUp\", \"value\": \"\uE008\"}," +
                "{\"type\": \"keyDown\", \"value\": \"i\"}," +
                "{\"type\": \"keyUp\", \"value\": \"i\"}]" +
                "} ]");
        Response actionsResponse = performActions(actionsJson);
        assertThat(actionsResponse.getStatus(), equalTo(WDStatus.SUCCESS.code()));
        Response response = getText(edit.getElementId());
        assertThat((String) response.getValue(), equalTo("Hi"));
    }
}
