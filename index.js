const path = require('path');
const version = require('./package.json').version;

const SERVER_APK_PATH = path.resolve(__dirname, 'apks', `appium-uiautomator2-server-v${version}.apk`);
const TEST_APK_PATH = path.resolve(__dirname, 'apks', 'appium-uiautomator2-server-debug-androidTest.apk');


module.exports = {
  SERVER_APK_PATH,
  TEST_APK_PATH,
  version,
};
