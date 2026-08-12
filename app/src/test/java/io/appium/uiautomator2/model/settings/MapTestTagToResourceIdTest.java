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

public class MapTestTagToResourceIdTest {

    private MapTestTagToResourceId mapTestTagToResourceId;

    @Before
    public void setup() {
        mapTestTagToResourceId = new MapTestTagToResourceId();
    }

    @Test
    public void shouldBeBoolean() {
        Assert.assertEquals(Boolean.class, mapTestTagToResourceId.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("mapTestTagToResourceId", mapTestTagToResourceId.getName());
    }

    @Test
    public void shouldBeDisabledByDefault() {
        Assert.assertEquals(false, mapTestTagToResourceId.getValue());
    }

    @Test
    public void shouldBeAbleToEnable() {
        mapTestTagToResourceId.apply(true);
        Assert.assertEquals(true, mapTestTagToResourceId.getValue());
    }

    @Test
    public void shouldBeAbleToDisable() {
        mapTestTagToResourceId.apply(true);
        mapTestTagToResourceId.apply(false);
        Assert.assertEquals(false, mapTestTagToResourceId.getValue());
    }
}
