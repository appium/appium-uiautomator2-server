'use strict';

const gulp = require('gulp');
const boilerplate = require('@appium/gulp-plugins').boilerplate.use(gulp);
const DEFAULTS = require('@appium/gulp-plugins').boilerplate.DEFAULTS;
const { androidHelpers } = require('appium-android-driver');
const { fs } = require('@appium/support');
const path = require('path');
const B = require('bluebird');


boilerplate({
  build: 'appium-uiautomator2-server',
  files: DEFAULTS.files.concat('index.js'),
  transpile: false,
});

gulp.task('sign-apk', async function signApks () {
  // Signs the APK with the default Appium Certificate
  const adb = await androidHelpers.createADB({});
  const apksToSign = await fs.glob('*.apk', {
    cwd: path.resolve('apks'),
    absolute: true,
  });
  return B.all(apksToSign.map((apk) => adb.sign(apk)));
});
