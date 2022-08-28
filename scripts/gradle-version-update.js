const fs = require('fs');
const path = require('path');
const semver = require('semver');
const log = require('fancy-log');

const VERSION_NAME_PATTERN = /^\s*versionName\s+['"](.+)['"]$/gm;
const VERSION_CODE_PATTERN = /^\s*versionCode\s+(.+)$/gm;

function parseArgValue (argName) {
  const argNamePattern = new RegExp(`^--${argName}\\b`);
  for (let i = 1; i < process.argv.length; ++i) {
    const arg = process.argv[i];
    if (argNamePattern.test(arg)) {
      return arg.includes('=') ? arg.split('=')[1] : process.argv[i + 1];
    }
  }
  return null;
}


async function gradleVersionUpdate() {
  const gradleFile = path.resolve(__dirname, '..', 'app', 'build.gradle');
  try {
    await fs.promises.access(gradleFile, fs.constants.W_OK);
  } catch (e) {
    throw new Error(`No '${gradleFile}' file found or it is not writeable`);
  }

  const version = parseArgValue('package-version');
  if (!version) {
    throw new Error('No package version argument (use `--package-version=xxx`)');
  }
  if (!semver.valid(version)) {
    throw new Error(
      `Invalid version specified '${version}'. Version should be in the form '1.2.3'`
    );
  }

  const gradleFilePayload = await fs.promises.readFile(gradleFile, 'utf8');
  const versionNameMatch = VERSION_NAME_PATTERN.exec(gradleFilePayload);
  if (!versionNameMatch) {
    throw new Error(`Cannot find the versionName field in '${gradleFile}'`);
  }
  // match will be like `versionName '1.2.3'`
  const newVersionName = versionNameMatch[0].replace(/\d+\.\d+\.\d+/, version);
  const versionCodeMatch = VERSION_CODE_PATTERN.exec(gradleFilePayload);
  if (!versionCodeMatch) {
    throw new Error(`Cannot find the versionCode field in '${gradleFile}'`);
  }
  // match will be like `versionCode 42`
  const newCode = parseInt(versionCodeMatch[1], 10) + 1;
  log.info(`Updating gradle build file '${gradleFile}' to version name '${version}' and version code '${newCode}'`);
  const newVersionCode = versionCodeMatch[0].replace(/\d+/, `${newCode}`);
  const newPayload = gradleFilePayload
    .replace(versionNameMatch[0], newVersionName)
    .replace(versionCodeMatch[0], newVersionCode);
  await fs.promises.writeFile(gradleFile, newPayload, 'utf8');
}

(async () => await gradleVersionUpdate())();
