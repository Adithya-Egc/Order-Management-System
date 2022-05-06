package com.adithyaegc.ordermanagementsystem.data

import com.adithyaegc.ordermanagementsystem.data.entity.Order
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val dao: OrderDao) {


    fun readAllOrders() = dao.readAllOrders()

    suspend fun deleteAll() = dao.deleteAll()

    suspend fun insertOrder(order: Order) = dao.insertOrder(order)

    suspend fun updateOrder(order: Order) = dao.updateOrder(order)

    suspend fun deleteOrder(order: Order) = dao.deleteOrder(order)


}