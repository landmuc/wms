package com.landmuc.wms

import com.landmuc.authentication.di.authenticationModule
import com.landmuc.create_event.di.createEventModule
import com.landmuc.event_list.di.eventListModule
import com.landmuc.event_user.di.eventDetailsModule
import com.landmuc.network.di.networkModule
import com.landmuc.search.di.searchModule
import com.landmuc.step_details.di.stepDetailsViewModelModule
import org.koin.dsl.module

val sharedModule = module {
    includes(
        networkModule,
        authenticationModule,
        eventListModule,
        eventDetailsModule,
        searchModule,
        createEventModule,
        stepDetailsViewModelModule
    )
}