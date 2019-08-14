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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.utils.JSONUtils;

import static io.appium.uiautomator2.model.Session.NO_ID;

public class GetSessions extends SafeRequestHandler {
    public GetSessions(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Session session = AppiumUIA2Driver.getInstance().getSession();
        JSONArray result = new JSONArray();
        if (session != null) {
            JSONObject sessionProps = new JSONObject();
            String sessionId = session.getSessionId();
            if (sessionId != null) {
                sessionProps.put("id", sessionId);
                JSONObject sessionCaps = new JSONObject();
                for (Map.Entry<String, Object> capEntry : session.getCapabilities().entrySet()) {
                    sessionCaps.put(capEntry.getKey(), JSONUtils.formatNull(capEntry.getValue()));
                }
                sessionProps.put("capabilities", sessionCaps);
            }
            result.put(sessionProps);
        }
        return new AppiumResponse(NO_ID, result);
    }
}
