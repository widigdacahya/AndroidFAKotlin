package com.cahyadesthian.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahyadesthian.githubuser.model.SearchRespone
import com.cahyadesthian.githubuser.model.UserSearchResponse
import com.cahyadesthian.githubuser.model.UsersResponse
import com.cahyadesthian.githubuser.networking.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserItemDetailViewModel : ViewModel() {

    private var _listIDYangDicari = MutableLiveData<ArrayList<String>>()
    val listIdYangDicari : LiveData<ArrayList<String>> = _listIDYangDicari

    private var _listUserDariItems = MutableLiveData<ArrayList<UserSearchResponse>>()
    val listUserDariItems : LiveData<ArrayList<UserSearchResponse>> = _listUserDariItems

    private var _listItemUserBagus = MutableLiveData<ArrayList<UsersResponse>>()
    val listItemUserBagus : LiveData<ArrayList<UsersResponse>> = _listItemUserBagus


    private var eachUser = MutableLiveData<UsersResponse>()

    fun findItemsUsersYangDicari(query: String) {
        RetrofitClient.apiService
            .getSearchUsers(query)
            .enqueue(object : Callback<SearchRespone>{
                override fun onResponse(
                    call: Call<SearchRespone>,
                    response: Response<SearchRespone>
                ) {
                    if(response.isSuccessful) {
                        _listUserDariItems.value = response.body()?.items
                    }else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<SearchRespone>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getItemUsersYangDicari() : LiveData<ArrayList<UserSearchResponse>> {
        return _listUserDariItems
    }


    fun setUserDetail(username: String) {
        RetrofitClient.apiService
            .getUserDetail(username)
            .enqueue(object : Callback<UsersResponse> {
                override fun onResponse(
                    call: Call<UsersResponse>,
                    response: Response<UsersResponse>
                ) {
                    if(response.isSuccessful) {
                        eachUser.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserDetail() : LiveData<UsersResponse> {
        return eachUser
    }




    companion object{
        private const val TAG = "UserItemDetailViewModel"
    }

}