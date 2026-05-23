package com.baha.sushigarden.features.productdetail

import androidx.lifecycle.ViewModel
import com.baha.sushigarden.data.models.Product
import com.baha.sushigarden.data.services.cart.CartService
import com.baha.sushigarden.data.services.catalog.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

data class ProductDetailUiState(
    val product: Product? = null,
    val quantity: Int = 1,
)

@HiltViewModel
class ProductDetailViewModel
    @Inject
    constructor(
        private val menuRepository: MenuRepository,
        private val cartService: CartService,
    ) : ViewModel() {
        private val _state = MutableStateFlow(ProductDetailUiState())
        val state: StateFlow<ProductDetailUiState> = _state.asStateFlow()

        fun loadProduct(productId: String) {
            Timber.d("Loading product: $productId")
            val product = menuRepository.getProductById(productId)
            _state.value = ProductDetailUiState(product = product)
            if (product == null) {
                Timber.w("Product not found: $productId")
            }
        }

        fun incrementQuantity() {
            Timber.d("Product qty increment: ${_state.value.quantity + 1}")
            _state.value = _state.value.copy(quantity = _state.value.quantity + 1)
        }

        fun decrementQuantity() {
            val q = _state.value.quantity
            if (q > 1) {
                Timber.d("Product qty decrement: ${q - 1}")
                _state.value = _state.value.copy(quantity = q - 1)
            }
        }

        fun addToCart() {
            val product = _state.value.product
            if (product == null) {
                Timber.w("Add to cart failed: no product loaded")
                return
            }
            Timber.i("Adding to cart: product=${product.name}, qty=${_state.value.quantity}")
            repeat(_state.value.quantity) {
                cartService.addProduct(product)
            }
            _state.value = _state.value.copy(quantity = 1)
        }
    }
