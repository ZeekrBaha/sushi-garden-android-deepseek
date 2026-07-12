package com.baha.sushigarden

import android.content.Context
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.baha.sushigarden.data.services.auth.AuthService
import com.baha.sushigarden.data.services.orders.OrderDao
import com.baha.sushigarden.features.profile.ProfileScreen
import com.baha.sushigarden.features.profile.ProfileViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ProfileFlowTest {
    @Inject
    lateinit var authService: AuthService

    @Inject
    lateinit var orderDao: OrderDao

    @Inject
    @ApplicationContext
    lateinit var context: Context

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
            val vm = remember { ProfileViewModel(authService, orderDao, context) }
            ProfileScreen(onLogout = {}, viewModel = vm)
        }
        composeRule.onNodeWithText("Выйти").assertIsDisplayed()
    }
}
