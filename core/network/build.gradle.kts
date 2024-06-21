import java.util.Properties

plugins {
    alias(libs.plugins.wms.android.feature)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.wms.android.supabase)
    alias(libs.plugins.wms.android.koin)
}

android {
    namespace = "com.landmuc.network"

//    defaultConfig {
//        // load values from apikeys.properties file
//        val keystoreFile = project.rootProject.file("apikeys.properties")
//        val properties = Properties()
//        properties.load(keystoreFile.inputStream())
//        val supabaseUrl = properties.getProperty("SUPABASE_URL") ?: ""  // return empty key in case something goes wrong
//        val supabaseKey = properties.getProperty("SUPABASE_KEY") ?: ""
//
//        buildConfigField(type = "String", name = "SUPABASE_URL", value = supabaseUrl)
//        buildConfigField(type = "String", name = "SUPABASE_KEY", value = supabaseKey)
//    }
//
//    buildFeatures {
//        buildConfig = true
//    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
}