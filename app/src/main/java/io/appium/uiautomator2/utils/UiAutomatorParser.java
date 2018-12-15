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

import java.util.ArrayList;
import java.util.List;

import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;


/**
 * For parsing strings passed in for the "-android uiautomator" locator strategy
 */
public class UiAutomatorParser {

    private static final String STATEMENT_DELIMITER = ";";
    private final List<UiSelector> selectors = new ArrayList<>();
    private String text;

    public List<UiSelector> parse(String textToParse) throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        selectors.clear();
        if (textToParse.isEmpty()) {
            throw new UiSelectorSyntaxException(textToParse, "Tried to parse an empty string. " +
                    "Expected to see a string consisting of text to be interpreted as " +
                    "UiAutomator java code.");
        }
        text = textToParse.trim();
        removeTailingSemicolon();

        while (text.length() > 0) {
            consumeStatement();
            consumeSemicolon();
        }

        return selectors;
    }

    private void removeTailingSemicolon() {
        if (text.endsWith(STATEMENT_DELIMITER)) {
            text = text.substring(0, text.length() - 1);
        }
    }

    private void consumeSemicolon() {
        if (text.startsWith(STATEMENT_DELIMITER)) {
            text = text.substring(1);
        }
    }

    private void consumeStatement() throws UiSelectorSyntaxException, UiObjectNotFoundException {
        text = text.trim();
        String statement;
        int index = 0;
        boolean isInsideStringLiteral = false;
        while (index < text.length()) {
            final char currentChar = text.charAt(index);

            if (currentChar == '"') {
                /* Skip escaped quotes */
                isInsideStringLiteral = !(isInsideStringLiteral && index > 0
                        && text.charAt(index - 1) != '\\');
            }

            if (STATEMENT_DELIMITER.equals(String.valueOf(text.charAt(index)))
                    && !isInsideStringLiteral) {
                break;
            }
            index++;
        }

        statement = text.substring(0, index).trim();
        UiScrollableParser uiScrollableParser = createUiScrollableParser(statement);
        if (uiScrollableParser.isUiScrollable()) {
            Logger.debug("Parsing scrollable: " + statement);
            selectors.add(uiScrollableParser.parse());
        } else {
            Logger.debug("Parsing selector: " + statement);
            selectors.add(createUiSelectorParser(statement).parse());
        }

        text = text.substring(index);
    }

    public UiSelectorParser createUiSelectorParser(String statement) {
        return new UiSelectorParser(statement);
    }

    public UiScrollableParser createUiScrollableParser(String statement) {
        return new UiScrollableParser(statement);
    }
}
