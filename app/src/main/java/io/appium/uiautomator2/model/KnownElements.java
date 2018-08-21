package io.appium.uiautomator2.model;

import android.support.annotation.Nullable;
import android.support.test.uiautomator.UiSelector;

import java.util.HashMap;
import java.util.Map;

import io.appium.uiautomator2.common.exceptions.StaleElementReferenceException;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.LocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.LocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.LocationHelpers.toSelector;

public class KnownElements {
    private static Map<String, AndroidElement> cache = new HashMap<>();

    private static String getCacheKey(AndroidElement element) {
        for (Map.Entry<String, AndroidElement> entry : cache.entrySet()) {
            if (entry.getValue().equals(element)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getIdOfElement(AndroidElement element) {
        if (cache.containsValue(element)) {
            return getCacheKey(element);
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
                Object ui2Object = null;
                try {
                    if (by instanceof By.ById) {
                        String locator = rewriteIdLocator((By.ById) by);
                        ui2Object = CustomUiDevice.getInstance().findObject(android.support.test.uiautomator.By.res(locator));
                    } else if (by instanceof By.ByAccessibilityId) {
                        ui2Object = CustomUiDevice.getInstance().findObject(android.support.test.uiautomator.By.desc(by.getElementLocator()));
                    } else if (by instanceof By.ByClass) {
                        ui2Object = CustomUiDevice.getInstance().findObject(android.support.test.uiautomator.By.clazz(by.getElementLocator()));
                    } else if (by instanceof By.ByXPath) {
                        final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null);
                        if (matchedNodes.size() > 0) {
                            ui2Object = CustomUiDevice.getInstance().findObject(matchedNodes);
                        }
                    } else if (by instanceof By.ByAndroidUiAutomator) {
                        UiSelector selector = toSelector(by.getElementLocator());
                        if (selector != null) {
                            ui2Object = CustomUiDevice.getInstance().findObject(selector);
                        }
                    }
                } catch (Exception e1) {
                    // ignore
                }
                if (ui2Object == null) {
                    throw new StaleElementReferenceException(String.format(
                            "The element '%s' does not exist in DOM anymore", id));
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
