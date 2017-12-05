
[![Build Status](https://team-appium.ci.cloudbees.com/view/All/job/appium-uiautomator2-server-e2e/badge/icon)](https://team-appium.ci.cloudbees.com/view/All/job/appium-uiautomator2-server-e2e/)
### appium-uiautomator2-server

A netty server that runs on the device listening for commands and executes using UiAutomator V2.

### building project
build the android project using below commands 

`gradle clean assembleServerDebug assembleServerDebugAndroidTest`


### Starting server
push both src and test apks to the device and execute the instrumentation tests.

`adb shell am instrument -w io.appium.uiautomator2.server.test/android.support.test.runner.AndroidJUnitRunner`



### run unitTest
build the unitTest flavor using the below commands 

`gradle clean assembleE2ETestDebug assembleE2ETestDebugAndroidTest`


unitTest flavor contains tests for handlers and can be invoked by using following command 

`gradle clean connectedE2ETestDebugAndroidTest`

the above command takes care about installing the AUT apk in to the testing device/emulator before running the tests.


you can also invoke the test using below command

`adb shell am instrument -w io.appium.uiautomator2.e2etest.test/android.support.test.runner.AndroidJUnitRunner`

Note: AUT apk should be installed before executing above command.


### Other Sections:
* [WIKI](https://github.com/appium/appium-uiautomator2-server/wiki)
* [Version Release](https://github.com/appium/appium-uiautomator2-server/blob/master/doc/release.md)
