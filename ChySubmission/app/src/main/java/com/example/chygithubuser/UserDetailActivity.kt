package com.example.chygithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chygithubuser.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val dataSent = intent.getParcelableExtra<GithubUser>(EXTRA_DATA) as GithubUser
        detailBinding.apply {
            ivPhotoDetailUI.setImageResource(dataSent.githubUserAvatar)
            tvNameDetailUI.text = dataSent.githubUserName
            tvUsernameDetailUI.text = dataSent.githubUserUsername
            tvCompanyDetailUI.text = dataSent.githubUserCompany
            tvRepoDataDetailUI.text = dataSent.githubUserRepo
            tvLocationDataDetailUI.text = dataSent.githubUserLocation
            tvFollowerDataDetailUI.text = dataSent.githubUserFollowers
            tvFollowingDataDetailUI.text = dataSent.githubUserFollowing
        }

        supportActionBar?.title = dataSent.githubUserName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }
}