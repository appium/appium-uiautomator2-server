package io.appium.uiautomator2.handler;


import static io.appium.uiautomator2.model.Session.NO_ID;
import static io.appium.uiautomator2.server.ServerInstrumentation.ServerContext;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import io.appium.uiautomator2.handler.request.NoSessionCommandHandler;
import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.api.BatteryStatusModel;
import io.appium.uiautomator2.model.api.StatusModel;
import io.appium.uiautomator2.model.api.touch.PackageModel;
import io.appium.uiautomator2.server.ServerInstrumentation;
import io.appium.uiautomator2.utils.BatteryHelper;

public class Getpackages extends SafeRequestHandler implements NoSessionCommandHandler {
    public Getpackages(String mappedUri) {
        super(mappedUri);
    }
    public static List<PackageModel> appDetails = new ArrayList<PackageModel>();
    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) {
        try{
            List<ApplicationInfo> apps = ServerContext.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
            for(ApplicationInfo appInfo: apps) {
                PackageModel model = new PackageModel();
                if(ServerContext.getPackageManager().getLaunchIntentForPackage(appInfo.packageName)!=null) {
                    model.setPackageName(appInfo.packageName);
                    model.setAppName((String) ServerContext.getPackageManager().getApplicationLabel(appInfo));
                    model.setPackageActivity(ServerContext.getPackageManager().getLaunchIntentForPackage(appInfo.packageName).getComponent().getClassName());
                    appDetails.add(model);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new AppiumResponse(NO_ID, appDetails);
    }
}
