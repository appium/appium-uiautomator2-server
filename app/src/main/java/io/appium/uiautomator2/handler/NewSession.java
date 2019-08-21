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

import io.appium.uiautomator2.common.exceptions.InvalidArgumentException;
import io.appium.uiautomator2.common.exceptions.SessionNotCreatedException;
import io.appium.uiautomator2.utils.w3c.W3CCapsUtils;
import org.json.JSONException;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.utils.Logger;
import org.json.JSONObject;

import java.util.Map;

public class NewSession extends SafeRequestHandler {
    private static final String CAPABILITIES_KEY = "capabilities";

    public NewSession(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        try {
            JSONObject w3cCaps = toJSON(request);
            Object capabilities = w3cCaps.opt(CAPABILITIES_KEY);
            if (!(capabilities instanceof JSONObject)) {
                throw new InvalidArgumentException(String.format(
                        "'%s' are mandatory for session creation", CAPABILITIES_KEY));
            }
            Map<String, Object> parsedCaps = W3CCapsUtils.parseCapabilities((JSONObject) capabilities);
            String sessionID = AppiumUIA2Driver.getInstance().initializeSession(parsedCaps);
            NotificationListener.getInstance().start();
            Logger.info(String.format("Created the new session with SessionID: %s",  sessionID));
            JSONObject result = new JSONObject();
            result.put("sessionId", sessionID);
            result.put("capabilities", capabilities);
            return new AppiumResponse(sessionID, result);
        } catch (Exception e) {
            throw new SessionNotCreatedException(e);
        }
    }
}
