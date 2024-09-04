package com.landmuc.authentication.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.mapper.toUser
import com.landmuc.domain.repository.AuthenticationRepository
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

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _surname = MutableStateFlow("")
    val surname = _surname.asStateFlow()

    private val _showOverlay = MutableStateFlow(false)
    val showOverlay = _showOverlay.asStateFlow()

    fun onEmailChanged(email: String) {
        _email.update { email }
    }

    fun onPasswordChanged(password: String) {
        _password.update { password }
    }

    fun onNameChanged(name: String) {
        _name.update { name }
    }
    fun onSurnameChanged(surname: String) {
        _surname.update { surname }
    }

    fun switchOverlayVisibility() {
        _showOverlay.update { !showOverlay.value }
    }

    fun signIn(signInResult: (Boolean) -> Unit ) {
        viewModelScope.launch {
            val successfulSignIn =
                authRep.signIn(
                    email = _email.value,
                    password = _password.value
                )
            signInResult(successfulSignIn)
        }
    }

    fun checkFirstLogInWithGoogle(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val googleUser = authRep.getGoogleUser()
            val googleUserName: String? = googleUser.name

            onResult(googleUserName == null)
        }
    }

    fun sendNewGoogleUserInfoToDatabase(
        name: String,
        surname: String
    ) {
        viewModelScope.launch{
            authRep.sendNewGoogleUserInfoToDatabase(
                name = name,
                surname = surname
            )
        }
    }
}

