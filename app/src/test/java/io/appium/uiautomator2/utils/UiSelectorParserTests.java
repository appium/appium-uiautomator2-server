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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;

import static org.hamcrest.Matchers.containsString;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class UiSelectorParserTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldBeAbleToParseSimpleUiSelector() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().className(android.widget.TextView.class).text("test")
                .clickable(false).selected(true).index(1);
        UiSelector actual = new UiSelectorParser("new UiSelector().className(android.widget" +
                ".TextView).text(\"test\").clickable(false).selected(true).index(1)").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseUiSelectorWithSpaces() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("test").clickable(false);
        UiSelector actual = new UiSelectorParser(
                "  new UiSelector() . text ( \"test\" ) . clickable ( false ) ").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseUiSelectorWithoutNewKeyword() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("test");
        UiSelector actual = new UiSelectorParser("UiSelector().text(\"test\")").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseUiSelectorWithoutConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("test");
        UiSelector actual = new UiSelectorParser("text(\"test\")").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseLiteralsWithParentheses() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text(")test(test)(");
        UiSelector actual = new UiSelectorParser("new UiSelector().text(\")test(test)(\")").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseLiteralsWithBrakes() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("te\nst");
        UiSelector actual = new UiSelectorParser("new UiSelector().text(\"te\nst\")").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseLiteralsWithQuotes() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("\"\"tes\\t(\"test)(");
        UiSelector actual = new UiSelectorParser(
                "new UiSelector().text(\"\\\"\\\"tes\\t(\\\"test)(\")").parse();
        assertSame(expected, actual);
    }

    @Test
    public void shouldBeAbleToParseNestedUiSelector() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiSelector expected = new UiSelector().text("test").childSelector(new UiSelector()
                .checkable(true));
        UiSelector actual = new UiSelectorParser(
                "new UiSelector().text(\"test\").childSelector(new UiSelector()" +
                        ".checkable(true))").parse();
        assertSame(expected, actual);
    }

    @Test()
    public void shouldThrowExceptionOnUnclosedParenthesis() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Unclosed paren in expression");
        new UiSelectorParser("new UiSelector().text(\"test\"()").parse();
    }

    @Test()
    public void shouldThrowExceptionIfNoPeriodAfterConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Expected \".\" at position 16");
        new UiSelectorParser("new UiSelector()text(\"test\")").parse();
    }

    @Test()
    public void shouldThrowExceptionIfNoSuitableConstructor() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("UiSelector has no suitable constructor");
        new UiSelectorParser("new UiSelector(1, true).text(\"test\")").parse();
    }

    @Test()
    public void shouldThrowExceptionIfNoOpeningParenthesisAfterMethodName() throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("No opening parenthesis after method name at position 17");
        new UiSelectorParser("new UiSelector().text)\"test\")").parse();
    }

    @Test()
    public void shouldThrowExceptionOnMethodWithInvalidArgsCount() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage(
                "`UiSelector` doesn't have suitable method `text` with arguments [\"test1\", 5]");
        new UiSelectorParser("new UiSelector().text(\"test1\", 5)").parse();
    }

    @Test()
    public void shouldThrowExceptionOnInvalidMethod() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("UiSelector has no `test` method");
        new UiSelectorParser("new UiSelector().test(5)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfBooleanArgHasInvalidType() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("invalidArg is not a boolean");
        new UiSelectorParser("new UiSelector().checkable(invalidArg)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfStringArgHasInvalidType() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("invalidArg is not a string");
        new UiSelectorParser("new UiSelector().text(invalidArg)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfIntegerArgHasInvalidType() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("invalidArg is not a integer");
        new UiSelectorParser("new UiSelector().index(invalidArg)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfArgTypeIsNotSupported() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Could not parse");
        new UiSelectorParser("new UiSelector().equals(test)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfClassNotFound() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage(containsString("com.fake.class"));
        new UiSelectorParser("new UiSelector().className(com.fake.class)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfMethodIsNotAccessible() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Problem using reflection to call `getInt` method");
        new UiSelectorParser("new UiSelector().getInt(5)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfMethodDoesNotReturnUiSelector() throws
            UiSelectorSyntaxException, UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Unsupported return value type:`String`.");
        new UiSelectorParser("new UiSelector().index(0).toString()").parse();
    }

    @Test()
    public void shouldThrowExceptionIfMethodThrowsException() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiAutomator2Exception.class);
        expectedException.expectMessage("java.util.regex.PatternSyntaxException: " +
                "Unclosed character class near index 0");
        new UiSelectorParser("new UiSelector().resourceIdMatches(\"[\")").parse();
    }

    @Test()
    public void shouldThrowExceptionIfMethodNameIsMissing() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Missing method name at position 17");
        new UiSelectorParser("new UiSelector().(0)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfArgumentIsMissing() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Missing argument at position 22");
        new UiSelectorParser("new UiSelector().index(,1)").parse();
    }

    @Test()
    public void shouldThrowExceptionIfLastArgumentIsMissing() throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        expectedException.expect(UiSelectorSyntaxException.class);
        expectedException.expectMessage("Missing argument at position 24");
        new UiSelectorParser("new UiSelector().index(0,)").parse();
    }

    private void assertSame(UiSelector expected, UiSelector actual) {
        Assert.assertEquals(expected.toString(), actual.toString());
    }
}
