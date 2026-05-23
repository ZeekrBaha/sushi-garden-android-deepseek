package com.baha.sushigarden.data.services.delivery

import com.baha.sushigarden.data.models.Courier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

data class CourierState(
    val courier: Courier = Courier(),
    val progress: Float = 0f,
    val etaSeconds: Int = 1500,
    val isDelivered: Boolean = false,
)

@Singleton
class CourierSimulator
    @Inject
    constructor() {
        private val _state = MutableStateFlow(CourierState())
        val state: StateFlow<CourierState> = _state

        private var job: Job? = null
        private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        fun start() {
            stop()
            _state.value = CourierState()
            job =
                scope.launch {
                    val totalSeconds = _state.value.etaSeconds
                    for (i in 0..totalSeconds) {
                        if (!isActive) break
                        val progress = i.toFloat() / totalSeconds
                        _state.value =
                            CourierState(
                                progress = progress,
                                etaSeconds = totalSeconds - i,
                                isDelivered = i >= totalSeconds,
                            )
                        delay(1000L)
                    }
                }
        }

        fun stop() {
            job?.cancel()
            job = null
        }
    }
