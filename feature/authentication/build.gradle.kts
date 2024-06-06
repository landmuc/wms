plugins {
    // convention plugins
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.wms.android.library.compose)
    alias(libs.plugins.wms.android.koin)
    // other plugins
    // alias(libs.plugins.compose.compiler) in AndroidLibraryComposeConventionPlugin
}

android {
    namespace = "com.landmuc.authentication"

    // covered by the android feature plugin
//    defaultConfig {
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
//    }
//
//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
//        }
//    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat) // required?
    implementation(libs.material) // replace by material 3? Material 3 in AndroidLibraryComposeConventionPlugin

//    implementation(platform(libs.androidx.compose.bom))
//    implementation(libs.bundles.compose)



    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}