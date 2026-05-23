package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.features.orders.OrdersScreen
import com.baha.sushigarden.features.orders.OrdersViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class OrdersFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun emptyOrders_showsEmptyState() {
        composeRule.setContent {
            val vm: OrdersViewModel = hiltViewModel()
            OrdersScreen(onOrderClick = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Заказов пока нет").assertIsDisplayed()
    }
}
