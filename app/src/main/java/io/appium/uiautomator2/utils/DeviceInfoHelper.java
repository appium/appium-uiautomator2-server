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
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.core.UiAutomatorBridge;

import static android.net.NetworkCapabilities.NET_CAPABILITY_CAPTIVE_PORTAL;
import static android.net.NetworkCapabilities.NET_CAPABILITY_CBS;
import static android.net.NetworkCapabilities.NET_CAPABILITY_DUN;
import static android.net.NetworkCapabilities.NET_CAPABILITY_EIMS;
import static android.net.NetworkCapabilities.NET_CAPABILITY_FOREGROUND;
import static android.net.NetworkCapabilities.NET_CAPABILITY_FOTA;
import static android.net.NetworkCapabilities.NET_CAPABILITY_IA;
import static android.net.NetworkCapabilities.NET_CAPABILITY_IMS;
import static android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET;
import static android.net.NetworkCapabilities.NET_CAPABILITY_MMS;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_CONGESTED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_METERED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_RESTRICTED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_ROAMING;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_SUSPENDED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_NOT_VPN;
import static android.net.NetworkCapabilities.NET_CAPABILITY_RCS;
import static android.net.NetworkCapabilities.NET_CAPABILITY_SUPL;
import static android.net.NetworkCapabilities.NET_CAPABILITY_TRUSTED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_VALIDATED;
import static android.net.NetworkCapabilities.NET_CAPABILITY_WIFI_P2P;
import static android.net.NetworkCapabilities.NET_CAPABILITY_XCAP;
import static android.net.NetworkCapabilities.TRANSPORT_BLUETOOTH;
import static android.net.NetworkCapabilities.TRANSPORT_CELLULAR;
import static android.net.NetworkCapabilities.TRANSPORT_ETHERNET;
import static android.net.NetworkCapabilities.TRANSPORT_LOWPAN;
import static android.net.NetworkCapabilities.TRANSPORT_VPN;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI;
import static android.net.NetworkCapabilities.TRANSPORT_WIFI_AWARE;


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

    @SuppressLint("UseSparseArrays")
    public static final Map<Integer, String> TRANSPORTS = new HashMap<>();
    static {
        TRANSPORTS.put(TRANSPORT_CELLULAR, "TRANSPORT_CELLULAR");
        TRANSPORTS.put(TRANSPORT_WIFI, "TRANSPORT_WIFI");
        TRANSPORTS.put(TRANSPORT_BLUETOOTH, "TRANSPORT_BLUETOOTH");
        TRANSPORTS.put(TRANSPORT_ETHERNET, "TRANSPORT_ETHERNET");
        TRANSPORTS.put(TRANSPORT_VPN, "TRANSPORT_VPN");
        TRANSPORTS.put(6, "TRANSPORT_LOWPAN");
    }

    public static String extractTransportTypes(NetworkCapabilities caps) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry: TRANSPORTS.entrySet()) {
            if (caps.hasTransport(entry.getKey())) {
                result.add(entry.getValue());
            }
        }
        return TextUtils.join(",", result);
    }

    @SuppressLint("UseSparseArrays")
    public static final Map<Integer, String> CAPS = new HashMap<>();
    static {
        TRANSPORTS.put(NET_CAPABILITY_MMS, "NET_CAPABILITY_MMS");
        TRANSPORTS.put(NET_CAPABILITY_SUPL, "NET_CAPABILITY_SUPL");
        TRANSPORTS.put(NET_CAPABILITY_DUN, "NET_CAPABILITY_DUN");
        TRANSPORTS.put(NET_CAPABILITY_FOTA, "NET_CAPABILITY_FOTA");
        TRANSPORTS.put(NET_CAPABILITY_IMS, "NET_CAPABILITY_IMS");
        TRANSPORTS.put(NET_CAPABILITY_CBS, "NET_CAPABILITY_CBS");
        TRANSPORTS.put(NET_CAPABILITY_WIFI_P2P, "NET_CAPABILITY_WIFI_P2P");
        TRANSPORTS.put(NET_CAPABILITY_IA, "NET_CAPABILITY_IA");
        TRANSPORTS.put(NET_CAPABILITY_RCS, "NET_CAPABILITY_RCS");
        TRANSPORTS.put(NET_CAPABILITY_XCAP, "NET_CAPABILITY_XCAP");
        TRANSPORTS.put(NET_CAPABILITY_EIMS, "NET_CAPABILITY_EIMS");
        TRANSPORTS.put(NET_CAPABILITY_NOT_METERED, "NET_CAPABILITY_NOT_METERED");
        TRANSPORTS.put(NET_CAPABILITY_INTERNET, "NET_CAPABILITY_INTERNET");
        TRANSPORTS.put(NET_CAPABILITY_NOT_RESTRICTED, "NET_CAPABILITY_NOT_RESTRICTED");
        TRANSPORTS.put(NET_CAPABILITY_TRUSTED, "NET_CAPABILITY_TRUSTED");
        TRANSPORTS.put(NET_CAPABILITY_NOT_VPN, "NET_CAPABILITY_NOT_VPN");
        TRANSPORTS.put(16, "NET_CAPABILITY_VALIDATED");
        TRANSPORTS.put(17, "NET_CAPABILITY_CAPTIVE_PORTAL");
        TRANSPORTS.put(18, "NET_CAPABILITY_NOT_ROAMING");
        TRANSPORTS.put(19, "NET_CAPABILITY_FOREGROUND");
        TRANSPORTS.put(20, "NET_CAPABILITY_NOT_CONGESTED");
        TRANSPORTS.put(21, "NET_CAPABILITY_NOT_SUSPENDED");
        TRANSPORTS.put(22, "NET_CAPABILITY_OEM_PAID");
    }

    public static String extractCapNames(NetworkCapabilities caps) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, String> entry: CAPS.entrySet()) {
            if (caps.hasCapability(entry.getKey())) {
                result.add(entry.getValue());
            }
        }
        return TextUtils.join(",", result);
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
