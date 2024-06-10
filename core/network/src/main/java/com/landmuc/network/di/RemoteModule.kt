package com.landmuc.network.di

import com.landmuc.network.SupabaseClient
import org.koin.dsl.module

val supabaseClientModule = module {
    single { SupabaseClient}
}