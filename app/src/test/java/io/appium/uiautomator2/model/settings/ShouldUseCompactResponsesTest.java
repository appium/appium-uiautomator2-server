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

import io.appium.uiautomator2.model.Session;

import static io.appium.uiautomator2.model.settings.Settings.SHOULD_USE_COMPACT_RESPONSES;

public class ShouldUseCompactResponsesTest {

    private ShouldUseCompactResponses shouldUseCompactResponses;

    @Before
    public void setup() {
        shouldUseCompactResponses = new ShouldUseCompactResponses();
    }

    @Test
    public void shouldBeBoolean() {
        Assert.assertEquals(Boolean.class, shouldUseCompactResponses.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("shouldUseCompactResponses", shouldUseCompactResponses.getName());
    }

    @Test
    public void shouldBeAbleToEnableShouldUseCompactResponses() {
        Session.capabilities.put(SHOULD_USE_COMPACT_RESPONSES.toString(), "true");
        Assert.assertEquals(true, shouldUseCompactResponses.getValue());
    }

    @Test
    public void shouldBeAbleToDisableShouldUseCompactResponses() {
        Session.capabilities.put(SHOULD_USE_COMPACT_RESPONSES.toString(), "false");
        Assert.assertEquals(false, shouldUseCompactResponses.getValue());
    }
}
