package com.cahyadesthian.apitodo

import retrofit2.Response
import retrofit2.http.GET

interface ToDoAPI {

    //place where we can put function to our API

    @GET("todo")
    suspend fun getListTodo(): Response<List<ToDo>>


}