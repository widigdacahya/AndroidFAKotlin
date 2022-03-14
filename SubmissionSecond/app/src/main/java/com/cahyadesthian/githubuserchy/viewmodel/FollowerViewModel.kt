package com.cahyadesthian.githubuserchy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.githubuserchy.model.UserItemsResponse
import com.cahyadesthian.githubuserchy.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerViewModel: ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<UserItemsResponse>>()

    fun setListFollowers(username: String) {
        RetrofitClient.apiInstance
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<UserItemsResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<UserItemsResponse>>,
                    response: Response<ArrayList<UserItemsResponse>>
                ) {
                    if(response.isSuccessful) listFollowers.postValue(response.body())
                }

                override fun onFailure(call: Call<ArrayList<UserItemsResponse>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure : ", it) }
                }

            })
    }


    fun getListFollower() : LiveData<ArrayList<UserItemsResponse>> = listFollowers

}