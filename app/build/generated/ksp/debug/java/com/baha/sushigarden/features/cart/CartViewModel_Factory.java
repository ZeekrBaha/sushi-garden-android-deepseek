package com.baha.sushigarden.features.cart;

import com.baha.sushigarden.data.services.cart.CartService;
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
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartService> cartServiceProvider;

  public CartViewModel_Factory(Provider<CartService> cartServiceProvider) {
    this.cartServiceProvider = cartServiceProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(cartServiceProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartService> cartServiceProvider) {
    return new CartViewModel_Factory(cartServiceProvider);
  }

  public static CartViewModel newInstance(CartService cartService) {
    return new CartViewModel(cartService);
  }
}
