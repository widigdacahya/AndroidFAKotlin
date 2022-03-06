package com.example.tablonefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {
    var appName: String = ""

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val fragment = HomeFragment()
        fragment.arguments = Bundle().apply {
            putInt(HomeFragment.ARG_SECTION_NUMBER, position+1)
            putString(HomeFragment.ARG_NAME, appName)
        }
        return fragment
    }



}