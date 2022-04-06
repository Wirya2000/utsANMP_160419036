package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantListFragment : Fragment() {
    private lateinit var viewModel: RestaurantListViewModel
    private val restaurantListAdapter = RestaurantListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(RestaurantListViewModel::class.java)
        viewModel.refresh()

        recyclerViewRestaurant.layoutManager = LinearLayoutManager(context)
        recyclerViewRestaurant.adapter = restaurantListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerViewRestaurant.visibility = View.GONE
            textErrorRestaurant.visibility = View.GONE
            progressLoadRestaurant.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            restaurantListAdapter.updateRestaurantList(it)
        }
        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorRestaurant.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recyclerViewRestaurant.visibility = View.GONE
                progressLoadRestaurant.visibility = View.VISIBLE
            } else {
                recyclerViewRestaurant.visibility = View.VISIBLE
                progressLoadRestaurant.visibility = View.GONE
            }
        }
    }
}