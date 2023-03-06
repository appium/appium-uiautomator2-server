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

import static io.appium.uiautomator2.model.Session.NO_ID;
import static io.appium.uiautomator2.server.ServerInstrumentation.ServerContext;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;
import io.appium.uiautomator2.handler.request.NoSessionCommandHandler;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.touch.appium.PackageModel;
import io.appium.uiautomator2.utils.Logger;

public class GetPackages extends SafeRequestHandler implements NoSessionCommandHandler {
    public GetPackages(String mappedUri) {
        super(mappedUri);
    }
    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        List<PackageModel> appDetails = new ArrayList<PackageModel>();
        try{
            PackageManager manager = ServerContext.getPackageManager();
            List<ApplicationInfo> apps = manager.getInstalledApplications(manager.GET_META_DATA);
            for(ApplicationInfo appInfo: apps) {
                PackageModel model = new PackageModel();
                if(manager.getLaunchIntentForPackage(appInfo.packageName)!=null) {
                    model.setPackageName(appInfo.packageName);
                    model.setAppName((String) manager.getApplicationLabel(appInfo));
                    model.setPackageActivity(manager.getLaunchIntentForPackage(appInfo.packageName).getComponent().getClassName());
                    appDetails.add(model);
                }
            }

        }catch (RuntimeException e){
            Logger.error("Unexpected Runtime Exception: ", e);
        }
        return new AppiumResponse(NO_ID, appDetails);
    }
}
