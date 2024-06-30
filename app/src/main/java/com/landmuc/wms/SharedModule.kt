package com.landmuc.wms

import com.landmuc.authentication.di.authenticationModule
import com.landmuc.authentication.di.signInViewModelModule
import com.landmuc.network.di.networkModule
import com.landmuc.network.di.supabaseClientModule
import org.koin.dsl.module

val sharedModule = module {
    includes(
        networkModule,
        authenticationModule
    )
}