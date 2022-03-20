package com.dicoding.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.newsapp.data.NewsRepository
import com.dicoding.newsapp.data.local.entity.NewsEntity
import kotlinx.coroutines.launch

/*
* STEP 4
* */
class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {

    fun getHeadlineNews() = newsRepository.getHeadlineNews()


    /**
     * STEP 8
     * ada hubungannya dengan pembuatan bookmark
     * */
    fun getBookmarkedNews() = newsRepository.getBookmarkedNews()

    fun saveNews(news: NewsEntity) {
        //newsRepository.setBookmarkedNews(news,true)
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news,true)
        }
    }

    fun deleteNews(news: NewsEntity) {
        //newsRepository.setBookmarkedNews(news,false)
        viewModelScope.launch {
            newsRepository.setNewsBookmark(news,false)
        }
    }

    /**
     * after step 8,
     * we will go to item_news.xml to make a bookmarke icon
     * */
}