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

import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiScrollable;
import androidx.test.uiautomator.UiSelector;

import java.util.Arrays;
import java.util.List;

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.ElementsCache;
import io.appium.uiautomator2.model.api.ScrollToModel;
import io.appium.uiautomator2.model.internal.ElementsLookupStrategy;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.model.internal.ElementsLookupStrategy.BY_ACCESSIBILITY_ID;
import static io.appium.uiautomator2.model.internal.ElementsLookupStrategy.BY_CLASS;
import static io.appium.uiautomator2.model.internal.ElementsLookupStrategy.BY_UIAUTOMATOR;
import static io.appium.uiautomator2.utils.Device.scrollToElement;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public class ScrollTo extends SafeRequestHandler {
    private static final List<String> SUPPORTED_STRATEGIES =
            Arrays.asList(BY_ACCESSIBILITY_ID.toString(), BY_CLASS.toString(), BY_UIAUTOMATOR.toString());

    public ScrollTo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        ScrollToModel model = toModel(request, ScrollToModel.class);
        String strategy = model.params.strategy;
        String selector = model.params.selector;
        final By by = ElementsLookupStrategy.ofName(strategy).toNativeSelector(selector);
        final UiSelector uiselector;
        if (by instanceof By.ByAccessibilityId) {
            uiselector = new UiSelector().description(selector);
        } else if (by instanceof By.ByClass) {
            uiselector = new UiSelector().className(selector);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            uiselector = toSelector(by.getElementLocator());
        } else {
            throw new InvalidArgumentException(
                    String.format("Unsupported strategy: '%s'. The only supported strategies are: %s.",
                            strategy, SUPPORTED_STRATEGIES));
        }

        UiScrollable origin = null;
        if (model.origin != null) {
            ElementsCache ke = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
            AndroidElement element = ke.get(model.origin.getUnifiedId());
            if (!(element.getUiObject() instanceof UiObject)) {
                throw new IllegalArgumentException("The given origin element must be a valid scrollable UiObject");
            }
            origin = new UiScrollable(((UiObject) element.getUiObject()).getSelector());
        }
        scrollToElement(origin, uiselector, model.params.maxSwipes);

        Logger.info(String.format("Scrolled via strategy: '%s' and selector '%s'.", strategy, selector));

        return new AppiumResponse(getSessionId(request));
    }
}
