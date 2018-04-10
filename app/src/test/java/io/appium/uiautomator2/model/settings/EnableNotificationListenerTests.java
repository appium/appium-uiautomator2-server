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

package io.appium.uiautomator2.model.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import io.appium.uiautomator2.model.NotificationListener;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NotificationListener.class})
public class EnableNotificationListenerTests {

    @Mock
    private static NotificationListener notificationListener;

    private EnableNotificationListener enableNotificationListener;

    @Before
    public void setup() {
        enableNotificationListener = new EnableNotificationListener();
        notificationListener = mock(NotificationListener.class);
        doNothing().when(notificationListener).stop();
        doNothing().when(notificationListener).start();

        PowerMockito.mockStatic(NotificationListener.class);
        when(NotificationListener.getInstance()).thenReturn(notificationListener);
    }

    @Test
    public void shouldBeBoolean() {
        Assert.assertEquals(Boolean.class, enableNotificationListener.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("enableNotificationListener", enableNotificationListener.getName());
    }

    @Test
    public void shouldBeAbleToStartNotificationListeners() {
        enableNotificationListener.update(true);
        verify(notificationListener).start();
    }

    @Test
    public void shouldBeAbleToStopNotificationListeners() {
        enableNotificationListener.update(false);
        verify(notificationListener).stop();
    }

    @Test
    public void shouldBeAbleToGetNotificationListenersStatus() {
        Whitebox.setInternalState(notificationListener, "isListening", true);
        Assert.assertEquals(true, enableNotificationListener.getValue());
    }
}
