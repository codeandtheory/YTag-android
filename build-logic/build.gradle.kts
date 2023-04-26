plugins {
    `kotlin-dsl`
}

group = "co.yml.ytag.buildlogic"

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
            id = "co.yml.ytag.application"
            implementationClass = "conventions.ApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "co.yml.ytag.application.compose"
            implementationClass = "conventions.ComposeApplicationConventionPlugin"
        }

        register("androidApplicationJacoco") {
            id = "co.yml.ytag.application.jacoco"
            implementationClass = "conventions.ApplicationJacocoConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "co.yml.ytag.library.compose"
            implementationClass = "conventions.LibraryComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "co.yml.ytag.library"
            implementationClass = "conventions.LibraryConventionPlugin"
        }

        register("androidLibraryJacoco") {
            id = "co.yml.ytag.library.jacoco"
            implementationClass = "conventions.LibraryJacocoConventionPlugin"
        }

        register("androidProjectJacoco") {
            id = "co.yml.ytag.project.jacoco"
            implementationClass = "conventions.ProjectJacocoConventionPlugin"
        }

        register("kotlinLibraryJacoco") {
            id = "co.yml.ytag.library.kotlin.jacoco"
            implementationClass = "conventions.KotlinLibraryJacocoConventionPlugin"
        }


        register("androidFeature") {
            id = "co.yml.ytag.feature"
            implementationClass = "conventions.FeatureConventionPlugin"
        }

        register("androidHilt") {
            id = "co.yml.ytag.hilt"
            implementationClass = "conventions.HiltConventionPlugin"
        }

        register("androidTest") {
            id = "co.yml.ytag.test"
            implementationClass = "conventions.AndroidTestConventionPlugin"
        }
    }
}
