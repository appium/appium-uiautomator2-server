import org.gradle.api.tasks.testing.Test
import java.io.File

plugins {
    `java-library`
}

val xpath2Root = layout.projectDirectory.dir("org.eclipse.wst.xml.xpath2.processor")
val xpath2TestsRoot = layout.projectDirectory.dir("org.eclipse.wst.xml.xpath2.processor.tests")

val upstreamUrl =
    "https://eclipse.googlesource.com/sourceediting/webtools.sourceediting.git"
val upstreamCommit = layout.projectDirectory.file("SOURCE_COMMIT").asFile.readText().trim()
val upstreamRoot = layout.projectDirectory.dir(".upstream")
val upstreamCheckoutDir = upstreamRoot.dir("webtools.sourceediting")
val upstreamXqtsRoot =
    upstreamCheckoutDir.dir("xpath/tests/org.w3c.xqts.testsuite")
val upstreamProcessorTestsRoot =
    upstreamCheckoutDir.dir("xpath/tests/org.eclipse.wst.xml.xpath2.processor.tests")

fun isUpstreamCheckoutContentValid(): Boolean {
    val checkoutDir = upstreamCheckoutDir.asFile
    val xqtsMarker = upstreamXqtsRoot.file("TestSources/emptydoc.xml").asFile
    val expectedResults = upstreamXqtsRoot.dir("ExpectedTestResults").asFile
    val processorBugFiles =
        upstreamProcessorTestsRoot.dir("bugTestFiles").asFile

    if (!checkoutDir.isDirectory ||
        !xqtsMarker.isFile ||
        !expectedResults.isDirectory ||
        !processorBugFiles.isDirectory ||
        !File(checkoutDir, ".git/HEAD").isFile
    ) {
        return false
    }

    return try {
        ProcessBuilder("git", "-C", checkoutDir.absolutePath, "rev-parse", "--verify", "HEAD")
            .redirectErrorStream(true)
            .start()
            .waitFor() == 0
    } catch (_: Exception) {
        false
    }
}

fun isUpstreamCheckoutValid(): Boolean {
    val commitMarker = File(upstreamCheckoutDir.asFile, ".commit")
    return isUpstreamCheckoutContentValid() &&
        commitMarker.isFile &&
        commitMarker.readText().trim() == upstreamCommit
}

fun org.gradle.api.Project.deleteUpstreamCheckout(logger: org.gradle.api.logging.Logger) {
    val checkoutDir = upstreamCheckoutDir.asFile
    val upstreamRootDir = upstreamRoot.asFile
    if (!checkoutDir.exists() && !upstreamRootDir.exists()) {
        return
    }

    logger.lifecycle("Removing upstream XPath2 test data at ${checkoutDir.absolutePath}")
    try {
        delete(checkoutDir)
        if (upstreamRootDir.exists() && upstreamRootDir.list().isNullOrEmpty()) {
            delete(upstreamRootDir)
        }
    } catch (e: Exception) {
        logger.warn("Gradle delete failed ({}), retrying with rm -rf", e.message)
        exec {
            if (checkoutDir.exists()) {
                commandLine("rm", "-rf", checkoutDir.absolutePath)
            }
            isIgnoreExitValue = false
        }
        if (upstreamRootDir.exists() && upstreamRootDir.list().isNullOrEmpty()) {
            upstreamRootDir.delete()
        }
    }
}

val cleanXpath2Upstream = tasks.register("cleanXpath2Upstream") {
    group = "xpath2"
    description = "Remove sparse-cloned upstream XPath2 test fixtures."
    doLast {
        deleteUpstreamCheckout(logger)
    }
}

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
        if (isUpstreamCheckoutValid()) {
            false
        } else {
            if (upstreamCheckoutDir.asFile.exists()) {
                logger.lifecycle(
                    "Upstream XPath2 test data is missing or invalid; re-fetching from $upstreamUrl",
                )
            }
            true
        }
    }

    doFirst {
        deleteUpstreamCheckout(logger)
    }

    doLast {
        val checkoutDir = upstreamCheckoutDir.asFile
        logger.lifecycle(
            "Fetching XPath2 test data from $upstreamUrl @ $upstreamCommit",
        )
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

        if (!isUpstreamCheckoutContentValid()) {
            deleteUpstreamCheckout(logger)
            error(
                "XPath2 test data fetch completed but checkout validation failed. " +
                    "Try: ./gradlew :vendor-xpath2:cleanXpath2Upstream :vendor-xpath2:fetchXpath2TestData",
            )
        }

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
    dependsOn("cleanXpath2Jar", cleanXpath2Upstream)
}
