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

import org.json.JSONException;
import org.json.JSONObject;

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public abstract class TouchEvent extends SafeRequestHandler {
    protected int clickX, clickY;
    protected AndroidElement element;
    protected JSONObject params;

    public TouchEvent(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException,
            UiObjectNotFoundException {
        params = new JSONObject(getPayload(request).getString("params"));
        if (params.has(ELEMENT_ID_KEY_NAME) && !(params.has("x") && params.has("y"))) {
            /*
             * Finding centerX and centerY.
             */
            String id = params.getString(ELEMENT_ID_KEY_NAME);
            element = KnownElements.getElementFromCache(id);
            if (element == null) {
                return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
            }
            final Rect bounds = element.getBounds();
            clickX = bounds.centerX();
            clickY = bounds.centerY();
        } else { // no element so extract x and y from params
            clickX = params.getInt("x");
            clickY = params.getInt("y");
        }

        if (executeTouchEvent()) {
            return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.UNKNOWN_ERROR, false);
    }

    protected abstract boolean executeTouchEvent() throws UiObjectNotFoundException, UiAutomator2Exception, JSONException;

    protected void printEventDebugLine(final String methodName, final Integer... duration) {
        String extra = "";
        if (duration.length > 0) {
            extra = ", duration: " + duration[0];
        }
        Logger.debug("Performing " + methodName + " x: " + clickX + ", y: " + clickY + extra);
    }
}
