package com.baha.sushigarden.features.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baha.sushigarden.data.models.AddOn
import com.baha.sushigarden.data.models.CartItem
import com.baha.sushigarden.data.models.defaultAddOns
import com.baha.sushigarden.data.services.cart.CartService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val addOns: List<AddOn> = defaultAddOns,
    val selectedAddOnIds: Set<String> = emptySet(),
    val totalRub: Int = 0,
)

@HiltViewModel
class CartViewModel
    @Inject
    constructor(
        private val cartService: CartService,
    ) : ViewModel() {
        private val _state = MutableStateFlow(CartUiState())
        val state: StateFlow<CartUiState> = _state.asStateFlow()

        init {
            Timber.i("CartViewModel initialized")
            viewModelScope.launch {
                cartService.items.collect { items ->
                    _state.value = _state.value.copy(items = items)
                }
            }
            viewModelScope.launch {
                cartService.selectedAddOns.collect { addOns ->
                    _state.value = _state.value.copy(selectedAddOnIds = addOns.map { it.id }.toSet())
                }
            }
            viewModelScope.launch {
                cartService.totalRub.collect { total ->
                    _state.value = _state.value.copy(totalRub = total)
                }
            }
        }

        fun incrementQuantity(productId: String) {
            Timber.d("Cart increment: product=$productId")
            cartService.incrementQuantity(productId)
        }

        fun decrementQuantity(productId: String) {
            Timber.d("Cart decrement: product=$productId")
            cartService.decrementQuantity(productId)
        }

        fun toggleAddOn(addOn: AddOn) {
            Timber.d("Cart toggle add-on: ${addOn.name}")
            cartService.toggleAddOn(addOn)
        }
    }
