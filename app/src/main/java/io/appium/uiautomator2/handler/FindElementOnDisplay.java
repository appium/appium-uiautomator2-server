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

import java.util.Map;

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
public class FindElementOnDisplay extends SafeRequestHandler {
    private static final String TAG = FindElementOnDisplay.class.getSimpleName();
    private static final long DEFAULT_TIMEOUT_MS = 1000L;

    public FindElementOnDisplay(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        FindElementOnDisplayModel model = toModel(request, FindElementOnDisplayModel.class);
        final Map<String, Object> selectorMap = model.selector;
        final Integer displayIndex = model.displayIndex; // index: 0=main, 1=secondary1 ...
        final Long timeout = model.timeout;
        final String contextId = isBlank(model.context) ? null : model.context;

        if (displayIndex == null) {
            throw new InvalidArgumentException("displayIndex parameter is required");
        }
        if (selectorMap == null || selectorMap.isEmpty()) {
            throw new InvalidArgumentException("Invalid selector: selector must be a non-empty map of BySelector fields");
        }

        ElementsCache cache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();

        AndroidElement contextElement = contextId == null ? null : cache.get(contextId);
        if (contextId != null && contextElement == null) {
            throw new InvalidArgumentException("Context element not found in cache: " + contextId);
        }

        int displayId = DisplayIdResolver.resolveDisplayId(displayIndex);
        Logger.info(TAG, String.format("Find element on display index=%d -> displayId=%d, selector='%s'",
                displayIndex, displayId, selectorMap));

        if (displayId == Display.DEFAULT_DISPLAY) {
            throw new InvalidArgumentException(
                    "Primary display search should use standard /element endpoint instead of /display/element"
            );
        }

        return findElementOnDisplay(request, selectorMap, displayId, timeout, contextElement);
    }

    private AppiumResponse findElementOnDisplay(IHttpRequest request,  Map<String, Object> selector, int displayId, Long timeout, AndroidElement context) {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
                throw new UnsupportedOperationException(
                        "Multi-display element search requires Android API level >= 30 (R)."
                );
            }

            long timeoutMs = (timeout == null) ? DEFAULT_TIMEOUT_MS : Math.max(timeout, 0L);

            UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

            BySelector bySelector = UiSelectorBuilder.fromMap(selector).displayId(displayId);

            Logger.info(TAG, String.format(
                    "Searching: displayId=%d timeout=%dms selector=%s",
                    displayId, timeoutMs, bySelector
            ));

            UiObject2 uiObject2;
            if (context != null) {
                if (context.getDisplayId() != displayId && displayId != Display.DEFAULT_DISPLAY) {
                    throw new IllegalArgumentException(String.format(
                            "Context element is on display %d but search was requested on display %d",
                            context.getDisplayId(), displayId));
                }

                Object uiObject = context.getUiObject();
                if (uiObject instanceof UiObject2) {
                    uiObject2 = ((UiObject2) uiObject).wait(Until.findObject(bySelector), timeoutMs);
                } else {
                    throw new InvalidArgumentException("Context search on display is supported only for UiObject2 elements");
                }
            } else {
                uiObject2 = device.wait(Until.findObject(bySelector), timeoutMs);
            }

            if (uiObject2 == null) {
                throw new ElementNotFoundException(String.format(
                        "Element not found on displayId %d with selector: %s (timeout=%dms)",
                        displayId, selector, timeoutMs));
            }

            AccessibleUiObject accessibleUiObject = AccessibleUiObject.toAccessibleUiObject(uiObject2);
            if (accessibleUiObject == null) {
                throw new ElementNotFoundException("Failed to convert UiObject2 to AccessibleUiObject");
            }

            ElementsCache elementsCache = AppiumUIA2Driver.getInstance().getSessionOrThrow().getElementsCache();
            String contextId = context != null ? context.getId() : null;
            AndroidElement androidElement = elementsCache.add(accessibleUiObject, true, null, contextId);
            androidElement.setDisplayId(displayId);

            Logger.info(TAG, String.format("Element found and cached: %s (displayId=%d)", androidElement, displayId));

            return new AppiumResponse(getSessionId(request), androidElement.toModel());

        } catch (ElementNotFoundException e) {
            throw e;
        } catch (InvalidArgumentException e) {
            throw e;
        } catch (UnsupportedOperationException e) {
            Logger.error(TAG, String.format("Unsupported operation when finding element on displayId=%d", displayId), e);
            throw e;
        } catch (Exception e) {
            Logger.error(TAG, String.format("Unexpected error finding element on displayId=%d", displayId), e);
            throw new ElementNotFoundException(String.format("Failed to find element on displayId=%d", displayId), e);
        }
    }
}