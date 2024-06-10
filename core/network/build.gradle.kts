plugins {
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.wms.android.supabase)
    alias(libs.plugins.wms.android.koin)
}

android {
    namespace = "com.landmuc.network"
}

dependencies {
    implementation(libs.androidx.core.ktx)
}