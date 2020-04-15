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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class UiAutomatorParserTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private UiAutomatorParser uiAutomatorParser;
    private final UiScrollableParser uiScrollableParser = mock(UiScrollableParser.class);
    private final UiSelectorParser uiSelectorParser = mock(UiSelectorParser.class);
    private final UiSelector scrollableSelector = new UiSelector().text("scroll");
    private final UiSelector selector = new UiSelector().text("selector");

    @Before
    public void setUp() throws UiSelectorSyntaxException, UiObjectNotFoundException {
        uiAutomatorParser = spy(new UiAutomatorParser());
        doReturn(true, false).when(uiScrollableParser).isUiScrollable();
        doReturn(scrollableSelector).when(uiScrollableParser).parse();
        doReturn(selector).when(uiSelectorParser).parse();
    }

    @Test
    public void shouldBeAbleToParseStatements() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        doReturn(uiScrollableParser).when(uiAutomatorParser).createUiScrollableParser(anyString());
        doReturn(uiSelectorParser).when(uiAutomatorParser).createUiSelectorParser(anyString());

        final String firstStatement = "new UiScrollable(new UiSelector().index(0))" +
                ".scrollTextIntoView(\"te;xt\")";
        final String secondStatement = "new UiSelector().text(\"te;st\")";
        List<UiSelector> selectors = uiAutomatorParser.parse("  " + firstStatement + " ; " +
                 secondStatement + " ; ");
        verify(uiAutomatorParser).createUiScrollableParser(firstStatement);
        verify(uiAutomatorParser).createUiSelectorParser(secondStatement);
        assertEquals(Arrays.asList(scrollableSelector, selector), selectors);
    }

    @Test
    public void shouldBeAbleToCreateUiScrollableParser() throws UiSelectorSyntaxException {
        assertEquals("new UiScrollable().test", uiAutomatorParser
                .createUiScrollableParser("UiScrollable().test").expression.toString());
    }

    @Test
    public void shouldBeAbleToCreateUiSelectorParser() throws UiSelectorSyntaxException {
        assertEquals("new UiSelector().test", uiAutomatorParser.
                createUiSelectorParser("test").expression.toString());
    }

    @Test
    public void shouldThrowExceptionIfStringIsEmpty() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Tried to parse an empty string");
        uiAutomatorParser.parse("");
    }
}
