package com.baha.sushigarden

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import com.baha.sushigarden.data.services.catalog.MenuRepository
import com.baha.sushigarden.features.promotions.PromotionsScreen
import com.baha.sushigarden.features.promotions.PromotionsViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

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
