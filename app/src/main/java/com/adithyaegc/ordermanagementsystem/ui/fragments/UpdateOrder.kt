package com.adithyaegc.ordermanagementsystem.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adithyaegc.ordermanagementsystem.R
import com.adithyaegc.ordermanagementsystem.data.entity.Order
import com.adithyaegc.ordermanagementsystem.databinding.FragmentUpdateOrderBinding
import com.adithyaegc.ordermanagementsystem.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateOrder : Fragment() {

    private var _binding: FragmentUpdateOrderBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateOrderArgs by navArgs()
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateOrderBinding.inflate(layoutInflater)

        setHasOptionsMenu(true)

        val orderNo = binding.etOrderId.setText(args.orderItem.orderNo.toString())
        val customerName = binding.etCustomerName.setText(args.orderItem.customerName)
        val orderDetails = binding.etOrderDetails.setText(args.orderItem.orderDetails)


        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_update -> updateDataOnDatabase()
            R.id.menu_delete -> deleteItemOrder()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteItemOrder() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mainViewModel.deleteOrder(args.orderItem)
            findNavController().navigate(R.id.action_updateOrder_to_ordersFragment)
            Toast.makeText(
                requireContext(),
                "Successfully removed ${args.orderItem.customerName}",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Item?")
        builder.setMessage("Are you sure you want to delete '${args.orderItem.customerName}?'")
        builder.create().show()

    }


    private fun updateDataOnDatabase() {
        val orderNo = binding.etOrderId.text.toString().toInt()
        val customerName = binding.etCustomerName.text.toString()
        val orderDetails = binding.etOrderDetails.text.toString()

        val validation = checkValidation(customerName, orderDetails)

        if (validation) {
            val updateOrder = Order(
                id = args.orderItem.id,
                orderNo = orderNo,
                customerName = customerName,
                orderDetails = orderDetails
            )

            mainViewModel.updateOrder(updateOrder)
            findNavController().navigate(R.id.action_updateOrder_to_ordersFragment)
            Toast.makeText(requireContext(), "Order Updated", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "Fields can't empty", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkValidation(customerName: String, orderDetails: String): Boolean {
        return (customerName.isNotEmpty() && orderDetails.isNotEmpty())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}