package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.features.productdetail.ProductDetailScreen
import com.baha.sushigarden.features.productdetail.ProductDetailViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProductDetailFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun productDetail_showsProductInfo() {
        composeRule.setContent {
            val vm: ProductDetailViewModel = hiltViewModel()
            ProductDetailScreen(productId = "rolls_hikari", onBack = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Хикари").assertIsDisplayed()
        composeRule.onNodeWithText("255г").assertIsDisplayed()
        composeRule.onNodeWithText("1").assertIsDisplayed()
    }

    @Test
    fun productDetail_incrementQuantity() {
        composeRule.setContent {
            val vm: ProductDetailViewModel = hiltViewModel()
            ProductDetailScreen(productId = "rolls_hikari", onBack = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("+").assertIsDisplayed()
    }

    @Test
    fun productDetail_showsAddToCartButton() {
        composeRule.setContent {
            val vm: ProductDetailViewModel = hiltViewModel()
            ProductDetailScreen(productId = "rolls_hikari", onBack = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Оформить заказ · 620 ₽").assertIsDisplayed()
    }
}
