package com.landmuc.domain.repository

import android.content.Context

interface AuthenticationRepository {
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun sendNewUserInfoToDatabase(name: String, surname: String, email: String): Boolean
    suspend fun checkFirstLogInWithGoogle(): Boolean
}