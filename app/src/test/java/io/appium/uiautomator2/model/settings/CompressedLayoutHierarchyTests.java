package io.appium.uiautomator2.model.settings;

import android.support.test.uiautomator.UiDevice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import io.appium.uiautomator2.utils.API;
import io.appium.uiautomator2.utils.Device;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Device.class, API.class})
public class CompressedLayoutHierarchyTests {

    private CompressedLayoutHierarchy compressedLayoutHierarchy;

    @Mock
    private UiDevice uiDevice;

    @Before
    public void setup() {
        compressedLayoutHierarchy = new CompressedLayoutHierarchy();
        Whitebox.setInternalState(API.class, "API_18", true);
        doNothing().when(uiDevice).setCompressedLayoutHeirarchy(anyBoolean());
        PowerMockito.mockStatic(Device.class);
        when(Device.getUiDevice()).thenReturn(uiDevice);
    }

    @Test
    public void shouldBeBoolean() {
        Assert.assertEquals(Boolean.class, compressedLayoutHierarchy.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("ignoreUnimportantViews", compressedLayoutHierarchy.getSettingName());
    }

    @Test
    public void shouldBeAbleToEnableCompressedLayout() {
        compressedLayoutHierarchy.updateSetting(true);
        verify(uiDevice).setCompressedLayoutHeirarchy(true);
    }

    @Test
    public void shouldBeAbleToDisableCompressedLayout() {
        compressedLayoutHierarchy.updateSetting(false);
        verify(uiDevice).setCompressedLayoutHeirarchy(false);
    }

    @Test
    public void shouldDoNothingForAPIBelow18() {
        Whitebox.setInternalState(API.class, "API_18", false);
        compressedLayoutHierarchy.updateSetting(true);
        verify(uiDevice, never()).setCompressedLayoutHeirarchy(anyBoolean());
    }
}
