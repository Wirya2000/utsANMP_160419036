package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.util.loadImage
import com.ubaya.ukuliner_160419036.viewmodel.MenuDetailViewModel
import com.ubaya.ukuliner_160419036.viewmodel.RestaurantDetailViewModel
import kotlinx.android.synthetic.main.fragment_menu_detail.*
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import kotlinx.android.synthetic.main.fragment_review_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RestaurantDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RestaurantDetailFragment : Fragment() {
    private lateinit var viewModel: RestaurantDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurant_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            val restaurantID = RestaurantDetailFragmentArgs.fromBundle(requireArguments()).restaurantID
            viewModel = ViewModelProvider(this).get(RestaurantDetailViewModel::class.java)
            viewModel.fetch(restaurantID)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.restaurantLiveData.observe(viewLifecycleOwner) {
            val restaurant = it
            restaurant?.let {
                textRestaurantDetailName.text = it.name
                textRestaurantDetailCategory.text = it.category
                textRestaurantDetailAddress.text = it.address
                textRestaurantDetailRating.text = it.rating.toString()
                imageRestaurantDetail.loadImage(it.photoURL, progressLoadRestaurantDetail)
            }
            buttonMenu.setOnClickListener {
                val action = RestaurantDetailFragmentDirections.actionMenuList(restaurant.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            buttonReview.setOnClickListener {
                val action = RestaurantDetailFragmentDirections.actionReviewList(restaurant.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            iconRestaurantDetailFavourite.setOnClickListener {
                iconRestaurantDetailFavourite.setImageResource(R.drawable.ic_baseline_favorite_24)
                Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show()
            }
        }
    }
}