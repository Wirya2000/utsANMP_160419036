package com.ubaya.ukuliner_160419036.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.model.Restaurant
import com.ubaya.ukuliner_160419036.util.loadImage
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import kotlinx.android.synthetic.main.fragment_restaurant_list.view.*
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class RestaurantListAdapter(val restaurantList:ArrayList<Restaurant>) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {
    class RestaurantViewHolder(var  view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.restaurant_list_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        with(holder.view) {
            textRestaurantName.text = restaurant.name
            textRestaurantRating.text = restaurant.rating.toString()
            textRestaurantAddress.text = restaurant.address
            textRestaurantCategory.text = restaurant.category
            textRestaurantRating.text = restaurant.rating.toString()
            imageRestaurantPhoto.loadImage(restaurant.photoURL, progressLoadRestaurantPhoto)
            cardViewRestaurant.setOnClickListener {
                val action = RestaurantListFragmentDirections.actionRestaurantDetail(restaurant.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = restaurantList.size

    fun updateRestaurantList(newRestaurantList: ArrayList<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }
}