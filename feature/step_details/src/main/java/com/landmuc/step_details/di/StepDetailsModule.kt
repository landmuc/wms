package com.landmuc.step_details.di

import com.landmuc.step_details.StepDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val stepDetailsViewModelModule = module {
    viewModel { StepDetailsViewModel(get())}
}

val stepDetailsModule = module {
    includes(
        stepDetailsViewModelModule
    )
}