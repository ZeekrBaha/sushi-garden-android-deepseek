package com.baha.sushigarden;

import com.baha.sushigarden.data.services.cart.CartService;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class CartFlowTest_MembersInjector implements MembersInjector<CartFlowTest> {
  private final Provider<CartService> cartServiceProvider;

  public CartFlowTest_MembersInjector(Provider<CartService> cartServiceProvider) {
    this.cartServiceProvider = cartServiceProvider;
  }

  public static MembersInjector<CartFlowTest> create(Provider<CartService> cartServiceProvider) {
    return new CartFlowTest_MembersInjector(cartServiceProvider);
  }

  @Override
  public void injectMembers(CartFlowTest instance) {
    injectCartService(instance, cartServiceProvider.get());
  }

  @InjectedFieldSignature("com.baha.sushigarden.CartFlowTest.cartService")
  public static void injectCartService(CartFlowTest instance, CartService cartService) {
    instance.cartService = cartService;
  }
}
