package com.baha.sushigarden.data.services.auth

import com.baha.sushigarden.BuildConfig
import com.baha.sushigarden.data.models.UserProfile
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeAuthService
    @Inject
    constructor() : AuthService {
        private var signedInUser: UserProfile? =
            if (BuildConfig.IS_UI_TEST) {
                null
            } else {
                UserProfile("demo_user", "Александр Новиков", "demo@sushigarden.app")
            }

        override val currentUser: UserProfile?
            get() = signedInUser

        override suspend fun register(
            name: String,
            email: String,
            password: String,
        ): Result<UserProfile> {
            Timber.i("FakeAuth register: email=$email")
            if (name.isBlank() || !email.contains("@") || password.length < 6) {
                Timber.w("FakeAuth register validation failed")
                return Result.failure(AuthException("Неверная почта или пароль"))
            }
            val profile = UserProfile("fake_${email.hashCode()}", name, email)
            signedInUser = profile
            return Result.success(profile)
        }

        override suspend fun login(
            email: String,
            password: String,
        ): Result<UserProfile> {
            Timber.i("FakeAuth login: email=$email")
            if (!email.contains("@") || password.length < 6) {
                Timber.w("FakeAuth login validation failed")
                return Result.failure(AuthException("Неверная почта или пароль"))
            }
            val profile = UserProfile("fake_${email.hashCode()}", "Александр Новиков", email)
            signedInUser = profile
            return Result.success(profile)
        }

        override suspend fun signOut() {
            Timber.i("FakeAuth signOut")
            signedInUser = null
        }
    }
