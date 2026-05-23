package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.features.auth.AuthScreen
import com.baha.sushigarden.features.auth.AuthViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class AuthFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun registerScreen_displaysCorrectTitle() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Регистрация").assertIsDisplayed()
    }

    @Test
    fun register_disabledWithoutConsent() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("ИМЯ").performTextInput("Александр")
        composeRule.onNodeWithText("ПОЧТА").performTextInput("test@test.com")
        composeRule.onNodeWithText("ПАРОЛЬ").performTextInput("password123")
        composeRule.onNodeWithText("Регистрация").performClick()
        composeRule.onNodeWithText("Подтвердите согласие с условиями").assertIsDisplayed()
    }

    @Test
    fun register_withInvalidEmail_showsError() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("ИМЯ").performTextInput("Тест")
        composeRule.onNodeWithText("ПОЧТА").performTextInput("invalid")
        composeRule.onNodeWithText("ПАРОЛЬ").performTextInput("password123")
        composeRule.onNodeWithText("Я согласен с Условиями предоставления услуг и Политикой конфиденциальности").performClick()
        composeRule.onNodeWithText("Регистрация").performClick()
        composeRule.onNodeWithText("Неверная почта или пароль").assertIsDisplayed()
    }

    @Test
    fun toggleToLogin_showsLoginMode() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Уже есть аккаунт?").performClick()
        composeRule.onNodeWithText("Войти").assertIsDisplayed()
    }

    @Test
    fun loginWithWrongCredentials_showsError() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Уже есть аккаунт?").performClick()
        composeRule.onNodeWithText("ПОЧТА").performTextInput("wrong@test.com")
        composeRule.onNodeWithText("ПАРОЛЬ").performTextInput("123")
        composeRule.onNodeWithText("Войти").performClick()
        composeRule.onNodeWithText("Неверная почта или пароль").assertIsDisplayed()
    }

    @Test
    fun loginWithShortPassword_showsError() {
        composeRule.setContent {
            val vm: AuthViewModel = hiltViewModel()
            AuthScreen(viewModel = vm, onAuthSuccess = {})
        }
        composeRule.onNodeWithText("Уже есть аккаунт?").performClick()
        composeRule.onNodeWithText("ПОЧТА").performTextInput("test@test.com")
        composeRule.onNodeWithText("ПАРОЛЬ").performTextInput("12345")
        composeRule.onNodeWithText("Войти").performClick()
        composeRule.onNodeWithText("Неверная почта или пароль").assertIsDisplayed()
    }
}
