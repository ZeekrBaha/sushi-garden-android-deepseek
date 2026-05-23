package com.baha.sushigarden.features.orders;

import com.baha.sushigarden.data.services.orders.OrderDao;
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
public final class OrdersViewModel_Factory implements Factory<OrdersViewModel> {
  private final Provider<OrderDao> orderDaoProvider;

  public OrdersViewModel_Factory(Provider<OrderDao> orderDaoProvider) {
    this.orderDaoProvider = orderDaoProvider;
  }

  @Override
  public OrdersViewModel get() {
    return newInstance(orderDaoProvider.get());
  }

  public static OrdersViewModel_Factory create(Provider<OrderDao> orderDaoProvider) {
    return new OrdersViewModel_Factory(orderDaoProvider);
  }

  public static OrdersViewModel newInstance(OrderDao orderDao) {
    return new OrdersViewModel(orderDao);
  }
}
