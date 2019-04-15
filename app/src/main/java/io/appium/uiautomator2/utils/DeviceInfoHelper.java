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

package io.appium.uiautomator2.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.core.UiAutomatorBridge;


public class DeviceInfoHelper {
    private final Context context;
    private final ConnectivityManager connManager;

    public DeviceInfoHelper(Context context) {
        this.context = context;
        this.connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    /**
     * A unique serial number identifying a device, if a device has multiple users,  each user appears as a
     * completely separate device, so the ANDROID_ID value is unique to each user.
     * See https://developer.android.com/reference/android/provider/Settings.Secure.html#ANDROID_ID
     * for more info.
     *
     * @return ANDROID_ID A 64-bit number (as a hex string) that is uniquely generated when the user
     * first sets up the device and should remain constant for the lifetime of the user's device. The value
     * may change if a factory reset is performed on the device.
     */
    @SuppressLint("HardwareIds")
    public String getAndroidId() {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * @return Build.MANUFACTURER value
     */
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * @return Build.MODEL value
     */
    public String getModelName() {
        return Build.MODEL;
    }

    /**
     * @return Build.BRAND value
     */
    public String getBrand() {
        return Build.BRAND;
    }

    /**
     * Current running OS's API VERSION
     * @return the os version as String
     */
    public String getApiVersion() {
        return Integer.toString(Build.VERSION.SDK_INT);
    }

    /**
     * @return The current version string, for example "1.0" or "3.4b5"
     */
    public String getPlatformVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * @return The logical density of the display in Density Independent Pixel units.
     */
    public int getDisplayDensity() {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getRealMetrics(metrics);
        return (int)(metrics.density * 160);
    }

    /**
     * Retrievs the name of the current celluar network carrier
     *
     * @return carrier name or null if it cannot be retrieved
     */
    @Nullable
    public String getCarrierName() {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        try {
            return telephonyManager == null ? null : telephonyManager.getNetworkOperatorName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the capabilities of the given network
     *
     * @param net android network object
     * @return Capabilities info
     */
    @Nullable
    public NetworkCapabilities extractCapabilities(Network net) {
        if (connManager == null) {
            return null;
        }
        return connManager.getNetworkCapabilities(net);
    }

    /**
     * Retrieves the information about the given network
     *
     * @param net android network object
     * @return Network info
     */
    @Nullable
    public NetworkInfo extractInfo(Network net) {
        if (connManager == null) {
            return null;
        }
        return connManager.getNetworkInfo(net);
    }

    /**
     * Retrieves available networks
     *
     * @return The list of network items
     */
    public List<Network> getNetworks() {
        if (connManager == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(Arrays.asList(connManager.getAllNetworks()));
    }

    /**
     * Retrieves the real size of the default display
     *
     * @return The display size in 'WxH' format
     */
    public String getRealDisplaySize() {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        android.graphics.Point p = new android.graphics.Point();
        display.getRealSize(p);
        return String.format("%sx%s", p.x, p.y);
    }
}
