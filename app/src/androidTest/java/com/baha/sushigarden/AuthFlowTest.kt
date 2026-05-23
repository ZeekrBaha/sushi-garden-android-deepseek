package com.baha.sushigarden

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.baha.sushigarden.data.services.auth.AuthService
import com.baha.sushigarden.features.auth.AuthScreen
import com.baha.sushigarden.features.auth.AuthViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class AuthFlowTest {
    @Inject
    lateinit var authService: AuthService

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun registerScreen_showsToggleLink() {
        composeRule.setContent {
            val vm = remember { AuthViewModel(authService) }
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Уже есть аккаунт?").assertIsDisplayed()
    }

    @Test
    fun toggleToLogin_showsLoginToggle() {
        composeRule.setContent {
            val vm = remember { AuthViewModel(authService) }
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Уже есть аккаунт?").performClick()
        composeRule.onNodeWithText("У вас нет аккаунта?").assertIsDisplayed()
    }
}
