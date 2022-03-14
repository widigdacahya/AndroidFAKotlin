package com.cahyadesthian.githubuserchy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.githubuserchy.model.UserDetailResponse
import com.cahyadesthian.githubuserchy.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel : ViewModel() {

    val user = MutableLiveData<UserDetailResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<UserDetailResponse>{
                override fun onResponse(
                    call: Call<UserDetailResponse>,
                    response: Response<UserDetailResponse>
                ) {
                    if(response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                    t.message?.let { Log.d("onFailure: ", it) }
                }

            })
    }


    fun getUserDetail(): LiveData<UserDetailResponse> = user

}