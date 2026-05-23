package com.baha.sushigarden.features.checkout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.models.OrderEntity
import com.baha.sushigarden.data.models.OrderLine
import com.baha.sushigarden.data.services.cart.CartService
import com.baha.sushigarden.data.services.delivery.FieldValidators
import com.baha.sushigarden.data.services.orders.OrderDao
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class CheckoutUiState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val deliveryFee: Int = 76,
    val serviceFee: Int = 76,
    val subtotal: Int = 0,
    val total: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false,
    val placedOrderId: String? = null,
)

@HiltViewModel
class CheckoutViewModel
    @Inject
    constructor(
        private val cartService: CartService,
        private val orderDao: OrderDao,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CheckoutUiState())
        val state: StateFlow<CheckoutUiState> = _state.asStateFlow()

        init {
            Timber.i("CheckoutViewModel initialized")
            viewModelScope.launch {
                cartService.totalRub.collect { subtotal ->
                    _state.value =
                        _state.value.copy(
                            subtotal = subtotal,
                            total = subtotal + _state.value.deliveryFee + _state.value.serviceFee,
                        )
                }
            }
        }

        fun updateName(name: String) {
            _state.value = _state.value.copy(name = name)
        }

        fun updatePhone(phone: String) {
            _state.value = _state.value.copy(phone = phone)
        }

        fun updateEmail(email: String) {
            _state.value = _state.value.copy(email = email)
        }

        private fun validate(): String? {
            val s = _state.value
            if (s.name.isBlank()) return "Введите имя"
            if (s.phone.isBlank()) return "Введите телефон"
            if (!FieldValidators.isValidPhone(s.phone)) return "Неверный формат телефона"
            if (s.email.isBlank()) return "Введите email"
            if (!s.email.contains("@") || !s.email.contains(".")) return "Неверный формат email"
            return null
        }

        fun placeOrder() {
            val validationError = validate()
            if (validationError != null) {
                Timber.w("Place order validation failed: $validationError")
                _state.value = _state.value.copy(error = validationError)
                return
            }

            viewModelScope.launch {
                _state.value = _state.value.copy(isLoading = true, error = null)
                try {
                    val items = cartService.items.first()
                    if (items.isEmpty()) {
                        _state.value =
                            _state.value.copy(isLoading = false, error = "Корзина пуста")
                        return@launch
                    }

                    val addOnsTotal = cartService.selectedAddOns.first().sumOf { it.priceRub }
                    val lines = items.map { OrderLine(it.product.name, it.quantity, it.product.priceRub) }
                    val subtotal = items.sumOf { it.lineTotal } + addOnsTotal
                    val total = subtotal + _state.value.deliveryFee + _state.value.serviceFee

                    val orderId = "order_${System.currentTimeMillis()}"
                    val orderEntity =
                        OrderEntity(
                            id = orderId,
                            createdAt = System.currentTimeMillis(),
                            totalRub = total,
                            linesJson = Gson().toJson(lines),
                        )
                    orderDao.insertOrder(orderEntity)
                    Timber.i("Order placed: id=$orderId, total=$total, items=${items.size}")
                    cartService.clear()

                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            isSuccess = true,
                            placedOrderId = orderId,
                        )
                } catch (e: Exception) {
                    Timber.e(e, "Place order failed")
                    _state.value =
                        _state.value.copy(
                            isLoading = false,
                            error = "Не удалось оформить заказ",
                        )
                }
            }
        }
    }
