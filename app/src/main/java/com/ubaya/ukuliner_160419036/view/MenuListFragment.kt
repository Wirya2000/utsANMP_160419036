package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.viewmodel.MenuListViewModel
import com.ubaya.ukuliner_160419036.viewmodel.RestaurantListViewModel
import kotlinx.android.synthetic.main.fragment_menu_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.refreshLayout

/**
 * A simple [Fragment] subclass.
 * Use the [MenuListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuListFragment : Fragment() {
    private lateinit var viewModel: MenuListViewModel
    private val menuListAdapter = MenuListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val restaurantID = MenuListFragmentArgs.fromBundle(requireArguments()).restaurantID
        viewModel = ViewModelProvider(this).get(MenuListViewModel::class.java)
        viewModel.refresh(restaurantID)

        recyclerViewMenu.layoutManager = LinearLayoutManager(context)
        recyclerViewMenu.adapter = menuListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerViewMenu.visibility = View.GONE
            textErrorMenu.visibility = View.GONE
            progressLoadMenu.visibility = View.VISIBLE
            viewModel.refresh(restaurantID)
            refreshLayout.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.menuLiveData.observe(viewLifecycleOwner) {
            menuListAdapter.updateMenuList(it)
        }
        viewModel.menuLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorMenu.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recyclerViewMenu.visibility = View.GONE
                progressLoadMenu.visibility = View.VISIBLE
            } else {
                recyclerViewMenu.visibility = View.VISIBLE
                progressLoadMenu.visibility = View.GONE
            }
        }
    }
}