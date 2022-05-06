package com.adithyaegc.ordermanagementsystem.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adithyaegc.ordermanagementsystem.data.entity.Order
import com.adithyaegc.ordermanagementsystem.databinding.RowItemBinding
import com.adithyaegc.ordermanagementsystem.ui.fragments.OrdersFragmentDirections

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    private var orderList = emptyList<Order>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val currentItem = orderList[position]

        holder.binding.tvCustomer.text = currentItem.customerName
        holder.binding.tvDetails.text = currentItem.orderDetails
        val orderNo = "Order No: ${currentItem.orderNo.toString()} "
        holder.binding.tvOrderNo.text = orderNo


        holder.binding.rowLayout.setOnClickListener { view ->

            val action =
                OrdersFragmentDirections.actionOrdersFragmentToUpdateOrder(orderList[position])

            view.findNavController().navigate(action)

        }

    }

    override fun getItemCount() = orderList.size


    class OrderViewHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Order>) {
        this.orderList = list
        notifyDataSetChanged()
    }
}