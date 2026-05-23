package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.data.models.Category
import com.baha.sushigarden.data.models.Product
import com.baha.sushigarden.data.services.cart.CartService
import com.baha.sushigarden.features.cart.CartScreen
import com.baha.sushigarden.features.cart.CartViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CartFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Inject
    lateinit var cartService: CartService

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun emptyCart_showsEmptyState() {
        composeRule.setContent {
            val vm: CartViewModel = hiltViewModel()
            CartScreen(onCheckout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Корзина пуста").assertIsDisplayed()
    }

    @Test
    fun filledCart_showsCartItems() {
        val product = Product("hikari", "Хикари", Category.rolls, 620, 255, "product_hikari", "desc")
        cartService.addProduct(product)

        composeRule.setContent {
            val vm: CartViewModel = hiltViewModel()
            CartScreen(onCheckout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Хикари").assertIsDisplayed()
        composeRule.onNodeWithText("Оформить заказ · 620 ₽").assertIsDisplayed()
    }

    @Test
    fun filledCart_showsCheckoutButton() {
        val product = Product("la", "Лос-Анджелес", Category.rolls, 707, 285, "product_la", "desc")
        cartService.addProduct(product)

        composeRule.setContent {
            val vm: CartViewModel = hiltViewModel()
            CartScreen(onCheckout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Оформить заказ · 707 ₽").assertIsDisplayed()
    }

    @Test
    fun cart_showsAddOns() {
        composeRule.setContent {
            val vm: CartViewModel = hiltViewModel()
            CartScreen(onCheckout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Добавить еще").assertIsDisplayed()
        composeRule.onNodeWithText("Васаби").assertIsDisplayed()
        composeRule.onNodeWithText("Имбирь").assertIsDisplayed()
    }
}
