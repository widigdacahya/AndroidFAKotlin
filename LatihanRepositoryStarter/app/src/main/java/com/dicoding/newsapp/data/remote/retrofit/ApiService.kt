package com.dicoding.newsapp.data.remote.retrofit

import com.dicoding.newsapp.data.remote.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines?country=id&category=science")
    //fun getNews(@Query("apiKey") apiKey: String): Call<NewsResponse>
    fun getNews(@Query("apiKey") apiKey: String): NewsResponse

    /**
     * STEP 2 Penerapan Coroutine (getNews call di delete)
     * Step 1 ada di NewsDao dimana ada beberapa method ditambai suspend
     * next step di NewsReposirtory
     * */
}