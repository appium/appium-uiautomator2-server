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

import org.json.JSONException;

import java.util.Map;
import java.util.Map.Entry;

import io.appium.uiautomator2.common.exceptions.UnsupportedSettingException;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.AppiumUIA2Driver;
import io.appium.uiautomator2.model.Session;
import io.appium.uiautomator2.model.settings.ISetting;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.Logger;

public class UpdateSettings extends SafeRequestHandler {

    public UpdateSettings(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Session session = AppiumUIA2Driver.getInstance().getSessionOrThrow();
        Map<String, Object> settings = getPayload(request, "settings");
        Logger.debug("Update settings: " + settings.toString());
        for (Entry<String, Object> entry : settings.entrySet()) {
            String settingName = entry.getKey();
            Object settingValue = entry.getValue();
            ISetting setting = getSetting(settingName);
            //noinspection unchecked
            setting.update(settingValue);
            session.setCapability(settingName, settingValue);
        }
        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, true);
    }

    public ISetting getSetting(String settingName) throws UnsupportedSettingException {
        for (final Settings value : Settings.values()) {
            if (value.toString().equals(settingName)) {
                return value.getSetting();
            }
        }
        throw new UnsupportedSettingException(settingName, Settings.names());
    }
}
