import co.yml.ytag.jacoco.setModuleTestCoverageLimits

@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.ytag.feature")
    id("co.yml.ytag.library.compose")
    id("co.yml.ytag.library")
    id("co.yml.ytag.library.jacoco")
}

private val limits = mutableMapOf(
    "instruction" to 100.0,
    "branch" to 50.0,
    "line" to 50.0,
    "complexity" to 50.0,
    "method" to 50.0,
    "class" to 50.0
)
setModuleTestCoverageLimits(limits)

android {
    namespace = "co.yml.ytag.feature.ytag"
}

dependencies {
    implementation(versionCatalogLibs.hilt.nav.compose)
    implementation(versionCatalogLibs.androidx.lifecycle.viewModelCompose)
    implementation(project(mapOf("path" to ":core:ui")))
    implementation(project(mapOf("path" to ":core:test")))
    testImplementation(project(mapOf("path" to ":core:test")))

    androidTestImplementation(versionCatalogLibs.androidx.compose.ui.test)
    androidTestImplementation(project(mapOf("path" to ":core:test")))
}
