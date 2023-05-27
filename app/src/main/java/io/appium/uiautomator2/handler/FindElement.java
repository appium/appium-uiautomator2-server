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

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.ElementsCache;
import io.appium.uiautomator2.model.api.FindElementModel;
import io.appium.uiautomator2.model.internal.ElementsLookupStrategy;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ElementLocationHelpers.findElement;
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
        final String contextId = isBlank(model.context) ? null : model.context;
        if (contextId == null) {
            Logger.info(String.format("method: '%s', selector: '%s'", method, selector));
        } else {
            Logger.info(String.format("method: '%s', selector: '%s', contextId: '%s'",
                    method, selector, contextId));
        }

        ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
        final By by = ElementsLookupStrategy.ofName(method).toNativeSelector(selector);
        final AccessibleUiObject element = contextId == null
                ? findElement(by)
                : findElement(by, elementsCache.get(contextId));
        if (element == null) {
            throw new ElementNotFoundException();
        }
        AndroidElement androidElement = elementsCache.add(element, true, by, contextId);
        return new AppiumResponse(getSessionId(request), androidElement.toModel());
    }
}
