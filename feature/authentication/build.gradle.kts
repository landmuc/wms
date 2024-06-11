plugins {
    // convention plugins
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.wms.android.library.compose)
    alias(libs.plugins.wms.android.koin)
    alias(libs.plugins.wms.android.supabase)
    //alias(libs.plugins.wms.android.ktor)

}

android {
    namespace = "com.landmuc.authentication"
}

dependencies {
    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat) // required?
//    implementation(libs.material) // replace by material 3? Material 3 in AndroidLibraryComposeConventionPlugin

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}