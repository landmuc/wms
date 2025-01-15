package com.landmuc.wms

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKtor() {
    dependencies {
        add("implementation", libs.findLibrary("ktor-client-core").get())
        add("implementation", libs.findLibrary("ktor-client-okhttp").get())
        add("implementation", libs.findLibrary("ktor-client-content-negotiation").get())
        add("implementation", libs.findLibrary("ktor-serialization-kotlinx-json").get())
    }
}