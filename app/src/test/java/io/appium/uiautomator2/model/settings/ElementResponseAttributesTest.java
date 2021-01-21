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

public class ElementResponseAttributesTest {
    private ElementResponseAttributes elementResponseAttributes;

    @Before
    public void setup() {
        elementResponseAttributes = new ElementResponseAttributes();
    }

    @Test
    public void shouldBeString() {
        Assert.assertEquals(String.class, elementResponseAttributes.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("elementResponseAttributes", elementResponseAttributes.getName());
    }

    @Test
    public void shouldBeAbleToDisableElementResponseAttributes() {
        elementResponseAttributes.apply("");
        Assert.assertEquals("", elementResponseAttributes.getValue());
    }

    @Test
    public void shouldBeAbleToEnableElementResponseAttributes() {
        elementResponseAttributes.apply("a,b");
        Assert.assertEquals("a,b", elementResponseAttributes.getValue());
        Assert.assertArrayEquals(new String[] {"a", "b"}, elementResponseAttributes.asArray());
    }
}
