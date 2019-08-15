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

import io.appium.uiautomator2.handler.request.BaseRequestHandler;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;
import java.util.HashMap;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UnsupportedSettingException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.AbstractSetting;
import io.appium.uiautomator2.model.settings.ActionAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.AllowInvisibleElements;
import io.appium.uiautomator2.model.settings.CompressedLayoutHierarchy;
import io.appium.uiautomator2.model.settings.ElementResponseAttributes;
import io.appium.uiautomator2.model.settings.EnableNotificationListener;
import io.appium.uiautomator2.model.settings.KeyInjectionDelay;
import io.appium.uiautomator2.model.settings.ScrollAcknowledgmentTimeout;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.model.settings.ShouldUseCompactResponses;
import io.appium.uiautomator2.model.settings.ShutdownOnPowerDisconnect;
import io.appium.uiautomator2.model.settings.WaitForIdleTimeout;
import io.appium.uiautomator2.model.settings.WaitForSelectorTimeout;

import static io.appium.uiautomator2.model.settings.Settings.ACTION_ACKNOWLEDGMENT_TIMEOUT;
import static io.appium.uiautomator2.model.settings.Settings.ALLOW_INVISIBLE_ELEMENTS;
import static io.appium.uiautomator2.model.settings.Settings.COMPRESSED_LAYOUT_HIERARCHY;
import static io.appium.uiautomator2.model.settings.Settings.ELEMENT_RESPONSE_ATTRIBUTES;
import static io.appium.uiautomator2.model.settings.Settings.ENABLE_NOTIFICATION_LISTENER;
import static io.appium.uiautomator2.model.settings.Settings.KEY_INJECTION_DELAY;
import static io.appium.uiautomator2.model.settings.Settings.SCROLL_ACKNOWLEDGMENT_TIMEOUT;
import static io.appium.uiautomator2.model.settings.Settings.SHOULD_USE_COMPACT_RESPONSES;
import static io.appium.uiautomator2.model.settings.Settings.SHUTDOWN_ON_POWER_DISCONNECT;
import static io.appium.uiautomator2.model.settings.Settings.WAIT_FOR_IDLE_TIMEOUT;
import static io.appium.uiautomator2.model.settings.Settings.WAIT_FOR_SELECTOR_TIMEOUT;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(BaseRequestHandler.class)
public class UpdateSettingsTests {
    private static final String SETTING_NAME = "my_setting";
    private static final String SETTING_VALUE = "my_value";
    private Session session;

    @Spy
    private UpdateSettings updateSettings = new UpdateSettings("my_uri");

    @Mock
    private AbstractSetting mySetting;

    @Mock
    private IHttpRequest req;

    @Before
    public void setUp() throws JSONException {
        AppiumUIA2Driver.getInstance().initializeSession(Collections.<String, Object>emptyMap());
        session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        HashMap<String, Object> payload = new HashMap<>();
        payload.put(SETTING_NAME, SETTING_VALUE);

        doNothing().when(mySetting).update(any());

        PowerMockito.mockStatic(BaseRequestHandler.class);
        when(BaseRequestHandler.getSessionId(req)).thenReturn("sessionId");
        when(BaseRequestHandler.getPayload(req, "settings")).thenReturn(payload);
        doReturn(mySetting).when(updateSettings).getSetting(SETTING_NAME);
    }

    @Test
    public void shouldBeAbleToReturnAllowInvisibleElementsSetting() {
        verifySettingIsAvailable(ALLOW_INVISIBLE_ELEMENTS, AllowInvisibleElements.class);
    }

    @Test
    public void shouldBeAbleToReturnCompressedLayoutHierarchySetting() {
        verifySettingIsAvailable(COMPRESSED_LAYOUT_HIERARCHY, CompressedLayoutHierarchy.class);
    }

    @Test
    public void shouldBeAbleToReturnAllowNotificationListenerSetting() {
        verifySettingIsAvailable(ENABLE_NOTIFICATION_LISTENER, EnableNotificationListener.class);
    }

    @Test
    public void shouldBeAbleToReturnWaitForIdleTimeoutSetting() {
        verifySettingIsAvailable(WAIT_FOR_IDLE_TIMEOUT, WaitForIdleTimeout.class);
    }

    @Test
    public void shouldBeAbleToReturnWaitForSelectorTimeoutSetting() {
        verifySettingIsAvailable(WAIT_FOR_SELECTOR_TIMEOUT, WaitForSelectorTimeout.class);
    }

    @Test
    public void shouldBeAbleToReturnActionAcknowledgmentTimeout() {
        verifySettingIsAvailable(ACTION_ACKNOWLEDGMENT_TIMEOUT, ActionAcknowledgmentTimeout.class);
    }

    @Test
    public void shouldBeAbleToReturnKeyInjectionDelay() {
        verifySettingIsAvailable(KEY_INJECTION_DELAY, KeyInjectionDelay.class);
    }

    @Test
    public void shouldBeAbleToReturnScrollAcknowledgmentTimeout() {
        verifySettingIsAvailable(SCROLL_ACKNOWLEDGMENT_TIMEOUT, ScrollAcknowledgmentTimeout.class);
    }

    @Test
    public void shouldBeAbleToReturnElementResponseAttributesSetting() {
        verifySettingIsAvailable(ELEMENT_RESPONSE_ATTRIBUTES, ElementResponseAttributes.class);
    }

    @Test
    public void shouldBeAbleToReturnShouldUseCompactResponsesSetting() {
        verifySettingIsAvailable(SHOULD_USE_COMPACT_RESPONSES, ShouldUseCompactResponses.class);
    }

    @Test
    public void shouldBeAbleToReturnShutdownOnPowerDisconnectSetting() {
        verifySettingIsAvailable(SHUTDOWN_ON_POWER_DISCONNECT, ShutdownOnPowerDisconnect.class);
    }

    @Test(expected=UnsupportedSettingException.class)
    public void shouldThrowExceptionIfSettingIsNotSupported() {
        updateSettings.getSetting("unsupported_setting");
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldBeAbleToUpdateSetting() {
        AppiumResponse response = updateSettings.handle(req);
        verify(mySetting).update(SETTING_VALUE);
        assertEquals(session.getCapability(SETTING_NAME), SETTING_VALUE);
        assertEquals(response.getHttpStatus(), HttpResponseStatus.OK);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldReturnResponseWithUnknownErrorStatusIfFailed() {
        doThrow(new UiAutomator2Exception("error")).when(mySetting).update(any());
        AppiumResponse resp = updateSettings.handle(req);
        assertNotEquals(resp.getHttpStatus(), HttpResponseStatus.OK);
        assertThat(resp.getValue(), is(instanceOf(Throwable.class)));
    }

    private void verifySettingIsAvailable(Settings setting, Class<? extends AbstractSetting> clazz) {
        assertThat(updateSettings.getSetting(setting.toString()), instanceOf(clazz));
    }
}
