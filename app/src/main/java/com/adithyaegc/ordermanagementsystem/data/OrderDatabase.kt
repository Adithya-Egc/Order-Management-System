package com.adithyaegc.ordermanagementsystem.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adithyaegc.ordermanagementsystem.data.entity.Order


@Database(entities = [Order::class], exportSchema = false, version = 1)
abstract class OrderDatabase: RoomDatabase() {

    abstract fun orderDao(): OrderDao
}