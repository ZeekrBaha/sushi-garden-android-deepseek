package com.baha.sushigarden.data.services.auth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class FakeAuthService_Factory implements Factory<FakeAuthService> {
  @Override
  public FakeAuthService get() {
    return newInstance();
  }

  public static FakeAuthService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FakeAuthService newInstance() {
    return new FakeAuthService();
  }

  private static final class InstanceHolder {
    private static final FakeAuthService_Factory INSTANCE = new FakeAuthService_Factory();
  }
}
