package com.baha.sushigarden.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.services.auth.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class AuthUiState(
    val isRegisterMode: Boolean = true,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isConsentChecked: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)

@HiltViewModel
class AuthViewModel
    @Inject
    constructor(
        private val authService: AuthService,
    ) : ViewModel() {
        private val _state = MutableStateFlow(AuthUiState())
        val state: StateFlow<AuthUiState> = _state.asStateFlow()

        val isAuthenticated = MutableStateFlow(authService.currentUser != null)

        init {
            Timber.i("AuthViewModel initialized, user: ${authService.currentUser?.email}")
        }

        fun onAuthSuccess() {
            Timber.i("Auth success, setting authenticated flag")
            isAuthenticated.value = true
        }

        fun toggleMode() {
            val newMode = !_state.value.isRegisterMode
            Timber.d("Toggling auth mode: ${if (newMode) "register" else "login"}")
            _state.value =
                _state.value.copy(
                    isRegisterMode = newMode,
                    error = null,
                )
        }

        fun updateName(name: String) {
            _state.value = _state.value.copy(name = name)
        }

        fun updateEmail(email: String) {
            _state.value = _state.value.copy(email = email)
        }

        fun updatePassword(password: String) {
            _state.value = _state.value.copy(password = password)
        }

        fun togglePasswordVisibility() {
            _state.value = _state.value.copy(isPasswordVisible = !_state.value.isPasswordVisible)
        }

        fun toggleConsent() {
            _state.value = _state.value.copy(isConsentChecked = !_state.value.isConsentChecked)
        }

        fun submit() {
            val s = _state.value
            if (s.isLoading) return

            if (s.isRegisterMode && !s.isConsentChecked) {
                _state.value = s.copy(error = "Подтвердите согласие с условиями")
                return
            }

            Timber.i("Submitting auth: mode=${if (s.isRegisterMode) "register" else "login"}, email=${s.email}")

            _state.value = s.copy(isLoading = true, error = null)

            viewModelScope.launch {
                val result =
                    if (s.isRegisterMode) {
                        authService.register(s.name, s.email, s.password)
                    } else {
                        authService.login(s.email, s.password)
                    }

                result.fold(
                    onSuccess = {
                        Timber.i("Auth successful for ${s.email}")
                        _state.value = _state.value.copy(isLoading = false)
                        isAuthenticated.value = true
                    },
                    onFailure = { e ->
                        Timber.e(e, "Auth failed for ${s.email}")
                        _state.value = _state.value.copy(isLoading = false, error = e.message ?: "Что-то пошло не так")
                    },
                )
            }
        }

        fun signOut() {
            Timber.i("Signing out")
            viewModelScope.launch {
                authService.signOut()
                isAuthenticated.value = false
                _state.value = AuthUiState()
            }
        }
    }
