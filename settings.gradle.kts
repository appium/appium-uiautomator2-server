pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
    }
}
rootProject.name = "appium-uiautomator2-server"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":vendor-xpath2")
project(":vendor-xpath2").projectDir = file("vendor")
