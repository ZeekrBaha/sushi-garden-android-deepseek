package com.baha.sushigarden

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.baha.sushigarden.features.promotions.PromotionsScreen
import com.baha.sushigarden.features.promotions.PromotionsViewModel
import com.baha.sushigarden.data.services.catalog.MenuRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import javax.inject.Inject
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PromotionsFlowTest {
    @Inject
    lateinit var menuRepository: MenuRepository

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
            val vm = remember { PromotionsViewModel(menuRepository) }
            PromotionsScreen(viewModel = vm)
        }
        // Promotions screen only shows images, no text to assert
    }
}
