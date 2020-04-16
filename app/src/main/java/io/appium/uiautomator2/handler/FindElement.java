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

import io.appium.uiautomator2.model.api.FindElementModel;

import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.common.exceptions.UiSelectorSyntaxException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.By.ByClass;
import io.appium.uiautomator2.model.By.ById;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class FindElement extends SafeRequestHandler {

    public FindElement(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        FindElementModel model = toModel(request, FindElementModel.class);
        final String method = model.strategy;
        final String selector = model.selector;
        final String contextId = model.context;

        Logger.info(String.format("Find element command using '%s' with selector '%s'.", method, selector));

        final By by = new NativeAndroidBySelector().pickFrom(method, selector);

        final Object element;
        try {
            element = isBlank(contextId)
                    ? this.findElement(by)
                    : this.findElement(by, contextId);
        } catch (ClassNotFoundException e) {
            throw new UiAutomator2Exception(e);
        }
        if (element == null) {
            throw new ElementNotFoundException();
        }

        String id = UUID.randomUUID().toString();
        AndroidElement androidElement = getAndroidElement(id, element, true, by, contextId);
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        session.getKnownElements().add(androidElement);
        return new AppiumResponse(getSessionId(request), androidElement.toModel());
    }

    @Nullable
    private Object findElement(By by) throws UiAutomator2Exception, UiObjectNotFoundException {
        refreshAccessibilityCache();
        if (by instanceof ById) {
            String locator = rewriteIdLocator((ById) by);
            return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.res(locator));
        } else if (by instanceof By.ByAccessibilityId) {
            return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof ByClass) {
            return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null, false);
            if (matchedNodes.isEmpty()) {
                throw new ElementNotFoundException();
            }
            return CustomUiDevice.getInstance().findObject(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            UiSelector selector = toSelector(by.getElementLocator());
            if (selector == null) {
                throw new UiSelectorSyntaxException(by.getElementLocator(), "");
            }
            return CustomUiDevice.getInstance().findObject(selector);
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }

    @Nullable
    private Object findElement(By by, String contextId) throws ClassNotFoundException,
            UiAutomator2Exception, UiObjectNotFoundException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement element = session.getKnownElements().getElementFromCache(contextId);
        if (element == null) {
            throw new ElementNotFoundException();
        }

        if (by instanceof ById) {
            String locator = rewriteIdLocator((ById) by);
            return element.getChild(androidx.test.uiautomator.By.res(locator));
        } else if (by instanceof By.ByAccessibilityId) {
            return element.getChild(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof ByClass) {
            return element.getChild(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), element, false);
            if (matchedNodes.isEmpty()) {
                throw new ElementNotFoundException();
            }
            return CustomUiDevice.getInstance().findObject(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            UiSelector selector = toSelector(by.getElementLocator());
            if (selector == null) {
                throw new UiSelectorSyntaxException(by.getElementLocator(), "");
            }
            return element.getChild(selector);
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }
}
