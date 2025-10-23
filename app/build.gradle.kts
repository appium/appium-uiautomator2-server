import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.BuildConfigField
import com.android.build.api.variant.TestVariant
import com.android.build.api.variant.impl.VariantOutputImpl
import java.io.ByteArrayOutputStream

buildscript {
    dependencies {
        classpath(libs.unmockplugin) {
            exclude(group = "com.android.tools.build", module = "gradle")
        }
    }
}
// Apply UnMock plugin via legacy syntax because it's not properly published
apply(plugin = "de.mobilej.unmock")
plugins {
    alias(libs.plugins.android.application)
}

java {
    // Ensures JDK 22+ Adoptium consistency across CI environments and avoids vendor-specific build issues
    if (System.getenv("CI") != null) {
        toolchain {
            languageVersion = JavaLanguageVersion.of(22)
            vendor = JvmVendorSpec.ADOPTIUM
        }
    }
}

project.base.archivesName = "appium-uiautomator2"
val buildTime = BuildConfigField(
    "String", "\"${System.currentTimeMillis()}\"", "build timestamp"
)

android {
    namespace = "io.appium.uiautomator2.test"
    compileSdk = 34
    defaultConfig {
        applicationId = "io.appium.uiautomator2"
        minSdk = 26
        targetSdk = 34
        versionCode = project.findProperty("versionCode").toString().toIntOrNull() ?: 1
        /**
         * versionName should be updated and inline with version in package.json for every npm release.
         */
        versionName = project.findProperty("versionName")?.toString() ?: "1.0.0-SNAPSHOT"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testOptions {
            testInstrumentationRunnerArguments += mapOf(
                "notAnnotation" to "androidx.test.filters.FlakyTest"
            )
        }
        buildFeatures {
            buildConfig = true
        }

    }
    buildTypes {
        getByName("debug") {
            isDebuggable = true
            vcsInfo {
                include = true
            }
        }
        create("customDebuggableBuildType") {
            isDebuggable = true
        }
    }
    androidComponents {
        onVariants { variant ->
            if (variant is TestVariant) {
                println("TestVariant: ${variant.name}")
            }
            // Add build-time information to BuildConfig so it can be accessed at runtime.
            variant.buildConfigFields.put("BUILD_TIME", buildTime)
            variant.androidTest?.buildConfigFields?.put("BUILD_TIME", buildTime)

            variant.outputs.forEach {
                if (it is VariantOutputImpl) {
                    it.outputFileName =
                        it.outputFileName.get().replace("debug", "v${it.versionName.get()}")
                }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
        val apiLevel : Int = runCatching {
            val getPropCommand = mutableListOf<String>()
            getPropCommand.add(0, adbFileProvider.get().asFile.absolutePath)
            getPropCommand.addAll(commandArgs)
            getPropCommand.addAll(listOf("shell","getprop","ro.build.version.sdk"))
            ProcessBuilder( getPropCommand)
                .start()
                .inputStream.bufferedReader().use { it.readText() }.trim().toIntOrNull()
        }.getOrNull()?:23

        commandArgs.add("install")
        if (apiLevel >= 23){
            commandArgs.add("-g")
        }
        commandArgs.add("-r")
        commandArgs.add(apkFile.absolutePath)
        setArgs(commandArgs)
        isIgnoreExitValue = false
        errorOutput = ByteArrayOutputStream()
        standardOutput = ByteArrayOutputStream()
    }

    doLast {
        logger.info("exitValue: ${executionResult.get().exitValue},\nstandardOutput: $standardOutput,\nerrorOutput: $errorOutput")
    }
}
val uninstallAUT by tasks.register("uninstallAUT", Exec::class) {
    group = "install"
    description = "Uninstall app under test (ApiDemos) using AGP's ADB."
    val extension = project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
    val adbFileProvider: Provider<RegularFile> = extension.sdkComponents.adb
    val targetSerial = System.getenv("ANDROID_SERIAL")
    doFirst {
        executable = adbFileProvider.get().asFile.absolutePath
        val commandArgs = mutableListOf<String>()
        if (!targetSerial.isNullOrBlank()) {
            commandArgs.add("-s")
            commandArgs.add(targetSerial)
            logger.quiet("Uninstalling to device: $targetSerial")
        }
        commandArgs.addAll(listOf("uninstall", "io.appium.android.apis"))
        setArgs(commandArgs)
        isIgnoreExitValue = true
    }
}

afterEvaluate {
    tasks.named("connectedE2eTestDebugAndroidTest").configure {
        dependsOn(installAUT)
    }
    tasks.named("uninstallAll").configure {
        dependsOn(uninstallAUT)
    }
}
