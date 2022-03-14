package com.cahyadesthian.githubuserchy.networking

import com.cahyadesthian.githubuserchy.model.ItemsSearch
import com.cahyadesthian.githubuserchy.model.UserDetailResponse
import com.cahyadesthian.githubuserchy.model.UserItemsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Search : https://api.github.com/search/users?q={username}
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchedUsers(
        @Query("q") query: String
    ): Call<ItemsSearch>


    //Detail user : https://api.github.com/users/{username}
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<UserDetailResponse>


    //List Follower : https://api.github.com/users/{username}/followers
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UserItemsResponse>>
    //ini bentuknya JSON array jadi callnya beda sama yang detail dan search


    //List Following : https://api.github.com/users/{username}/following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username : String
    ) : Call<ArrayList<UserItemsResponse>>



}