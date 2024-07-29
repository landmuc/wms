package com.landmuc.event_user.di

import com.landmuc.event_user.EventUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val eventUserViewModelModule = module {
    viewModel { EventUserViewModel(get()) }
}

val eventUserModule = module {
    includes(
        eventUserViewModelModule
    )
}