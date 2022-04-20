package com.cahyadesthian.learnexercisethree.data.model

data class UserResponse(
    val login: String,
    val id:Int,
    val avatar_url: String,
    val followers_url : String,
    val following_url : String,
    val followers: Int,
    val following: Int,
    val name: String,
    val company : String,
    val location : String
)