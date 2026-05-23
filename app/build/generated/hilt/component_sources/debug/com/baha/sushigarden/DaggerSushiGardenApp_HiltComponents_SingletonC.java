package com.baha.sushigarden;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.baha.sushigarden.data.services.auth.AuthService;
import com.baha.sushigarden.data.services.cart.CartService;
import com.baha.sushigarden.data.services.catalog.MenuRepository;
import com.baha.sushigarden.data.services.delivery.CourierSimulator;
import com.baha.sushigarden.data.services.orders.OrderDao;
import com.baha.sushigarden.data.services.orders.SushiGardenDatabase;
import com.baha.sushigarden.di.AppModule_ProvideAuthServiceFactory;
import com.baha.sushigarden.di.AppModule_ProvideCartServiceFactory;
import com.baha.sushigarden.di.AppModule_ProvideDatabaseFactory;
import com.baha.sushigarden.di.AppModule_ProvideMenuRepositoryFactory;
import com.baha.sushigarden.di.AppModule_ProvideOrderDaoFactory;
import com.baha.sushigarden.features.auth.AuthViewModel;
import com.baha.sushigarden.features.auth.AuthViewModel_HiltModules;
import com.baha.sushigarden.features.cart.CartViewModel;
import com.baha.sushigarden.features.cart.CartViewModel_HiltModules;
import com.baha.sushigarden.features.catalog.CatalogViewModel;
import com.baha.sushigarden.features.catalog.CatalogViewModel_HiltModules;
import com.baha.sushigarden.features.checkout.CheckoutViewModel;
import com.baha.sushigarden.features.checkout.CheckoutViewModel_HiltModules;
import com.baha.sushigarden.features.orders.OrdersViewModel;
import com.baha.sushigarden.features.orders.OrdersViewModel_HiltModules;
import com.baha.sushigarden.features.productdetail.ProductDetailViewModel;
import com.baha.sushigarden.features.productdetail.ProductDetailViewModel_HiltModules;
import com.baha.sushigarden.features.profile.ProfileViewModel;
import com.baha.sushigarden.features.profile.ProfileViewModel_HiltModules;
import com.baha.sushigarden.features.promotions.PromotionsViewModel;
import com.baha.sushigarden.features.promotions.PromotionsViewModel_HiltModules;
import com.baha.sushigarden.features.tracking.TrackingViewModel;
import com.baha.sushigarden.features.tracking.TrackingViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerSushiGardenApp_HiltComponents_SingletonC {
  private DaggerSushiGardenApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public SushiGardenApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements SushiGardenApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements SushiGardenApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements SushiGardenApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements SushiGardenApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements SushiGardenApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements SushiGardenApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements SushiGardenApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public SushiGardenApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends SushiGardenApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends SushiGardenApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends SushiGardenApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends SushiGardenApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(9).put(LazyClassKeyProvider.com_baha_sushigarden_features_auth_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_cart_CartViewModel, CartViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_catalog_CatalogViewModel, CatalogViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_checkout_CheckoutViewModel, CheckoutViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_orders_OrdersViewModel, OrdersViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_productdetail_ProductDetailViewModel, ProductDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_profile_ProfileViewModel, ProfileViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_promotions_PromotionsViewModel, PromotionsViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_baha_sushigarden_features_tracking_TrackingViewModel, TrackingViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_baha_sushigarden_features_profile_ProfileViewModel = "com.baha.sushigarden.features.profile.ProfileViewModel";

      static String com_baha_sushigarden_features_catalog_CatalogViewModel = "com.baha.sushigarden.features.catalog.CatalogViewModel";

      static String com_baha_sushigarden_features_promotions_PromotionsViewModel = "com.baha.sushigarden.features.promotions.PromotionsViewModel";

      static String com_baha_sushigarden_features_checkout_CheckoutViewModel = "com.baha.sushigarden.features.checkout.CheckoutViewModel";

      static String com_baha_sushigarden_features_productdetail_ProductDetailViewModel = "com.baha.sushigarden.features.productdetail.ProductDetailViewModel";

      static String com_baha_sushigarden_features_orders_OrdersViewModel = "com.baha.sushigarden.features.orders.OrdersViewModel";

      static String com_baha_sushigarden_features_cart_CartViewModel = "com.baha.sushigarden.features.cart.CartViewModel";

      static String com_baha_sushigarden_features_auth_AuthViewModel = "com.baha.sushigarden.features.auth.AuthViewModel";

      static String com_baha_sushigarden_features_tracking_TrackingViewModel = "com.baha.sushigarden.features.tracking.TrackingViewModel";

      @KeepFieldType
      ProfileViewModel com_baha_sushigarden_features_profile_ProfileViewModel2;

      @KeepFieldType
      CatalogViewModel com_baha_sushigarden_features_catalog_CatalogViewModel2;

      @KeepFieldType
      PromotionsViewModel com_baha_sushigarden_features_promotions_PromotionsViewModel2;

      @KeepFieldType
      CheckoutViewModel com_baha_sushigarden_features_checkout_CheckoutViewModel2;

      @KeepFieldType
      ProductDetailViewModel com_baha_sushigarden_features_productdetail_ProductDetailViewModel2;

      @KeepFieldType
      OrdersViewModel com_baha_sushigarden_features_orders_OrdersViewModel2;

      @KeepFieldType
      CartViewModel com_baha_sushigarden_features_cart_CartViewModel2;

      @KeepFieldType
      AuthViewModel com_baha_sushigarden_features_auth_AuthViewModel2;

      @KeepFieldType
      TrackingViewModel com_baha_sushigarden_features_tracking_TrackingViewModel2;
    }
  }

  private static final class ViewModelCImpl extends SushiGardenApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<CartViewModel> cartViewModelProvider;

    private Provider<CatalogViewModel> catalogViewModelProvider;

    private Provider<CheckoutViewModel> checkoutViewModelProvider;

    private Provider<OrdersViewModel> ordersViewModelProvider;

    private Provider<ProductDetailViewModel> productDetailViewModelProvider;

    private Provider<ProfileViewModel> profileViewModelProvider;

    private Provider<PromotionsViewModel> promotionsViewModelProvider;

    private Provider<TrackingViewModel> trackingViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.cartViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.catalogViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.checkoutViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.ordersViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.productDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
      this.profileViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 6);
      this.promotionsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 7);
      this.trackingViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 8);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(9).put(LazyClassKeyProvider.com_baha_sushigarden_features_auth_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_cart_CartViewModel, ((Provider) cartViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_catalog_CatalogViewModel, ((Provider) catalogViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_checkout_CheckoutViewModel, ((Provider) checkoutViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_orders_OrdersViewModel, ((Provider) ordersViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_productdetail_ProductDetailViewModel, ((Provider) productDetailViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_profile_ProfileViewModel, ((Provider) profileViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_promotions_PromotionsViewModel, ((Provider) promotionsViewModelProvider)).put(LazyClassKeyProvider.com_baha_sushigarden_features_tracking_TrackingViewModel, ((Provider) trackingViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_baha_sushigarden_features_auth_AuthViewModel = "com.baha.sushigarden.features.auth.AuthViewModel";

      static String com_baha_sushigarden_features_cart_CartViewModel = "com.baha.sushigarden.features.cart.CartViewModel";

      static String com_baha_sushigarden_features_promotions_PromotionsViewModel = "com.baha.sushigarden.features.promotions.PromotionsViewModel";

      static String com_baha_sushigarden_features_productdetail_ProductDetailViewModel = "com.baha.sushigarden.features.productdetail.ProductDetailViewModel";

      static String com_baha_sushigarden_features_tracking_TrackingViewModel = "com.baha.sushigarden.features.tracking.TrackingViewModel";

      static String com_baha_sushigarden_features_orders_OrdersViewModel = "com.baha.sushigarden.features.orders.OrdersViewModel";

      static String com_baha_sushigarden_features_profile_ProfileViewModel = "com.baha.sushigarden.features.profile.ProfileViewModel";

      static String com_baha_sushigarden_features_catalog_CatalogViewModel = "com.baha.sushigarden.features.catalog.CatalogViewModel";

      static String com_baha_sushigarden_features_checkout_CheckoutViewModel = "com.baha.sushigarden.features.checkout.CheckoutViewModel";

      @KeepFieldType
      AuthViewModel com_baha_sushigarden_features_auth_AuthViewModel2;

      @KeepFieldType
      CartViewModel com_baha_sushigarden_features_cart_CartViewModel2;

      @KeepFieldType
      PromotionsViewModel com_baha_sushigarden_features_promotions_PromotionsViewModel2;

      @KeepFieldType
      ProductDetailViewModel com_baha_sushigarden_features_productdetail_ProductDetailViewModel2;

      @KeepFieldType
      TrackingViewModel com_baha_sushigarden_features_tracking_TrackingViewModel2;

      @KeepFieldType
      OrdersViewModel com_baha_sushigarden_features_orders_OrdersViewModel2;

      @KeepFieldType
      ProfileViewModel com_baha_sushigarden_features_profile_ProfileViewModel2;

      @KeepFieldType
      CatalogViewModel com_baha_sushigarden_features_catalog_CatalogViewModel2;

      @KeepFieldType
      CheckoutViewModel com_baha_sushigarden_features_checkout_CheckoutViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.baha.sushigarden.features.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.provideAuthServiceProvider.get());

          case 1: // com.baha.sushigarden.features.cart.CartViewModel 
          return (T) new CartViewModel(singletonCImpl.provideCartServiceProvider.get());

          case 2: // com.baha.sushigarden.features.catalog.CatalogViewModel 
          return (T) new CatalogViewModel(singletonCImpl.provideMenuRepositoryProvider.get());

          case 3: // com.baha.sushigarden.features.checkout.CheckoutViewModel 
          return (T) new CheckoutViewModel(singletonCImpl.provideCartServiceProvider.get(), singletonCImpl.orderDao());

          case 4: // com.baha.sushigarden.features.orders.OrdersViewModel 
          return (T) new OrdersViewModel(singletonCImpl.orderDao());

          case 5: // com.baha.sushigarden.features.productdetail.ProductDetailViewModel 
          return (T) new ProductDetailViewModel(singletonCImpl.provideMenuRepositoryProvider.get(), singletonCImpl.provideCartServiceProvider.get());

          case 6: // com.baha.sushigarden.features.profile.ProfileViewModel 
          return (T) new ProfileViewModel(singletonCImpl.provideAuthServiceProvider.get(), singletonCImpl.orderDao(), ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.baha.sushigarden.features.promotions.PromotionsViewModel 
          return (T) new PromotionsViewModel(singletonCImpl.provideMenuRepositoryProvider.get());

          case 8: // com.baha.sushigarden.features.tracking.TrackingViewModel 
          return (T) new TrackingViewModel(singletonCImpl.courierSimulatorProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends SushiGardenApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends SushiGardenApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends SushiGardenApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AuthService> provideAuthServiceProvider;

    private Provider<CartService> provideCartServiceProvider;

    private Provider<MenuRepository> provideMenuRepositoryProvider;

    private Provider<SushiGardenDatabase> provideDatabaseProvider;

    private Provider<CourierSimulator> courierSimulatorProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private OrderDao orderDao() {
      return AppModule_ProvideOrderDaoFactory.provideOrderDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideAuthServiceProvider = DoubleCheck.provider(new SwitchingProvider<AuthService>(singletonCImpl, 0));
      this.provideCartServiceProvider = DoubleCheck.provider(new SwitchingProvider<CartService>(singletonCImpl, 1));
      this.provideMenuRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<MenuRepository>(singletonCImpl, 2));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<SushiGardenDatabase>(singletonCImpl, 3));
      this.courierSimulatorProvider = DoubleCheck.provider(new SwitchingProvider<CourierSimulator>(singletonCImpl, 4));
    }

    @Override
    public void injectSushiGardenApp(SushiGardenApp sushiGardenApp) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.baha.sushigarden.data.services.auth.AuthService 
          return (T) AppModule_ProvideAuthServiceFactory.provideAuthService();

          case 1: // com.baha.sushigarden.data.services.cart.CartService 
          return (T) AppModule_ProvideCartServiceFactory.provideCartService();

          case 2: // com.baha.sushigarden.data.services.catalog.MenuRepository 
          return (T) AppModule_ProvideMenuRepositoryFactory.provideMenuRepository();

          case 3: // com.baha.sushigarden.data.services.orders.SushiGardenDatabase 
          return (T) AppModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.baha.sushigarden.data.services.delivery.CourierSimulator 
          return (T) new CourierSimulator();

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
