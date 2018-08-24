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
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AndroidElement;
import io.appium.uiautomator2.model.KnownElements;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

/**
 * This handler is used to get the boundaries of elements that support it.
 */
public class GetRect extends SafeRequestHandler {

    public GetRect(String mappedUri) {
        super(mappedUri);
    }

    public static JSONObject getElementRectJSON(AndroidElement element) throws UiObjectNotFoundException, JSONException {
        final JSONObject result = new JSONObject();
        final Rect rect = element.getBounds();
        result.put("x", rect.left);
        result.put("y", rect.top);
        result.put("width", rect.width());
        result.put("height", rect.height());
        return result;
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException, UiObjectNotFoundException {
        Logger.info("Get Rect of element command");
        String id = getElementId(request);
        final JSONObject result;
        AndroidElement element = KnownElements.getElementFromCache(id);
        if (element == null) {
            return new AppiumResponse(getSessionId(request), WDStatus.NO_SUCH_ELEMENT);
        }
        result = getElementRectJSON(element);
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, result);
    }
}
