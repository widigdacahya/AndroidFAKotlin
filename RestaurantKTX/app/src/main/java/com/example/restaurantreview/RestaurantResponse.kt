package com.example.restaurantreview

import com.google.gson.annotations.SerializedName

/*> To Sent Data Review
*[>1. MAke Model]
* This model to
* receive response
*
* The response is somethin like
* we can see here https://restaurant-api.dicoding.dev/detail/uewq1zg2zlskfw1e867
*in short it's look like
* {
//"error": false,
//"message": "success",
//"restaurant": {
/////"id": "uewq1zg2zlskfw1e867",
/////"name": "Kafein",
/////"description": "Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,",
/////"city": "Aceh",
/////"address": "Jln. Belimbing Timur no 27",
/////"pictureId": "15",
/////"rating": 4.6,
/////"categories": [],
/////"menus": {},
/////"customerReviews": []
///}
}
*
* */


//[take data from server]
data class RestaurantResponse(

	@field:SerializedName("restaurant")
	val restaurant: Restaurant,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message: String
)


//[take JSon Object]
data class Restaurant(

	@field:SerializedName("customerReviews")
	val customerReviews: List<CustomerReviewsItem>,

	@field:SerializedName("pictureId")
	val pictureId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("rating")
	val rating: Double,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("id")
	val id: String
)

//[take JSONArray]
data class CustomerReviewsItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("review")
	val review: String,

	@field:SerializedName("name")
	val name: String
)

//[For sending revuews]
data class PostReviewResponse(
	@field:SerializedName("customerReviews")
	val customerReviews: List<CustomerReviewsItem>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("message")
	val message:String


)