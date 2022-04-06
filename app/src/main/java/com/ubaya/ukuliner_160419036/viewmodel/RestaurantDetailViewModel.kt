package com.ubaya.ukuliner_160419036.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.ubaya.ukuliner_160419036.model.Restaurant

class RestaurantDetailViewModel(application: Application) : AndroidViewModel(application) {
    val restaurantLiveData = MutableLiveData<Restaurant>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(restaurantID: String) {
        queue = Volley.newRequestQueue(getApplication())
        Log.d("masuk", restaurantID)
        val url = "http://my-json-server.typicode.com/Wirya2000/utsANMP_160419036/restaurant/$restaurantID"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val result = Gson().fromJson<Restaurant>(it, Restaurant::class.java)
                restaurantLiveData.value = result
                Log.d("showvolley", it)
            },
            {
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}