package com.cahyadesthian.secondsubmissionchy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.secondsubmissionchy.R
import com.cahyadesthian.secondsubmissionchy.databinding.ActivityDetailUserBinding
import com.cahyadesthian.secondsubmissionchy.ui.adapter.SectionPagerAdapter
import com.cahyadesthian.secondsubmissionchy.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private lateinit var viewModel : DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        if (username != null) {
            viewModel.setUserDetail(username)
        }

        viewModel.getUserDetail().observe(this, {
            if(it!=null) {
                detailUserBinding.apply {
                    tvNameDetailUI.text = it.name
                    tvUsernameDetailUI.text = it.login
                    tvCompanydataDetailUI.text = it.company
                    tvDatalocDetailUI.text = it.location
                    tvDatafollowerDetailUI.text = it.followers.toString()
                    tvDatafollowwingDetailUI.text = it.following.toString()

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivUserdetailDetailUI)

                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)
        val viewPager: ViewPager2 = detailUserBinding.vpDetailUI
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = detailUserBinding.tabsFollowDetailUI
        TabLayoutMediator(tabs,viewPager) { tab,position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        supportActionBar?.elevation = 0f

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