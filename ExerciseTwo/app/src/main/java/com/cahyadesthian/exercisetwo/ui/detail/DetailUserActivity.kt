package com.cahyadesthian.exercisetwo.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.cahyadesthian.exercisetwo.R
import com.cahyadesthian.exercisetwo.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var detailUserBinding: ActivityDetailUserBinding

    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailUserBinding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(detailUserBinding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)


        //untuk memberikan data useranme ke fragment
        //agar farggent dapat mengeathui data follownya sapa
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        if (username != null) {
            viewModel.setUserDetail(username)
        }

        viewModel.getUserDetail().observe(this,{
            if(it != null) {
                detailUserBinding.apply {
                    tvNameDetailUI.text = it.name
                    tvUsernameDetailUI.text = it.login
                    tvFollowerdataDetailUI.text = it.followers.toString()
                    tvFollowingdataDetailUI.text = it.following.toString()

                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivProfileDetailUI)

                }
            }
        })


        //setting viewpager
        val sectionPagerAdapter = SectionPagerAdapter(this,supportFragmentManager, bundle)
        detailUserBinding.apply {
            viewpagerDetailUI.adapter = sectionPagerAdapter
            tablayoutDetailUI.setupWithViewPager(viewpagerDetailUI)
        }
    }


    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }

}