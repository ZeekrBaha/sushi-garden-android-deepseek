package com.baha.sushigarden.di;

import com.baha.sushigarden.data.services.cart.CartService;
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
public final class AppModule_ProvideCartServiceFactory implements Factory<CartService> {
  @Override
  public CartService get() {
    return provideCartService();
  }

  public static AppModule_ProvideCartServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CartService provideCartService() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCartService());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideCartServiceFactory INSTANCE = new AppModule_ProvideCartServiceFactory();
  }
}
