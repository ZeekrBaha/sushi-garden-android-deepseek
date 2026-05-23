package com.baha.sushigarden.features.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.services.delivery.CourierSimulator
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TrackingUiState(
    val courierName: String = "Максим Винокур",
    val courierRole: String = "Курьер",
    val etaSeconds: Int = 1500,
    val progress: Float = 0f,
    val isDelivered: Boolean = false,
    val courierLatLng: LatLng = TrackingConstants.restaurantLatLng,
)

object TrackingConstants {
    const val RESTAURANT_LAT = 51.6608
    const val RESTAURANT_LNG = 39.2003
    const val DESTINATION_LAT = 51.6720
    const val DESTINATION_LNG = 39.1843

    val restaurantLatLng = LatLng(RESTAURANT_LAT, RESTAURANT_LNG)
    val destinationLatLng = LatLng(DESTINATION_LAT, DESTINATION_LNG)
}

@HiltViewModel
class TrackingViewModel
    @Inject
    constructor(
        private val courierSimulator: CourierSimulator,
    ) : ViewModel() {
        private val _state = MutableStateFlow(TrackingUiState())
        val state: StateFlow<TrackingUiState> = _state.asStateFlow()

        init {
            courierSimulator.start()
            viewModelScope.launch {
                courierSimulator.state.collect { courierState ->
                    val courierLat =
                        TrackingConstants.RESTAURANT_LAT +
                            (TrackingConstants.DESTINATION_LAT - TrackingConstants.RESTAURANT_LAT) * courierState.progress
                    val courierLng =
                        TrackingConstants.RESTAURANT_LNG +
                            (TrackingConstants.DESTINATION_LNG - TrackingConstants.RESTAURANT_LNG) * courierState.progress
                    _state.value =
                        TrackingUiState(
                            courierName = courierState.courier.name,
                            courierRole = courierState.courier.role,
                            etaSeconds = courierState.etaSeconds,
                            progress = courierState.progress,
                            isDelivered = courierState.isDelivered,
                            courierLatLng = LatLng(courierLat, courierLng),
                        )
                }
            }
        }

        override fun onCleared() {
            super.onCleared()
            courierSimulator.stop()
        }
    }
