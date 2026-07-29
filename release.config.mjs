import releaseConfig from '@appium/semantic-release-config';

export default releaseConfig({
  githubAssets: [
    {
      path: 'apks/*.apk',
      label: 'Server Apps',
    },
  ],
});
