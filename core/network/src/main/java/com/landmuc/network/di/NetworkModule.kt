package com.landmuc.network.di

import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.domain.repository.EventDataRepository
import com.landmuc.network.SupabaseClient
import com.landmuc.network.repository.AuthenticationRepositoryImpl
import com.landmuc.network.repository.EventDataRepositoryImpl
import org.koin.dsl.module

val supabaseClientModule = module {
    single { SupabaseClient }
}

val authenticationRepositoryModule = module {
    single<AuthenticationRepository> { AuthenticationRepositoryImpl( get() ) }
}

val eventDataRepositoryModule = module {
    single<EventDataRepository> { EventDataRepositoryImpl( get() ) }
}

val networkModule = module {
    includes(
        supabaseClientModule,
        authenticationRepositoryModule,
        eventDataRepositoryModule
    )
}