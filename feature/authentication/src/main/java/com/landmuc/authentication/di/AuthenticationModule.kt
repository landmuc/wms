package com.landmuc.authentication.di

import com.landmuc.authentication.sign_in.SignInViewModel
import com.landmuc.authentication.sign_up.SignUpViewModel
import com.landmuc.domain.use_case.ConfirmPassword
import com.landmuc.domain.use_case.ValidateEmail
import com.landmuc.domain.use_case.ValidatePassword
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory { ValidateEmail() }
    factory { ValidatePassword() }
    factory { ConfirmPassword() }
}

val signInViewModelModule = module {
    viewModel { SignInViewModel(get()) }
}

val signUpViewModelModule = module {
    viewModel { SignUpViewModel(get(), get(), get(), get()) }
}

val authenticationModule = module {
    includes(
        useCaseModule,
        signInViewModelModule,
        signUpViewModelModule
    )
}