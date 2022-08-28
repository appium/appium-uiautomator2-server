const glob = require('glob');
const path = require('path');
const ADB = require('appium-adb');
const B = require('bluebird');


async function signApks () {
  // Signs the APK with the default Appium Certificate
  const adb = new ADB();
  const apksToSign = await glob('*.apk', {
    cwd: path.resolve('apks'),
    absolute: true,
  });
  if (apksToSign.length) {
    await B.all(apksToSign.map((apk) => adb.sign(apk)));
  }
}

(async () => await signApks())();
