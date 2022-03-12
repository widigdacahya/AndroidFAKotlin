package com.cahyadesthian.secondsubmissionchy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.secondsubmissionchy.api.ApiClient
import com.cahyadesthian.secondsubmissionchy.model.UserSearchResponse
import com.cahyadesthian.secondsubmissionchy.model.UsersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserMainViewModel : ViewModel() {

    val githubUserList = MutableLiveData<ArrayList<UsersResponse>>()


    fun setSearchUsers(query: String) {
        ApiClient.apiService
            .getSearchedUsers(query)
            .enqueue(object : Callback<UserSearchResponse>{
                override fun onResponse(
                    call: Call<UserSearchResponse>,
                    response: Response<UserSearchResponse>
                ) {
                    if(response.isSuccessful) githubUserList.postValue(response.body()?.items)

                }
                override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<UsersResponse>> {
        return githubUserList
    }
}