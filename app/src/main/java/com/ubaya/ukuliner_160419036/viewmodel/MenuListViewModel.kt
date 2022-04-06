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
import com.google.gson.reflect.TypeToken
import com.ubaya.ukuliner_160419036.model.Menu
import com.ubaya.ukuliner_160419036.model.Restaurant

class MenuListViewModel(application: Application) : AndroidViewModel(application) {
    val menuLiveData = MutableLiveData<ArrayList<Menu>>()
    val menuLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(restaurantID: String) {
        menuLoadErrorLiveData.value = false
        loadingLiveData.value = true
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://my-json-server.typicode.com/Wirya2000/utsANMP_160419036/menu?restaurant_id=$restaurantID"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<ArrayList<Menu>>() {}.type
                val result = Gson().fromJson<ArrayList<Menu>>(it, sType)
                menuLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                menuLoadErrorLiveData.value = true
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