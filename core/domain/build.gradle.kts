plugins {
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.landmuc.domain"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.datetime)

    implementation(libs.androidx.core.ktx)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}