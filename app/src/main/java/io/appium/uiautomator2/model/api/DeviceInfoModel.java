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

package io.appium.uiautomator2.model.api;

import io.appium.uiautomator2.model.api.network.BluetoothInfoModel;
import io.appium.uiautomator2.model.api.network.NetworkInfoModel;

import java.util.List;

public class DeviceInfoModel extends BaseModel {
    public String androidId;
    public String manufacturer;
    public String model;
    public String brand;
    public String apiVersion;
    public String platformVersion;
    public String carrierName;
    public String realDisplaySize;
    public Integer displayDensity;
    public List<NetworkInfoModel> networks;
    public String locale;
    public String timeZone;
    public BluetoothInfoModel bluetooth;

    public DeviceInfoModel() {}
}
