@Suppress("DSL_SCOPE_VIOLATION") // scope violation issue: work around suggested from: https://github.com/gradle/gradle/issues/22797
plugins {
    id("co.yml.ytag.library")
    id("co.yml.ytag.hilt")
}

android {
    namespace = "co.yml.ytag.test"
}

dependencies {
    implementation(project(mapOf("path" to ":core:common")))
    implementation(project(mapOf("path" to ":core:data")))
    implementation(versionCatalogLibs.bundles.test)
    implementation(versionCatalogLibs.hilt.test)
    implementation(versionCatalogLibs.androidx.test.monitor)
}
