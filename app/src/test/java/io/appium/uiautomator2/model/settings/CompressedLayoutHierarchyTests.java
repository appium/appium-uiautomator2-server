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

import android.support.test.uiautomator.UiDevice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import io.appium.uiautomator2.utils.Device;

import static io.appium.uiautomator2.model.settings.Settings.COMPRESSED_LAYOUT_HIERARCHY;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Device.class})
public class CompressedLayoutHierarchyTests {

    private CompressedLayoutHierarchy compressedLayoutHierarchy;

    @Mock
    private UiDevice uiDevice;

    @Before
    public void setup() {
        compressedLayoutHierarchy = new CompressedLayoutHierarchy();
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
        Assert.assertEquals("ignoreUnimportantViews", compressedLayoutHierarchy.getName());
    }

    @Test
    public void shouldBeAbleToEnableCompressedLayout() {
        compressedLayoutHierarchy.update(true);
        verify(uiDevice).setCompressedLayoutHeirarchy(true);
        Assert.assertEquals(true, COMPRESSED_LAYOUT_HIERARCHY.getSetting().getValue());
    }

    @Test
    public void shouldBeAbleToDisableCompressedLayout() {
        compressedLayoutHierarchy.update(false);
        verify(uiDevice).setCompressedLayoutHeirarchy(false);
        Assert.assertEquals(false, COMPRESSED_LAYOUT_HIERARCHY.getSetting().getValue());
    }
}
