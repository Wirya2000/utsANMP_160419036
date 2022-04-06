package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.ubaya.ukuliner_160419036.R
import kotlinx.android.synthetic.main.fragment_create_review.*

/**
 * A simple [Fragment] subclass.
 * Use the [CreateReviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateReviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonCreateReview.setOnClickListener {
            Toast.makeText(context, "Review has been posted", Toast.LENGTH_SHORT).show()
            val restaurantID = CreateReviewFragmentArgs.fromBundle(requireArguments()).restaurantId
            val action = CreateReviewFragmentDirections.actionBackReviewList(restaurantID)
            Navigation.findNavController(it).navigate(action)
        }
    }
}