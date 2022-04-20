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
 * Dealing data with follower fragment
 */

class FollowerViewModel: ViewModel() {

    val listFollowers = MutableLiveData<ArrayList<UserItemResponse>>()

    fun setListFollowers(username: String) {
        RetrofitClient.apiInstance
            .getFollower(username)
            .enqueue(object : Callback<ArrayList<UserItemResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<UserItemResponse>>,
                    response: Response<ArrayList<UserItemResponse>>
                ) {
                    if(response.isSuccessful) listFollowers.postValue(response.body())
                }

                override fun onFailure(call: Call<ArrayList<UserItemResponse>>, t: Throwable) {
                    t.message?.let { Log.d("onFailure : ", it) }
                }

            })
    }

    fun getListFollower() : LiveData<ArrayList<UserItemResponse>> = listFollowers

}