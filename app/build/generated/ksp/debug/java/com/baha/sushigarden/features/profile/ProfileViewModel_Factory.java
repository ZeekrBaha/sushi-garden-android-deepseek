package com.baha.sushigarden.features.profile;

import android.content.Context;
import com.baha.sushigarden.data.services.auth.AuthService;
import com.baha.sushigarden.data.services.orders.OrderDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<AuthService> authServiceProvider;

  private final Provider<OrderDao> orderDaoProvider;

  private final Provider<Context> contextProvider;

  public ProfileViewModel_Factory(Provider<AuthService> authServiceProvider,
      Provider<OrderDao> orderDaoProvider, Provider<Context> contextProvider) {
    this.authServiceProvider = authServiceProvider;
    this.orderDaoProvider = orderDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(authServiceProvider.get(), orderDaoProvider.get(), contextProvider.get());
  }

  public static ProfileViewModel_Factory create(Provider<AuthService> authServiceProvider,
      Provider<OrderDao> orderDaoProvider, Provider<Context> contextProvider) {
    return new ProfileViewModel_Factory(authServiceProvider, orderDaoProvider, contextProvider);
  }

  public static ProfileViewModel newInstance(AuthService authService, OrderDao orderDao,
      Context context) {
    return new ProfileViewModel(authService, orderDao, context);
  }
}
