enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("versionCatalogLibs") {
            from(files("./gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "CoreUICatalog"
include(":app")
include(":core:ui")
include(":core:common")
include(":core:data")
include(":core:test")
include("feature:ytag")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
