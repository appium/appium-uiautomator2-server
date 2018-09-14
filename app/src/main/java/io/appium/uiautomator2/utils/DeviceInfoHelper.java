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
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.view.Display;

import io.appium.uiautomator2.core.UiAutomatorBridge;


public class DeviceInfoHelper {
    private final Context context;

    public DeviceInfoHelper(Context context) {
        this.context = context;
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
     * */
    @SuppressLint("HardwareIds")
    public String getAndroidId() {
        return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    /**
     * @return Build.MANUFACTURER value
     * */
    public String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * @return Build.MODEL value
     * */
    public String getModelName() {
        return Build.MODEL;
    }

    /**
     * @return Build.BRAND value
     * */
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
