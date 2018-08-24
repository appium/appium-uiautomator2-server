package io.appium.uiautomator2.handler;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;

import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.server.WDStatus;

import static android.net.wifi.WifiManager.WIFI_STATE_DISABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_DISABLING;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLED;
import static android.net.wifi.WifiManager.WIFI_STATE_ENABLING;

public class WifiHandler {

    private static WifiManager wfm;


    public static AppiumResponse toggle(final boolean setTo, final String sessionId) {
        wfm = (WifiManager) InstrumentationRegistry
                .getInstrumentation().getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        boolean status = wfm.setWifiEnabled(setTo);
        if (!status) {
            String errorMsg = "Unable to " + (setTo ? "ENABLE" : "DISABLE") + "WIFI";
            return new AppiumResponse(sessionId, WDStatus.UNKNOWN_ERROR, errorMsg);
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
            return new AppiumResponse(sessionId, WDStatus.TIMEOUT
                    , String.format("Changing WIFI State not completed in %s ms", TIMEOUT));
        }
        int response = (wifiState == WIFI_STATE_ENABLED) ? 1 : 0;
        return new AppiumResponse(sessionId, WDStatus.SUCCESS, response);

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
