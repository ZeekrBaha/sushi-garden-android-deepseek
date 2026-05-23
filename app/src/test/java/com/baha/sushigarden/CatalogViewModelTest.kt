package com.baha.sushigarden

import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.services.catalog.LocalMenuRepository
import com.baha.sushigarden.features.catalog.CatalogViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CatalogViewModelTest {
    @Test
    fun `initial state selects rolls category`() {
        val vm = CatalogViewModel(LocalMenuRepository())
        assertEquals(Category.rolls, vm.state.value.selectedCategory)
    }

    @Test
    fun `selectCategory filters products`() {
        val vm = CatalogViewModel(LocalMenuRepository())
        vm.selectCategory(Category.sushi)
        assertTrue(
            vm.state.value.products
                .all { it.category == Category.sushi },
        )
    }

    @Test
    fun `selectCategory switches products`() {
        val vm = CatalogViewModel(LocalMenuRepository())
        vm.selectCategory(Category.wok)
        assertEquals(1, vm.state.value.products.size)
    }

    @Test
    fun `categories list contains all`() {
        val vm = CatalogViewModel(LocalMenuRepository())
        assertEquals(Category.entries.size, vm.state.value.categories.size)
    }
}
