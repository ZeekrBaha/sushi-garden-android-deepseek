package com.baha.sushigarden.data.models

data class DeliveryAddress(
    val title: String,
) {
    companion object {
        val demo = DeliveryAddress("Воронеж, Мира 36")
    }
}
