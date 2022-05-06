package com.adithyaegc.ordermanagementsystem.di

import android.content.Context
import androidx.room.Room
import com.adithyaegc.ordermanagementsystem.data.OrderDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        OrderDatabase::class.java,
        "order_database"
    ).build()


    @Provides
    @Singleton
    fun provideDao(orderDatabase: OrderDatabase) = orderDatabase.orderDao()




}