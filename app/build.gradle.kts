@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.ytag.application")
    id("co.yml.ytag.application.jacoco")
    id("co.yml.ytag.application.compose")
    id("co.yml.ytag.hilt")
}

android {
    namespace = "co.yml.ytag"
    defaultConfig {
        applicationId = "co.yml.ytag"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(versionCatalogLibs.hilt.nav.compose)
    implementation(versionCatalogLibs.androidx.lifecycle.viewModelCompose)

    implementation(project(mapOf("path" to ":core:ui")))
    implementation(project(mapOf("path" to ":feature:ytag")))

    androidTestImplementation(versionCatalogLibs.androidx.test.core)
    androidTestImplementation(versionCatalogLibs.androidx.test.core.ktx)
    androidTestImplementation(versionCatalogLibs.androidx.test.ext)
    androidTestImplementation(versionCatalogLibs.androidx.test.runner)
    androidTestImplementation(versionCatalogLibs.androidx.test.rules)
    debugImplementation("androidx.compose.ui:ui-test-manifest:${versionCatalogLibs.versions.compose.ui.testing}")

    androidTestImplementation(project(mapOf("path" to ":core:test")))
}
