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
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

public class BatteryHelper {
    private final Context context;

    public BatteryHelper(Context context) {
        this.context = context;
    }

    private Intent getBatteryStatusIntent() {
        final Intent result = context.registerReceiver(null,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        if (result == null) {
            throw new BatteryError("Cannot retrieve battery information from the system");
        }
        return result;
    }

    /**
     * @return Current battery level as double value in range [0.0. 1.0],
     * where 1.0 means 100% charge.
     * -1 is returned if the actual value cannot be retrieved from the system.
     */
    public double getLevel() {
        final Intent batteryStatus = getBatteryStatusIntent();
        final int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        final int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        return level < 0 ? -1 : level * 1.0 / scale;
    }

    /**
     * @return One of possible battery status constants:
     * - BATTERY_STATUS_UNKNOWN = 1
     * - BATTERY_STATUS_CHARGING = 2
     * - BATTERY_STATUS_DISCHARGING = 3
     * - BATTERY_STATUS_NOT_CHARGING = 4
     * - BATTERY_STATUS_FULL = 5
     * -1 is returned if the actual value cannot be retrieved from the system.
     */
    public int getStatus() {
        return getBatteryStatusIntent().getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    }

    public static class BatteryError extends RuntimeException {
        BatteryError(String message) {
            super(message);
        }
    }
}
