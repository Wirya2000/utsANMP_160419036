package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.viewmodel.FavouriteRestaurantViewModel
import com.ubaya.ukuliner_160419036.viewmodel.MenuListViewModel
import com.ubaya.ukuliner_160419036.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.fragment_favourite_restaurant.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.refreshLayout

/**
 * A simple [Fragment] subclass.
 * Use the [FavouriteRestaurantFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouriteRestaurantFragment : Fragment() {
    private lateinit var viewModel: FavouriteRestaurantViewModel
    private val favouriteListAdapter = FavouriteRestaurantAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FavouriteRestaurantViewModel::class.java)
        viewModel.refresh()

        recyclerViewFavourite.layoutManager = LinearLayoutManager(context)
        recyclerViewFavourite.adapter = favouriteListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerViewFavourite.visibility = View.GONE
            textErrorFavourite.visibility = View.GONE
            progressLoadFavourite.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            favouriteListAdapter.updateRestaurantList(it)
        }
        viewModel.restaurantLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorFavourite.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recyclerViewFavourite.visibility = View.GONE
                progressLoadFavourite.visibility = View.VISIBLE
            } else {
                recyclerViewFavourite.visibility = View.VISIBLE
                progressLoadFavourite.visibility = View.GONE
            }
        }
    }
}