package com.baha.sushigarden.features.orders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.models.Order
import com.baha.sushigarden.data.models.OrderLine
import com.baha.sushigarden.data.services.orders.OrderDao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OrdersUiState(
    val orders: List<Order> = emptyList(),
)

data class OrderDetailUiState(
    val order: Order? = null,
)

@HiltViewModel
class OrdersViewModel
    @Inject
    constructor(
        private val orderDao: OrderDao,
    ) : ViewModel() {
        private val _state = MutableStateFlow(OrdersUiState())
        val state: StateFlow<OrdersUiState> = _state.asStateFlow()

        private val _detailState = MutableStateFlow(OrderDetailUiState())
        val detailState: StateFlow<OrderDetailUiState> = _detailState.asStateFlow()

        init {
            viewModelScope.launch {
                orderDao.getAllOrders().collect { entities ->
                    val orders =
                        entities.map { entity ->
                            val type = object : TypeToken<List<OrderLine>>() {}.type
                            val lines: List<OrderLine> = Gson().fromJson(entity.linesJson, type)
                            Order(entity.id, entity.createdAt, entity.totalRub, lines)
                        }
                    _state.value = OrdersUiState(orders = orders)
                }
            }
        }

        fun loadOrderDetail(orderId: String) {
            viewModelScope.launch {
                val entity = orderDao.getOrderById(orderId) ?: return@launch
                val type = object : TypeToken<List<OrderLine>>() {}.type
                val lines: List<OrderLine> = Gson().fromJson(entity.linesJson, type)
                _detailState.value =
                    OrderDetailUiState(
                        order = Order(entity.id, entity.createdAt, entity.totalRub, lines),
                    )
            }
        }
    }
