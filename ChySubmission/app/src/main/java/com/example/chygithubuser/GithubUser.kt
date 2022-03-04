package com.example.chygithubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    var githubUserUsername: String,
    var githubUserName: String,
    var githubUserLocation: String,
    var githubUserRepo: String,
    var githubUserCompany: String,
    var githubUserFollowers: String,
    var githubUserFollowing: String,
    var githubUserAvatar: Int
) : Parcelable
