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

import androidx.annotation.Nullable;
import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AccessibleUiObject;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.internal.CustomUiDevice;

public class ActiveElement extends SafeRequestHandler {

    public ActiveElement(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws ElementNotFoundException, UiObjectNotFoundException {
        final AccessibleUiObject element = findFocused();

        if (element == null) {
            throw new ElementNotFoundException();
        }

        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        AndroidElement androidElement = session.getElementsCache().add(element, true);

        return new AppiumResponse(getSessionId(request), androidElement.toModel());
    }

    @Nullable
    private AccessibleUiObject findFocused() {
        return CustomUiDevice.getInstance().findObject(androidx.test.uiautomator.By.focused(true));
    }
}
