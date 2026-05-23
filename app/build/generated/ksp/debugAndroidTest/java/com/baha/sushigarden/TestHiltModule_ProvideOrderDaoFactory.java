package com.baha.sushigarden;

import com.baha.sushigarden.data.services.orders.OrderDao;
import com.baha.sushigarden.data.services.orders.SushiGardenDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class TestHiltModule_ProvideOrderDaoFactory implements Factory<OrderDao> {
  private final Provider<SushiGardenDatabase> databaseProvider;

  public TestHiltModule_ProvideOrderDaoFactory(Provider<SushiGardenDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public OrderDao get() {
    return provideOrderDao(databaseProvider.get());
  }

  public static TestHiltModule_ProvideOrderDaoFactory create(
      Provider<SushiGardenDatabase> databaseProvider) {
    return new TestHiltModule_ProvideOrderDaoFactory(databaseProvider);
  }

  public static OrderDao provideOrderDao(SushiGardenDatabase database) {
    return Preconditions.checkNotNullFromProvides(TestHiltModule.INSTANCE.provideOrderDao(database));
  }
}
