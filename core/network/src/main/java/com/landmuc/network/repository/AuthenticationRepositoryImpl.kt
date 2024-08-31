package com.landmuc.network.repository

import android.util.Log
import com.landmuc.domain.dto.NewUserDto
import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient
import com.typesafe.config.ConfigException.Null
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.Column
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

    override suspend fun checkFirstLogInWithGoogle(): Boolean {
       return try {
           val user = client.supabaseClient.auth.currentUserOrNull()
           val userIdAsString = user?.id
           val userIdAsUUID = UUID.fromString(userIdAsString)

           val userName = client.supabaseClient.from("wms_users").select(
               columns = Columns.raw("name")) {
                   filter {
                       eq("id", userIdAsUUID)
                   }
               }

           if (userName.decodeAsOrNull<String>() == null) true else false

       } catch (e: Exception) {
           e.printStackTrace()
           false
       }
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