package com.baha.sushigarden

import android.content.Context
import androidx.room.Room
import com.baha.sushigarden.data.services.auth.AuthService
import com.baha.sushigarden.data.services.auth.FakeAuthService
import com.baha.sushigarden.data.services.cart.CartService
import com.baha.sushigarden.data.services.cart.InMemoryCartService
import com.baha.sushigarden.data.services.catalog.LocalMenuRepository
import com.baha.sushigarden.data.services.catalog.MenuRepository
import com.baha.sushigarden.data.services.orders.OrderDao
import com.baha.sushigarden.data.services.orders.SushiGardenDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [com.baha.sushigarden.di.AppModule::class],
)
object TestHiltModule {
    @Provides
    @Singleton
    fun provideAuthService(): AuthService = FakeAuthService()

    @Provides
    @Singleton
    fun provideMenuRepository(): MenuRepository = LocalMenuRepository()

    @Provides
    @Singleton
    fun provideCartService(): CartService = InMemoryCartService()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): SushiGardenDatabase = Room.inMemoryDatabaseBuilder(context, SushiGardenDatabase::class.java).build()

    @Provides
    fun provideOrderDao(database: SushiGardenDatabase): OrderDao = database.orderDao()
}
