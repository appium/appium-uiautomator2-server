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

package io.appium.uiautomator2.handler;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UnsupportedSettingException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.AbstractSetting;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.CompressedLayoutHierarchy;
import io.appium.uiautomator2.model.settings.EnableNotificationListener;
import io.appium.uiautomator2.model.settings.WaitForIdleTimeout;
import io.appium.uiautomator2.server.WDStatus;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
public class UpdateSettingsTests {

    private static final String SETTING_NAME = "my_setting";
    private static final String SETTING_VALUE = "my_value";

    @Spy
    private UpdateSettings updateSettings = new UpdateSettings("my_uri");

    @Mock
    private AbstractSetting mySetting;

    @Mock
    private IHttpRequest req;

    @Before
    public void setup() throws InstantiationException, IllegalAccessException, JSONException {
        Session.capabilities.remove(SETTING_NAME);
        HashMap<String, Object> payload = new HashMap<>();
        payload.put(SETTING_NAME, SETTING_VALUE);

        doNothing().when(mySetting).updateSetting(Matchers.anyObject());

        doReturn("sessionId").when(updateSettings).getSessionId(req);
        doReturn(payload).when(updateSettings).getPayload(req, "settings");
        doReturn(mySetting).when(updateSettings).getSetting(SETTING_NAME);
    }

    @Test
    public void shouldBeAbleToReturnAllowInvisibleElementsSetting() throws InstantiationException, IllegalAccessException {
        verifySettingIsAvailable(AllowInvisibleElements.SETTING_NAME, AllowInvisibleElements.class);
    }

    @Test
    public void shouldBeAbleToReturnCompressedLayoutHierarchySetting() throws InstantiationException, IllegalAccessException {
        verifySettingIsAvailable(CompressedLayoutHierarchy.SETTING_NAME, CompressedLayoutHierarchy.class);
    }

    @Test
    public void shouldBeAbleToReturnAllowNotificationListenerSetting() throws InstantiationException, IllegalAccessException {
        verifySettingIsAvailable(EnableNotificationListener.SETTING_NAME, EnableNotificationListener.class);
    }

    @Test
    public void shouldBeAbleToReturnWaitForIdleTimeoutSetting() throws InstantiationException, IllegalAccessException {
        verifySettingIsAvailable(WaitForIdleTimeout.SETTING_NAME, WaitForIdleTimeout.class);
    }

    @Test(expected=UnsupportedSettingException.class)
    public void shouldThrowExceptionIfSettingIsNotSupported() throws InstantiationException, IllegalAccessException {
        updateSettings.getSetting("unsupported_setting");
    }

    @Test
    public void shouldBeAbleToUpdateSetting() {
        AppiumResponse response = updateSettings.safeHandle(req);
        verify(mySetting).updateSetting(SETTING_VALUE);
        assertEquals(Session.capabilities.get(SETTING_NAME), SETTING_VALUE);
        assertEquals(WDStatus.SUCCESS.code(), response.getStatus());
        assertEquals(true, response.getValue());
    }

    @Test
    public void shouldReturnResponseWithUnknownErrorStatusIfFailed() {
        doThrow(new UiAutomator2Exception("error")).when(mySetting).updateSetting(Matchers.anyObject());
        AppiumResponse resp = updateSettings.safeHandle(req);
        assertEquals(WDStatus.UNKNOWN_ERROR.code(), resp.getStatus());
        assertThat(resp.getValue().toString(), containsString("UiAutomator2Exception: error"));
    }

    private void verifySettingIsAvailable(String settingName, Class<? extends AbstractSetting> clazz) throws InstantiationException, IllegalAccessException {
        assertThat(updateSettings.getSetting(settingName), instanceOf(clazz));
    }
}
