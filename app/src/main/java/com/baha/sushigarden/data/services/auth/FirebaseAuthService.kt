package com.baha.sushigarden.data.services.auth

import com.baha.sushigarden.data.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAuthService
    @Inject
    constructor() : AuthService {
        private val auth = FirebaseAuth.getInstance()

        override val currentUser: UserProfile?
            get() =
                auth.currentUser?.let {
                    UserProfile(
                        id = it.uid,
                        name = it.displayName ?: "",
                        email = it.email ?: "",
                    )
                }

        override suspend fun register(
            name: String,
            email: String,
            password: String,
        ): Result<UserProfile> {
            return try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                result.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
                val user = result.user ?: return Result.failure(AuthException("Registration failed"))
                Result.success(UserProfile(user.uid, name, email))
            } catch (e: Exception) {
                Result.failure(AuthException(mapFirebaseError(e)))
            }
        }

        override suspend fun login(
            email: String,
            password: String,
        ): Result<UserProfile> {
            return try {
                val result = auth.signInWithEmailAndPassword(email, password).await()
                val user = result.user ?: return Result.failure(AuthException("Login failed"))
                Result.success(UserProfile(user.uid, user.displayName ?: "", email))
            } catch (e: Exception) {
                Result.failure(AuthException(mapFirebaseError(e)))
            }
        }

        override suspend fun signOut() {
            auth.signOut()
        }

        private fun mapFirebaseError(e: Exception): String {
            val msg = e.message ?: ""
            return when {
                msg.contains("INVALID_LOGIN_CREDENTIALS", ignoreCase = true) -> "Неверная почта или пароль"
                msg.contains("NETWORK_ERROR", ignoreCase = true) -> "Нет соединения"
                msg.contains("EMAIL_EXISTS", ignoreCase = true) -> "Эта почта уже зарегистрирована"
                msg.contains("WEAK_PASSWORD", ignoreCase = true) -> "Пароль должен быть минимум 6 символов"
                msg.contains("INVALID_EMAIL", ignoreCase = true) -> "Неверный формат почты"
                else -> "Что-то пошло не так"
            }
        }
    }
