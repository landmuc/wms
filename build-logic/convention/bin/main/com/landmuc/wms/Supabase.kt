package com.landmuc.wms

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureSupabase() {
    dependencies {
        val bom = libs.findLibrary("supabase-bom").get()
        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("supabase-postgrest-kt").get())
        add("implementation", libs.findLibrary("supabase-realtime-kt").get())
        add("implementation", libs.findLibrary("supabase-gotrue-kt").get())
        add("implementation", libs.findLibrary("supabase-compose-auth").get())
        add("implementation", libs.findLibrary("supabase-compose-auth-ui").get())
    }
}