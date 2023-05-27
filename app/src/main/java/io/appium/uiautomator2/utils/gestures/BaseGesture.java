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

package io.appium.uiautomator2.utils.gestures;

import static io.appium.uiautomator2.model.ElementsCache.toAndroidElement;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.findElement;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.ElementsCache;
import io.appium.uiautomator2.model.api.FindElementModel;
import io.appium.uiautomator2.model.internal.ElementsLookupStrategy;

public class BaseGesture {
    protected static AndroidElement locateElement(FindElementModel locator) {
        String strategy = locator.strategy;
        String selector = locator.selector;
        String contextId = isBlank(locator.context) ? null : locator.context;

        ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
        final By by = ElementsLookupStrategy.ofName(strategy).toNativeSelector(selector);
        AccessibleUiObject accessibleUiObject;
        try {
            accessibleUiObject = contextId == null
                    ? findElement(by)
                    : findElement(by, elementsCache.get(contextId));
        } catch (UiObjectNotFoundException e) {
            throw new ElementNotFoundException();
        }
        if (accessibleUiObject == null) {
            throw new ElementNotFoundException();
        }
        // We deliberately don't put the found element into the cache as it is expected
        // that scheduled actions might be repeated frequently and we don't want to fill the
        // cache only with these elements
        return toAndroidElement(accessibleUiObject, true, by, contextId);
    }
}
