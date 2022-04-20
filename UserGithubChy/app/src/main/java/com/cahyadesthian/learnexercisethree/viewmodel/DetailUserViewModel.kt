package com.cahyadesthian.learnexercisethree.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cahyadesthian.learnexercisethree.data.FavUserRepository
import com.cahyadesthian.learnexercisethree.networking.RetrofitClient
import com.cahyadesthian.learnexercisethree.data.model.UserResponse
import com.cahyadesthian.learnexercisethree.data.database.FavUser
import com.cahyadesthian.learnexercisethree.data.database.UserFabDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



/**
 * Dealing data with detail screen
 * it will deal also with the
 * db by repository (kind of better practice to separate concern of app)
 */

class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    private val favUserRepository: FavUserRepository
    init {
        val favUserDao = UserFabDatabase.getDatabase(application)?.favUserDao()
        favUserRepository = favUserDao?.let { FavUserRepository(it) }!!
    }

    val user = MutableLiveData<UserResponse>()

    fun setUserDetail(username: String) {
        RetrofitClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<UserResponse> {
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }

            })
    }

    fun getUserDetail(): LiveData<UserResponse> = user


    fun addToFav(username: String,id: Int, avatar_url:String) {
        CoroutineScope(Dispatchers.IO).launch {
            var user = FavUser(id, username,avatar_url)
            favUserRepository.addToFav(user)
        }
    }

    suspend fun checkUser(id:Int) = favUserRepository.checkUser(id)

    fun removeFromFav(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            favUserRepository.deleteFromFav(id)
        }
    }


}