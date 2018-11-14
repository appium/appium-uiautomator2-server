/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.utils;

import org.junit.Test;

import static io.appium.uiautomator2.utils.XMLHelpers.toNodeName;
import static io.appium.uiautomator2.utils.XMLHelpers.toSafeString;
import static org.junit.Assert.assertEquals;

public class XMLHelpersTests {

    @Test
    public void keepsValidNodeName() {
        assertEquals(toNodeName("foo.Bar"), "foo.Bar");
    }

    @Test
    public void stripsInvalidStartingCharsFromNodeName() {
        assertEquals(toNodeName("#$%bla"), "bla");
    }

    @Test
    public void replacesInvalidCharsInNodeName() {
        assertEquals(toNodeName("1_bl\n1a"), "_bl_1a");
    }

    @Test
    public void replacesInvalidCharsInXml() {
        assertEquals(toSafeString("bl\uFFFFa", "?"), "bl?a");
    }
}
