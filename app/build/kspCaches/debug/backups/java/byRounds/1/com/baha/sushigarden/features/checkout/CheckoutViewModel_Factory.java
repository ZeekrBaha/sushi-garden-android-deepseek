package com.baha.sushigarden.features.checkout;

import com.baha.sushigarden.data.services.cart.CartService;
import com.baha.sushigarden.data.services.orders.OrderDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class CheckoutViewModel_Factory implements Factory<CheckoutViewModel> {
  private final Provider<CartService> cartServiceProvider;

  private final Provider<OrderDao> orderDaoProvider;

  public CheckoutViewModel_Factory(Provider<CartService> cartServiceProvider,
      Provider<OrderDao> orderDaoProvider) {
    this.cartServiceProvider = cartServiceProvider;
    this.orderDaoProvider = orderDaoProvider;
  }

  @Override
  public CheckoutViewModel get() {
    return newInstance(cartServiceProvider.get(), orderDaoProvider.get());
  }

  public static CheckoutViewModel_Factory create(Provider<CartService> cartServiceProvider,
      Provider<OrderDao> orderDaoProvider) {
    return new CheckoutViewModel_Factory(cartServiceProvider, orderDaoProvider);
  }

  public static CheckoutViewModel newInstance(CartService cartService, OrderDao orderDao) {
    return new CheckoutViewModel(cartService, orderDao);
  }
}
