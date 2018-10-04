import { androidHelpers } from 'appium-android-driver';
import { asyncify } from 'asyncbox';
import path from 'path';
import packageJson from '../../../package.json';

async function main () {
  // Signs the APK with the default Appium Certificate
  const adb = await androidHelpers.createADB({});
  const pathToApk = path.resolve('apks', `appium-uiautomator2-server-v${packageJson.version}.apk`);
  await adb.sign(pathToApk);
}

asyncify(main);