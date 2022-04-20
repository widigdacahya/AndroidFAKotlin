package com.cahyadesthian.learnexercisethree.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Dealing data with following fragment
 *
 */

class FollowingrViewModel: ViewModel() {

    val listFollowing = MutableLiveData<ArrayList<UserItemResponse>>()

    fun setListFollowing(username: String) {
        RetrofitClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<UserItemResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<UserItemResponse>>,
                    response: Response<ArrayList<UserItemResponse>>
                ) {
                    if(response.isSuccessful) listFollowing.postValue(response.body())
                }

                override fun onFailure(call: Call<ArrayList<UserItemResponse>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure : ", it) }
                }

            })
    }

    fun getListFollowing() : LiveData<ArrayList<UserItemResponse>> = listFollowing

}