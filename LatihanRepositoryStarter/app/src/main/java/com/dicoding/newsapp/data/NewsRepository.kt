package com.dicoding.newsapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.dicoding.newsapp.BuildConfig

import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.data.local.room.NewsDao
import com.dicoding.newsapp.data.remote.response.NewsResponse
import com.dicoding.newsapp.data.remote.retrofit.ApiService
import com.dicoding.newsapp.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Constructor

/**
 * STEP 2
 * untuk menyimpan data dari network ke local
 *
 * */
class NewsRepository private constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao,
    private val appExecutors: AppExecutors
) {

    private val result = MediatorLiveData<Result<List<NewsEntity>>>()

    /**
     * STEP 3 ada perubahan dikarekanakn penerapan coroutine
     * */
    //fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> {
    fun getHeadlineNews(): LiveData<Result<List<NewsEntity>>> = liveData {
        //result.value = Result.Loading
        emit(Result.Loading)
        try {
            val response = apiService.getNews(BuildConfig.API_KEY)
            val articles = response.articles
            val newsList = articles.map { article ->
                val isBookmarked = newsDao.isNewsBookmarked(article.title)
                NewsEntity(
                    article.title,
                    article.publishedAt,
                    article.urlToImage,
                    article.url,
                    isBookmarked
                )
            }
            newsDao.deleteAll()
            newsDao.insertNews(newsList)
        } catch (e: Exception) {
            Log.d("NewsRepository", "getHeadlineNews: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }

        val localData: LiveData<Result<List<NewsEntity>>> = newsDao.getNews().map { Result.Success(it) }
        emitSource(localData)

        /*
        val client = apiService.getNews(BuildConfig.API_KEY)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if(response.isSuccessful) {
                    val articles = response.body()?.articles
                    val newsList = ArrayList<NewsEntity>()
                    appExecutors.diskIO.execute() {
                        articles?.forEach { article ->
                            val isBookmarked = newsDao.isNewsBookmarked(article.title)
                            val news = NewsEntity(
                                article.title,
                                article.publishedAt,
                                article.urlToImage,
                                article.url,
                                isBookmarked
                            )
                            newsList.add(news)
                        }
                        newsDao.deleteAll()
                        newsDao.insertNews(newsList)
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }

        })

        */

        /*
        val localData = newsDao.getNews()
        result.addSource(localData) { newData: List<NewsEntity> ->
            result.value = Result.Success(newData)
        }
        return result
        */

    }



    /**
     * STEP 7
     * membuat bookmark
     * */
    //7_1
    fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        return newsDao.getBookmarkedNews()
    }
    //7_1


    //7_2

    /*
    fun setBookmarkedNews(news: NewsEntity, bookmarkState: Boolean) {
        appExecutors.diskIO.execute {
            news.isBookmarked = bookmarkState
            newsDao.updateNews(news)
        }
    }*/

    //7_2


    /**
     * Penerapan Coroutine pada setBookmarkedNews
     * **/
    suspend fun setNewsBookmark(news:NewsEntity, bookmarkState: Boolean) {
        news.isBookmarked = bookmarkState
        newsDao.updateNews(news)
    }


    companion object {
        @Volatile
        private var instance : NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            newsDao: NewsDao,
            appExecutors: AppExecutors
        ): NewsRepository =
            instance?: synchronized(this) {
                instance?: NewsRepository(apiService,newsDao,appExecutors)
            }.also { instance = it }
    }


}

/***
 * Pada fungsi ini, terdapat beberapa langkah untuk membuat offline support, antara lain:
 * 1 Inisiasi dengan status Loading.
 * 2 Mengambil dari dari network dengan ApiService.
 * 3 Membaca data ketika response berhasil.
 * 4 Mengecek apakah data yang ada sudah ada di dalam bookmark atau belum.
 * 5 Mengubah data response menjadi entity sebelum dimasukkan ke dalam database.
 * 6 Menghapus semua data dari database yang tidak ditandai bookmark.
 * 7 Memasukkan data baru dari internet ke dalam database.
 * 8 Memberi status jika terjadi eror.
 * 9 Mengambil data dari database yang merupakan sumber utama untuk dikonsumsi dan memberi tanda sukses.
 * Dengan skema di atas, maka semua data yang didapat dari network disimpan
 * ke dalam database. Sehingga, ketika tidak ada koneksi internet,
 * aplikasi tetap dapat menampilkan data yang sudah disimpan dari database.
 * Inilah fungsi Repository yang digunakan untuk mengatur dari berbagai sumber data.
 *
 *
 *
 * */