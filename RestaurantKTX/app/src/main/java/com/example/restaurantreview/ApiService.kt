package com.example.restaurantreview

import retrofit2.Call
import retrofit2.http.*

/**
 * kumpulan endpoint
 *Pada aplikasi ini terdapat 2 endpoint
 *1.  fungsi getRestaurant dengan annotation @GET
 *2.  fungsi postReview dengan annotation @POST
 *
 * getRestaurant dengan annotation @GET untuk mengambil data.
 * penggantian variable {id} pada endpoint dengan menggunakan @Path
 * sehingga dapat mengakses detail suatu restoran dengan
 * URL https://restaurant-api.dicoding.dev/detail/uewq1zg2zlskfw1e867.
 *
 *
 *  fungsi postReview dengan annotation @POST untuk mengirim data.
 *  Selain itu, Anda juga dapat menambahkan @Header untuk menyematkan
 *  token jika API tersebut membutuhkan otorisasi. Kemudian,
 *  Anda harus memakai anotasi @FormUrlEncoded untuk mengirimkan
 *  data dengan menggunakan @Field. Pastikan key yang dimasukkan
 *  pada @Field harus sama dengan field yang ada pada API.
 *  Jika tidak sama, data tidak akan terkirim.
 *
 *
 * */

interface ApiService {
    @GET("detail/{id}")
    fun getRestaurant(
        @Path("id") id:String
    ): Call<RestaurantResponse>



    //[To send repiew]
    @FormUrlEncoded
    @Headers("Authorization: token 12345")
    @POST("review")
    fun postReview(
        @Field("id") id:String,
        @Field("name") name:String,
        @Field("review") review: String
    ) : Call<PostReviewResponse>



}

