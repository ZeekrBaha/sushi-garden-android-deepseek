package com.baha.sushigarden.features.promotions;

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
public final class PromotionsViewModel_Factory implements Factory<PromotionsViewModel> {
  private final Provider<MenuRepository> menuRepositoryProvider;

  public PromotionsViewModel_Factory(Provider<MenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public PromotionsViewModel get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static PromotionsViewModel_Factory create(
      Provider<MenuRepository> menuRepositoryProvider) {
    return new PromotionsViewModel_Factory(menuRepositoryProvider);
  }

  public static PromotionsViewModel newInstance(MenuRepository menuRepository) {
    return new PromotionsViewModel(menuRepository);
  }
}
