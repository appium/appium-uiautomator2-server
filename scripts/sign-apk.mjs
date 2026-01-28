import fs from 'node:fs';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
import {ADB} from 'appium-adb';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);


async function signApks () {
  // Signs the APK with the default Appium Certificate
  const adb = new ADB();
  const apksRoot = path.resolve(__dirname, '..', 'apks');
  const apks = (await fs.promises.readdir(apksRoot))
    .filter((name) => path.extname(name) === '.apk');
  if (!apks.length) {
    throw new Error(`There are no .apk files available for signing in '${apksRoot}'`);
  }
  await Promise.all(apks.map((name) => adb.sign(path.join(apksRoot, name))));
}

(async () => await signApks())();

