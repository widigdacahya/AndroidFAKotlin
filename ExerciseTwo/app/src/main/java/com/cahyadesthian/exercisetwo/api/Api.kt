package com.cahyadesthian.exercisetwo.api

import com.cahyadesthian.exercisetwo.data.model.DetailUserResponse
import com.cahyadesthian.exercisetwo.data.model.User
import com.cahyadesthian.exercisetwo.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>


    //untuk yang detail
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<DetailUserResponse>


    //untuk endpoint follower
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>
    //ini bentuknya JSON array jadi callnya beda sama yang detail dan search

    //untuk endpoint following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username : String
    ) : Call<ArrayList<User>>

}