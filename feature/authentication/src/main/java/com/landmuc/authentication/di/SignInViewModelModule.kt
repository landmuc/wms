package com.landmuc.authentication.di

import com.landmuc.authentication.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInViewModelModule = module {
    viewModel { SignInViewModel(get()) }
}

