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


class FollowerViewMode: ViewModel() {
    val listFollowers = MutableLiveData<ArrayList<UsersResponse>>()

    fun setListFollowers(username: String) {
        ApiClient.apiService
            .getFollowers(username)
            .enqueue(object : Callback<ArrayList<UsersResponse>>{
                override fun onResponse(
                    call: Call<ArrayList<UsersResponse>>,
                    response: Response<ArrayList<UsersResponse>>
                ) {
                    if(response.isSuccessful) {
                        listFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<UsersResponse>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                }

            })
    }


    fun getListFollower(): LiveData<ArrayList<UsersResponse>> {
        return listFollowers
    }
}