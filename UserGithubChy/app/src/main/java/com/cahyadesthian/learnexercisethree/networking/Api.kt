package com.cahyadesthian.learnexercisethree.networking

import com.cahyadesthian.learnexercisethree.data.model.SearchResponse
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    //Search : https://api.github.com/search/users?q={username}
    @GET("search/users")
    fun getSearchedUsers(
        @Query("q") query: String
    ): Call<SearchResponse>


    //Detail user : https://api.github.com/users/{username}
    @GET("users/{username}")
    fun getUserDetail (
    @Path("username") username: String
    ) : Call<UserResponse>

    //List Follower : https://api.github.com/users/{username}/followers
    @GET("users/{username}/followers")
    fun getFollower (
        @Path("username") username: String
    ) : Call<ArrayList<UserItemResponse>>
    //ini bentuknya JSON array jadi callnya beda sama yang detail dan search


    //List Following : https://api.github.com/users/{username}/following
    @GET("users/{username}/following")
    fun getFollowing (
        @Path("username") username: String
    ) : Call<ArrayList<UserItemResponse>>



}