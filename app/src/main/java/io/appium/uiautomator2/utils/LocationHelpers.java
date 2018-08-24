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

import android.support.annotation.Nullable;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import java.util.List;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoGetter;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.UiAutomationElement;
import io.appium.uiautomator2.model.XPathFinder;

import static io.appium.uiautomator2.utils.AXWindowHelpers.currentActiveWindowRoot;

public class LocationHelpers {
    /**
     * java_package : type / name
     * <p>
     * com.example.Test:id/enter
     * <p>
     * ^[a-zA-Z_] - Java package must start with letter or underscore
     * [a-zA-Z0-9\._]* - Java package may contain letters, numbers, periods and
     * underscores : - : ends the package and starts the type [^\/]+ - type is
     * made up of at least one non-/ characters \\/ - / ends the type and starts
     * the name [\S]+$ - the name contains at least one non-space character and
     * then the line is ended
     * <p>
     * Example:
     * http://java-regex-tester.appspot.com/regex/5f04ac92-f9aa-45a6-b1dc-e2c25fd3cc6b
     */
    private static final Pattern resourceIdRegex = Pattern
            .compile("^[a-zA-Z_][a-zA-Z0-9._]*:[^/]+/[\\S]+$");

    public static String rewriteIdLocator(By.ById by) {
        String locator = by.getElementLocator();

        if (!resourceIdRegex.matcher(by.getElementLocator()).matches()) {
            // not a fully qualified resource id
            // transform "textToBeChanged" into:
            // com.example.android.testing.espresso.BasicSample:id/textToBeChanged
            // it's prefixed with the app package.
            locator = Session.capabilities.get("appPackage") + ":id/" + by.getElementLocator();
            Logger.debug("Updated findElement locator strategy: " + locator);
        }
        return locator;
    }

    /**
     * returns  UiObject2 for an xpath expression
     * TODO: Need to handle contextId based finding
     */
    public static NodeInfoList getXPathNodeMatch(final String expression, @Nullable AndroidElement element)
            throws UiAutomator2Exception {
        final UiAutomationElement xpathRoot = element == null
                ? UiAutomationElement.rebuildForNewRoot(currentActiveWindowRoot(),
                NotificationListener.getInstance().getToastMessage())
                : UiAutomationElement.rebuildForNewRoot(
                AccessibilityNodeInfoGetter.fromUiObject(element.getUiObject()), null);
        return new XPathFinder(expression).find(xpathRoot);
    }

    @Nullable
    public static UiSelector toSelector(String uiaExpression) throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        List<UiSelector> selectors = toSelectors(uiaExpression);
        return selectors.isEmpty() ? null : selectors.get(0);
    }

    public static List<UiSelector> toSelectors(String uiaExpression) throws UiSelectorSyntaxException,
            UiObjectNotFoundException {
        UiAutomatorParser uiAutomatorParser = new UiAutomatorParser();
        return uiAutomatorParser.parse(uiaExpression);
    }
}
