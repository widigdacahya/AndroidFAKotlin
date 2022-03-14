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

class UserViewModel : ViewModel() {

    var listUser = MutableLiveData<ArrayList<UserSearchResponse>>()

    //var listUser = ArrayList<UserSearchResponse>()

    //var listUser = UserSearchResponse

    //var listUserToReturn = arrayListOf<UsersResponse>()
    //var listIdUser = ArrayList<String>()

    fun setSearchUsers(query: String) {
        RetrofitClient.apiService
            .getSearchUsers(query)
            .enqueue(object : Callback<SearchRespone>{
                override fun onResponse(
                    call: Call<SearchRespone>,
                    response: Response<SearchRespone>
                ) {
                    if(response.isSuccessful) {
                        listUser.postValue(response.body()?.items)

                        /*
                        var tempSearchResp = response.body()?.items
                        if (tempSearchResp != null) {
                            tempSearchResp.forEach {
                                ////(listUser).(add)(it)
                                listUser.postValue(it)
                            }
                        }*/
                        //listUser.add(tempSearchResp)
                        //listUser.add(response.body().items)
                    }
                }

                override fun onFailure(call: Call<SearchRespone>, t: Throwable) {
                    t.message?.let { Log.d("onFailure", it) }
                }

            })
    }


    /*

    fun processUser(listUser: MutableLiveData<ArrayList<UserSearchResponse>>) {
        //var userTemp : MutableLiveData<UsersResponse>? = null
        var userTemp : UsersResponse


        for(element in listUser) {
            listIdUser.add(element.login)
        }




        for(people in listIdUser) {
            RetrofitClient.apiService
                .getUserDetail(people)
                .enqueue(object : Callback<UsersResponse>{
                    override fun onResponse(
                        call: Call<UsersResponse>,
                        response: Response<UsersResponse>
                    ) {
                        if(response.isSuccessful) {
                            //userTemp?.postValue(response.body())
                            userTemp = response.body()!!
                            listUserToReturn.add(userTemp)
                        }
                    }

                    override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                        t.message?.let { Log.d("Failure", it) }
                    }

                })
        }


    }


    fun hereYourList() : ArrayList<UsersResponse> {
        processUser(listUser)
        return listUserToReturn
    }

*/

    fun getSearchUsers() : LiveData<ArrayList<UserSearchResponse>> {
        return listUser
    }


}

