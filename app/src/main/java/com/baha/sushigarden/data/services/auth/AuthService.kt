package com.baha.sushigarden.data.services.auth

import com.baha.sushigarden.data.models.UserProfile

interface AuthService {
    val currentUser: UserProfile?

    suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Result<UserProfile>

    suspend fun login(
        email: String,
        password: String,
    ): Result<UserProfile>

    suspend fun signOut()
}

class AuthException(
    message: String,
) : Exception(message)
