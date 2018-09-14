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

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;

import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.DeviceInfoHelper;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.JSONUtils.formatNull;

public class GetDeviceInfo extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = InstrumentationRegistry.getInstrumentation();

    public GetDeviceInfo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws JSONException {
        Logger.info("Get Device Info command");
        final JSONObject response = new JSONObject();
        final DeviceInfoHelper deviceInfoHelper = new DeviceInfoHelper(mInstrumentation
                .getTargetContext());
        response.put("androidId", deviceInfoHelper.getAndroidId());
        response.put("manufacturer", deviceInfoHelper.getManufacturer());
        response.put("model", deviceInfoHelper.getModelName());
        response.put("brand", deviceInfoHelper.getBrand());
        response.put("apiVersion", deviceInfoHelper.getApiVersion());
        response.put("carrierName", formatNull(deviceInfoHelper.getCarrierName()));
        response.put("realDisplaySize", deviceInfoHelper.getRealDisplaySize());

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, response);
    }
}
