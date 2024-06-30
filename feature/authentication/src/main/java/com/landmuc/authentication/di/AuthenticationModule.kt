package com.landmuc.authentication.di

import com.landmuc.authentication.SignInViewModel
import com.landmuc.authentication.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val signInViewModelModule = module {
    viewModel { SignInViewModel(get(), get()) }
}

val signUpViewModelModule = module {
    viewModel { SignUpViewModel(get(), get(), get(), get())}
}

val authenticationModule = module {
    includes(
        signInViewModelModule,
        signUpViewModelModule
    )
}