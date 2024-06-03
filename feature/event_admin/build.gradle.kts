plugins {
    // convention plugins
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.wms.android.library.compose)
    // other plugins
}

android {
    namespace = "com.landmuc.event_admin"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}