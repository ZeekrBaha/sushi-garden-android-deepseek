package com.baha.sushigarden.data.models

data class Product(
    val id: String,
    val name: String,
    val category: Category,
    val priceRub: Int,
    val weightGrams: Int,
    val imageName: String,
    val description: String,
)

enum class Category(
    val displayName: String,
) {
    sushi("Суши"),
    rolls("Роллы"),
    hotRolls("Горячие роллы"),
    salads("Салаты"),
    wok("WOK"),
}
