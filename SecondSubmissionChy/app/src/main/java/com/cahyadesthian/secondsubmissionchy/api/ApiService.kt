package com.cahyadesthian.secondsubmissionchy.api

import com.cahyadesthian.secondsubmissionchy.model.UserSearchResponse
import com.cahyadesthian.secondsubmissionchy.model.UsersResponse
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
    ): Call<UserSearchResponse>

    //Detail user : https://api.github.com/users/{username}
    //untuk yang detail
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username : String
    ): Call<UsersResponse>

    //List Follower : https://api.github.com/users/{username}/followers
    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<UsersResponse>>

    //List Following : https://api.github.com/users/{username}/following
    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username : String
    ) : Call<ArrayList<UsersResponse>>

}


