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

import java.util.Collections;
import java.util.Map;

import io.appium.uiautomator2.handler.request.NoSessionCommandHandler;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.api.SessionModel;

import static io.appium.uiautomator2.model.Session.NO_ID;

public class GetSessions extends SafeRequestHandler implements NoSessionCommandHandler {

    public GetSessions(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        Session session = AppiumUIA2Driver.getInstance().getSession();
        if (session == null || session.getSessionId() == null) {
            return new AppiumResponse(NO_ID, Collections.emptyList());
        }

        ResponseModel model = new ResponseModel(session.getSessionId(), session.getCapabilities());
        return new AppiumResponse(NO_ID, Collections.singletonList(model));
    }

    private static class ResponseModel extends SessionModel {
        // https://webdriver.io/docs/api/jsonwp/#getsessions
        public String id;

        ResponseModel(String sessionId, Map<String, Object> capabilities) {
            super(sessionId, capabilities);
            this.id = sessionId;
        }
    }
}
