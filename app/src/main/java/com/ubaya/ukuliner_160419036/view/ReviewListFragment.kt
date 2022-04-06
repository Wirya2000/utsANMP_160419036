package com.ubaya.ukuliner_160419036.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.viewmodel.ReviewListViewModel
import kotlinx.android.synthetic.main.fragment_review_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewListFragment : Fragment() {
    private lateinit var viewModel: ReviewListViewModel
    private val reviewListAdapter = ReviewListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val restaurantID = ReviewListFragmentArgs.fromBundle(requireArguments()).restaurantID
        viewModel = ViewModelProvider(this).get(ReviewListViewModel::class.java)
        viewModel.refresh(restaurantID)

        recyclerViewReview.layoutManager = LinearLayoutManager(context)
        recyclerViewReview.adapter = reviewListAdapter

        observeViewModel()

        refreshLayout.setOnRefreshListener {
            recyclerViewReview.visibility = View.GONE
            textErrorReview.visibility = View.GONE
            progressLoadReviewFrag.visibility = View.VISIBLE
            viewModel.refresh(restaurantID)
            refreshLayout.isRefreshing = false
        }

        fabCreateReview.setOnClickListener {
            val action = ReviewListFragmentDirections.actionCreateReview(restaurantID)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun observeViewModel() {
        viewModel.reviewLiveData.observe(viewLifecycleOwner) {
            reviewListAdapter.updateReviewList(it)
        }
        viewModel.reviewLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorReview.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                recyclerViewReview.visibility = View.GONE
                progressLoadReviewFrag.visibility = View.VISIBLE
            } else {
                recyclerViewReview.visibility = View.VISIBLE
                progressLoadReviewFrag.visibility = View.GONE
            }
        }
    }
}