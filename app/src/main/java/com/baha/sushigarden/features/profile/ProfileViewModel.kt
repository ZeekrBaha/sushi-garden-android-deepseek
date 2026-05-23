package com.baha.sushigarden.features.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.models.UserProfile
import com.baha.sushigarden.data.services.auth.AuthService
import com.baha.sushigarden.data.services.orders.OrderDao
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class ProfileUiState(
    val user: UserProfile? = null,
    val phone: String = "",
    val orderCount: Int = 0,
    val isLoggingOut: Boolean = false,
)

@HiltViewModel
class ProfileViewModel
    @Inject
    constructor(
        private val authService: AuthService,
        private val orderDao: OrderDao,
        @ApplicationContext private val context: Context,
    ) : ViewModel() {
        private val prefs = context.getSharedPreferences("sushi_garden_profile", Context.MODE_PRIVATE)

        private val _state = MutableStateFlow(ProfileUiState())
        val state: StateFlow<ProfileUiState> = _state.asStateFlow()

        init {
            Timber.i("ProfileViewModel initialized, user: ${authService.currentUser?.email}")
            val savedPhone = prefs.getString("phone", "") ?: ""
            _state.value = ProfileUiState(user = authService.currentUser, phone = savedPhone)
            viewModelScope.launch {
                val count = orderDao.getAllOrders().first().size
                Timber.d("Profile order count loaded: $count")
                _state.value = _state.value.copy(orderCount = count)
            }
        }

        fun updatePhone(phone: String) {
            Timber.d("Profile phone updated")
            _state.value = _state.value.copy(phone = phone)
            prefs.edit().putString("phone", phone).apply()
        }

        fun logout() {
            Timber.i("Profile logout")
            _state.value = _state.value.copy(isLoggingOut = true)
            viewModelScope.launch {
                authService.signOut()
            }
        }
    }
