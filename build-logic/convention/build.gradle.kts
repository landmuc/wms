import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.landmuc.wms.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
//    compileOnly(libs.compose.gradlePlugin)
//    compileOnly(libs.android.tools.common)
}

gradlePlugin {
    // register the convention plugin
    plugins {
        register("androidApplication") {
            id = "wms.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
    }
    plugins {
        register("androidLibrary") {
            id = "wms.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
    plugins {
        register("androidApplicationCompose") {
            id = "wms.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
    plugins {
        register("androidLibraryCompose") {
            id = "wms.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
    }
    plugins {
        register("androidFeature") {
            id = "wms.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
    plugins {
        register("androidKoin") {
            id = "wms.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }
    }
}