@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.coreui.library")
}

android {
    namespace = "co.yml.coreui.test"
}

dependencies {
    implementation(project(mapOf("path" to ":core:common")))
    implementation(versionCatalogLibs.bundles.test)
    implementation(versionCatalogLibs.androidx.test.monitor)
}
