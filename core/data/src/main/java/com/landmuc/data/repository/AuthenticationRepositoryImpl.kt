package com.landmuc.data.repository

import com.landmuc.domain.repository.AuthenticationRepository
import com.landmuc.network.SupabaseClient

class AuthenticationRepositoryImpl(
    private val client: SupabaseClient
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}