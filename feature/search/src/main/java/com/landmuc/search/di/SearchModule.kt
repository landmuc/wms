package com.landmuc.search.di

import com.landmuc.domain.use_case.UpdateFollowedEventsInEventList
import com.landmuc.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory { UpdateFollowedEventsInEventList() }
}

val searchViewModelModule = module {
    viewModel { SearchViewModel( get(), get() ) }
}

val searchModule = module {
    includes(
        useCaseModule,
        searchViewModelModule
    )
}