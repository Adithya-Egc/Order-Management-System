package com.adithyaegc.ordermanagementsystem.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adithyaegc.ordermanagementsystem.viewmodel.MainViewModel
import com.adithyaegc.ordermanagementsystem.ui.adapter.OrderAdapter
import com.adithyaegc.ordermanagementsystem.R
import com.adithyaegc.ordermanagementsystem.databinding.FragmentOrdersBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OrdersFragment : Fragment() {

    private var _binging: FragmentOrdersBinding? = null
    private val binding get() = _binging!!

    private val orderAdapter: OrderAdapter by lazy { OrderAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binging = FragmentOrdersBinding.inflate(
            LayoutInflater.from(layoutInflater.context),
            container,
            false
        )

        setupRecyclerView()
        setHasOptionsMenu(true)

        binding.fabOrder.setOnClickListener {
            findNavController().navigate(R.id.action_ordersFragment_to_addOrder)
        }

        mainViewModel.readAllData.observe(viewLifecycleOwner) { data ->
            orderAdapter.setData(data)

        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.order_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete_all) {
            deleteAllItems()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllItems() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mainViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully removed everything. ",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete Item?")
        builder.setMessage("Are you sure you want to delete everything.")
        builder.create().show()


    }

    private fun setupRecyclerView() {
        binding.rView.apply {
            adapter = orderAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binging = null
    }
}