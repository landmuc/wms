package com.landmuc.wms

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKoin() {
    dependencies {
        val bom = libs.findLibrary("koin-bom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("koin-core").get())
        add("implementation", libs.findLibrary("koin-android").get())
        add("implementation", libs.findLibrary("koin-androidx-compose").get())
        add("implementation", libs.findLibrary("koin-ktor").get())
    }
}