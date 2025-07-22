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

public class AlwaysTraversableViewClassesTest {
    private AlwaysTraversableViewClasses alwaysTraversableViewClasses;

    @Before
    public void setup() {
        alwaysTraversableViewClasses = new AlwaysTraversableViewClasses();
    }

    @Test
    public void shouldBeString() {
        Assert.assertEquals(String.class, alwaysTraversableViewClasses.getValueType());
    }

    @Test
    public void shouldReturnValidSettingName() {
        Assert.assertEquals("alwaysTraversableViewClasses", alwaysTraversableViewClasses.getName());
    }

    @Test
    public void shouldBeAbleToDisableTraversableViewClasses() {
        alwaysTraversableViewClasses.apply("");
        Assert.assertEquals("", alwaysTraversableViewClasses.getValue());
    }

    @Test
    public void shouldBeAbleToEnableTraversableViewClasses() {
        alwaysTraversableViewClasses.apply("a,b");
        Assert.assertEquals("a,b", alwaysTraversableViewClasses.getValue());
        Assert.assertArrayEquals(new String[] {"^a$", "^b$"}, alwaysTraversableViewClasses.asArray());
    }
}
