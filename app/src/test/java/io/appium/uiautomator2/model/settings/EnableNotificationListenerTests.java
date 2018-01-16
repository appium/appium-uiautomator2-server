package io.appium.uiautomator2.model.settings;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

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
        Assert.assertEquals("enableNotificationListener", enableNotificationListener.getSettingName());
    }

    @Test
    public void shouldBeAbleToStartNotificationListeners() {
        enableNotificationListener.updateSetting(true);
        verify(notificationListener).start();
    }

    @Test
    public void shouldBeAbleToStopNotificationListeners() {
        enableNotificationListener.updateSetting(false);
        verify(notificationListener).stop();
    }
}
