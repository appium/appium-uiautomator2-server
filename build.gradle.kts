plugins {
    alias(libs.plugins.android.application) apply false
}

tasks.register("clean", Delete::class).configure {
    delete(
        rootProject.layout.buildDirectory,
//        rootProject.layout.projectDirectory.file("apks")
    )
}