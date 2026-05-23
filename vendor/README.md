# Vendored dependencies

## `org.eclipse.wst.xml.xpath2.processor`

Upstream: [Eclipse WTP Source Editing](https://eclipse.googlesource.com/sourceediting/webtools.sourceediting.git) (`xpath/bundles/org.eclipse.wst.xml.xpath2.processor/`).

The pinned upstream commit is recorded in [`SOURCE_COMMIT`](./SOURCE_COMMIT).

### License and notice

This component is **PsychoPath XPath 2.0 Processor** from the Eclipse Web Tools Platform.

- **License:** [Eclipse Public License 2.0](https://www.eclipse.org/legal/epl-2.0/) (EPL-2.0). Source files retain their original copyright headers (`SPDX-License-Identifier: EPL-2.0`).
- **About / redistribution notice:** [`org.eclipse.wst.xml.xpath2.processor/about.html`](./org.eclipse.wst.xml.xpath2.processor/about.html) (upstream “About This Content” text, unchanged).
- **Bundle metadata:** [`plugin.properties`](./org.eclipse.wst.xml.xpath2.processor/plugin.properties), [`META-INF/MANIFEST.MF`](./org.eclipse.wst.xml.xpath2.processor/META-INF/MANIFEST.MF).

`about.html`, `plugin.properties`, and `META-INF/` are included in the built JAR under `app/libs/`, matching the upstream Eclipse bundle layout.

### Build the JAR consumed by the server module

```bash
./gradlew :vendor-xpath2:jar
```

The output is written to `app/libs/org.eclipse.wst.xml.xpath2.processor.jar` and is produced automatically before `npm run build`.

## Upstream unit tests

Test **sources** (with local harness patches) live under [`org.eclipse.wst.xml.xpath2.processor.tests/src`](./org.eclipse.wst.xml.xpath2.processor.tests/src/).

Large test **fixtures** are not stored in git. They are sparse-cloned from the same upstream repository at `SOURCE_COMMIT` into `vendor/.upstream/webtools.sourceediting/` when you run tests:

- `xpath/tests/org.w3c.xqts.testsuite` — W3C XPath 2.0 vectors (`TestSources/`, `Queries/`, `ExpectedTestResults/`)
- `xpath/tests/org.eclipse.wst.xml.xpath2.processor.tests` — bug regression XML/XSD fixtures (`bugTestFiles/`, etc.)

Eclipse/OSGi resource loading is replaced with classpath lookup via `TestResourceBundles`.

### Run tests

```bash
./gradlew :vendor-xpath2:test
# or
npm run test:vendor-xpath2
```

The `fetchXpath2TestData` task runs automatically before `test` (requires network on first run). CI runs this via `npm run test:vendor-xpath2`.

This executes the upstream `AllPsychoPathTests` aggregate suite (8000+ cases) through `PsychoPathTestSuiteAdapter`.
