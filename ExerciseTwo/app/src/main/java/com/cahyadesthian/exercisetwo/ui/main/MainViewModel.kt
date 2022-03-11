package com.cahyadesthian.exercisetwo.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.exercisetwo.api.RetrofitClient
import com.cahyadesthian.exercisetwo.data.model.User
import com.cahyadesthian.exercisetwo.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel : ViewModel() {

    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if(response.isSuccessful) {
                        listUser.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }


    fun getSearchUsers() : LiveData<ArrayList<User>> {
        return listUser
    }

}