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
import android.bluetooth.BluetoothAdapter;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.model.api.DeviceInfoModel;
import io.appium.uiautomator2.model.api.network.BluetoothInfoModel;
import io.appium.uiautomator2.model.api.network.NetworkCapabilitiesModel;
import io.appium.uiautomator2.model.api.network.NetworkInfoModel;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.DeviceInfoHelper;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static io.appium.uiautomator2.utils.ReflectionUtils.getField;

public class GetDeviceInfo extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = getInstrumentation();

    public GetDeviceInfo(String mappedUri) {
        super(mappedUri);
    }

    @Nullable
    private static <T> T extractSafeValue(String fieldName, Object source) {
        try {
            //noinspection unchecked
            return (T) getField(fieldName, source);
        } catch (UiAutomator2Exception | ClassCastException ign) {
            return null;
        }
    }

    private static List<NetworkInfoModel> extractNetworkInfo(DeviceInfoHelper deviceInfoHelper) {
        List<NetworkInfoModel> result = new ArrayList<>();
        for (Network network : deviceInfoHelper.getNetworks()) {
            NetworkInfoModel resultItem = new NetworkInfoModel();
            NetworkInfo networkInfo = deviceInfoHelper.extractInfo(network);
            if (networkInfo != null) {
                resultItem.type = networkInfo.getType();
                resultItem.typeName = networkInfo.getTypeName();
                resultItem.subtype = networkInfo.getSubtype();
                resultItem.subtypeName = networkInfo.getSubtypeName();
                resultItem.isConnected = networkInfo.isConnected();
                resultItem.detailedState = networkInfo.getDetailedState().name();
                resultItem.state = networkInfo.getState().name();
                resultItem.extraInfo = networkInfo.getExtraInfo();
                resultItem.isAvailable = networkInfo.isAvailable();
                resultItem.isFailover = networkInfo.isFailover();
                resultItem.isRoaming = networkInfo.isRoaming();
            }

            NetworkCapabilities networkCaps = deviceInfoHelper.extractCapabilities(network);
            NetworkCapabilitiesModel caps = new NetworkCapabilitiesModel();
            if (networkCaps != null) {
                caps.transportTypes = DeviceInfoHelper.extractTransportTypes(networkCaps);
                caps.networkCapabilities = DeviceInfoHelper.extractCapNames(networkCaps);
                caps.linkUpstreamBandwidthKbps = networkCaps.getLinkUpstreamBandwidthKbps();
                caps.linkDownBandwidthKbps = networkCaps.getLinkDownstreamBandwidthKbps();
                caps.signalStrength = extractSafeValue("mSignalStrength", networkCaps);
                caps.SSID = extractSafeValue("mSSID", networkCaps);
            }
            resultItem.capabilities = networkCaps == null ? null : caps;

            if (networkInfo != null || networkCaps != null) {
                result.add(resultItem);
            }
        }
        return result;
    }

    @Nullable
    private static BluetoothInfoModel extractBluetoothInfo(DeviceInfoHelper deviceInfoHelper) {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        return bluetoothAdapter == null
                ? null
                : new BluetoothInfoModel(deviceInfoHelper.toBluetoothStateString(bluetoothAdapter.getState()));
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        DeviceInfoModel result = new DeviceInfoModel();
        final DeviceInfoHelper deviceInfoHelper = new DeviceInfoHelper(mInstrumentation.getTargetContext());
        result.androidId = deviceInfoHelper.getAndroidId();
        result.manufacturer = deviceInfoHelper.getManufacturer();
        result.model = deviceInfoHelper.getModelName();
        result.brand = deviceInfoHelper.getBrand();
        result.apiVersion = deviceInfoHelper.getApiVersion();
        result.platformVersion = deviceInfoHelper.getPlatformVersion();
        result.carrierName = deviceInfoHelper.getCarrierName();
        result.realDisplaySize = deviceInfoHelper.getRealDisplaySize();
        result.displayDensity = deviceInfoHelper.getDisplayDensity();
        result.networks = extractNetworkInfo(deviceInfoHelper);
        result.locale = deviceInfoHelper.getLocale();
        result.timeZone = deviceInfoHelper.getTimeZone();
        result.bluetooth = extractBluetoothInfo(deviceInfoHelper);

        return new AppiumResponse(getSessionId(request), result);
    }
}
