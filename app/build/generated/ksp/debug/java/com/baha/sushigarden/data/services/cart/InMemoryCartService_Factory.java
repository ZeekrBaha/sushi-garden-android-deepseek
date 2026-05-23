package com.baha.sushigarden.data.services.cart;

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
public final class InMemoryCartService_Factory implements Factory<InMemoryCartService> {
  @Override
  public InMemoryCartService get() {
    return newInstance();
  }

  public static InMemoryCartService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static InMemoryCartService newInstance() {
    return new InMemoryCartService();
  }

  private static final class InstanceHolder {
    private static final InMemoryCartService_Factory INSTANCE = new InMemoryCartService_Factory();
  }
}
