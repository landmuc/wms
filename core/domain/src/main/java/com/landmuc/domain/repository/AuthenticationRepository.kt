package com.landmuc.domain.repository

import android.content.Context
import com.landmuc.domain.dto.UserDto

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun sendNewUserInfoToDatabase(name: String, surname: String, email: String): Boolean
    suspend fun getGoogleUser(): UserDto
    suspend fun sendNewGoogleUserInfoToDatabase(name: String, surname: String): Boolean
}