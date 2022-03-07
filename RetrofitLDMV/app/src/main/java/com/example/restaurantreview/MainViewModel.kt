package com.example.restaurantreview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant


    private val _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    /*
    *
    * LiveData dengan Single Event
    * */
    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackBarText: LiveData<Event<String>> = _snackbarText


    init {
        findRestaurant()
    }

    private fun findRestaurant() {
        //showLoading(true)
        _isLoading.value = true

        //pemanggilan endpoint
        /*
        * fungsi enqueue untuk menjalankan request secara asynchronous di background
        *
        * */
        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<RestaurantResponse> {
            override fun onResponse(call: Call<RestaurantResponse>, response: Response<RestaurantResponse>
            ) {
                //showLoading(false)

                _isLoading.value = false

                if(response.isSuccessful) {
                    val responseBody = response.body()
                    if(responseBody != null) {
                        //setRestaurantData(responseBody.restaurant)
                        //setReviewData(responseBody.restaurant.customerReviews)

                        _restaurant.value = response.body()?.restaurant
                        _listReview.value = response.body()?.restaurant?.customerReviews
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }

            }


            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                //showLoading(false)
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })

    }

    fun postReview(review:String) {
        //showLoading(true)

        _isLoading.value = true
        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "Desthian", review)
        client.enqueue(object :Callback<PostReviewResponse> {
            override fun onResponse(
                call: Call<PostReviewResponse>,
                response: Response<PostReviewResponse>
            ) {
                //showLoading(false)

                _isLoading.value = false
                //val responseBody = response.body()
                //if(response.isSuccessful && responseBody!=null) {
                //    setReviewData(responseBody.customerReviews)
                //} else {
                //    Log.e(MainActivity.TAG, "onFailure: ${response.message()}")
                //}

                if(response.isSuccessful) {
                    _listReview.value = response.body()?.customerReviews
                    _snackbarText.value = Event(response.body()?.message.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                //showLoading(false)
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }




    companion object {
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

}