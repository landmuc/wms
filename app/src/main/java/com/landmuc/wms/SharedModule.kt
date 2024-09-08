package com.landmuc.wms

import com.landmuc.authentication.di.authenticationModule
import com.landmuc.event_list.di.eventListModule
import com.landmuc.event_user.di.eventUserModule
import com.landmuc.network.di.networkModule
import com.landmuc.search.di.searchModule
import org.koin.dsl.module

val sharedModule = module {
    includes(
        networkModule,
        authenticationModule,
        eventListModule,
        eventUserModule,
        searchModule
    )
}