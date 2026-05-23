package com.baha.sushigarden.data.models

data class AddOn(
    val id: String,
    val name: String,
    val priceRub: Int,
)

val defaultAddOns =
    listOf(
        AddOn("wasabi", "Васаби", 60),
        AddOn("ginger", "Имбирь", 60),
        AddOn("soy", "Соевый соус", 60),
    )
