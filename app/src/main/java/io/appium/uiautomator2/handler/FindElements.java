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
import androidx.test.uiautomator.UiSelector;

import io.appium.uiautomator2.common.exceptions.NotImplementedException;
import io.appium.uiautomator2.model.api.FindElementModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.By;
import io.appium.uiautomator2.model.By.ById;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.model.internal.NativeAndroidBySelector;
import io.appium.uiautomator2.utils.ElementHelpers;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static io.appium.uiautomator2.utils.AXWindowHelpers.refreshAccessibilityCache;
import static io.appium.uiautomator2.utils.Device.getAndroidElement;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.getXPathNodeMatch;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.rewriteIdLocator;
import static io.appium.uiautomator2.utils.ElementLocationHelpers.toSelectors;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;
import static io.appium.uiautomator2.utils.StringHelpers.isBlank;

public class FindElements extends SafeRequestHandler {

    private static final Pattern endsWithInstancePattern = Pattern.compile(".*INSTANCE=\\d+]$");

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

        Logger.info(String.format("Find elements command using '%s' with selector '%s'.", method, selector));

        By by = new NativeAndroidBySelector().pickFrom(method, selector);

        final List<?> elements;
        try {
            elements = isBlank(contextId)
                    ? this.findElements(by)
                    : this.findElements(by, contextId);

            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            for (Object element : elements) {
                String id = UUID.randomUUID().toString();
                AndroidElement androidElement = getAndroidElement(id, element, false, by, contextId);
                session.getKnownElements().add(androidElement);
                result.add(androidElement.toModel());
            }
            return new AppiumResponse(getSessionId(request), result);
        } catch (ElementNotFoundException ignored) {
            /* For findElements up on no Element. instead of throwing exception unlike in findElement,
               empty array should be return. for more info refer:
               https://github.com/SeleniumHQ/selenium/wiki/JsonWireProtocol#sessionsessionidelements
              */
            return new AppiumResponse(getSessionId(request), result);
        }
    }

    private List<Object> findElements(By by) throws UiObjectNotFoundException {
        refreshAccessibilityCache();

        if (by instanceof By.ById) {
            String locator = rewriteIdLocator((ById) by);
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.res(locator));
        } else if (by instanceof By.ByAccessibilityId) {
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.desc(by.getElementLocator()));
        } else if (by instanceof By.ByClass) {
            return CustomUiDevice.getInstance().findObjects(androidx.test.uiautomator.By.clazz(by.getElementLocator()));
        } else if (by instanceof By.ByXPath) {
            //TODO: need to handle the context parameter in a smart way
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), null, true);
            return matchedNodes.isEmpty()
                    ? Collections.emptyList()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            //TODO: need to handle the context parameter in a smart way
            return getUiObjectsUsingAutomator(toSelectors(by.getElementLocator()), "");
        }

        String msg = String.format("By locator %s is curently not supported!", by.getClass().getSimpleName());
        throw new NotImplementedException(msg);
    }

    private List<?> findElements(By by, String contextId) throws UiObjectNotFoundException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement element = session.getKnownElements().getElementFromCache(contextId);
        if (element == null) {
            throw new ElementNotFoundException();
        }

        if (by instanceof ById) {
            String locator = rewriteIdLocator((ById) by);
            return element.getChildren(androidx.test.uiautomator.By.res(locator), by);
        } else if (by instanceof By.ByAccessibilityId) {
            return element.getChildren(androidx.test.uiautomator.By.desc(by.getElementLocator()), by);
        } else if (by instanceof By.ByClass) {
            return element.getChildren(androidx.test.uiautomator.By.clazz(by.getElementLocator()), by);
        } else if (by instanceof By.ByXPath) {
            final NodeInfoList matchedNodes = getXPathNodeMatch(by.getElementLocator(), element, true);
            return matchedNodes.isEmpty()
                    ? Collections.emptyList()
                    : CustomUiDevice.getInstance().findObjects(matchedNodes);
        } else if (by instanceof By.ByAndroidUiAutomator) {
            return getUiObjectsUsingAutomator(toSelectors(by.getElementLocator()), contextId);
        }
        String msg = String.format("By locator %s is currently not supported!", by.getClass().getSimpleName());
        throw new UnsupportedOperationException(msg);
    }

    /**
     * returns  List<UiObject> using '-android automator' expression
     **/
    private List<Object> getUiObjectsUsingAutomator(List<UiSelector> selectors, String contextId)
            throws InvalidSelectorException {
        List<Object> foundElements = new ArrayList<>();
        for (final UiSelector sel : selectors) {
            // With multiple selectors, we expect that some elements may not
            // exist.
            try {
                Logger.debug("Using: " + sel.toString());
                final List<Object> elementsFromSelector = fetchElements(sel, contextId);
                foundElements.addAll(elementsFromSelector);
            } catch (final UiObjectNotFoundException ignored) {
                //for findElements up on no elements, empty array should return.
            }
        }
        foundElements = ElementHelpers.dedupe(foundElements);
        return foundElements;
    }

    /**
     * finds elements with given UiSelector return List<UiObject
     */
    private List<Object> fetchElements(UiSelector sel, String key)
            throws UiObjectNotFoundException, InvalidSelectorException {
        //TODO: finding elements with contextId yet to implement
        boolean keepSearching = true;
        final String selectorString = sel.toString();
        final boolean useIndex = selectorString.contains("CLASS_REGEX=");
        final boolean endsWithInstance = endsWithInstancePattern.matcher(selectorString).matches();
        Logger.debug("getElements selector:" + selectorString);
        final ArrayList<Object> elements = new ArrayList<>();
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();

        // If sel is UiSelector[CLASS=android.widget.Button, INSTANCE=0]
        // then invoking instance with a non-0 argument will corrupt the selector.
        //
        // sel.instance(1) will transform the selector into:
        // UiSelector[CLASS=android.widget.Button, INSTANCE=1]
        //
        // The selector now points to an entirely different element.
        if (endsWithInstance) {
            Logger.debug("Selector ends with instance.");
            // There's exactly one element when using instance.
            UiObject instanceObj = getUiDevice().findObject(sel);
            if (instanceObj != null && instanceObj.exists()) {
                elements.add(instanceObj);
            }
            return elements;
        }

        UiObject lastFoundObj;
        final AndroidElement baseEl = session.getKnownElements().getElementFromCache(key);

        UiSelector tmp;
        int counter = 0;
        while (keepSearching) {
            if (baseEl == null) {
                Logger.debug("Element[" + key + "] is null: (" + counter + ")");

                if (useIndex) {
                    Logger.debug("  using index...");
                    tmp = sel.index(counter);
                } else {
                    tmp = sel.instance(counter);
                }

                Logger.debug("getElements tmp selector:" + tmp.toString());
                lastFoundObj = getUiDevice().findObject(tmp);
            } else {
                Logger.debug("Element[" + key + "] is " + baseEl.getId() + ", counter: "
                        + counter);
                lastFoundObj = (UiObject) baseEl.getChild(sel.instance(counter));
            }
            counter++;
            if (lastFoundObj != null && lastFoundObj.exists()) {
                elements.add(lastFoundObj);
            } else {
                keepSearching = false;
            }
        }
        return elements;
    }
}
