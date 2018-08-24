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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Configurator.class})
public class ActionAcknowledgmentTimeoutTests {

    private ActionAcknowledgmentTimeout actionAcknowledgmentTimeout;

    @Mock
    private Configurator configurator;

    @Before
    public void setup() {
        actionAcknowledgmentTimeout = new ActionAcknowledgmentTimeout();
        PowerMockito.mockStatic(Configurator.class);
        when(Configurator.getInstance()).thenReturn(configurator);
        when(configurator.setActionAcknowledgmentTimeout(anyLong())).thenReturn(configurator);
    }

    @Test
    public void shouldBeLong() {
        Assert.assertEquals(Long.class, actionAcknowledgmentTimeout.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("actionAcknowledgmentTimeout", actionAcknowledgmentTimeout.getName());
    }

    @Test
    public void shouldBeAbleToSetActionAcknowledgmentTimeout() {
        actionAcknowledgmentTimeout.update(123);
        verify(configurator).setActionAcknowledgmentTimeout(123);
    }

    @Test
    public void shouldBeAbleToGetActionAcknowledgmentTimeout() {
        doReturn(123L).when(configurator).getActionAcknowledgmentTimeout();
        Assert.assertEquals(Long.valueOf(123), actionAcknowledgmentTimeout.getValue());
    }
}
