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

import io.appium.uiautomator2.handler.request.BaseRequestHandler;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.powermock.modules.junit4.PowerMockRunner;

import io.appium.uiautomator2.http.IHttpRequest;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
public class GetSettingsTests {

    @Spy
    private final GetSettings getSettings = new GetSettings("my_uri");

    @Mock
    private IHttpRequest req;

    @Test
    public void shouldBeAbleToUpdateSetting() throws JSONException {
        JSONObject getSessionPayload = BaseRequestHandler.toJSON(req);

        assertEquals(JSONObject.class, getSessionPayload.getClass());
    }
}
