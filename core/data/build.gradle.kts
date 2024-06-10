plugins {
    alias(libs.plugins.wms.android.feature)
}

android {
    namespace = "com.landmuc.data"
}

dependencies {
    api(project(":core:network"))
    api(project(":core:domain"))

    implementation(libs.androidx.core.ktx)
}