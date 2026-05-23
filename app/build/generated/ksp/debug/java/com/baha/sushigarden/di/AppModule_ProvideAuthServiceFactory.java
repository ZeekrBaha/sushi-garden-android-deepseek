package com.baha.sushigarden.di;

import com.baha.sushigarden.data.services.auth.AuthService;
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
public final class AppModule_ProvideAuthServiceFactory implements Factory<AuthService> {
  @Override
  public AuthService get() {
    return provideAuthService();
  }

  public static AppModule_ProvideAuthServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthService provideAuthService() {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAuthService());
  }

  private static final class InstanceHolder {
    private static final AppModule_ProvideAuthServiceFactory INSTANCE = new AppModule_ProvideAuthServiceFactory();
  }
}
