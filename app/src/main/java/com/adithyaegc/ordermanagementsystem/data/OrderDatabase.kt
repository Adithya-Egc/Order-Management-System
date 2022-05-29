package com.adithyaegc.ordermanagementsystem.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.adithyaegc.ordermanagementsystem.data.entity.Order
import com.adithyaegc.ordermanagementsystem.di.MyApplicationScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Order::class], exportSchema = false, version = 1)
abstract class OrderDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    class CallBack @Inject constructor(
        private val orderDb: Provider<OrderDatabase>,
        @MyApplicationScope private val myApplicationScope: kotlinx.coroutines.CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = orderDb.get().orderDao()

            myApplicationScope.launch {
                dao.insertOrder(
                    Order(
                        orderNo = 1234,
                        customerName = "Customer One",
                        orderDetails = "Food and Drinks Details here.."
                    )
                )

                dao.insertOrder(
                    Order(
                        orderNo = 2345,
                        customerName = "Customer Two",
                        orderDetails = "Some Random details about food order"
                    )
                )
            }
        }
    }
}