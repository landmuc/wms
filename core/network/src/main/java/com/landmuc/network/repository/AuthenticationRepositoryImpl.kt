package com.landmuc.network.repository

import com.landmuc.domain.dto.NewUserDto
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from

class AuthenticationRepositoryImpl(
    private val client: SupabaseClient
): AuthenticationRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): Boolean {
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

    override suspend fun signUp(
        email: String,
        password: String
    ): Boolean {
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

    override suspend fun sendNewUserInfoToDatabase(
        name: String,
        surname: String,
        email: String
    ): Boolean {
        val newUser = NewUserDto(
            name = name,
            surname = surname,
            email = email
        )

        return try {
            client.supabaseClient.from("wms_users").insert(newUser)
            true
        }
        catch (e: Exception) {
            e.printStackTrace()
            false
        }


    }

}