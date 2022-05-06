package com.adithyaegc.ordermanagementsystem.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "order_table")
@Parcelize
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val orderNo: Int,
    val customerName: String,
    val orderDetails: String,
) : Parcelable




