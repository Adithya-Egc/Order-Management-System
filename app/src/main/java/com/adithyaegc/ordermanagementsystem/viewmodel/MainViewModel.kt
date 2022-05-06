package com.adithyaegc.ordermanagementsystem.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.adithyaegc.ordermanagementsystem.data.entity.Order
import com.adithyaegc.ordermanagementsystem.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _readAllData: LiveData<List<Order>> = repository.readAllOrders()
    val readAllData: LiveData<List<Order>>
        get() = _readAllData


    fun insertOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertOrder(order)
    }

    fun updateOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateOrder(order)
    }


    fun deleteOrder(order: Order) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteOrder(order)
    }


    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}