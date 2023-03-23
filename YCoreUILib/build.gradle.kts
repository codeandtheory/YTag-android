@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.coreui.library.compose")
    id("co.yml.coreui.library")
    id("co.yml.coreui.library.jacoco")
}

android {
    namespace = "co.yml.ycoreui"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    testImplementation(versionCatalogLibs.mockk.android)
    androidTestImplementation(versionCatalogLibs.compose.ui.testing)
    androidTestImplementation(versionCatalogLibs.androidx.junit)
    androidTestImplementation(versionCatalogLibs.androidx.test.espresso.core)
    androidTestImplementation(versionCatalogLibs.androidx.test.core)
    androidTestImplementation(versionCatalogLibs.androidx.test.core.ktx)
    androidTestImplementation(versionCatalogLibs.androidx.test.ext)
    androidTestImplementation(versionCatalogLibs.androidx.test.runner)
    androidTestImplementation(versionCatalogLibs.androidx.test.rules)

    debugImplementation("androidx.compose.ui:ui-test-manifest:${versionCatalogLibs.versions.compose.ui.testing}")
}
