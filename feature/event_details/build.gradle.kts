plugins {
    // convention plugins
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.wms.android.library.compose)
    alias(libs.plugins.wms.android.koin)
    // other plugins
}

android {
    namespace = "com.landmuc.event_user"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:domain"))

    //implementation(libs.material.extended)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.datetime)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}