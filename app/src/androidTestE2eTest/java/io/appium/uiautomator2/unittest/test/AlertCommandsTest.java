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
import org.json.JSONException;
import org.junit.Test;

import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.unittest.test.internal.BaseTest;
import io.appium.uiautomator2.unittest.test.internal.Response;

import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.acceptAlert;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.dismissAlert;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.findElement;
import static io.appium.uiautomator2.unittest.test.internal.commands.DeviceCommands.getAlertText;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AlertCommandsTest extends BaseTest {

    private void setupView() throws JSONException {
        Response response = findElement(By.accessibilityId("App"));
        clickAndWaitForStaleness(response.getElementId());
        response = findElement(By.accessibilityId("Alert Dialogs"));
        clickAndWaitForStaleness(response.getElementId());
    }

    @Test
    public void verifyAcceptingAnAlert() throws JSONException {
        setupView();

        Response response = findElement(By.accessibilityId("OK Cancel dialog with a long message"));
        clickAndWaitForStaleness(response.getElementId());

        response = acceptAlert(null);
        assertTrue(response.isSuccessful());
    }

    @Test
    public void verifyDismissingAnAlertWithButton() throws JSONException {
        setupView();

        Response response = findElement(By.accessibilityId("OK Cancel dialog with a long message"));
        clickAndWaitForStaleness(response.getElementId());

        response = dismissAlert("CANCEL");
        assertTrue(response.isSuccessful());
    }

    @Test
    public void verifyGettingAlertText() throws JSONException {
        setupView();

        Response response = findElement(By.accessibilityId("OK Cancel dialog with a message"));
        clickAndWaitForStaleness(response.getElementId());

        response = getAlertText();
        assertTrue(response.isSuccessful());
        assertThat((String) response.getValue(), startsWith("Lorem ipsum dolor"));
    }

    @Test
    public void verifyExceptionIfNoAlertPresent() throws JSONException {
        setupView();

        Response response = getAlertText();
        assertEquals(response.code(), HttpResponseStatus.NOT_FOUND.code());
    }
}
