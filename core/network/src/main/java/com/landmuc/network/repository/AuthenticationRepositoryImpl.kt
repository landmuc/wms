package com.landmuc.network.repository

import android.util.Log
import com.landmuc.domain.dto.UserDto
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.Columns
import io.ktor.http.ContentType.Application.Json
import java.util.UUID

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
        return try {
            val user = client.supabaseClient.auth.currentUserOrNull()
            val userIdAsString = user?.id
            val userIdAsUUID = UUID.fromString(userIdAsString)

            client.supabaseClient.from("wms_users").update(
                {
                    set("name", name)
                    set("surname", surname)
                    set("email", email)
                }
            ) {
                filter {
                    eq("id",userIdAsUUID )
                }
            }
            true
        }
        catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    //TODO: Implement try{} catch{}
    override suspend fun getGoogleUser(): UserDto {
           val user = client.supabaseClient.auth.currentUserOrNull()
           val userIdAsString = user?.id
           val userIdAsUUID = UUID.fromString(userIdAsString)

           return client.supabaseClient.postgrest.from("wms_users").select() {
                   filter {
                       eq("id", userIdAsUUID)
                   }
               }.decodeSingle<UserDto>()
       }



    override suspend fun sendNewGoogleUserInfoToDatabase(
        name: String,
        surname: String
    ): Boolean {
        return try {
            val user = client.supabaseClient.auth.currentUserOrNull()
            val userIdAsString = user?.id
            val userIdAsUUID = UUID.fromString(userIdAsString)

            val userEmail = user?.email

            client.supabaseClient.from("wms_users").update(
                {
                    set("name", name)
                    set("surname", surname)
                    set("email", userEmail)
                }
            ) {
                filter {
                    eq("id",userIdAsUUID )
                }
            }
            true
        }
        catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}