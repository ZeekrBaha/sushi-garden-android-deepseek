package com.baha.sushigarden.features.tracking;

import com.baha.sushigarden.data.services.delivery.CourierSimulator;
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
public final class TrackingViewModel_Factory implements Factory<TrackingViewModel> {
  private final Provider<CourierSimulator> courierSimulatorProvider;

  public TrackingViewModel_Factory(Provider<CourierSimulator> courierSimulatorProvider) {
    this.courierSimulatorProvider = courierSimulatorProvider;
  }

  @Override
  public TrackingViewModel get() {
    return newInstance(courierSimulatorProvider.get());
  }

  public static TrackingViewModel_Factory create(
      Provider<CourierSimulator> courierSimulatorProvider) {
    return new TrackingViewModel_Factory(courierSimulatorProvider);
  }

  public static TrackingViewModel newInstance(CourierSimulator courierSimulator) {
    return new TrackingViewModel(courierSimulator);
  }
}
