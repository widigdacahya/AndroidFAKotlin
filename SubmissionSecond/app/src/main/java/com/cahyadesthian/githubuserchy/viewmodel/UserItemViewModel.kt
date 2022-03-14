package com.cahyadesthian.githubuserchy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.githubuserchy.model.ItemsSearch
import com.cahyadesthian.githubuserchy.model.UserItemsResponse
import com.cahyadesthian.githubuserchy.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserItemViewModel : ViewModel() {

    val githubUsers = MutableLiveData<ArrayList<UserItemsResponse>>()

    fun setSearchedUser(q: String) {
        RetrofitClient.apiInstance
            .getSearchedUsers(q)
            .enqueue(object : Callback<ItemsSearch>{

                override fun onResponse(call: Call<ItemsSearch>, response: Response<ItemsSearch>) {
                    if (response.isSuccessful) {
                        githubUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<ItemsSearch>, t: Throwable) {
                    t.message?.let {
                        Log.d("onFailure : ", it)
                    }
                }

            })
    }


    fun getResultSearchedUser() : LiveData<ArrayList<UserItemsResponse>> = githubUsers

}