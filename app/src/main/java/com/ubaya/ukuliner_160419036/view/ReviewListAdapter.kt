package com.ubaya.ukuliner_160419036.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.ukuliner_160419036.R
import com.ubaya.ukuliner_160419036.model.Review
import com.ubaya.ukuliner_160419036.util.loadImage
import kotlinx.android.synthetic.main.review_list_item.view.*

class ReviewListAdapter(val reviewList: ArrayList<Review>) : RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        with(holder.view) {
            textReviewUsername.text = review.username
            textReviewContent.text = review.content
            ratingBarReview.rating = review.rating!!
            cardViewReview.setOnClickListener {
                val action = ReviewListFragmentDirections.actionReviewDetail(review.id.toString())
                Navigation.findNavController(it).navigate(action)
            }
            imageReview.loadImage(review.photoURL, progressLoadingReview)
        }
    }

    override fun getItemCount() = reviewList.size

    fun updateReviewList(newStudentList: ArrayList<Review>) {
        reviewList.clear()
        reviewList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}