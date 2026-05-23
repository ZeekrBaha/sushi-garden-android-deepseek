package com.baha.sushigarden.data.services.delivery;

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
public final class CourierSimulator_Factory implements Factory<CourierSimulator> {
  @Override
  public CourierSimulator get() {
    return newInstance();
  }

  public static CourierSimulator_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static CourierSimulator newInstance() {
    return new CourierSimulator();
  }

  private static final class InstanceHolder {
    private static final CourierSimulator_Factory INSTANCE = new CourierSimulator_Factory();
  }
}
