package com.cahyadesthian.learnexercisethree.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.databinding.ActivityMainBinding
import com.cahyadesthian.learnexercisethree.ui.adapter.UserGridRecyclerViewAdapter
import com.cahyadesthian.learnexercisethree.ui.setting.SettingPreferences
import com.cahyadesthian.learnexercisethree.ui.setting.SettingViewModelFactory
import com.cahyadesthian.learnexercisethree.viewmodel.MainViewModel
import com.cahyadesthian.learnexercisethree.viewmodel.SettingViewModel


/***
 * [Main Activity]
 * main screen, where we will looking for github users around the world
 * we also set the splash screen by this activity,
 * will having menu for night mode and show favourited screen
 * */

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var gridRecyclerViewAdapter : UserGridRecyclerViewAdapter
    private lateinit var inputUser : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnExerciseThree)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingViewModel.getThemeSettings().observe(this, { isDarkModeActive: Boolean ->
            if(isDarkModeActive) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        })

        gridRecyclerViewAdapter = UserGridRecyclerViewAdapter()
        gridRecyclerViewAdapter.setOnItemClickCallback(object: UserGridRecyclerViewAdapter.OnItemClickCallback {

            override fun onItemClicked(data: UserItemResponse) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainBinding.apply {
            rvUsersMainUI.layoutManager = GridLayoutManager(this@MainActivity,2)
            rvUsersMainUI.setHasFixedSize(true)
            rvUsersMainUI.adapter = gridRecyclerViewAdapter

            etMainUI.setOnKeyListener { _, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchInputUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }



        }

        viewModel.getSearedchUsers().observe(this, {

            if(it!= null) {
                gridRecyclerViewAdapter.setData(it)
                loadingIndicator(false)
                showNone(false)
                hidePicture()
            }

            if(it.isEmpty()) {
                showNone(true)
            }

        })


    }



    private fun searchInputUser() {
        inputUser = mainBinding.etMainUI.text.toString()
        if(inputUser.isEmpty()) return

        loadingIndicator(true)
        hidePicture()
        viewModel.setSearchedUsers(inputUser)
    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) mainBinding.pbMainUI.visibility = View.VISIBLE else mainBinding.pbMainUI.visibility = View.INVISIBLE
    }

    private fun hidePicture() {
        mainBinding.ivIllustrationMainUI.visibility = View.GONE
    }


    private fun showNone(isData: Boolean) {
        if (isData) {
            mainBinding.apply {
                ivNotfoundMainUI.visibility = View.VISIBLE
                tvNoneMainUI.visibility = View.VISIBLE
            }
        } else {
            mainBinding.apply {
                ivNotfoundMainUI.visibility = View.INVISIBLE
                tvNoneMainUI.visibility = View.INVISIBLE
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.fav_menu -> {
                Intent(this, FavouriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting_mode -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}