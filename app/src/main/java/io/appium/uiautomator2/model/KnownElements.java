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

package io.appium.uiautomator2.model;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiSelector;
import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;

public class KnownElements {
    private static Map<String, AndroidElement> cache = new HashMap<>();

    @Nullable
    private static String getCacheKey(AndroidElement element) {
        for (Map.Entry<String, AndroidElement> entry : cache.entrySet()) {
            if (entry.getValue().equals(element)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Nullable
    public static AndroidElement getElementFromCache(String id) {
        AndroidElement result = cache.get(id);
        if (result != null) {
            // It might be that cached UI object has been invalidated
            // after AX cache reset has been performed. So we try to recreate
            // the cached object automatically
            // in order to avoid an unexpected StaleElementReferenceException
            try {
                result.getName();
            } catch (Exception e) {
                final By by = result.getBy();
                Logger.debug(String.format("Trying to restore the cached element '%s'", by));
                Object ui2Object = null;
                try {
                    if (by instanceof By.ById) {
                        String locator = rewriteIdLocator((By.ById) by);
                        ui2Object = CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.res(locator));
                    } else if (by instanceof By.ByAccessibilityId) {
                        ui2Object = CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.desc(by.getElementLocator()));
                    } else if (by instanceof By.ByClass) {
                        ui2Object = CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
                    } else if (by instanceof By.ByXPath) {
                        final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null, false);
                        if (!matchedNodes.isEmpty()) {
                            ui2Object = CustomUiDevice.getInstance().findObject(matchedNodes);
                        }
                    } else if (by instanceof By.ByAndroidUiAutomator) {
                        UiSelector selector = toSelector(by.getElementLocator());
                        if (selector != null) {
                            ui2Object = CustomUiDevice.getInstance().findObject(selector);
                        }
                    }
                } catch (Exception e1) {
                    Logger.info(String.format(
                            "An exception happened while restoring the cached element '%s'", id), e1);
                }
                if (ui2Object == null) {
                    throw new StaleElementReferenceException(String.format(
                            "The element '%s' does not exist in DOM anymore", by));
                }
                AndroidElement androidElement = getAndroidElement(id, ui2Object, result.getBy());
                cache.put(androidElement.getId(), androidElement);
            }
        }
        return cache.get(id);
    }

    public String add(AndroidElement element) {
        if (cache.containsValue(element)) {
            return getCacheKey(element);
        }
        cache.put(element.getId(), element);
        return element.getId();
    }

    public void clear() {
        if (!cache.isEmpty()) {
            cache.clear();
            System.gc();
        }
    }
}
