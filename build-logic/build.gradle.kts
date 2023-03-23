plugins {
    `kotlin-dsl`
}

group = "co.yml.coreui.buildlogic"

repositories {
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    compileOnly(versionCatalogLibs.android.gradle.plugin)
    compileOnly(versionCatalogLibs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "co.yml.coreui.application"
            implementationClass = "conventions.ApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "co.yml.coreui.application.compose"
            implementationClass = "conventions.ComposeApplicationConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "co.yml.coreui.application.jacoco"
            implementationClass = "conventions.ApplicationJacocoConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "co.yml.coreui.library.compose"
            implementationClass = "conventions.LibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "co.yml.coreui.library"
            implementationClass = "conventions.LibraryConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "co.yml.coreui.library.jacoco"
            implementationClass = "conventions.LibraryJacocoConventionPlugin"
        }

        register("androidProjectJacoco") {
            id = "co.yml.coreui.project.jacoco"
            implementationClass = "conventions.ProjectJacocoConventionPlugin"
        }

        register("kotlinLibraryJacoco") {
            id = "co.yml.coreui.library.kotlin.jacoco"
            implementationClass = "conventions.KotlinLibraryJacocoConventionPlugin"
        }

        register("androidTest") {
            id = "co.yml.coreui.test"
            implementationClass = "conventions.AndroidTestConventionPlugin"
        }
    }
}
