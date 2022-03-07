package com.example.restaurantreview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.restaurantreview.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)



        supportActionBar?.hide()


        //[connect with mainViewModel]
        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.restaurant.observe(this, {restaurant ->
            setRestaurantData(restaurant)
        })

        mainViewModel.listReview.observe(this, {consumerReviews ->
            setReviewData(consumerReviews)
        })

        mainViewModel.isLoading.observe(this, {
            showLoading(it)
        })



        val layoutManager = LinearLayoutManager(this)
        mainBinding.rvReviewUI.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        mainBinding.rvReviewUI.addItemDecoration(itemDecoration)

        //[ LiveData View Model, so this findrestaurant will moved to View Model :D ]
        //findRestaurant()


        //[When button send]
        mainBinding.btnSendUI.setOnClickListener { view ->

            //[changed after view mode :) ]
            //postReview(mainBinding.etReviewUI.text.toString())
            mainViewModel.postReview(mainBinding.etReviewUI.text.toString())


            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken,0)
        }
    }

    //[Moved to MainViewModel too :D ]
//    private fun postReview(review:String) {
//        showLoading(true)
//        val client = ApiConfig.getApiService().postReview(RESTAURANT_ID, "Widigda", review)
//        client.enqueue(object :Callback<PostReviewResponse> {
//            override fun onResponse(
//                call: Call<PostReviewResponse>,
//                response: Response<PostReviewResponse>
//            ) {
//                showLoading(false)
//                val responseBody = response.body()
//                if(response.isSuccessful && responseBody!=null) {
//                    setReviewData(responseBody.customerReviews)
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//
//        })
//    }
//



    //[ LiveData View Model, so this findrestaurant will moved to View Model :D ]
//    private fun findRestaurant() {
//        showLoading(true)
//
//        //pemanggilan endpoint
//        /*
//        * fungsi enqueue untuk menjalankan request secara asynchronous di background
//        *
//        * */
//        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
//        client.enqueue(object : Callback<RestaurantResponse> {
//            override fun onResponse(call: Call<RestaurantResponse>,response: Response<RestaurantResponse>
//            ) {
//                showLoading(false)
//                if(response.isSuccessful) {
//                    val responseBody = response.body()
//                    if(responseBody != null) {
//                        setRestaurantData(responseBody.restaurant)
//                        setReviewData(responseBody.restaurant.customerReviews)
//                    }
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//
//            }
//
//
//            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
//                showLoading(false)
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//
//        })
//
//    }


    private fun setRestaurantData(restaurant: Restaurant) {
        mainBinding.tvTitleUI.text = restaurant.name
        mainBinding.tvDescUI.text = restaurant.description

        Glide.with(this@MainActivity)
            .load("https://restaurant-api.dicoding.dev/images/large/${restaurant.pictureId}")
            .into(mainBinding.ivPictureUI)
    }


    private fun setReviewData(consumerReviews: List<CustomerReviewsItem>) {
        val listReview = ArrayList<String>()
        for(review in consumerReviews) {
            listReview.add(
                """
                    ${review.review}
                    - ${review.name}
                """.trimIndent()
            )
        }
        val adapter = ReviewAdapter(listReview)
        mainBinding.rvReviewUI.adapter = adapter
        mainBinding.etReviewUI.setText("")
    }


    private fun showLoading(isLoading: Boolean) {
        if(isLoading) {
            mainBinding.progressBarUI.visibility = View.VISIBLE
        } else {
            mainBinding.progressBarUI.visibility = View.GONE
        }
    }



    companion object {
        private const val TAG = "MainActivity"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}