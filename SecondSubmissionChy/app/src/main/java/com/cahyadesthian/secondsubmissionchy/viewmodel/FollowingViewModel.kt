package com.cahyadesthian.secondsubmissionchy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.secondsubmissionchy.api.ApiClient
import com.cahyadesthian.secondsubmissionchy.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FollowingViewMode: ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<UsersResponse>>()

    fun setListFollowing(username: String) {
        ApiClient.apiService
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<UsersResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<UsersResponse>>,
                    response: Response<ArrayList<UsersResponse>>
                ) {
                    if(response.isSuccessful) {
                        listFollowing.postValue(response.body())
                    }
                }
                override fun onFailure(call: Call<ArrayList<UsersResponse>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                }

            })
    }

    fun getListFollowing(): LiveData<ArrayList<UsersResponse>> {
        return listFollowing
    }
}