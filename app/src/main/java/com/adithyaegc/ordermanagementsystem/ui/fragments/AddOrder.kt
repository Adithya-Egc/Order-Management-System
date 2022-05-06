package com.adithyaegc.ordermanagementsystem.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adithyaegc.ordermanagementsystem.viewmodel.MainViewModel
import com.adithyaegc.ordermanagementsystem.R
import com.adithyaegc.ordermanagementsystem.data.entity.Order
import com.adithyaegc.ordermanagementsystem.databinding.FragmentAddOrderBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddOrder : Fragment() {

    private var _binding: FragmentAddOrderBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOrderBinding.inflate(
            LayoutInflater.from(layoutInflater.context),
            container,
            false
        )
        setHasOptionsMenu(true)


        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_check) {
            insertDataToDatabase()
        }

        return super.onOptionsItemSelected(item)
    }


    private fun insertDataToDatabase() {

        val customerName = binding.etCustomerName.text.toString()
        val orderDetails = binding.etOrderDetails.text.toString()
        val orderNo = binding.etOrderId.text.toString()

        val validation = checkValidation(customerName, orderDetails, orderNo)

        if (validation) {
            val newOrder = Order(
                id = 0,
                orderNo = Integer.parseInt(orderNo),
                customerName = customerName,
                orderDetails = orderDetails
            )

            mainViewModel.insertOrder(newOrder)
            Toast.makeText(requireContext(), "Successfully added.", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addOrder_to_ordersFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill all details", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkValidation(
        customerName: String,
        orderDetails: String,
        orderNo: String
    ): Boolean {
        return (customerName.isNotEmpty() && orderDetails.isNotEmpty() && orderNo.isNotEmpty())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}