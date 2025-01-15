package com.landmuc.wms

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 26
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    configureKotlin()

    //dependencies {}
}

/**
 * Configure base Kotlin options
 */
internal fun Project.configureKotlin() {
    // Use withType to workaround https://youtrack.jetbrains.com/issue/KT-55947
    tasks.withType<KotlinCompile>().configureEach {
//        kotlinOptions {
//            // Set JVM target to 11
//            jvmTarget = JavaVersion.VERSION_11.toString()
//        }
        // from https://kotlinlang.org/docs/gradle-compiler-options.html#types-for-compiler-options
        compilerOptions.jvmTarget.set(JvmTarget.JVM_11)
    }
}