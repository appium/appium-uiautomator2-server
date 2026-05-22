plugins {
    alias(libs.plugins.android.application) apply false
}

tasks.register("clean", Delete::class).configure {
    dependsOn(":vendor-xpath2:cleanXpath2Jar")
    delete(
        rootProject.layout.buildDirectory,
        rootProject.layout.projectDirectory.file("apks")
    )
}