package com.baha.sushigarden.features.productdetail;

import com.baha.sushigarden.data.services.cart.CartService;
import com.baha.sushigarden.data.services.catalog.MenuRepository;
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
public final class ProductDetailViewModel_Factory implements Factory<ProductDetailViewModel> {
  private final Provider<MenuRepository> menuRepositoryProvider;

  private final Provider<CartService> cartServiceProvider;

  public ProductDetailViewModel_Factory(Provider<MenuRepository> menuRepositoryProvider,
      Provider<CartService> cartServiceProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
    this.cartServiceProvider = cartServiceProvider;
  }

  @Override
  public ProductDetailViewModel get() {
    return newInstance(menuRepositoryProvider.get(), cartServiceProvider.get());
  }

  public static ProductDetailViewModel_Factory create(
      Provider<MenuRepository> menuRepositoryProvider, Provider<CartService> cartServiceProvider) {
    return new ProductDetailViewModel_Factory(menuRepositoryProvider, cartServiceProvider);
  }

  public static ProductDetailViewModel newInstance(MenuRepository menuRepository,
      CartService cartService) {
    return new ProductDetailViewModel(menuRepository, cartService);
  }
}
