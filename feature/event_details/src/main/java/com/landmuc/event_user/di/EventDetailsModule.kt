package com.landmuc.event_user.di

import com.landmuc.event_user.EventDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val eventDetailsViewModelModule = module {
    viewModel { EventDetailsViewModel(get()) }
}

val eventDetailsModule = module {
    includes(
        eventDetailsViewModelModule
    )
}