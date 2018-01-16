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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Spy;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AbstractSettingTests {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Spy
    private DummySetting dummySetting;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldThrowExceptionIfTypeIsNotValid() {
        thrown.expect(UiAutomator2Exception.class);
        thrown.expectMessage("Invalid setting value type. Got: java.lang.Integer. Expected: java.lang.String");
        dummySetting.updateSetting(10);
    }

    @Test
    public void shouldNotThrowExceptionIfApplyFailed() {
        doThrow(new UiAutomator2Exception("error")).when(dummySetting).apply(anyString());
        dummySetting.updateSetting("test");
    }

    @Test
    public void shouldReturnValidValueType() {
        Assert.assertEquals(String.class, dummySetting.getValueType());
    }

    @Test
    public void shouldCallApplyWithValidValue() {
        dummySetting.updateSetting("test");
        verify(dummySetting).apply("test");
    }

    private class DummySetting extends AbstractSetting {

        public DummySetting() {
            super(String.class);
        }

        @Override
        public String getSettingName() {
            return "dummy";
        }

        @Override
        protected void apply(Object value) {

        }
    }

}
