package com.landmuc.authentication

import android.content.Context
import androidx.lifecycle.ViewModel
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val client: SupabaseClient
): ViewModel() {
    private val _userState = MutableStateFlow("User not logged in!")
    val userState = _userState.asStateFlow()

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

    fun checkGoogleLoginStatus(context: Context, result: NativeSignInResult) {
        when(result) {
            is NativeSignInResult.Success -> {
                _userState.value = "Logged in via Google"
            }
            is NativeSignInResult.ClosedByUser -> {}
            is NativeSignInResult.Error -> {
                val message = result.message
                _userState.value = result.message
            }
            is NativeSignInResult.NetworkError -> {
                val message = result.message
                _userState.value = result.message
            }
        }
    }

}

