package com.baha.sushigarden

import com.baha.sushigarden.data.models.AddOn
import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.models.Product
import com.baha.sushigarden.data.services.cart.InMemoryCartService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CartServiceTest {
    private val cartService = InMemoryCartService()
    private val product = Product("hikari", "Хикари", Category.Rolls, 620, 255, "product_hikari", "desc")

    @Test
    fun `addProduct adds item to cart`() =
        runTest {
            cartService.addProduct(product)
            val items = cartService.items.first()
            assertEquals(1, items.size)
            assertEquals("hikari", items.first().id)
            cartService.clear()
        }

    @Test
    fun `incrementQuantity increases quantity`() =
        runTest {
            cartService.addProduct(product)
            cartService.incrementQuantity("hikari")
            val items = cartService.items.first()
            assertEquals(2, items.first().quantity)
            cartService.clear()
        }

    @Test
    fun `decrementQuantity removes item at 1`() =
        runTest {
            cartService.addProduct(product)
            cartService.decrementQuantity("hikari")
            val items = cartService.items.first()
            assertTrue(items.isEmpty())
        }

    @Test
    fun `toggleAddOn adds and removes addon`() =
        runTest {
            val addOn = AddOn("wasabi", "Васаби", 60)
            cartService.toggleAddOn(addOn)
            val addons = cartService.selectedAddOns.first()
            assertEquals(1, addons.size)
            cartService.toggleAddOn(addOn)
            val addonsAfter = cartService.selectedAddOns.first()
            assertTrue(addonsAfter.isEmpty())
        }

    @Test
    fun `clear empties cart`() =
        runTest {
            cartService.addProduct(product)
            cartService.clear()
            val items = cartService.items.first()
            assertTrue(items.isEmpty())
        }
}
