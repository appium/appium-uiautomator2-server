#!/usr/bin/env node

const replace = require('replace-in-file');
const path = require('path');
const argv = require('yargs').argv


const GRADLE_BUILD_FILE = path.resolve(__dirname, 'app', 'build.gradle');

// specify the package version to update to using the `--package-version=XXX` flag
const version = argv['package-version'];

if (!version) {
  console.error('No updated version specified');
  process.exit(1);
}
if (!/\d+\.\d+\.\d+/.test(version)) {
  console.error(`Invalid version specified '${version}'`);
  console.error(`Version should be in the form '1.2.3'`);
  process.exit(2);
}

console.log(`Updating gradle build file to version '${version}'`);
replace({
  files: GRADLE_BUILD_FILE,
  from: /^\s+versionName\s"(.+)"$/gm,
  to: (match, ...args) => {
    // match will be like `versionName "1.2.3"`
    return match.replace(/\d+\.\d+\.\d/, version);
  },
}).then((changes) => {
  console.log(`Updated files: ${changes.join(', ')}`);
}).catch((err) => {
  console.error(`Unable to bump version: ${err.message}`);
  process.exit(3);
});