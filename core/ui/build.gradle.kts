@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.ytag.library.compose")
    id("co.yml.ytag.library")
    id("co.yml.ytag.library.jacoco")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
}

android {
    namespace = "co.yml.ytag.core.ui"
}

dependencies {
    testImplementation(project(mapOf("path" to ":core:test")))
    androidTestImplementation(project(mapOf("path" to ":core:test")))
}
val dokkaOutputDir = "$buildDir/dokka"

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}
val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

publishing {
    repositories {
        maven {
            name = "YTag"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")
            credentials {
                username = project.findProperty("mavenCentralUsername")?.toString()
                    ?: System.getenv("MAVEN_USERNAME")
                password = project.findProperty("mavenCentralPassword")?.toString()
                    ?: System.getenv("MAVEN_PASSWORD")
            }
        }
    }
    publications {
        register<MavenPublication>("release") {
            groupId = "co.yml"
            artifactId = "ytag"
            version = "1.0.0"
            afterEvaluate {
                println("Components:${components.names}")
                from(components["productionRelease"])
            }
            artifact(javadocJar)
            pom {
                name.set("YTag")
                description.set("Y Tag is a UI element in Android (some times referred to as chips) which displays a piece of information. It consist of a leading icon(optional), Text and a trailing icon (optional).")
                url.set("https://github.com/yml-org/YTag-android")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("sreekuttancj")
                        name.set("Sreekuttan C J")
                        url.set("https://github.com/sreekuttancj")
                    }
                    developer {
                        id.set("dkk009")
                        name.set("Deepak KK")
                        url.set("https://github.com/dkk009")
                    }
                    developer {
                        id.set("kikoso")
                        name.set("Enrique López Mañas")
                        url.set("https://github.com/kikoso")
                    }
                }
                scm {
                    url.set("https://github.com/yml-org/YTag-android")
                    connection.set("scm:git:git://github.com/yml-org/YTag-android.git")
                    developerConnection.set("scm:git:ssh://git@github.com:yml-org/YTag-android.git")
                }
            }
        }
    }
}
signing {
    useInMemoryPgpKeys(
        project.findProperty("signing.keyId")?.toString() ?: System.getenv("SIGNINGKEY"),
        project.findProperty("signing.InMemoryKey")?.toString() ?: System.getenv("MEMORY_KEY"),
        project.findProperty("signing.password")?.toString() ?: System.getenv("SIGNINGPASSWORD")
    )
    sign(publishing.publications)
}
