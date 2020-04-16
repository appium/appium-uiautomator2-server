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

import io.appium.uiautomator2.model.api.SendKeysModel;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

import static android.text.TextUtils.isEmpty;
import static androidx.test.uiautomator.By.focused;
import static io.appium.uiautomator2.utils.Device.getUiDevice;
import static io.appium.uiautomator2.utils.ElementHelpers.findElement;
import static io.appium.uiautomator2.utils.ModelUtils.toModel;

/**
 * Send keys to a given element.
 */
public class SendKeysToElement extends SafeRequestHandler {

    public SendKeysToElement(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        String elementId = getElementId(request);
        AndroidElement element;
        if (elementId != null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
        } else {
            //perform action on focused element
            element = findElement(focused(true));
        }
        SendKeysModel model = toModel(request, SendKeysModel.class);
        String text = model.text;
        boolean replace = model.replace == null ? false : model.replace;

        boolean pressEnter = false;
        if (text.endsWith("\\n")) {
            pressEnter = true;
            text = text.replace("\\n", "");
            Logger.debug("Will press Enter after setting text");
        }

        if (!replace) {
            String currentText = element.getText();
            if (!isEmpty(currentText)) {
                element.clear();
                if (!isEmpty(element.getText())) {
                    // clear could have failed, or we could have a hint in the field
                    // we'll assume it is the latter
                    Logger.debug("Could not clear the text. Assuming the remainder is a hint text.");
                    currentText = "";
                }
                text = currentText + text;
            }
        }
        if (!element.setText(text)) {
            throw new InvalidElementStateException(String.format("Cannot set the element to '%s'. " +
                    "Did you interact with the correct element?", model.text));
        }

        if (pressEnter) {
            Logger.debug(getUiDevice().pressEnter()
                    ? "Sent Enter key to the device"
                    : "Could not send Enter key to the device");
        }
        return new AppiumResponse(getSessionId(request));
    }
}


