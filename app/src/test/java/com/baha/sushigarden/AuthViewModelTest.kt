package com.baha.sushigarden

import com.baha.sushigarden.data.services.auth.FakeAuthService
import com.baha.sushigarden.features.auth.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AuthViewModelTest {
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is register mode`() {
        val vm = AuthViewModel(FakeAuthService())
        assertEquals(true, vm.state.value.isRegisterMode)
    }

    @Test
    fun `toggleMode switches between register and login`() {
        val vm = AuthViewModel(FakeAuthService())
        vm.toggleMode()
        assertEquals(false, vm.state.value.isRegisterMode)
        vm.toggleMode()
        assertEquals(true, vm.state.value.isRegisterMode)
    }

    @Test
    fun `submit with register and no consent shows error`() {
        val vm = AuthViewModel(FakeAuthService())
        vm.submit()
        assertEquals("Подтвердите согласие с условиями", vm.state.value.error)
    }

    @Test
    fun `submit with valid register data authenticates`() =
        runTest {
            val vm = AuthViewModel(FakeAuthService())
            vm.updateName("Test")
            vm.updateEmail("test@test.com")
            vm.updatePassword("password123")
            vm.toggleConsent()
            vm.submit()
            testDispatcher.scheduler.advanceUntilIdle()
            assertTrue(vm.isAuthenticated.value)
        }

    @Test
    fun `login with invalid credentials shows error`() =
        runTest {
            val vm = AuthViewModel(FakeAuthService())
            vm.toggleMode()
            vm.updateEmail("invalid")
            vm.updatePassword("12")
            vm.submit()
            testDispatcher.scheduler.advanceUntilIdle()
            assertEquals("Неверная почта или пароль", vm.state.value.error)
        }
}
