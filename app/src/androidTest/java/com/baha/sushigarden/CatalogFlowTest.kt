package com.baha.sushigarden

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.baha.sushigarden.data.services.catalog.MenuRepository
import com.baha.sushigarden.features.catalog.CatalogScreen
import com.baha.sushigarden.features.catalog.CatalogViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CatalogFlowTest {
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
    fun catalog_displaysCategoryPills() {
        composeRule.setContent {
            val vm = remember { CatalogViewModel(menuRepository) }
            CatalogScreen(onProductClick = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Суши").assertIsDisplayed()
        composeRule.onNodeWithText("Роллы").assertIsDisplayed()
        composeRule.onNodeWithText("Горячие роллы").assertIsDisplayed()
        composeRule.onNodeWithText("Салаты").assertIsDisplayed()
        composeRule.onNodeWithText("WOK").assertIsDisplayed()
    }

    @Test
    fun catalog_showsProductGrid() {
        composeRule.setContent {
            val vm = remember { CatalogViewModel(menuRepository) }
            CatalogScreen(onProductClick = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Хикари").assertIsDisplayed()
        composeRule.onNodeWithText("Лос-Анджелес").assertIsDisplayed()
    }

    @Test
    fun catalog_switchingCategoryShowsProducts() {
        composeRule.setContent {
            val vm = remember { CatalogViewModel(menuRepository) }
            CatalogScreen(onProductClick = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Суши").performClick()
        composeRule.onNodeWithText("Суши с лососем").assertIsDisplayed()
    }
}
