package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.viewmodel.ReviewDetailViewModel
import kotlinx.android.synthetic.main.fragment_review_detail.*
import kotlinx.android.synthetic.main.review_list_item.view.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewDetailFragment : Fragment() {
    private lateinit var viewModel: ReviewDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (arguments != null) {
            val reviewID = ReviewDetailFragmentArgs.fromBundle(requireArguments()).reviewID
            viewModel = ViewModelProvider(this).get(ReviewDetailViewModel::class.java)
            viewModel.fetch(reviewID)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.reviewLiveData.observe(viewLifecycleOwner) {
            val review = it
            review?.let {
                textReviewDetailDate.text = it.date.toString()
                textReviewDetailUsername.text = it.username
                textReviewDetailTitle.text = it.title
                textReviewDetailContent.text = it.content
                ratingBarReviewDetail.rating = it.rating!!
            }
        }
    }
}