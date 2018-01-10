**Publishing:**

Publishing is a matter of running the [npm version command](https://docs.npmjs.com/cli/version):
```shell
npm version major|minor|patch
```
which will

1. Update the appropriate npm version and tag in git.
1. Update the `app/build.gradle` file with the npm package version.
1. Build the server and test app.
1. Put everything into the expected place.

Followed by pushing the commit and running the [npm publish command](https://docs.npmjs.com/cli/publish):
```shell
git push --tags origin master
npm publish
```
which will
1. Push result to GitHub
1. Publish to npm
