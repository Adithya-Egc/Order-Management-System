package com.adithyaegc.ordermanagementsystem.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adithyaegc.ordermanagementsystem.data.entity.Order

@Dao
interface OrderDao {


    @Query("SELECT * FROM order_table")
    fun readAllOrders(): LiveData<List<Order>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)

    @Query("DELETE FROM order_table")
    suspend fun deleteAll()


}