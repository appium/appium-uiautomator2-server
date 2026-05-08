import path from 'node:path';
import {createRequire} from 'node:module';
import {fileURLToPath} from 'node:url';

const require = createRequire(import.meta.url);
export const version = require('./package.json').version;

const __dirname = path.dirname(fileURLToPath(import.meta.url));
export const SERVER_APK_PATH = path.resolve(__dirname, 'apks', `appium-uiautomator2-server-v${version}.apk`);
export const TEST_APK_PATH = path.resolve(__dirname, 'apks', 'appium-uiautomator2-server-debug-androidTest.apk');
