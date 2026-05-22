import org.gradle.api.tasks.testing.Test

plugins {
    `java-library`
}

val xpath2Root = layout.projectDirectory.dir("org.eclipse.wst.xml.xpath2.processor")
val xpath2TestsRoot = layout.projectDirectory.dir("org.eclipse.wst.xml.xpath2.processor.tests")

val upstreamUrl =
    "https://eclipse.googlesource.com/sourceediting/webtools.sourceediting.git"
val upstreamCommit = layout.projectDirectory.file("SOURCE_COMMIT").asFile.readText().trim()
val upstreamCheckoutDir = layout.projectDirectory.dir(".upstream/webtools.sourceediting")
val upstreamXqtsRoot =
    upstreamCheckoutDir.dir("xpath/tests/org.w3c.xqts.testsuite")
val upstreamProcessorTestsRoot =
    upstreamCheckoutDir.dir("xpath/tests/org.eclipse.wst.xml.xpath2.processor.tests")

val fetchXpath2TestData = tasks.register("fetchXpath2TestData") {
    group = "xpath2"
    description =
        "Sparse-clone W3C XQTS and PsychoPath test fixtures from the pinned upstream commit."
    val commitMarker = upstreamCheckoutDir.file(".commit")
    val xqtsMarker = upstreamXqtsRoot.file("TestSources/emptydoc.xml")
    inputs.property("commit", upstreamCommit)
    outputs.file(commitMarker)
    outputs.file(xqtsMarker)

    onlyIf {
        !commitMarker.asFile.exists() ||
            commitMarker.asFile.readText().trim() != upstreamCommit ||
            !xqtsMarker.asFile.exists()
    }

    doLast {
        val checkoutDir = upstreamCheckoutDir.asFile
        logger.lifecycle(
            "Fetching XPath2 test data from $upstreamUrl @ $upstreamCommit",
        )
        project.delete(checkoutDir)
        checkoutDir.mkdirs()

        fun git(vararg args: String) {
            exec {
                workingDir(checkoutDir)
                commandLine(listOf("git") + args)
                isIgnoreExitValue = false
            }
        }

        git("init", "-q")
        git("remote", "add", "origin", upstreamUrl)
        git("fetch", "-q", "--depth", "1", "origin", upstreamCommit)
        git("checkout", "-q", "FETCH_HEAD")
        git("sparse-checkout", "init", "--cone")
        git(
            "sparse-checkout",
            "set",
            "xpath/tests/org.eclipse.wst.xml.xpath2.processor.tests",
            "xpath/tests/org.w3c.xqts.testsuite",
        )
        commitMarker.asFile.writeText("$upstreamCommit\n")
        logger.lifecycle("XPath2 test data ready at ${checkoutDir.absolutePath}")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    main {
        java {
            setSrcDirs(listOf(xpath2Root.dir("src")))
        }
        resources {
            setSrcDirs(
                listOf(
                    xpath2Root.dir("grammars"),
                    xpath2Root.dir("META-INF"),
                ),
            )
        }
    }
    test {
        java {
            setSrcDirs(listOf(xpath2TestsRoot.dir("src")))
            exclude(
                "**/XPath20TestPlugin.java",
                "**/FilteringPerformanceTest.java",
            )
        }
        resources {
            setSrcDirs(listOf(upstreamXqtsRoot, upstreamProcessorTestsRoot))
            exclude(
                "**/.settings/**",
                "**/.classpath",
                "**/.project",
                "**/META-INF/**",
                "**/build.properties",
                "**/pom.xml",
                "**/build.xml",
                "**/src/**/*.java",
                "**/XQTSCatalog.html",
                "**/ReportingResults/**",
                "**/TestSuiteDocumentation/**",
                "**/createXPath2*.xsl",
                "**/ExtractXPath2*.xsl",
                "**/JunitTest.java",
            )
        }
    }
}

dependencies {
    implementation(libs.xercesimpl)
    implementation(libs.icu4j)
    implementation(libs.java.cup.runtime)

    testImplementation(sourceSets.main.get().output)
    testImplementation(libs.xmlunit)
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.vintage.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.jar {
    archiveBaseName.set("org.eclipse.wst.xml.xpath2.processor")
    archiveVersion.set("")
    destinationDirectory.set(rootProject.layout.projectDirectory.dir("app/libs"))
    // Preserve upstream Eclipse bundle license notice and OSGi metadata (build.properties bin.includes).
    from(xpath2Root) {
        include("about.html", "plugin.properties")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.compileTestJava {
    dependsOn(fetchXpath2TestData)
}

tasks.processTestResources {
    dependsOn(fetchXpath2TestData)
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from(xpath2TestsRoot.dir("src/org/eclipse/wst/xml/xpath2/processor/test/xml")) {
        into("org/eclipse/wst/xml/xpath2/processor/test/xml")
    }
}

tasks.withType<Test>().configureEach {
    dependsOn(fetchXpath2TestData)
}

tasks.test {
    useJUnitPlatform()
    filter {
        includeTestsMatching("org.eclipse.wst.xml.xpath2.processor.test.PsychoPathTestSuiteAdapter")
    }
    maxHeapSize = "2g"
    testLogging {
        events("failed", "skipped")
    }
}

tasks.register("cleanXpath2Jar", Delete::class) {
    group = "build"
    description = "Remove the vendored XPath2 processor JAR from app/libs."
    val libsDir = rootProject.layout.projectDirectory.dir("app/libs")
    delete(
        libsDir.file("org.eclipse.wst.xml.xpath2.processor.jar"),
        libsDir.asFileTree.matching { include("org.eclipse.wst.xml.xpath2.processor*.jar") },
    )
}

tasks.named("clean") {
    dependsOn("cleanXpath2Jar")
    delete(upstreamCheckoutDir)
}
