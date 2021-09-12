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

package io.appium.uiautomator2.handler;

import androidx.test.uiautomator.UiObjectNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.NotImplementedException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.ElementsCache;
import io.appium.uiautomator2.model.api.FindElementModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.internal.ElementsLookupStrategy;
import io.appium.uiautomator2.utils.ByUiAutomatorFinder;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;
import static io.appium.uiautomator2.utils.StringHelpers.pluralize;

import android.util.Log;

public class FindElements extends SafeRequestHandler {

    public FindElements(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        FindElementModel model = toModel(request, FindElementModel.class);
        final String method = model.strategy;
        final String selector = model.selector;
        final String contextId = isBlank(model.context) ? null : model.context;
        if (contextId == null) {
            Logger.info(String.format("method: '%s', selector: '%s'", method, selector));
        } else {
            Logger.info(String.format("method: '%s', selector: '%s', contextId: '%s'",
                    method, selector, contextId));
        }

        ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
        final By by = ElementsLookupStrategy.ofName(method).toNativeSelector(selector);
        List<AccessibleUiObject> elements;
        try {
            elements = contextId == null
                    ? this.findElements(by)
                    : this.findElements(by, elementsCache.get(contextId));
        } catch (ElementNotFoundException e) {
            Logger.warn(String.format("Got an exception while looking for multiple matches using " +
                    "selector %s", by));
            Logger.warn(Log.getStackTraceString(e));
            // Return an empty array:
            // https://github.com/SeleniumHQ/selenium/wiki/JsonWireProtocol#sessionsessionidelements
            return new AppiumResponse(getSessionId(request), Collections.emptyList());
        }
        if (elements.isEmpty()) {
            Logger.info("Found zero matches");
            return new AppiumResponse(getSessionId(request), Collections.emptyList());
        }

        Logger.info(String.format(
                "Caching %s", pluralize(elements.size(), "found element")));
        List<Object> result = new ArrayList<>();
        for (AccessibleUiObject element : elements) {
            AndroidElement androidElement = elementsCache.add(element, false, by, contextId);
            result.add(androidElement.toModel());
        }
        Logger.info(String.format("Cached %s", pluralize(result.size(), "element")));
        return new AppiumResponse(getSessionId(request), result);
    }

    private List<AccessibleUiObject> findElements(By by) {
        refreshAccessibilityCache();

        if (by instanceof By.ById) {
            String locator = rewriteIdLocator((By.ById) by);
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.res(locator));
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

    private List<AccessibleUiObject> findElements(By by, AndroidElement context) {
        if (by instanceof By.ById) {
            String locator = rewriteIdLocator((By.ById) by);
            return context.getChildren(androidx.test.uiautomator.By.res(locator), by);
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

}
