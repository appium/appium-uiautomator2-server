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

import java.util.List;
import java.util.stream.Collectors;

import io.appium.uiautomator2.handler.request.NoSessionCommandHandler;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.PackageModel;

// Get a list of all applications (System and Installed) with packageActivity.
// It does not include applications wich has no launchable activities in the manifest.
public class GetPackages extends SafeRequestHandler implements NoSessionCommandHandler {
    public GetPackages(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        PackageManager manager = getApplicationContext().getPackageManager();
        List<ApplicationInfo> apps = manager.getInstalledApplications(manager.GET_META_DATA);
        List<PackageModel> appDetails = apps.stream()
                .filter(appInfo -> manager.getLaunchIntentForPackage(appInfo.packageName) != null)
                .map(appInfo -> new PackageModel(appInfo.packageName,
                        manager.getLaunchIntentForPackage(appInfo.packageName).getComponent().getClassName(),
                        charSequenceToNullableString(manager.getApplicationLabel(appInfo))))
                .collect(Collectors.toList());
        return new AppiumResponse(getSessionId(request) == null ? NO_ID : getSessionId(request), appDetails);
    }
}
