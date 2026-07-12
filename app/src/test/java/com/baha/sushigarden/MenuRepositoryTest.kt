package com.baha.sushigarden

import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.services.catalog.LocalMenuRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class MenuRepositoryTest {
    private val repo = LocalMenuRepository()

    @Test
    fun `getProducts returns all items`() {
        assertEquals(9, repo.getProducts().size)
    }

    @Test
    fun `getProductsByCategory returns filtered items`() {
        val rolls = repo.getProductsByCategory(Category.Rolls)
        assertEquals(4, rolls.size)
    }

    @Test
    fun `getProductById returns correct product`() {
        val product = repo.getProductById("hikari")
        assertNotNull(product)
        assertEquals("Хикари", product?.name)
    }

    @Test
    fun `getProductById returns null for unknown`() {
        assertNull(repo.getProductById("nonexistent"))
    }

    @Test
    fun `getCategories returns all categories`() {
        assertEquals(5, repo.getCategories().size)
    }

    @Test
    fun `getBanners returns banners`() {
        assertEquals(2, repo.getBanners().size)
    }
}
