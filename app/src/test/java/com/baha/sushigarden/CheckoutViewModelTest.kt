package com.baha.sushigarden

import com.baha.sushigarden.data.services.cart.InMemoryCartService
import com.baha.sushigarden.data.services.orders.OrderDao
import com.baha.sushigarden.features.checkout.CheckoutViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CheckoutViewModelTest {

    private val cartService = InMemoryCartService()
    private val orderDao = mockk<OrderDao>(relaxed = true)

    private lateinit var viewModel: CheckoutViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        coEvery { orderDao.insertOrder(any()) } returns Unit
        viewModel =
            CheckoutViewModel(cartService = cartService, orderDao = orderDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `place order with empty fields shows error`() {
        viewModel.placeOrder()
        assert(viewModel.state.value.error != null)
    }

    @Test
    fun `place order with invalid phone shows format error`() {
        viewModel.updateName("Test")
        viewModel.updatePhone("abc")
        viewModel.updateEmail("test@test.com")
        viewModel.placeOrder()
        assert(viewModel.state.value.error == "Неверный формат телефона")
    }

    @Test
    fun `place order with invalid email shows format error`() {
        viewModel.updateName("Test")
        viewModel.updatePhone("+79001234567")
        viewModel.updateEmail("notanemail")
        viewModel.placeOrder()
        assert(viewModel.state.value.error == "Неверный формат email")
    }

    @Test
    fun `place order with empty cart shows cart empty error`() {
        viewModel.updateName("Test")
        viewModel.updatePhone("+79001234567")
        viewModel.updateEmail("test@test.com")
        viewModel.placeOrder()
        assert(viewModel.state.value.error == "Корзина пуста")
    }
}
