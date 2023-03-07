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

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static io.appium.uiautomator2.model.Session.NO_ID;
import static io.appium.uiautomator2.utils.StringHelpers.charSequenceToNullableString;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.handler.request.NoSessionCommandHandler;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.touch.appium.PackageModel;


public class GetPackages extends SafeRequestHandler implements NoSessionCommandHandler {
    public GetPackages(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        List<PackageModel> appDetails = new ArrayList<>();
        PackageManager manager = getApplicationContext().getPackageManager();
        List<ApplicationInfo> apps = manager.getInstalledApplications(manager.GET_META_DATA);
        for (ApplicationInfo appInfo : apps) {
            // Filtering out unnecessary sub packages without Intent
            if (manager.getLaunchIntentForPackage(appInfo.packageName) != null) {
                appDetails.add(new PackageModel(appInfo.packageName,
                        manager.getLaunchIntentForPackage(appInfo.packageName).getComponent().getClassName(),
                        charSequenceToNullableString(manager.getApplicationLabel(appInfo))));
            }
        }
        return new AppiumResponse(NO_ID, appDetails);
    }
}
