package com.cahyadesthian.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.githubuser.model.UsersResponse
import com.cahyadesthian.githubuser.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    val user = MutableLiveData<UsersResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiService
            .getUserDetail(username)
            .enqueue(object : Callback<UsersResponse> {
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if(response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun setUserDetailWow(username: String) : LiveData<UsersResponse> {
        RetrofitClient.apiService
            .getUserDetail(username)
            .enqueue(object : Callback<UsersResponse> {
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if(response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })

        return user
    }

    fun getUserDetail() : LiveData<UsersResponse> {
        return user
    }
}