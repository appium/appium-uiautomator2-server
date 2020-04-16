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

import io.appium.uiautomator2.model.api.BatteryStatusModel;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.utils.BatteryHelper;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

public class GetBatteryInfo extends SafeRequestHandler {
    private final Instrumentation mInstrumentation = getInstrumentation();

    public GetBatteryInfo(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        final BatteryHelper batteryHelper = new BatteryHelper(mInstrumentation
                .getTargetContext());
        return new AppiumResponse(getSessionId(request), new BatteryStatusModel(
                batteryHelper.getLevel(),
                batteryHelper.getStatus()
        ));
    }
}
