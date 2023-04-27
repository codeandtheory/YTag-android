@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.ytag.library.compose")
    id("co.yml.ytag.library")
    id("co.yml.ytag.library.jacoco")
}

android {
    namespace = "co.yml.ytag.ui"
}

dependencies {
    testImplementation(project(mapOf("path" to ":core:test")))
    androidTestImplementation(project(mapOf("path" to ":core:test")))
}
