'use strict';

const gulp = require('gulp');
const boilerplate = require('appium-gulp-plugins').boilerplate.use(gulp);
const { androidHelpers } = require('appium-android-driver');
const path = require('path');
const { version } = require('./package.json');


boilerplate({
  build: 'appium-uiautomator2-server',
  transpile: false,
});

gulp.task('sign-apk', async function signApks () {
  // Signs the APK with the default Appium Certificate
  const adb = await androidHelpers.createADB({});
  const pathToApk = path.resolve('apks', `appium-uiautomator2-server-v${version}.apk`);
  return adb.sign(pathToApk);
});
