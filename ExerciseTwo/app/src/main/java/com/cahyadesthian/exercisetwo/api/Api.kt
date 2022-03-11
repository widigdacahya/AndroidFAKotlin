package com.cahyadesthian.exercisetwo.api

import com.cahyadesthian.exercisetwo.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}