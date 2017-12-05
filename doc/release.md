**Publishing:**

Appium UIAutomator2 server and driver modules published as [appium-uiautomator2-server](https://www.npmjs.com/package/appium-uiautomator2-server) and [appium-uiautomator2-driver](https://www.npmjs.com/package/appium-uiautomator2-driver).


[`appium-uiautomator2-driver`](https://github.com/appium/appium-uiautomator2-driver/blob/master/lib/installer.js#L6) depends on release version for downloading and installing of appium-uiautomator2-server apks, gradle [`versionName`](https://github.com/appium/appium-uiautomator2-server/blob/master/app/build.gradle#L33) should always be inline with npm [`version`](https://github.com/appium/appium-uiautomator2-server/blob/master/package.json#L3).

**Server Release:**

* appium-uiautomator2-server-v**x.x.x**.apk and appium-uiautomator2-server-debug-androidTest.apk needs to be attached for every release and **x.x.x**<apk version> should always be inline with github release version.

* `gradle clean assembleServerDebug assembleServerDebugAndroidTest` will generate appium-uiautomator2-server-vx.x.x.apk and appium-uiautomator2-server-debug-androidTest.apk in `/app/build/outputs`.
* Upload APK files to [release](https://github.com/appium/appium-uiautomator2-server/releases)

**Update Server apk version and SHAs in UiAutomator2 driver:**

Once after you do the server release, you also need to specify the server apks SHA-512 hash and version in [UiAutomator2-Driver](https://github.com/appium/appium-uiautomator2-driver/blob/master/lib/installer.js#L10)
``` java
Example:
const SERVER_DOWNLOAD_SHA512 = "server_apk_hash";

const SERVER_TEST_DOWNLOAD_SHA512 = "androidTest_apk_hash"
```

Note:
* You could use online file hash calculators like [md5file.com](https://md5file.com/calculator) to find SHA-512



