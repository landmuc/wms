package com.landmuc.authentication.sign_up

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.landmuc.domain.event.SignUpResult
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.domain.use_case.ConfirmPassword
import com.landmuc.domain.use_case.ValidateEmail
import com.landmuc.domain.use_case.ValidatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRep: AuthenticationRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val confirmPassword: ConfirmPassword
): ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()

    private val _surname = MutableStateFlow("")
    val surname = _surname.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm = _passwordConfirm.asStateFlow()


    fun onEmailChanged(email: String) {
        _email.update { email }
    }
    fun onNameChanged(name: String) {
        _name.update { name }
    }
    fun onSurnameChanged(surname: String) {
        _surname.update { surname }
    }
    fun onPasswordChanged(password: String) {
        _password.update { password }
    }
    fun onPasswordConfirmChanged(password: String) {
        _passwordConfirm.update { password }
    }

    fun signUp(onResult: (SignUpResult) -> Unit, ) {
        if (!validateEmail(_email.value)) { return onResult(SignUpResult.InvalidEmail) }

        if (!validatePassword(_password.value)) { return onResult(SignUpResult.InvalidPassword) }

        if (!confirmPassword(_password.value, _passwordConfirm.value)) { return onResult(SignUpResult.InvalidPasswordMatch) }

        if (validateEmail(_email.value) &&
            validatePassword(_password.value) &&
            confirmPassword(_password.value, _passwordConfirm.value)
        ) { return onResult(SignUpResult.ValidCredentials) }
    }

    fun signUpRequest(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val signUpResult =
                authRep.signUp(
                    email = _email.value,
                    password = _password.value
                )
            onResult(signUpResult)
        }
    }

    fun sendNewUserInfoToDatabase(
        name: String,
        surname: String,
        email: String
    ) {
        viewModelScope.launch{
            authRep.sendNewUserInfoToDatabase(
                name = name,
                surname = surname,
                email = email
            )
        }
    }

}