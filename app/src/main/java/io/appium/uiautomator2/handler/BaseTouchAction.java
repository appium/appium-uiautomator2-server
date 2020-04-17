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

import android.graphics.Rect;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.core.InteractionController;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.model.api.touch.appium.TouchEventModel;
import io.appium.uiautomator2.model.api.touch.appium.TouchEventParams;

import androidx.test.uiautomator.UiObjectNotFoundException;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.ModelUtils.toModel;

public abstract class BaseTouchAction extends SafeRequestHandler {
    protected int clickX;
    protected int clickY;
    protected AndroidElement element;
    protected TouchEventParams params;

    public BaseTouchAction(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        params = toModel(request, TouchEventModel.class).params;
        final String elementId = params.getUnifiedId();
        if (elementId != null && params.x == null && params.y == null) {
            Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
            element = session.getKnownElements().getElementFromCache(elementId);
            if (element == null) {
                throw new ElementNotFoundException();
            }
            final Rect bounds = element.getBounds();
            clickX = bounds.centerX();
            clickY = bounds.centerY();
        } else { // no element so extract x and y from params
            if (params.x == null || params.y == null) {
                throw new IllegalArgumentException(
                        "Both x and y coordinates must be provided without element id set");
            }
            clickX = params.x;
            clickY = params.y;
        }

        executeEvent();
        return new AppiumResponse(getSessionId(request));
    }

    protected abstract void executeEvent() throws UiObjectNotFoundException;

    protected InteractionController getIc() {
        return UiAutomatorBridge.getInstance().getInteractionController();
    }

    protected String getName() {
        return getClass().getSimpleName();
    }

    protected void printEventDebugLine() {
        printEventDebugLine(null);
    }

    protected void printEventDebugLine(@Nullable Integer duration) {
        Logger.debug(String.format(
                "Performing %s at x: (%s, %s)%s", getName(), clickY, clickY,
                duration == null ? "" : String.format(", duration: %s", duration)));
    }
}
