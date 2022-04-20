package com.cahyadesthian.learnexercisethree.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.learnexercisethree.networking.RetrofitClient
import com.cahyadesthian.learnexercisethree.data.model.SearchResponse
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Dealing data
 * that searched by user
 * */

class MainViewModel: ViewModel() {

    val listUsers = MutableLiveData<ArrayList<UserItemResponse>>()

    fun setSearchedUsers(query: String) {
        RetrofitClient.apiInstance
            .getSearchedUsers(query)
            .enqueue(object: Callback<SearchResponse>{
                override fun onResponse(
                    call: Call<SearchResponse>,
                    response: Response<SearchResponse>
                ) {
                    if(response.isSuccessful) {
                        listUsers.postValue(response.body()?.items)
                    }
                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getSearedchUsers(): LiveData<ArrayList<UserItemResponse>> = listUsers

}