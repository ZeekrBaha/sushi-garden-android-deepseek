package com.baha.sushigarden.features.catalog;

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
public final class CatalogViewModel_Factory implements Factory<CatalogViewModel> {
  private final Provider<MenuRepository> menuRepositoryProvider;

  public CatalogViewModel_Factory(Provider<MenuRepository> menuRepositoryProvider) {
    this.menuRepositoryProvider = menuRepositoryProvider;
  }

  @Override
  public CatalogViewModel get() {
    return newInstance(menuRepositoryProvider.get());
  }

  public static CatalogViewModel_Factory create(Provider<MenuRepository> menuRepositoryProvider) {
    return new CatalogViewModel_Factory(menuRepositoryProvider);
  }

  public static CatalogViewModel newInstance(MenuRepository menuRepository) {
    return new CatalogViewModel(menuRepository);
  }
}
