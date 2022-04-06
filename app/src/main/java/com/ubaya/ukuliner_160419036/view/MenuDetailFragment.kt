package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.util.loadImage
import com.ubaya.ukuliner_160419036.viewmodel.MenuDetailViewModel
import kotlinx.android.synthetic.main.fragment_menu_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [MenuDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuDetailFragment : Fragment() {
    private lateinit var viewModel: MenuDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            val menuID = MenuDetailFragmentArgs.fromBundle(requireArguments()).menuID
            viewModel = ViewModelProvider(this).get(MenuDetailViewModel::class.java)
            viewModel.fetch(menuID)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.menuLiveData.observe(viewLifecycleOwner) {
            val menu = it
            menu?.let {
                textMenuDetailName.text = it.name
                textMenuDetailDescription.text = it.description
                textMenuDetailPrice.text = "Rp. ${it.price.toString()}"
                imageMenuDetailPhoto.loadImage(it.photoURL, progressLoadingMenuPhoto)
            }
        }
    }
}