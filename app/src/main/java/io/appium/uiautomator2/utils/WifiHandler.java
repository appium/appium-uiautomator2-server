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

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.SystemClock;

import io.appium.uiautomator2.common.exceptions.InvalidElementStateException;
import io.appium.uiautomator2.http.AppiumResponse;

import static android.net.wifi.WifiManager.WIFI_STATE_DISABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLING;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

public class WifiHandler {

    private static WifiManager wfm;

    public static AppiumResponse toggle(final boolean setTo, final String sessionId) {
        wfm = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean status = wfm.setWifiEnabled(setTo);
        if (!status) {
            String errorMsg = "Unable to " + (setTo ? "ENABLE" : "DISABLE") + "WIFI";
            throw new InvalidElementStateException(errorMsg);
        }
        int wifiState = wfm.getWifiState();
        // If the WIFI state change is in progress,
        // wait until the TIMEOUT has expired
        final int TIMEOUT = 2000;
        final long then = System.currentTimeMillis();
        long now = then;
        while (isInProgress() || !isSuccessful(setTo) && now - then < TIMEOUT) {
            //WIFI State change is in progress, wait for completion
            SystemClock.sleep(100);
            now = System.currentTimeMillis();
            wifiState = wfm.getWifiState();
        }
        if (isInProgress() || !isSuccessful(setTo)) {
            throw new InvalidElementStateException(
                    String.format("Changing WIFI State not completed in %s ms", TIMEOUT));
        }
        int response = (wifiState == WIFI_STATE_ENABLED) ? 1 : 0;
        return new AppiumResponse(sessionId, response);

    }

    private static boolean isInProgress() {
        return wfm.getWifiState() == WIFI_STATE_DISABLING || wfm.getWifiState() == WIFI_STATE_ENABLING;
    }

    private static boolean isSuccessful(boolean desired) {
        if (isInProgress()) {
            return false;
        }
        return desired
                ? wfm.getWifiState() == WIFI_STATE_ENABLED
                : wfm.getWifiState() == WIFI_STATE_DISABLED;
    }
}
