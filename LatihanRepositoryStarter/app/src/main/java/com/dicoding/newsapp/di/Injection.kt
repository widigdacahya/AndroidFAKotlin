package com.dicoding.newsapp.di

import android.content.Context
import com.dicoding.newsapp.data.NewsRepository
import com.dicoding.newsapp.data.local.room.NewsDatabase
import com.dicoding.newsapp.data.remote.retrofit.ApiConfig
import com.dicoding.newsapp.utils.AppExecutors

/**
 * STEP 3
 *  menggunakan service locator untuk memasukkan object tersebut pada ViewModelFactory.
 *  next NewsViewmOdel
 * */

object Injection {

    fun provideRepository(context: Context) : NewsRepository {
        val apiService = ApiConfig.getApiService()
        val database = NewsDatabase.getInstance(context)
        val dao = database.newsDao()
        val appExecutors = AppExecutors()
        return NewsRepository.getInstance(apiService,dao,appExecutors)
    }

}