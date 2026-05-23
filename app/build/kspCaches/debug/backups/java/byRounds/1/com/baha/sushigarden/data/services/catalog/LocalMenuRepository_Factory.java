package com.baha.sushigarden.data.services.catalog;

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
public final class LocalMenuRepository_Factory implements Factory<LocalMenuRepository> {
  @Override
  public LocalMenuRepository get() {
    return newInstance();
  }

  public static LocalMenuRepository_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocalMenuRepository newInstance() {
    return new LocalMenuRepository();
  }

  private static final class InstanceHolder {
    private static final LocalMenuRepository_Factory INSTANCE = new LocalMenuRepository_Factory();
  }
}
