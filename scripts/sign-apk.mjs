import {fs} from '@appium/support';
import path from 'node:path';
import {fileURLToPath} from 'node:url';
import {ADB} from 'appium-adb';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

/**
 * Signs the APKs with the default Appium Certificate
 */
async function signApks () {
  const adb = new ADB();
  const apksRoot = path.resolve(__dirname, '..', 'apks');
  const apks = (await fs.readdir(apksRoot))
    .filter((name) => path.extname(name) === '.apk');
  if (!apks.length) {
    throw new Error(`There are no .apk files available for signing in '${apksRoot}'`);
  }
  await Promise.all(apks.map((name) => adb.sign(path.join(apksRoot, name))));
}

await signApks();
