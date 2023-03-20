import co.yml.coreui.jacoco.addExclusion

@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.coreui.library")
    id("co.yml.coreui.hilt")
    id("co.yml.coreui.library.jacoco")
}

private val excludedFiles = mutableSetOf(
    "**/co.yml.coreui/core/network/model/*",
    "**/co.yml.coreui/core/network/di/*",
    "**/co.yml.coreui/core/network/apis/PostApi.*",
    "**/co.yml.coreui/core/network/Constants.*",
    "**/co.yml.coreui/core/network/Routes.*"
)

addExclusion(excludedFiles)

android {
    namespace = "co.yml.coreui.network"
}

dependencies {
    implementation(project(mapOf("path" to ":core:common")))
    // Ktor
    implementation(versionCatalogLibs.bundles.ktor)

    testImplementation(versionCatalogLibs.ktor.client.mock)
    testImplementation(project(mapOf("path" to ":core:test")))
    implementation(versionCatalogLibs.androidx.test.monitor)
    androidTestImplementation(versionCatalogLibs.bundles.test)
    androidTestImplementation(versionCatalogLibs.coroutine.test)
    androidTestImplementation(versionCatalogLibs.hilt.test)
    androidTestImplementation(project(mapOf("path" to ":core:test")))
    androidTestImplementation(kotlin("test"))
}
