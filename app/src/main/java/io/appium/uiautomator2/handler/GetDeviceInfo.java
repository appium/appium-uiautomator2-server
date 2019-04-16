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
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.server.WDStatus;
import io.appium.uiautomator2.utils.DeviceInfoHelper;
import io.appium.uiautomator2.utils.Logger;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static io.appium.uiautomator2.utils.JSONUtils.formatNull;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class GetDeviceInfo extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = getInstrumentation();

    public GetDeviceInfo(String mappedUri) {
        super(mappedUri);
    }

    private static Object extractSafeJSONValue(String fieldName, Object source) {
        try {
            return formatNull(getField(fieldName, source));
        } catch (UiAutomator2Exception ign) {
            return JSONObject.NULL;
        }
    }

    private static JSONArray extractNetworkInfo(DeviceInfoHelper deviceInfoHelper) throws JSONException {
        JSONArray result = new JSONArray();
        for (Network network : deviceInfoHelper.getNetworks()) {
            JSONObject resultItem = new JSONObject();
            NetworkInfo networkInfo = deviceInfoHelper.extractInfo(network);
            if (networkInfo != null) {
                resultItem.put("type", networkInfo.getType());
                resultItem.put("typeName", networkInfo.getTypeName());
                resultItem.put("subtype", networkInfo.getSubtype());
                resultItem.put("subtypeName", networkInfo.getSubtypeName());
                resultItem.put("isConnected", networkInfo.isConnected());
                resultItem.put("connectionState", networkInfo.getDetailedState().ordinal());
                resultItem.put("extraInfo", formatNull(networkInfo.getExtraInfo()));
                resultItem.put("isAvailable", networkInfo.isAvailable());
                resultItem.put("isFailover", networkInfo.isFailover());
                resultItem.put("isRoaming", networkInfo.isRoaming());
            }

            NetworkCapabilities networkCaps = deviceInfoHelper.extractCapabilities(network);
            JSONObject caps = new JSONObject();
            if (networkCaps != null) {
                caps.put("transportTypes", DeviceInfoHelper.extractTransportTypes(networkCaps));
                caps.put("networkCapabilities", DeviceInfoHelper.extractCapNames(networkCaps));
                caps.put("linkUpstreamBandwidthKbps", networkCaps.getLinkUpstreamBandwidthKbps());
                caps.put("linkDownBandwidthKbps", networkCaps.getLinkDownstreamBandwidthKbps());
                caps.put("signalStrength",
                        extractSafeJSONValue("mSignalStrength", networkCaps));
                caps.put("networkSpecifier",
                        extractSafeJSONValue("mNetworkSpecifier", networkCaps));
                caps.put("SSID", extractSafeJSONValue("mSSID", networkCaps));
            }
            resultItem.put("capabilities", formatNull(networkCaps == null ? null : caps));

            if (resultItem.length() > 0) {
                result.put(resultItem);
            }
        }
        return result;
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
        response.put("platformVersion", deviceInfoHelper.getPlatformVersion());
        response.put("carrierName", formatNull(deviceInfoHelper.getCarrierName()));
        response.put("realDisplaySize", deviceInfoHelper.getRealDisplaySize());
        response.put("displayDensity", deviceInfoHelper.getDisplayDensity());
        response.put("networks", extractNetworkInfo(deviceInfoHelper));

        return new AppiumResponse(getSessionId(request), WDStatus.SUCCESS, response);
    }
}
