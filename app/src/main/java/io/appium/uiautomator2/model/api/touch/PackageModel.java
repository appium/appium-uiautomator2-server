package io.appium.uiautomator2.model.api.touch;

public class PackageModel {
    String PackageName;
    String PackageActivity;
    String AppName;

    public String getAppName() {
        return AppName;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getPackageActivity() {
        return PackageActivity;
    }

    public void setPackageActivity(String packageActivity) {
        PackageActivity = packageActivity;
    }
}
