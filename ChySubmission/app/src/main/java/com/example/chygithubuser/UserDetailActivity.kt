package com.example.chygithubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chygithubuser.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
    }

    private lateinit var detailBinding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        val dataSent = intent.getParcelableExtra<GithubUser>(EXTRA_DATA) as GithubUser
        detailBinding.ivPhotoDetailUI.setImageResource(dataSent.githubUserAvatar)
        detailBinding.tvNameDetailUI.text = dataSent.githubUserName
        detailBinding.tvUsernameDetailUI.text = dataSent.githubUserUsername
        detailBinding.tvCompanyDetailUI.text = dataSent.githubUserCompany
        detailBinding.tvRepoDataDetailUI.text = dataSent.githubUserRepo
        detailBinding.tvLocationDataDetailUI.text = dataSent.githubUserLocation
        detailBinding.tvFollowerDataDetailUI.text = dataSent.githubUserFollowers
        detailBinding.tvFollowingDataDetailUI.text = dataSent.githubUserFollowing

        supportActionBar?.title = dataSent.githubUserName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}