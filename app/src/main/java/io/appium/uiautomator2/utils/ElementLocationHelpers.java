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
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.NotImplementedException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.UiElementSnapshot;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.settings.DisableIdLocatorAutocompletion;
import io.appium.uiautomator2.model.settings.MapTestTagToResourceId;
import io.appium.uiautomator2.model.settings.Settings;

import static io.appium.uiautomator2.core.AxNodeInfoExtractor.toAxNodeInfo;
import static io.appium.uiautomator2.utils.AXWindowHelpers.resetAccessibilityCache;
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

    public static String rewriteIdLocator(By.ById by) {
        String locator = by.getElementLocator();
        if (Settings.get(DisableIdLocatorAutocompletion.class).getValue()
                || resourceIdRegex.matcher(locator).matches()) {
            return locator;
        }

        // not a fully qualified resource id
        // transform "textToBeChanged" into:
        // com.example.android.testing.espresso.BasicSample:id/textToBeChanged
        // it's prefixed with the app package.
        String packageName = getPackageName();
        if (packageName == null) {
            throw new UiAutomator2Exception(String.format(
                    "Cannot rewrite element locator '%1$s' to its complete form, because " +
                            "the current application package name is unknown. Consider " +
                            "providing the app package name or changing the locator to " +
                            "'<package_name>:id/%1$s' format.", locator));
        }
        return String.format("%s:id/%s", packageName, locator);
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

    @Nullable
    public static AccessibleUiObject findElement(By by) throws UiObjectNotFoundException {
        resetAccessibilityCache();

        if (by instanceof By.ById) {
            return findElementById((By.ById) by, null);
        } else if (by instanceof By.ByAccessibilityId) {
            return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof By.ByClass) {
            return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null, false);
            if (matchedNodes.isEmpty()) {
                throw new ElementNotFoundException();
            }
            return CustomUiDevice.getInstance().findObject(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findOne((By.ByAndroidUiAutomator) by);
        }

        throw new NotImplementedException(
                String.format("%s locator is not supported", by.getClass().getSimpleName())
        );
    }

    @Nullable
    public static AccessibleUiObject findElement(By by, AndroidElement context) throws UiObjectNotFoundException {
        if (by instanceof By.ById) {
            return findElementById((By.ById) by, context);
        } else if (by instanceof By.ByAccessibilityId) {
            return context.getChild(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof By.ByClass) {
            return context.getChild(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), context, false);
            if (matchedNodes.isEmpty()) {
                throw new ElementNotFoundException();
            }
            return CustomUiDevice.getInstance().findObject(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findOne((By.ByAndroidUiAutomator) by, context);
        }

        throw new NotImplementedException(
                String.format("%s locator is not supported", by.getClass().getSimpleName())
        );
    }

    public static List<AccessibleUiObject> findElements(By by) {
        resetAccessibilityCache();

        if (by instanceof By.ById) {
            return findElementsById((By.ById) by, null);
        } else if (by instanceof By.ByAccessibilityId) {
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof By.ByClass) {
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null, true);
            return matchedNodes.isEmpty()
                    ? Collections.<AccessibleUiObject>emptyList()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findMany((By.ByAndroidUiAutomator) by);
        }

        throw new NotImplementedException(
                String.format("%s locator is not supported", by.getClass().getSimpleName())
        );
    }

    public static List<AccessibleUiObject> findElements(By by, AndroidElement context) {
        if (by instanceof By.ById) {
            return findElementsById((By.ById) by, context);
        } else if (by instanceof By.ByAccessibilityId) {
            return context.getChildren(androidx.test.uiautomator.By.desc(by.getElementLocator()), by);
        } else if (by instanceof By.ByClass) {
            return context.getChildren(androidx.test.uiautomator.By.clazz(by.getElementLocator()), by);
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), context, true);
            return matchedNodes.isEmpty()
                    ? Collections.<AccessibleUiObject>emptyList()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findMany((By.ByAndroidUiAutomator) by, context);
        }

        throw new NotImplementedException(
                String.format("%s locator is not supported", by.getClass().getSimpleName())
        );
    }

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

    /**
     * Builds an XPath expression that matches nodes by {@code resource-id}, going through the
     * same attribute computation as {@link Attribute#RESOURCE_ID} (see
     * {@code AxNodeInfoHelper.getResourceId}), where a Compose {@code testTag} unconditionally
     * takes precedence over the node's real {@code resource-id} when the
     * {@link MapTestTagToResourceId} setting is enabled. Used to align {@code By.ById} lookups
     * with that precedence, since the native {@code UiSelector}/{@code BySelector} id matcher
     * only ever sees the real {@code resource-id}.
     * <p>
     * {@code rawLocator} is matched as-is to support Compose {@code testTag}s, which - unlike
     * resource ids - are never package-qualified by {@link #rewriteIdLocator}.
     * {@code rewrittenLocator} (the output of {@link #rewriteIdLocator}) is matched too, so
     * package-autocompleted lookups of real resource ids keep working; it is only added when it
     * differs from {@code rawLocator}.
     */
    static String resourceIdXPath(String rawLocator, String rewrittenLocator) {
        String rawLiteral = toXPathStringLiteral(rawLocator);
        return rawLocator.equals(rewrittenLocator)
                ? String.format(".//*[@resource-id=%s]", rawLiteral)
                : String.format(".//*[@resource-id=%s or @resource-id=%s]",
                        rawLiteral, toXPathStringLiteral(rewrittenLocator));
    }

    /**
     * Builds an XPath 1.0 string literal for the given value. XPath 1.0 has no escape
     * mechanism for quote characters, so the value is wrapped in whichever quote character
     * it does not contain; if it contains both, {@code concat()} is used to splice it back
     * together around single-quote boundaries.
     */
    static String toXPathStringLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }
        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }
        String[] parts = value.split("'", -1);
        StringBuilder result = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.append(", \"'\", ");
            }
            result.append('\'').append(parts[i]).append('\'');
        }
        return result.append(')').toString();
    }

    private static Set<Attribute> extractQueriedAttributes(String xpathExpression) {
        if (xpathExpression.contains("@*")) {
            return new HashSet<>(Arrays.asList(UiElementSnapshot.SUPPORTED_ATTRIBUTES));
        }

        return Arrays.stream(Attribute.values())
                .filter(attr -> xpathExpression.contains("@" + attr.toString()))
                .collect(Collectors.toSet());
    }

    /**
     * Shared {@code By.ById} handling for both {@link #findElement} overloads: rewrites the
     * locator, and - if {@link MapTestTagToResourceId} is enabled - resolves it via the
     * testTag-aware XPath lookup, throwing {@link ElementNotFoundException} on no match, exactly
     * like the {@code By.ByXPath} branches. Otherwise falls back to the native id matcher,
     * scoped to {@code context} when given.
     */
    @Nullable
    private static AccessibleUiObject findElementById(
            By.ById by, @Nullable AndroidElement context) throws UiObjectNotFoundException {
        String locator = rewriteIdLocator(by);
        if (Settings.get(MapTestTagToResourceId.class).getValue()) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(
                    resourceIdXPath(by.getElementLocator(), locator), context, false);
            if (matchedNodes.isEmpty()) {
                throw new ElementNotFoundException();
            }
            return CustomUiDevice.getInstance().findObject(matchedNodes);
        }
        return context == null
                ? CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.res(locator))
                : context.getChild(androidx.test.uiautomator.By.res(locator));
    }

    /**
     * Shared {@code By.ById} handling for both {@link #findElements} overloads. See
     * {@link #findElementById} for the resolution logic; the only difference is that an empty
     * match yields an empty list rather than throwing, matching the {@code By.ByXPath} branches.
     */
    private static List<AccessibleUiObject> findElementsById(By.ById by, @Nullable AndroidElement context) {
        String locator = rewriteIdLocator(by);
        if (Settings.get(MapTestTagToResourceId.class).getValue()) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(
                    resourceIdXPath(by.getElementLocator(), locator), context, true);
            return matchedNodes.isEmpty()
                    ? Collections.<AccessibleUiObject>emptyList()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        }
        return context == null
                ? CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.res(locator))
                : context.getChildren(androidx.test.uiautomator.By.res(locator), by);
    }
}
