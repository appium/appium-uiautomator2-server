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

import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiSelector;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.UiElementSnapshot;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class ElementLocationHelpers {
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

    @Nullable
    private static String getPackageName() {
        String pkg = AppiumUIA2Driver.getInstance()
                .getSessionOrThrow()
                .getCapability("appPackage", "");
        if (isBlank(pkg)) {
            pkg = CustomUiDevice.getInstance().getInstrumentation()
                    .getTargetContext()
                    .getPackageName();
        }
        return isBlank(pkg) ? null : pkg;
    }

    public static String rewriteIdLocator(By.ById by) {
        String locator = by.getElementLocator();

        if (!resourceIdRegex.matcher(by.getElementLocator()).matches()) {
            // not a fully qualified resource id
            // transform "textToBeChanged" into:
            // com.example.android.testing.espresso.BasicSample:id/textToBeChanged
            // it's prefixed with the app package.
            String packageName = getPackageName();
            if (packageName == null) {
                throw new UiAutomator2Exception(String.format(
                        "Cannot rewrite element locator '%s' to its complete form, because " +
                                "the current application package name is unknown. Consider " +
                                "providing the app package name or changing the locator to " +
                                "'<package_name>:id/%s' format.", by.getElementLocator(), by.getElementLocator()));
            }
            locator = String.format("%s:id/%s", packageName, locator);
            Logger.debug("Updated findElement locator strategy: " + locator);
        }
        return locator;
    }

    private static Set<Attribute> extractQueriedAttributes(String xpathExpression) {
        if (xpathExpression.contains("@*")) {
            return new HashSet<>(Arrays.asList(UiElementSnapshot.SUPPORTED_ATTRIBUTES));
        }

        Set<Attribute> result = new HashSet<>();
        for (Attribute attr : Attribute.values()) {
            if (xpathExpression.contains("@" + attr.toString())) {
                result.add(attr);
            }
        }
        return result;
    }

    public static NodeInfoList getXPathNodeMatch(
            final String expression, @Nullable AndroidElement element, boolean multiple) {
        AccessibilityNodeInfo root = element == null ? null : toAxNodeInfo(element.getUiObject());
        // We are trying to be smart here and only include the actually queried
        // attributes into the source XML document. This allows to improve the performance a lot
        // while building this document.
        Set<Attribute> includedAttributes = extractQueriedAttributes(expression);
        Logger.info(String.format("The following attributes will be included to the page source: %s",
                includedAttributes));
        return new AccessibilityNodeInfoDumper(root, includedAttributes).findNodes(expression, multiple);
    }

    public static UiSelector toSelector(String uiaExpression) throws UiSelectorSyntaxException {
        return toSelectors(uiaExpression).get(0);
    }

    public static List<UiSelector> toSelectors(String uiaExpression) throws UiSelectorSyntaxException {
        List<UiSelector> selectors = new UiAutomatorParser().parse(uiaExpression);
        if (selectors.isEmpty()) {
            throw new UiSelectorSyntaxException(uiaExpression);
        }
        return selectors;
    }
}
