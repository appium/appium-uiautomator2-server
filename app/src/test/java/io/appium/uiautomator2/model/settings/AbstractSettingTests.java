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
import org.mockito.Spy;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbstractSettingTests {
    @Spy
    private DummyIntegerSetting dummyIntegerSetting;

    @Spy
    private DummyLongSetting dummyLongSetting;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void dummyIntegerSettingShouldThrowExceptionIfTypeIsNotValid() {
        boolean exceptionThrown = false;
        try {
            dummyIntegerSetting.update("test");
        } catch (UiAutomator2Exception e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void dummyIntegerSettingShouldNotThrowExceptionIfApplyFailed() {
        doThrow(new UiAutomator2Exception("error")).when(dummyIntegerSetting).apply(anyInt());
        boolean exceptionThrown = false;
        try {
            dummyIntegerSetting.update(10);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        Assert.assertFalse(exceptionThrown);
    }

    @Test
    public void dummyIntegerSettingShouldReturnValidValueType() {
        Assert.assertEquals(Integer.class, dummyIntegerSetting.getValueType());
    }

    @Test
    public void dummyIntegerSettingShouldCallApplyWithValidValue() {
        dummyIntegerSetting.update(123);
        verify(dummyIntegerSetting).apply(123);
        Assert.assertEquals(Integer.valueOf(123), dummyIntegerSetting.getValue());
    }

    private class DummyIntegerSetting extends AbstractSetting<Integer> {
        private Integer value = null;

        public DummyIntegerSetting() {
            super(Integer.class, "dummyInteger");
        }

        @Override
        public Integer getValue() {
            return value;
        }

        @Override
        protected void apply(Integer value) {
            this.value = value;
        }
    }

    @Test
    public void dummyLongSettingShouldThrowExceptionIfTypeIsNotValid() {
        boolean exceptionThrown = false;
        try {
            dummyLongSetting.update("test");
        } catch (UiAutomator2Exception e) {
            exceptionThrown = true;
        }
        Assert.assertTrue(exceptionThrown);
    }

    @Test
    public void dummyLongSettingShouldNotThrowExceptionIfApplyFailed() {
        doThrow(new UiAutomator2Exception("error")).when(dummyLongSetting).apply(anyLong());
        boolean exceptionThrown = false;
        try {
            dummyLongSetting.update(10L);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        Assert.assertFalse(exceptionThrown);
    }

    @Test
    public void dummyLongSettingShouldReturnValidValueType() {
        Assert.assertEquals(Long.class, dummyLongSetting.getValueType());
    }

    @Test
    public void dummyLongSettingShouldCallApplyWithValidValue() {
        dummyLongSetting.update(123L);
        verify(dummyLongSetting).apply(123L);
        Assert.assertEquals(Long.valueOf(123), dummyLongSetting.getValue());
    }

    private class DummyLongSetting extends AbstractSetting<Long> {
        private Long value = null;

        public DummyLongSetting() {
            super(Long.class, "dummyLong");
        }

        @Override
        public Long getValue() {
            return value;
        }

        @Override
        protected void apply(Long value) {
            this.value = value;
        }
    }
}
