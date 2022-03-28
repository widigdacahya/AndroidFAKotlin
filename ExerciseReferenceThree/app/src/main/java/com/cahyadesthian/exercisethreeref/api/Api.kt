package com.cahyadesthian.exercisethreeref.api

import android.os.Build
import com.cahyadesthian.exercisethreeref.BuildConfig
import com.cahyadesthian.exercisethreeref.BuildConfig.API_KEY_EXERCISE_THREE_REFERENCE
import com.cahyadesthian.exercisethreeref.data.model.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    private val magicString: String
        get() = BuildConfig.API_KEY_EXERCISE_THREE_REFERENCE

    @GET("search/users")
    //@Header("Authorization: token ${this.magicString}")
    fun getSearchUsers(
        @Query("q") query: String
    ):Call<SearchResponse>


}