package com.baha.sushigarden.data.services.cart

import com.baha.sushigarden.data.models.AddOn
import com.baha.sushigarden.data.models.CartItem
import com.baha.sushigarden.data.models.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

interface CartService {
    val items: StateFlow<List<CartItem>>
    val selectedAddOns: StateFlow<Set<AddOn>>
    val totalRub: StateFlow<Int>

    fun addProduct(product: Product)

    fun removeProduct(productId: String)

    fun incrementQuantity(productId: String)

    fun decrementQuantity(productId: String)

    fun toggleAddOn(addOn: AddOn)

    fun clear()
}

@Singleton
class InMemoryCartService
    @Inject
    constructor() : CartService {
        override val items = MutableStateFlow<List<CartItem>>(emptyList())
        override val selectedAddOns = MutableStateFlow<Set<AddOn>>(emptySet())
        override val totalRub = MutableStateFlow(0)

        private val scope = CoroutineScope(Dispatchers.Default + SupervisorJob())

        init {
            scope.launch {
                combine(items, selectedAddOns) { itemList, addOns ->
                    itemList.sumOf { it.lineTotal } + addOns.sumOf { it.priceRub }
                }.collect { total ->
                    totalRub.value = total
                }
            }
        }

        override fun addProduct(product: Product) {
            Timber.d("Cart add product: ${product.name}")
            items.update { current ->
                val existing = current.find { it.product.id == product.id }
                if (existing != null) {
                    current.map { if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it }
                } else {
                    current + CartItem(product, 1)
                }
            }
        }

        override fun removeProduct(productId: String) {
            Timber.d("Cart remove product: $productId")
            items.update { current -> current.filter { it.product.id != productId } }
        }

        override fun incrementQuantity(productId: String) {
            Timber.d("Cart increment qty: $productId")
            items.update { current ->
                current.map { if (it.product.id == productId) it.copy(quantity = it.quantity + 1) else it }
            }
        }

        override fun decrementQuantity(productId: String) {
            Timber.d("Cart decrement qty: $productId")
            items.update { current ->
                current.mapNotNull {
                    if (it.product.id == productId) {
                        if (it.quantity <= 1) null else it.copy(quantity = it.quantity - 1)
                    } else {
                        it
                    }
                }
            }
        }

        override fun toggleAddOn(addOn: AddOn) {
            Timber.d("Cart toggle add-on: ${addOn.name}")
            selectedAddOns.update { current ->
                if (current.any { it.id == addOn.id }) {
                    current.filter { it.id != addOn.id }.toSet()
                } else {
                    current + addOn
                }
            }
        }

        override fun clear() {
            Timber.i("Cart cleared")
            items.value = emptyList()
            selectedAddOns.value = emptySet()
        }
    }
