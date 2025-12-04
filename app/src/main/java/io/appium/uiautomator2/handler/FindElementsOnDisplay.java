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

import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

import android.os.Build;
import android.view.Display;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.BySelector;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.Until;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.selector.UiSelectorBuilder;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.ElementsCache;
import io.appium.uiautomator2.model.api.FindElementOnDisplayModel;
import io.appium.uiautomator2.utils.DisplayIdResolver;
import io.appium.uiautomator2.utils.Logger;

public class FindElementsOnDisplay extends SafeRequestHandler {
    private static final long DEFAULT_TIMEOUT_MS = 1000L;

    public FindElementsOnDisplay(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        FindElementOnDisplayModel model = toModel(request, FindElementOnDisplayModel.class);
        final Map<String, Object> selectorMap = model.selector;
        final Integer displayIndex = model.displayIndex; // Python-side index: 0=main, 1=secondary1 ...
        final Long timeout = model.timeout;
        final String contextId = isBlank(model.context) ? null : model.context;

        if (displayIndex == null) {
            throw new InvalidArgumentException("displayId parameter (index) is required");
        }
        if (selectorMap == null || selectorMap.isEmpty()) {
            throw new InvalidArgumentException("selector must be provided and non-empty");
        }

        AndroidElement contextElement = null;
        if (contextId != null) {
            ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
            contextElement = elementsCache.get(contextId);
            if (contextElement == null) {
                throw new InvalidArgumentException("context element not found in session cache: " + contextId);
            }
        }

        int systemDisplayId = DisplayIdResolver.resolveDisplayId(displayIndex);

        Logger.info(String.format(
                "[MultiDisplay] Find elements on display index=%d -> systemDisplayId=%d, selector='%s'",
                displayIndex, systemDisplayId, selectorMap));

        if (systemDisplayId == Display.DEFAULT_DISPLAY) {
            throw new InvalidArgumentException("For primary display, please use standard /elements endpoint");
        }

        return findElementsOnDisplay(request, selectorMap, systemDisplayId, timeout, contextElement);
    }

    private AppiumResponse findElementsOnDisplay(IHttpRequest request,  Map<String, Object> selector, int displayId, Long timeout, AndroidElement context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                throw new UnsupportedOperationException("Multi display findElements not supported for Android API < 30");
            }

            long timeoutMs = (timeout == null) ? DEFAULT_TIMEOUT_MS : Math.max(timeout, 0L);
            Logger.info(String.format("[MultiDisplay] Searching elements with timeout=%dms on displayId=%d", timeoutMs, displayId));

            UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            BySelector bySelector = UiSelectorBuilder.fromMap(selector).displayId(displayId);

            List<UiObject2> uiObjects;
            if (context != null) {
                if (context.getDisplayId() != displayId) {
                    throw new InvalidArgumentException(String.format(
                            "Context element is on display %d, but search is for display %d",
                            context.getDisplayId(), displayId));
                }

                Object uiObject = context.getUiObject();
                if (uiObject instanceof UiObject2) {
                    uiObjects = ((UiObject2) uiObject).wait(Until.findObjects(bySelector), timeoutMs);
                } else {
                    throw new UnsupportedOperationException("Context element search only supported for UiObject2 elements");
                }
            } else {
                uiObjects =  device.wait(Until.findObjects(bySelector), timeoutMs);
            }

            if (uiObjects == null || uiObjects.isEmpty()) {
                Logger.info(String.format("[MultiDisplay] No elements found on displayId %d with selector: %s",
                        displayId, selector));
                return new AppiumResponse(getSessionId(request), new ArrayList<>());
            }

            ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
            List<AndroidElement> androidElements = new ArrayList<>();
            String contextId = context != null ? context.getId() : null;

            for (UiObject2 uiObject : uiObjects) {
                AccessibleUiObject accessibleUiObject = AccessibleUiObject.toAccessibleUiObject(uiObject);
                if (accessibleUiObject != null) {
                    AndroidElement androidElement = elementsCache.add(accessibleUiObject, false, null,contextId);
                    androidElement.setDisplayId(displayId);
                    androidElements.add(androidElement);
                }
            }

            Logger.info(String.format("[MultiDisplay] Found %d elements on displayId %d",
                    androidElements.size(), displayId));

            List<Object> elementModels = androidElements.stream()
                    .map(element -> {
                        try {
                            return element.toModel();
                        } catch (Exception e) {
                            Logger.error("Error converting element to model", e);
                            return null;
                        }
                    })
                    .filter(model -> model != null)
                    .collect(Collectors.toList());

            return new AppiumResponse(getSessionId(request), elementModels);

        } catch (Exception e) {
            Logger.error(String.format("Unexpected error finding elements on displayId=%d", displayId), e);
            throw new ElementNotFoundException(String.format("Failed to find elements on displayId=%d", displayId), e);
        }
    }

}