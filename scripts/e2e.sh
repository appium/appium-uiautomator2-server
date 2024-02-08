#!/bin/bash -xe

classes=(AlertCommandsTest ActionsCommandsTest ElementCommandsTest DeviceCommandsTest)
did_fail=0
for cls_name in "${classes[@]}"; do
  if ! ./gradlew connectedE2eTestDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=io.appium.uiautomator2.unittest.test.$cls_name -Pandroid.testInstrumentationRunnerArguments.notAnnotation=io.appium.uiautomator2.unittest.test.internal.SkipHeadlessDevices; then
    did_fail=1
  fi
done

if [[ did_fail -eq 1 ]]; then
  exit 1
fi
