package com.landmuc.authentication.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRep: AuthenticationRepository,
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.update { email }
    }

    fun onPasswordChanged(password: String) {
        _password.update { password }
    }

    fun signIn(signInResult: (Boolean) -> Unit ) {
        viewModelScope.launch {
            val successfulSignIn =
                authRep.signIn(
                    email = _email.value,
                    password = _password.value
                )
            signInResult( successfulSignIn )
        }
    }

    fun checkFirstLogInWithGoogle(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val isNameNull = authRep.checkFirstLogInWithGoogle()
            onResult(isNameNull)
        }
    }
}

