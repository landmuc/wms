package com.landmuc.network.di

import com.landmuc.network.SupabaseClient
import com.landmuc.network.repository.AuthenticationRepositoryImpl
import org.koin.dsl.module

val supabaseClientModule = module {
    single { SupabaseClient}
}

val authenticationRepositoryModule = module {
    single { AuthenticationRepositoryImpl( get() ) }
}

val networkModule = module {
    includes(
        supabaseClientModule,
        authenticationRepositoryModule
    )
}