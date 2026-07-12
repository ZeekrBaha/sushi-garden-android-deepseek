package com.baha.sushigarden

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.baha.sushigarden.data.services.cart.CartService
import com.baha.sushigarden.data.services.orders.OrderDao
import com.baha.sushigarden.features.checkout.CheckoutScreen
import com.baha.sushigarden.features.checkout.CheckoutViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CheckoutFlowTest {
    @Inject
    lateinit var cartService: CartService

    @Inject
    lateinit var orderDao: OrderDao

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun checkoutScreen_showsFields() {
        composeRule.setContent {
            val vm = remember { CheckoutViewModel(cartService, orderDao) }
            CheckoutScreen(onOrderPlaced = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Итого").assertIsDisplayed()
        composeRule.onNodeWithText("Подтвердить").assertIsDisplayed()
    }

    @Test
    fun checkout_showsDeliveryFees() {
        composeRule.setContent {
            val vm = remember { CheckoutViewModel(cartService, orderDao) }
            CheckoutScreen(onOrderPlaced = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Доставка").assertIsDisplayed()
        composeRule.onNodeWithText("Сервисный сбор").assertIsDisplayed()
    }
}
