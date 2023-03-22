@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.coreui.application")
    id("co.yml.coreui.application.jacoco")
    id("co.yml.coreui.application.compose")
}

android {
    namespace = "co.yml.coreui"
    defaultConfig {
        applicationId = "co.yml.coreui"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(versionCatalogLibs.androidx.lifecycle.viewModelCompose)

    implementation(project(mapOf("path" to ":YCoreUILib")))

    androidTestImplementation(versionCatalogLibs.androidx.test.core)
    androidTestImplementation(versionCatalogLibs.androidx.test.core.ktx)
    androidTestImplementation(versionCatalogLibs.androidx.test.ext)
    androidTestImplementation(versionCatalogLibs.androidx.test.runner)
    androidTestImplementation(versionCatalogLibs.androidx.test.rules)

    debugImplementation("androidx.compose.ui:ui-test-manifest:${versionCatalogLibs.versions.compose}")
}
