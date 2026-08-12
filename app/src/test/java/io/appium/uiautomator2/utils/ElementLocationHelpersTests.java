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

package io.appium.uiautomator2.utils;

import org.junit.Assert;
import org.junit.Test;

public class ElementLocationHelpersTests {

    @Test
    public void shouldQuoteValueWithoutQuotes() {
        Assert.assertEquals("'foo'", ElementLocationHelpers.toXPathStringLiteral("foo"));
    }

    @Test
    public void shouldDoubleQuoteValueContainingSingleQuote() {
        Assert.assertEquals("\"fo'o\"", ElementLocationHelpers.toXPathStringLiteral("fo'o"));
    }

    @Test
    public void shouldSingleQuoteValueContainingDoubleQuote() {
        Assert.assertEquals("'fo\"o'", ElementLocationHelpers.toXPathStringLiteral("fo\"o"));
    }

    @Test
    public void shouldConcatValueContainingBothQuoteTypes() {
        Assert.assertEquals("concat('fo', \"'\", 'o\"bar')",
                ElementLocationHelpers.toXPathStringLiteral("fo'o\"bar"));
    }

    @Test
    public void shouldMatchOnlyRawLocatorWhenRewriteIsANoop() {
        Assert.assertEquals(".//*[@resource-id='loginButton']",
                ElementLocationHelpers.resourceIdXPath("loginButton", "loginButton"));
    }

    @Test
    public void shouldMatchBothRawAndRewrittenLocatorWhenTheyDiffer() {
        Assert.assertEquals(".//*[@resource-id='loginButton' or @resource-id='com.example.app:id/loginButton']",
                ElementLocationHelpers.resourceIdXPath("loginButton", "com.example.app:id/loginButton"));
    }
}
