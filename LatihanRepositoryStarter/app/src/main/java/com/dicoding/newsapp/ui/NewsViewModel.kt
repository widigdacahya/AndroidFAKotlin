package com.dicoding.newsapp.ui

import androidx.lifecycle.ViewModel
import com.dicoding.newsapp.data.NewsRepository

/*
* STEP 4
* */
class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    fun getHeadlineNews() = newsRepository.getHeadlineNews()

}