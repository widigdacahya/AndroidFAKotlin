package com.cahyadesthian.learnexercisethree.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.databinding.ActivityDetailUserBinding
import com.cahyadesthian.learnexercisethree.ui.adapter.SectionPagerAdapter
import com.cahyadesthian.learnexercisethree.viewmodel.DetailUserViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding
    private lateinit var detailViewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        loadingIndicator(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)

        detailViewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)


        if (username != null) {
            detailViewModel.setUserDetail(username)
        }
        detailViewModel.getUserDetail().observe(this) {
            if (it != null) {
                detailUserBinding.apply {
                    tvLoginDetailUI.text = it.login
                    tvNameDetailUI.text = it.name
                    tvFollowersDataDetailUI.text = it.followers.toString()
                    tvFollowingDataDetailUI.text = it.following.toString()

                    if (it.location != null) {
                        tvLocationdataDetailUI.text = it.location
                    }

                    if (it.company != null) {
                        tvWorkdataDetailUI.text = it.company
                    }
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .placeholder(R.drawable.placeholder_user)
                        .into(ivProfileDetailUI)

                    loadingIndicator(false)
                    supportActionBar?.title = it.login
                }

            }
        }

        var isFavourited = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if(count>0) {
                    detailUserBinding.toggleFavDetailUI.isChecked = true
                    isFavourited = true
                } else {
                    detailUserBinding.toggleFavDetailUI.isChecked = false
                    isFavourited = false
                }
            }
        }


        detailUserBinding.toggleFavDetailUI.setOnClickListener {
            isFavourited = !isFavourited
            if(isFavourited) {

                if (username != null && avatarUrl != null) detailViewModel.addToFav(username,id,avatarUrl)

            } else {
                detailViewModel.removeFromFav(id)
            }

            detailUserBinding.toggleFavDetailUI.isChecked = isFavourited
        }


        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)
        val viewPager : ViewPager2 = detailUserBinding.viewPagerDetailUI
        viewPager.adapter = sectionPagerAdapter

        val tabs: TabLayout = detailUserBinding.tabsDetailUI
        TabLayoutMediator(tabs,viewPager) {tab,position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()

    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) detailUserBinding.pbUserinfoDetailUI?.visibility = View.VISIBLE else detailUserBinding.pbUserinfoDetailUI?.visibility = View.INVISIBLE
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"

        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
        )
    }

}