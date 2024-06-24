plugins {
    alias(libs.plugins.wms.android.feature)
}

android {
    namespace = "com.landmuc.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}