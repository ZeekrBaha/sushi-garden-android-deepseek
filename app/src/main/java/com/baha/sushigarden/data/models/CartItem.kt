package com.baha.sushigarden.data.models

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
) {
    val id: String get() = product.id
    val lineTotal: Int get() = product.priceRub * quantity
}
