plugins {
    `java-library`
}

val xpath2Root = layout.projectDirectory.dir("org.eclipse.wst.xml.xpath2.processor")

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
}

dependencies {
    implementation(libs.xercesimpl)
    implementation(libs.icu4j)
    implementation(libs.java.cup.runtime)
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
}
