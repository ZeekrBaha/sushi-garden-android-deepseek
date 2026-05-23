package com.baha.sushigarden.data.services.catalog

import com.baha.sushigarden.data.models.Banner
import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.models.Product
import javax.inject.Inject
import javax.inject.Singleton

interface MenuRepository {
    fun getProducts(): List<Product>

    fun getProductsByCategory(category: Category): List<Product>

    fun getProductById(id: String): Product?

    fun getCategories(): List<Category>

    fun getBanners(): List<Banner>
}

@Singleton
class LocalMenuRepository
    @Inject
    constructor() : MenuRepository {
        private val allProducts =
            listOf(
                Product("hikari", "Хикари", Category.rolls, 620, 255, "product_hikari", "Креветка в темпуре, сливочный сыр, огурец."),
                Product("la", "Лос-Анджелес", Category.rolls, 707, 285, "product_la", "Лосось, сливочный сыр, авокадо, икра тобико."),
                Product("idaho", "Айдахо маки", Category.rolls, 810, 285, "product_idaho", "Запечённый ролл с лососем и сыром."),
                Product("osaka", "Осака маки", Category.rolls, 740, 275, "product_osaka", "Угорь, огурец, унаги соус."),
                Product("sushi_salmon", "Суши с лососем", Category.sushi, 120, 35, "product_la", "Свежий лосось на рисе."),
                Product("sushi_eel", "Суши с угрём", Category.sushi, 150, 35, "product_osaka", "Копчёный угорь на рисе."),
                Product("hot_ebi", "Эби темпура", Category.hotRolls, 690, 260, "product_idaho", "Ролл с креветкой в темпуре."),
                Product("salad_chuka", "Чука салат", Category.salads, 320, 150, "product_hikari", "Салат чука с ореховым соусом."),
                Product("wok_udon", "Удон с курицей", Category.wok, 450, 350, "product_idaho", "Лапша удон с курицей и овощами."),
            )

        private val banners =
            listOf(
                Banner("promo_1", "banner_promo_1"),
                Banner("promo_2", "banner_promo_2"),
            )

        override fun getProducts(): List<Product> = allProducts

        override fun getProductsByCategory(category: Category): List<Product> = allProducts.filter { it.category == category }

        override fun getProductById(id: String): Product? = allProducts.find { it.id == id }

        override fun getCategories(): List<Category> = Category.entries

        override fun getBanners(): List<Banner> = banners
    }
