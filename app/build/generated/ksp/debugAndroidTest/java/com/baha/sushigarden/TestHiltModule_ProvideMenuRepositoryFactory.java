package com.baha.sushigarden;

import com.baha.sushigarden.data.services.catalog.MenuRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TestHiltModule_ProvideMenuRepositoryFactory implements Factory<MenuRepository> {
  @Override
  public MenuRepository get() {
    return provideMenuRepository();
  }

  public static TestHiltModule_ProvideMenuRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MenuRepository provideMenuRepository() {
    return Preconditions.checkNotNullFromProvides(TestHiltModule.INSTANCE.provideMenuRepository());
  }

  private static final class InstanceHolder {
    private static final TestHiltModule_ProvideMenuRepositoryFactory INSTANCE = new TestHiltModule_ProvideMenuRepositoryFactory();
  }
}
