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
public final class FirebaseAuthService_Factory implements Factory<FirebaseAuthService> {
  @Override
  public FirebaseAuthService get() {
    return newInstance();
  }

  public static FirebaseAuthService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseAuthService newInstance() {
    return new FirebaseAuthService();
  }

  private static final class InstanceHolder {
    private static final FirebaseAuthService_Factory INSTANCE = new FirebaseAuthService_Factory();
  }
}
