package com.landmuc.event_list.di

import com.landmuc.event_list.EventListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val eventListViewModelModule = module {
    viewModel { EventListViewModel(get()) }
}

val eventListModule = module {
    includes(
        eventListViewModelModule
    )
}