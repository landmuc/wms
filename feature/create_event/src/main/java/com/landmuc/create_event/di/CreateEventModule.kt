package com.landmuc.create_event.di

import com.landmuc.create_event.CreateEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val createModuleViewModelModule = module {
    viewModelOf( ::CreateEventViewModel )
}

val createEventModule = module {
    includes(
        createModuleViewModelModule
    )
}