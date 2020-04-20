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

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.KnownElements;

import static java.util.Objects.requireNonNull;

public class ScrollToElement extends SafeRequestHandler {

    public ScrollToElement(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        String[] elementIds = getElementIds(request);
        KnownElements ke = AppiumUIA2Driver.getInstance().getSessionOrThrow().getKnownElements();
        AndroidElement element = ke.getElementFromCache(elementIds[0]);
        if (element == null) {
            throw new ElementNotFoundException();
        }
        AndroidElement scrollToElement = ke.getElementFromCache(elementIds[1]);
        if (scrollToElement == null) {
            throw new ElementNotFoundException();
        }

        // attempt to get UiObjects from the container and scroll to element
        // if we can't, we have to error out, since scrollIntoView only works with UiObjects
        // (and not for example UiObject2)
        // TODO make an equivalent implementation of this method for UiObject2 if possible
        StringBuilder errorMsg = new StringBuilder();
        UiObject elementUiObject = null;
        UiObject scrollElementUiObject = null;
        try {
            elementUiObject = (UiObject) element.getUiObject();
            try {
                scrollElementUiObject = (UiObject) scrollToElement.getUiObject();
            } catch (ClassCastException e) {
                errorMsg.append("Scroll to Element");
            }
        } catch (ClassCastException e) {
            errorMsg.append("Element");
        }

        if (errorMsg.length() > 0) {
            errorMsg.append(" was not an instance of UiObject; only UiSelector is supported. " +
                    "Ensure you use the '-android uiautomator' locator strategy when " +
                    "finding elements for use with ScrollToElement");
            throw new InvalidArgumentException(errorMsg.toString());
        }

        UiScrollable uiScrollable = new UiScrollable(requireNonNull(elementUiObject).getSelector());
        if (!uiScrollable.scrollIntoView(requireNonNull(scrollElementUiObject))) {
            throw new UiObjectNotFoundException(String.format("Cannot scroll to %s element",
                    requireNonNull(scrollElementUiObject).getSelector().toString()));
        }
        return new AppiumResponse(getSessionId(request));
    }

    private static class UiScrollable extends androidx.test.uiautomator.UiScrollable {
        public UiScrollable(UiSelector container) {
            super(container);
        }

        @Override
        public boolean scrollIntoView(UiObject obj) throws UiObjectNotFoundException {
            if (obj.exists()) {
                return true;
            }

            // we will need to reset the search from the beginning to start search
            flingToBeginning(getMaxSearchSwipes());
            if (obj.exists()) {
                return true;
            }

            for (int x = 0; x < getMaxSearchSwipes(); x++) {
                if (!scrollForward()) {
                    return false;
                }

                if (obj.exists()) {
                    return true;
                }
            }

            return false;
        }

    }
}
