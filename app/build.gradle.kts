import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import java.io.ByteArrayOutputStream
buildscript {
    dependencies {
        classpath(libs.unmockplugin)
    }
}

plugins {
    alias(libs.plugins.android.application)
}

// Apply UnMock plugin via legacy syntax because it's not properly published
apply(plugin = "de.mobilej.unmock")

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

android {
    namespace = "io.appium.uiautomator2.test"
    compileSdk = 34
    defaultConfig {
        applicationId = "io.appium.uiautomator2"
        minSdk = 21
        targetSdk = 34
        versionCode = 210
        /**
         * versionName should be updated and inline with version in package.json for every npm release.
         */
        versionName = "7.3.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildFeatures {
            buildConfig = true
        }
    }
    buildTypes {
        val debug by getting {
            isDebuggable = true
        }
        create("customDebuggableBuildType") {
            isDebuggable = true
        }
    }

    androidComponents {
        onVariants { variant ->
            variant.outputs.forEach { output ->
                val outputImpl = output as? com.android.build.api.variant.impl.VariantOutputImpl
                outputImpl?.outputFileName?.set(
                    outputImpl.outputFileName.get()
                        .replace("debug.apk", "v${defaultConfig.versionName}.apk")
                )
            }
        }
    }

    flavorDimensions += "default"
    productFlavors {
        create("e2eTest") {
            applicationId = "io.appium.uiautomator2.e2etest"
            dimension = "default"
        }
        create("server") {
            applicationId = "io.appium.uiautomator2.server"
            dimension = "default"
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
            all {
                it.jvmArgs(
                    listOf(
                        "--add-opens", "java.base/java.lang=ALL-UNNAMED",
                        "--add-opens", "java.base/java.time=ALL-UNNAMED",
                        "--add-opens", "java.base/java.time.format=ALL-UNNAMED",
                        "--add-opens", "java.base/java.util=ALL-UNNAMED",
                        "--add-opens", "java.base/java.util.concurrent=ALL-UNNAMED",
                        "--add-exports", "java.base/sun.nio.ch=ALL-UNNAMED",
                        "--add-opens", "java.base/java.lang.reflect=ALL-UNNAMED",
                        "--add-opens", "java.base/java.io=ALL-UNNAMED",
                        "--add-opens", "java.base/java.net=ALL-UNNAMED",
                        "--add-opens", "java.base/sun.net.www.protocol.http=ALL-UNNAMED",
                        "--add-exports", "jdk.unsupported/sun.misc=ALL-UNNAMED"
                    )
                )
            }
        }
    }
    packaging {
        resources {
            excludes += setOf(
                "META-INF/maven/com.google.guava/guava/pom.properties",
                "META-INF/maven/com.google.guava/guava/pom.xml"
            )
        }
    }
    lint {
        abortOnError = false
    }
}

extensions.configure<de.mobilej.unmock.UnMockExtension>("unMock") {
    keepStartingWith("com.android.internal.util.")
    keepStartingWith("android.util.")
    keepStartingWith("android.view.")
    keepStartingWith("android.internal.")
}

dependencies {
    // Local JARs dependency
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    // Dependencies using the version catalog (libs)
    implementation(libs.bundles.androix.test)
    implementation(libs.uiautomator)
    implementation(libs.gson)
    implementation(libs.netty.all)
    implementation(libs.junidecode)
    // Dependencies required for XPath search
    implementation(libs.xercesimpl)
    implementation(libs.java.cup.runtime)
    implementation(libs.icu4j)
    // Test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.json)
    testImplementation(libs.bundles.powermock)
    testImplementation(libs.robolectric)
    testImplementation(libs.javassist)
    // Android test dependencies
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.okhttp)
}

val installAUT by tasks.register("installAUT", Exec::class) {
    group = "install"
    description = "Install app under test (ApiDemos) using AGP's ADB."
    val extension = project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
    // To avoid issues caused by incorrect configuration of the ANDROID_HOME environment variable
    // or version inconsistencies from multiple adb installations.
    val adbFileProvider: Provider<RegularFile> = extension.sdkComponents.adb
    val apkFile = project.file("../node_modules/android-apidemos/apks/ApiDemos-debug.apk")
    val targetSerial = System.getenv("ANDROID_SERIAL")
    inputs.file(apkFile)
        .withPathSensitivity(PathSensitivity.ABSOLUTE)
        .withPropertyName("autApkInput")
        .skipWhenEmpty(false)

    doFirst {
        if (!apkFile.exists()) {
            throw GradleException("Required AUT APK not found at: ${apkFile.absolutePath}")
        }
        executable = adbFileProvider.get().asFile.absolutePath
        val commandArgs = mutableListOf<String>()

        if (!targetSerial.isNullOrBlank()) {
            commandArgs.add("-s")
            commandArgs.add(targetSerial)
            logger.quiet("Installing to device: $targetSerial")
        }

        commandArgs.addAll(listOf("install", "-r", "-g", apkFile.path))
        setArgs(commandArgs)
        isIgnoreExitValue = true
        errorOutput = ByteArrayOutputStream()
        standardOutput = ByteArrayOutputStream()
    }

    doLast {
        logger.info(standardOutput.toString())
        executionResult.get().let { res ->
            if (res.exitValue != 0) {
                if ("no devices/emulators found" !in errorOutput.toString()) {
                    logger.warn(
                        """
                >>>Note: This installation used '-g' flag which requires API 23+.
                For older devices, if needed, use:
                adb ${if (!targetSerial.isNullOrBlank()) "-s $targetSerial " else ""}install -r "${apkFile.path}"
            """.trimIndent()
                    )
                }
            }
            logger.error(errorOutput.toString())
            res.assertNormalExitValue()
        }
    }
}
afterEvaluate {
    tasks.named("connectedE2eTestDebugAndroidTest").configure {
        dependsOn(installAUT)
    }
}
