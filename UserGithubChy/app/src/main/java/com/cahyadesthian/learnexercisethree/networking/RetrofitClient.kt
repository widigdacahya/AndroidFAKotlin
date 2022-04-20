package com.cahyadesthian.learnexercisethree.networking


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(InterceptorHeader())
    }.build()

    private val retrofitApi: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance: Api by lazy {
        retrofitApi.create(Api::class.java)
    }


}