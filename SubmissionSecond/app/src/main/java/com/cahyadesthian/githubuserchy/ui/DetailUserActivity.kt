package com.cahyadesthian.githubuserchy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cahyadesthian.githubuserchy.R
import com.cahyadesthian.githubuserchy.databinding.ActivityDetailUserBinding
import com.cahyadesthian.githubuserchy.ui.adapter.SectionPagerAdapter
import com.cahyadesthian.githubuserchy.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private lateinit var detaiUserViewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)
        loadingIndicator(true)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)

        detaiUserViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            detaiUserViewModel.setUserDetail(username)
        }
        detaiUserViewModel.getUserDetail().observe(this, {

            if(it!=null) {
                detailUserBinding.apply {
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_user)
                        .into(ivPhotoUserDetailUI)

                    tvUsernamedataDetailUI.text = it.login
                    tvNameDetailUI.text = it.name

                    if(it.location!=null){
                        tvLocationdataDetailUI.text = it.location
                    }

                    if(it.company!=null){
                        tvWorkdataDetailUI.text = it.company
                    }


                    tvFollowerdataDetailUI.text = it.followers.toString()
                    tvFollowingdataUI.text = it.following.toString()
                    loadingIndicator(false)
                    supportActionBar?.title = it.login
                }
            }

        })

        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)
        val viewPager : ViewPager2 = detailUserBinding.vpDetailUI
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = detailUserBinding.tablayoutDetailUI
        TabLayoutMediator(tabs,viewPager) {tab,position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) detailUserBinding.pbDetailuserDetailUI?.visibility = View.VISIBLE else detailUserBinding.pbDetailuserDetailUI?.visibility = View.INVISIBLE
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }
}