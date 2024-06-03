import org.jetbrains.kotlin.gradle.idea.tcs.extras.projectArtifactsClasspathKey

plugins {
    // convention plugins
    alias(libs.plugins.wms.android.application)
    alias(libs.plugins.wms.android.application.compose)
    // other plugins
    //alias(libs.plugins.compose.compiler) //in AndroidApplicationComposeConventionPlugin
}

android {
    namespace = "com.landmuc.wms"

    defaultConfig {
        applicationId = "com.landmuc.wms"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }
    composeOptions {}
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Modules
    // in nowInAndroid: implementation(projects.feature.search)
    implementation(project(":feature:authentication"))
    implementation(project(":feature:event_list"))
    implementation(project(":feature:event_admin"))
    implementation(project(":feature:event_user"))

    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.lifecycle.runtime.ktx) // in AndroidApplicationComposeConventionPlugin
//    implementation(libs.androidx.activity.compose) // in AndroidApplicationComposeConventionPlugin
//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.bundles.compose)


    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}