package com.adithyaegc.ordermanagementsystem.di

import android.content.Context
import androidx.room.Room
import com.adithyaegc.ordermanagementsystem.data.OrderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(
        @ApplicationContext context: Context,
        callback: OrderDatabase.CallBack
    ) = Room.databaseBuilder(
        context,
        OrderDatabase::class.java,
        "order_database"
    ).fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()


    @Provides
    @Singleton
    fun provideDao(orderDatabase: OrderDatabase) = orderDatabase.orderDao()



    @Provides
    @Singleton
    @MyApplicationScope
    fun provideCoroutineScope() = CoroutineScope(SupervisorJob())


}


@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MyApplicationScope