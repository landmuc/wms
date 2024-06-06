import org.jetbrains.kotlin.gradle.idea.tcs.extras.projectArtifactsClasspathKey

plugins {
    // convention plugins
    alias(libs.plugins.wms.android.application)
    alias(libs.plugins.wms.android.application.compose)
    alias(libs.plugins.wms.android.koin)
    alias(libs.plugins.wms.android.supabase)
    alias(libs.plugins.wms.android.ktor)
    // other plugins
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

//    // Koin
//    implementation(platform(libs.koin.bom))
//    implementation(libs.koin.core)
//    implementation(libs.koin.android)
//    implementation(libs.koin.androidx.compose)
//    implementation(libs.koin.ktor)

//    //Supabase
//    implementation(platform(libs.supabase.bom))
//    implementation(libs.supabase.postgrest.kt)
//    implementation(libs.supabase.realtime.kt)
//    implementation(libs.supabase.gotrue.kt)
//    implementation(libs.supabase.compose.auth)
//    implementation(libs.supabase.compose.auth.ui)
//
//    //Ktor
//    implementation(libs.ktor.client.core)
//    implementation(libs.ktor.client.okhttp)
//    implementation(libs.ktor.client.content.negotiation)
//    implementation(libs.ktor.serialization.kotlinx.json)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}