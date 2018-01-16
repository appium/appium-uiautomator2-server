package io.appium.uiautomator2.model.settings;

import android.support.test.uiautomator.Configurator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configurator.class})
public class WaitForIdleTimeoutTests {

    private WaitForIdleTimeout waitForIdeTimeout;

    @Mock
    private Configurator configurator = mock(Configurator.class);

    @Before
    public void setup() {
        waitForIdeTimeout = new WaitForIdleTimeout();
        PowerMockito.mockStatic(Configurator.class);
        when(Configurator.getInstance()).thenReturn(configurator);
        when(configurator.setWaitForIdleTimeout(anyLong())).thenReturn(configurator);
    }

    @Test
    public void shouldBeInteger() {
        Assert.assertEquals(Integer.class, waitForIdeTimeout.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("waitForIdleTimeout", waitForIdeTimeout.getSettingName());
    }

    @Test
    public void shouldBeAbleToSetIdleTimeout() {
        waitForIdeTimeout.updateSetting(123);
        verify(configurator).setWaitForIdleTimeout(123);
    }
}
