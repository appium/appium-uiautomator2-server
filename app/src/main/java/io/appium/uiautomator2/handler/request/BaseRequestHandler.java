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

package io.appium.uiautomator2.handler.request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.test.uiautomator.UiObjectNotFoundException;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.AppiumServlet;
import io.appium.uiautomator2.utils.Logger;

public abstract class BaseRequestHandler {

    private final String mappedUri;

    public BaseRequestHandler(String mappedUri) {
        this.mappedUri = mappedUri;
    }

    public String getMappedUri() {
        return mappedUri;
    }

    public String getElementId(IHttpRequest request) {
        return (String) request.data().get(AppiumServlet.ELEMENT_ID_KEY);
    }

    public String getNameAttribute(IHttpRequest request) {
        return (String) request.data().get(AppiumServlet.NAME_ID_KEY);
    }

    public JSONObject toJSON(IHttpRequest request) throws JSONException {
        String json = request.body();
        Logger.debug("payload: " + json);
        if (json != null && !json.isEmpty()) {
            return new JSONObject(json);
        }
        return new JSONObject();
    }

    public Map<String, Object> getPayload(IHttpRequest request, String jsonKey) throws JSONException {
        JSONObject payload = toJSON(request);
        if (jsonKey != null) {
            payload = payload.getJSONObject(jsonKey);
        }

        Map<String, Object> map = new LinkedHashMap<>();
        Iterator<String> keysItr = payload.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = payload.get(key);
            map.put(key, value);
        }
        return map;
    }

    public String getSessionId(IHttpRequest request) {
        return (String) request.data().get(AppiumServlet.SESSION_ID_KEY);
    }

    public abstract AppiumResponse handle(IHttpRequest request);

    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException,
            UiObjectNotFoundException {
        return handle(request);
    }
}
