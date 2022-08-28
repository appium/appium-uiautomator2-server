const fs = require('fs');
const path = require('path');
const { ADB } = require('appium-adb');
const B = require('bluebird');


async function signApks () {
  // Signs the APK with the default Appium Certificate
  const adb = new ADB();
  const apksRoot = path.resolve(__dirname, '..', 'apks');
  const apks = (await fs.promises.readdir(apksRoot))
    .filter((name) => path.extname(name) === '.apk');
  if (!apks.length) {
    throw new Error(`There are no .apk files available for signing in '${apksRoot}'`);
  }
  await B.all(apks.map((name) => adb.sign(path.join(apksRoot, name))));
}

(async () => await signApks())();
