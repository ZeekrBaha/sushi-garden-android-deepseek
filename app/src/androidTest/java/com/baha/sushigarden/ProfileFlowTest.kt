package com.baha.sushigarden

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import com.baha.sushigarden.features.profile.ProfileScreen
import com.baha.sushigarden.features.profile.ProfileViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ProfileFlowTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun profileScreen_showsLogoutButton() {
        composeRule.setContent {
            val vm: ProfileViewModel = hiltViewModel()
            ProfileScreen(onLogout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Выйти").assertIsDisplayed()
    }
}
