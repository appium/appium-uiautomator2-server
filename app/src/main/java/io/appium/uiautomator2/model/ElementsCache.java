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

import android.util.LruCache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelector;

public class ElementsCache {
    private final LruCache<String, AndroidElement> cache;

    ElementsCache(int maxSize) {
        this.cache = new LruCache<>(maxSize);
    }

    private static AndroidElement toAndroidElement(Object element, boolean isSingleMatch,
                                                   @Nullable By by, @Nullable String contextId) {
        return toAndroidElement(element, isSingleMatch, by, contextId, null);
    }

    private static AndroidElement toAndroidElement(Object element, boolean isSingleMatch,
                                                   @Nullable By by, @Nullable String contextId,
                                                   @Nullable String id) {
        if (element instanceof UiObject2) {
            UiObject2Element result = new UiObject2Element((UiObject2) element, isSingleMatch, by, contextId);
            return id == null ? result : result.withId(id);
        } else if (element instanceof UiObject) {
            UiObjectElement result = new UiObjectElement((UiObject) element, isSingleMatch, by, contextId);
            return id == null ? result : result.withId(id);
        }
        throw new IllegalStateException(
                String.format("Unknown element type: %s", element.getClass().getName()));
    }

    private AndroidElement restore(AndroidElement element) {
        final By by = element.getBy();
        if (by == null) {
            throw new StaleElementReferenceException(String.format(
                    "The element '%s' does not exist in DOM anymore", element.getId()));
        }

        // We cannot restore a single element from a locator that matches multiple items
        if (!element.isSingleMatch()) {
            throw new StaleElementReferenceException(String.format(
                    "Cached elements '%s' do not exist in DOM anymore", by));
        }

        Logger.debug(String.format("Trying to restore the cached element '%s'", by));
        final AndroidElement searchRoot = element.getContextId() == null
                ? null
                : get(element.getContextId());
        Object ui2Object = null;
        try {
            if (by instanceof By.ById) {
                String locator = rewriteIdLocator((By.ById) by);
                ui2Object = searchRoot == null
                        ? CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.res(locator))
                        : searchRoot.getChild(androidx.test.uiautomator.By.res(locator));
            } else if (by instanceof By.ByAccessibilityId) {
                ui2Object = searchRoot == null
                        ? CustomUiDevice.getInstance().findObject(
                        androidx.test.uiautomator.By.desc(by.getElementLocator()))
                        : searchRoot.getChild(androidx.test.uiautomator.By.desc(by.getElementLocator()));
            } else if (by instanceof By.ByClass) {
                ui2Object = searchRoot == null
                        ? CustomUiDevice.getInstance().findObject(
                        androidx.test.uiautomator.By.clazz(by.getElementLocator()))
                        : searchRoot.getChild(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
            } else if (by instanceof By.ByXPath) {
                final NodeInfoList matchedNodes = getXPathNodeMatch(
                        by.getElementLocator(), searchRoot, false);
                if (!matchedNodes.isEmpty()) {
                    ui2Object = CustomUiDevice.getInstance().findObject(matchedNodes);
                }
            } else if (by instanceof By.ByAndroidUiAutomator) {
                UiSelector selector = toSelector(by.getElementLocator());
                if (selector != null) {
                    ui2Object = searchRoot == null
                            ? CustomUiDevice.getInstance().findObject(selector)
                            : searchRoot.getChild(selector);
                }
            }
        } catch (Exception e) {
            Logger.warn(String.format(
                    "An exception happened while restoring the cached element '%s'", by), e);
        }
        if (ui2Object == null) {
            throw new StaleElementReferenceException(String.format(
                    "The element '%s' does not exist in DOM anymore", by));
        }
        AndroidElement restoredElement = toAndroidElement(ui2Object,
                element.isSingleMatch(), element.getBy(), element.getContextId(), element.getId());
        cache.put(restoredElement.getId(), restoredElement);
        return restoredElement;
    }

    @NonNull
    public AndroidElement get(String id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "A valid cached element identifier must be provided. Got null instead");
        }

        AndroidElement resultElement;
        synchronized (cache) {
            resultElement = cache.get(id);
            if (resultElement != null) {
                // It might be that cached UI object has been invalidated
                // after AX cache reset has been performed. So we try to recreate
                // the cached object automatically
                // in order to avoid an unexpected StaleElementReferenceException
                try {
                    resultElement.getName();
                } catch (Exception e) {
                    Logger.info(String.format("The element identified by '%s' has been reported as stale (%s). " +
                            "Trying to restore it", id, e.getMessage()));
                    resultElement = restore(resultElement);
                }
            }
        }

        if (resultElement == null) {
            throw new ElementNotFoundException(
                    String.format("The element identified by '%s' is not present in the cache " +
                            "or has expired. Try to find it again", id));
        }
        return resultElement;
    }

    public AndroidElement add(Object element, boolean isSingleMatch) {
        return add(element, isSingleMatch, null, null);
    }

    public AndroidElement add(Object element, boolean isSingleMatch, @Nullable By by) {
        return add(element, isSingleMatch, by, null);
    }

    public AndroidElement add(Object element, boolean isSingleMatch, @Nullable By by,
                              @Nullable String contextId) {
        AndroidElement androidElement = toAndroidElement(element, isSingleMatch, by, contextId);
        synchronized (cache) {
            cache.put(androidElement.getId(), androidElement);
        }
        return androidElement;
    }
}
