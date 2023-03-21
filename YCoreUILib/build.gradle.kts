@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.coreui.library.compose")
    id("co.yml.coreui.library")
    id("co.yml.coreui.library.jacoco")
}

android {
    namespace = "co.yml.ycoreui"
}

dependencies {
    //Compose
    implementation (versionCatalogLibs.bundles.compose)
    //ktx
    implementation (versionCatalogLibs.bundles.ktx)
    //Android Testing
    testImplementation(project(mapOf("path" to ":core:test")))
    androidTestImplementation(project(mapOf("path" to ":core:test")))
}
