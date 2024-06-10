plugins {
    alias(libs.plugins.wms.android.feature)
}

android {
    namespace = "com.landmuc.domain"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}