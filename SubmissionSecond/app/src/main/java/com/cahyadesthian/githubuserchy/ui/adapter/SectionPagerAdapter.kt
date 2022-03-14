package com.cahyadesthian.githubuserchy.ui.adapter

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cahyadesthian.githubuserchy.ui.fragment.FollowerFragment
import com.cahyadesthian.githubuserchy.ui.fragment.FollowingFragment

class SectionPagerAdapter(activity: AppCompatActivity ,data: Bundle) : FragmentStateAdapter(activity) {

    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position) {
            0 -> fragment = FollowingFragment()
            1 -> fragment = FollowerFragment()
        }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }
}