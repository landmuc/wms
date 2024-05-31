plugins {
    // convention plugins
    alias(libs.plugins.wms.android.library)
    alias(libs.plugins.wms.android.feature)
    // other plugins
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.landmuc.event_list"
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