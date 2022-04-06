package com.ubaya.ukuliner_160419036.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.model.Menu
import com.ubaya.ukuliner_160419036.model.Restaurant
import com.ubaya.ukuliner_160419036.util.loadImage
import kotlinx.android.synthetic.main.fragment_menu_list.view.*
import kotlinx.android.synthetic.main.fragment_restaurant_detail.*
import kotlinx.android.synthetic.main.menu_list_item.view.*
import kotlinx.android.synthetic.main.restaurant_list_item.view.*

class MenuListAdapter(val menuList:ArrayList<Menu>) : RecyclerView.Adapter<MenuListAdapter.MenuViewHolder>() {
    class MenuViewHolder(var  view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.menu_list_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = menuList[position]
        with(holder.view) {
            textMenuName.text = menu.name
            textMenuDescription.text = menu.description
            textMenuPrice.text = "Rp. ${menu.price.toString()}"
            imageMenuPhoto.loadImage(menu.photoURL, progressLoadingMenuItem)
            cardViewMenu.setOnClickListener {
                val action = MenuListFragmentDirections.actionMenuDetail(menu.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun getItemCount() = menuList.size

    fun updateMenuList(newMenuList: ArrayList<Menu>) {
        menuList.clear()
        menuList.addAll(newMenuList)
        notifyDataSetChanged()
    }
}