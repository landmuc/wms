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


}

