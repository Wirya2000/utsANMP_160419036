package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.util.loadImage
import com.ubaya.ukuliner_160419036.viewmodel.ProfileDetailViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlin.math.log

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    private lateinit var viewModel: ProfileDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val profileID = 1
        Log.d("masuk2", "masuk2")
        viewModel = ViewModelProvider(this).get(ProfileDetailViewModel::class.java)
        viewModel.fetch(profileID.toString())

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            val profile = it
            profile?.let {
                editProfileName.setText(it.name)
                editProfileEmail.setText(it.email)
                editProfilePhone.setText(it.phone)
                editProfileUserbame.setText(it.username)
                imageProfile.loadImage(it.photoURL, progressLoadingProfile)
            }
            buttonUpdate.setOnClickListener {
                Toast.makeText(context, "Profile has been updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}