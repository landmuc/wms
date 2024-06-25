package com.landmuc.network.repository

import android.app.Activity
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthenticationRepositoryImpl(
    private val client: SupabaseClient
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            client.supabaseClient.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            client.supabaseClient.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}