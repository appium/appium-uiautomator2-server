#!/bin/bash -xe

adb shell settings put global hidden_api_policy  1
adb shell settings put global hidden_api_policy_pre_p_apps  1
adb shell settings put global hidden_api_policy_p_apps 1

classes=(AlertCommandsTest ActionsCommandsTest ElementCommandsTest DeviceCommandsTest)
did_fail=0
for cls_name in "${classes[@]}"; do
  if ! ./gradlew connectedE2eTestDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=io.appium.uiautomator2.unittest.test.$cls_name; then
    did_fail=1
  fi
done

if [[ did_fail -eq 1 ]]; then
  exit 1
fi
