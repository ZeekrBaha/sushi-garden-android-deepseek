package com.baha.sushigarden;

import android.content.Context;
import com.baha.sushigarden.data.services.orders.SushiGardenDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class TestHiltModule_ProvideDatabaseFactory implements Factory<SushiGardenDatabase> {
  private final Provider<Context> contextProvider;

  public TestHiltModule_ProvideDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public SushiGardenDatabase get() {
    return provideDatabase(contextProvider.get());
  }

  public static TestHiltModule_ProvideDatabaseFactory create(Provider<Context> contextProvider) {
    return new TestHiltModule_ProvideDatabaseFactory(contextProvider);
  }

  public static SushiGardenDatabase provideDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(TestHiltModule.INSTANCE.provideDatabase(context));
  }
}
