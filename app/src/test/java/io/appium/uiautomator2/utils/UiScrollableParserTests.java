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
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SuppressWarnings("deprecation")
@RunWith(PowerMockRunner.class)
@PrepareForTest(value = {UiDevice.class})
public class UiScrollableParserTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UiDevice uiDevice;

    private UiScrollable uiScrollableSpy;
    private UiObject uiObject;
    private Boolean isUiObjectExist;

    @Captor
    private ArgumentCaptor<UiSelector> uiSelectorArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Captor
    private ArgumentCaptor<Boolean> booleanArgumentCaptor;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(UiDevice.class);
        when(UiDevice.getInstance()).thenReturn(uiDevice);
        isUiObjectExist = false;
    }

    @Test
    public void shouldBeAbleToParseUiSelectorInConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        new UiScrollableParserSpy("new UiScrollable(new UiSelector().text(\"test\"))" +
                ".scrollTextIntoView(\"test\")").parse();
        UiSelector expectedUiScrollableSelector = new UiSelector().text("test");
        assertEquals(expectedUiScrollableSelector, uiScrollableSpy.getSelector());
    }

    @Test
    public void shouldBeAbleToParseUiSelectorInConstructorIfTheFinalMethodDoesNotReturnUiObject()
            throws UiSelectorSyntaxException, UiObjectNotFoundException {
        new UiScrollableParserSpy("new UiScrollable(new UiSelector().text(\"test\"))" +
                ".scrollToEnd(20)").parse();
        UiSelector expectedUiScrollableSelector = new UiSelector().text("test");
        assertEquals(expectedUiScrollableSelector, uiScrollableSpy.getSelector());
    }

    @Test
    public void shouldBeAbleToChainUiScrollableMethods() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        final String locator = "new UiScrollable(new UiSelector()).setAsHorizontalList()" +
                ".setMaxSearchSwipes(10).scrollIntoView(new UiSelector())";
        new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy).setAsHorizontalList();
        verify(uiScrollableSpy).setMaxSearchSwipes(10);
    }

    @Test
    public void shouldToAbleToInvokeScrollIntoViewWithUiSelectorParam() throws
            UiObjectNotFoundException, UiSelectorSyntaxException {
        final String locator = "new UiScrollable(new UiSelector()).scrollIntoView(" +
                "new UiSelector().index(0).text(\"test\"))";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy).scrollIntoView(any(UiSelector.class));
        assertEquals(new UiSelector().index(0).text("test"), uiSelector);
    }

    @Test
    public void shouldToAbleToInvokeScrollTextIntoView() throws UiObjectNotFoundException,
            UiSelectorSyntaxException {
        final String locator = "new UiScrollable(new UiSelector()).scrollTextIntoView(" +
                "\"test\")";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy).scrollTextIntoView("test");
        assertEquals(new UiSelector().text("test"), uiSelector);
    }

    @Test
    public void shouldToAbleToInvokeScrollDescriptionIntoView() throws UiObjectNotFoundException,
            UiSelectorSyntaxException {
        final String locator = "new UiScrollable(new UiSelector()).scrollDescriptionIntoView(" +
                "\"test\")";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy).scrollDescriptionIntoView("test");
        assertEquals(new UiSelector().description("test"), uiSelector);
    }

    @Test
    public void shouldNotInvokeScrollIntoViewWithUiSelectorParamIfObjectExists() throws
            UiObjectNotFoundException, UiSelectorSyntaxException {
        isUiObjectExist = true;
        final String locator = "new UiScrollable(new UiSelector()).scrollIntoView(" +
                "new UiSelector().index(0).text(\"test\"))";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy, never()).scrollIntoView(any(UiSelector.class));
        assertEquals(new UiSelector().index(0).text("test"), uiSelector);
    }

    @Test
    public void shouldNotInvokeScrollTextIntoViewIfTextExists() throws UiObjectNotFoundException,
            UiSelectorSyntaxException {
        isUiObjectExist = true;
        final String locator = "new UiScrollable(new UiSelector()).scrollTextIntoView(" +
                "\"test\")";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy, never()).scrollTextIntoView(anyString());
        assertEquals(new UiSelector().text("test"), uiSelector);
    }

    @Test
    public void shouldNotInvokeScrollDescriptionIntoViewIfDescriptionExists() throws
            UiObjectNotFoundException, UiSelectorSyntaxException {
        isUiObjectExist = true;
        final String locator = "new UiScrollable(new UiSelector()).scrollDescriptionIntoView(" +
                "\"test\")";
        UiSelector uiSelector = new UiScrollableParserSpy(locator).parse();
        verify(uiScrollableSpy, never()).scrollDescriptionIntoView(anyString());
        assertEquals(new UiSelector().description("test"), uiSelector);
    }

    @Test
    public void shouldReturnUiSelectorFromUiObjectIfLastMethodReturnUiObject() throws
            UiObjectNotFoundException, UiSelectorSyntaxException {
        uiObject = new UiObject(new UiSelector().resourceId("testId"));
        final String locator = "new UiScrollable(new UiSelector()).getChildByText(" +
                "new UiSelector().resourceId(\"testId\"), \"text\", true)";
        UiScrollableParser uiScrollableParser = new UiScrollableParserSpy(locator);

        assertEquals(uiObject.getSelector(), uiScrollableParser.parse());
        verify(uiScrollableSpy).getChildByText(uiSelectorArgumentCaptor.capture(),
                stringArgumentCaptor.capture(), booleanArgumentCaptor.capture());
        assertEquals(uiObject.getSelector(), uiSelectorArgumentCaptor.getValue());
        Assert.assertEquals("text", stringArgumentCaptor.getValue());
        Assert.assertEquals(true, booleanArgumentCaptor.getValue());
    }

    @Test
    public void shouldThrowExceptionIfNoConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("didn't start with an acceptable prefix. " +
                "Acceptable prefixes are: `new UiScrollable` or `UiScrollable`");
        new UiScrollableParserSpy("test").parse();
    }

    @Test
    public void shouldReThrowUiObjectNotFoundException() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiObjectNotFoundException.class);
        expectedException.expectMessage("not found");
        final String locator = "new UiScrollable(new UiSelector()).getChildByText(" +
                "new UiSelector().resourceId(\"testId\"), \"text\")";
        new UiScrollableParserSpy(locator).parse();
    }

    @Test
    public void isUiScrollableShouldReturnTrueIfStringStartsWithConstructor() {
        assertTrue(new UiScrollableParserSpy("new UiScrollable").isUiScrollable());
    }

    @Test
    public void isUiScrollableShouldReturnFalseIfStringDoesNotStartWithConstructor() {
        assertFalse(new UiScrollableParserSpy("test").isUiScrollable());
    }

    private void assertEquals(UiSelector expected, UiSelector actual) {
        Assert.assertEquals(expected.toString(), actual.toString());
    }

    /**
     * We can't mock all dependencies of UiScrollable due to package private modifier
     * of {@link androidx.test.uiautomator.QueryController}.
     * But we can stub methods with these unmockable deps.
     */
    private class UiScrollableParserSpy extends UiScrollableParser {

        UiScrollableParserSpy(String expression) {
            super(expression);
        }

        @Override
        protected UiScrollable consumeConstructor() throws UiSelectorSyntaxException,
                UiObjectNotFoundException {
            UiScrollable self = super.consumeConstructor();
            uiScrollableSpy = spy(getTarget());
            doReturn(true).when(uiScrollableSpy).scrollIntoView(any(UiSelector.class));
            doReturn(true).when(uiScrollableSpy).scrollIntoView(any(UiObject.class));
            doReturn(uiObject).when(uiScrollableSpy).getChildByText(any(UiSelector.class),
                    anyString(), anyBoolean());
            doThrow(new UiObjectNotFoundException("not found")).when(uiScrollableSpy)
                    .getChildByText(any(UiSelector.class), anyString());
            doReturn(true).when(uiScrollableSpy).scrollForward(anyInt());
            setTarget(uiScrollableSpy);
            return self;
        }

        @Override
        protected UiObject createUiObject(UiSelector uiSelector) {
            uiObject = spy(super.createUiObject(uiSelector));
            doReturn(isUiObjectExist).when(uiObject).exists();
            return uiObject;
        }
    }
}
