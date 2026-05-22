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
