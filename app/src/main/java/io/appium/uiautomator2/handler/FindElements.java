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
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.FindElementModel;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.internal.ElementsLookupStrategy;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.ByUiAutomatorFinder;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class FindElements extends SafeRequestHandler {

    public FindElements(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        List<Object> result = new ArrayList<>();
        FindElementModel model = toModel(request, FindElementModel.class);
        final String method = model.strategy;
        final String selector = model.selector;
        final String contextId = model.context;
        if (contextId == null) {
            Logger.info(String.format("method: '%s', selector: '%s'", method, selector));
        } else {
            Logger.info(String.format("method: '%s', selector: '%s', contextId: '%s'",
                    method, selector, contextId));
        }

        final By by = ElementsLookupStrategy.ofName(method).toNativeSelector(selector);

        final List<AccessibleUiObject> elements;
        try {
            elements = isBlank(contextId) ? this.findElements(by) : this.findElements(by, contextId);

            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            for (AccessibleUiObject element : elements) {
                AndroidElement androidElement = session.getElementsCache().add(element, false, by, contextId);
                result.add(androidElement.toModel());
            }
            return new AppiumResponse(getSessionId(request), result);
        } catch (ElementNotFoundException ignored) {
            // Return an empty array:
            // https://github.com/SeleniumHQ/selenium/wiki/JsonWireProtocol#sessionsessionidelements
            return new AppiumResponse(getSessionId(request), result);
        }
    }

    private List<AccessibleUiObject> findElements(By by) throws UiObjectNotFoundException {
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
                    ? new ArrayList<AccessibleUiObject>()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findMany((By.ByAndroidUiAutomator) by);
        }

        String msg = String.format("By locator %s is curently not supported!", by.getClass().getSimpleName());
        throw new NotImplementedException(msg);
    }

    private List<AccessibleUiObject> findElements(By by, String contextId) throws UiObjectNotFoundException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement element = session.getElementsCache().get(contextId);

        if (by instanceof By.ById) {
            String locator = rewriteIdLocator((By.ById) by);
            return element.getChildren(androidx.test.uiautomator.By.res(locator), by);
        } else if (by instanceof By.ByAccessibilityId) {
            return element.getChildren(androidx.test.uiautomator.By.desc(by.getElementLocator()), by);
        } else if (by instanceof By.ByClass) {
            return element.getChildren(androidx.test.uiautomator.By.clazz(by.getElementLocator()), by);
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), element, true);
            return matchedNodes.isEmpty()
                    ? new ArrayList<AccessibleUiObject>()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return new ByUiAutomatorFinder().findMany((By.ByAndroidUiAutomator) by, element);
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }

}
