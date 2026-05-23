package com.baha.sushigarden.features.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.baha.sushigarden.ui.designsystem.AppColor
import com.baha.sushigarden.ui.designsystem.AppFont
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun TrackingScreen(
    viewModel: TrackingViewModel =
        androidx.hilt.navigation.compose
            .hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(TrackingConstants.restaurantLatLng, 14f)
        }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings =
                com.google.maps.android.compose.MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false,
                    scrollGesturesEnabled = true,
                ),
        ) {
            val restaurantMarkerState = remember { MarkerState(position = TrackingConstants.restaurantLatLng) }
            val destinationMarkerState = remember { MarkerState(position = TrackingConstants.destinationLatLng) }

            Marker(
                state = restaurantMarkerState,
                title = "Ресторан",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
            )

            Marker(
                state = destinationMarkerState,
                title = "Воронеж, Мира 36",
                icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
            )

            if (!state.isDelivered) {
                val courierMarkerState = remember(state.courierLatLng) { MarkerState(position = state.courierLatLng) }
                Marker(
                    state = courierMarkerState,
                    title = "Курьер",
                    snippet = "${state.etaSeconds / 60} мин",
                    icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                )
            }

            Polyline(
                points = listOf(TrackingConstants.restaurantLatLng, state.courierLatLng),
                color = Color(0xFFEC1A35),
                width = 6f,
            )
        }

        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 16.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColor.tabBar)
                        .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        modifier =
                            Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(AppColor.pricePill),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = AppColor.textSecondary,
                            modifier = Modifier.size(24.dp),
                        )
                    }

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = state.courierName,
                            style = AppFont.productTitle,
                            color = AppColor.textPrimary,
                        )
                        Text(
                            text = state.courierRole,
                            style = AppFont.weight,
                            color = AppColor.textSecondary,
                        )
                    }

                    Text(
                        text = if (state.isDelivered) "Доставлен" else "${state.etaSeconds / 60} мин",
                        style = AppFont.price,
                        color = AppColor.accent,
                    )
                }
            }
        }
    }
}
