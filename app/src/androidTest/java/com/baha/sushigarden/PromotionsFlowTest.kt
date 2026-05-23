package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.features.promotions.PromotionsScreen
import com.baha.sushigarden.features.promotions.PromotionsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PromotionsFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun promotionsScreen_displaysBanners() {
        composeRule.setContent {
            val vm: PromotionsViewModel = hiltViewModel()
            PromotionsScreen(viewModel = vm)
        }
        composeRule.onNodeWithText("Акции").assertIsDisplayed()
    }
}
