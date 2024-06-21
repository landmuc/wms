import org.jetbrains.kotlin.gradle.idea.tcs.extras.projectArtifactsClasspathKey
import org.jetbrains.kotlin.name.StandardClassIds
import java.util.Collections
import java.util.Properties

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

//        // load values from apikeys.properties file
//        val keystoreFile = project.rootProject.file("apikeys.properties")
//        val properties = Properties()
//        properties.load(keystoreFile.inputStream())
//        val supabaseUrl = properties.getProperty("SUPABASE_URL") ?: ""  // return empty key in case something goes wrong
//        val supabaseKey = properties.getProperty("SUPABASE_KEY") ?: ""
//
//        buildConfigField(type = "String", name = "SUPABASE_URL", value = supabaseUrl)
//        buildConfigField(type = "String", name = "SUPABASE_KEY", value = supabaseKey)

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
        //buildConfig = true
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
    implementation(project(":core:network"))

    implementation(project(":feature:authentication"))
    implementation(project(":feature:event_list"))
    implementation(project(":feature:event_admin"))
    implementation(project(":feature:event_user"))

    implementation(libs.androidx.core.ktx)

    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Google Credential Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}