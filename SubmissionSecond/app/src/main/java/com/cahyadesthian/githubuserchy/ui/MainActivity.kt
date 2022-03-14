package com.cahyadesthian.githubuserchy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.cahyadesthian.githubuserchy.R
import com.cahyadesthian.githubuserchy.databinding.ActivityMainBinding
import com.cahyadesthian.githubuserchy.model.UserItemsResponse
import com.cahyadesthian.githubuserchy.ui.adapter.UserGridRecyclerViewAdapter
import com.cahyadesthian.githubuserchy.viewmodel.UserItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var inputUser : String
    private lateinit var userItemViewModel : UserItemViewModel

    //private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        installSplashScreen().apply {
            setKeepVisibleCondition{
                splashViewModel.isLoading.value
            }

        }*/
        setTheme(R.style.Theme_GithubUserChy)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val userItemAdapter = UserGridRecyclerViewAdapter()
        userItemAdapter.notifyDataSetChanged()
        userItemAdapter.setOnItemClickCallback(object : UserGridRecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(user: UserItemsResponse) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, user.login)
                    startActivity(it)
                }
            }

        })

        userItemViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserItemViewModel::class.java)
        userItemViewModel.getResultSearchedUser().observe(this, {

            if(it!=null) {
                println("Woy apa ni " + it)
                userItemAdapter.setList(it)
                loadingIndicator(false)
                showNone(false)
                hidePicture()
            }

            if(it.isEmpty()) {
                showNone(true)
            }

        })


        mainBinding.apply {
            rvUserlistMainUI.layoutManager = GridLayoutManager(this@MainActivity,2)
            rvUserlistMainUI.setHasFixedSize(true)
            rvUserlistMainUI.adapter = userItemAdapter


            etMainUI.setOnKeyListener { _, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchInputUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }


    }


    private fun searchInputUser() {
        inputUser = mainBinding.etMainUI.text.toString()
        if(inputUser.isEmpty()) return
        loadingIndicator(true)
        hidePicture()
        userItemViewModel.setSearchedUser(inputUser)
    }

    private fun loadingIndicator(isLoading: Boolean) {
        if(isLoading) mainBinding.pbMainUI.visibility = View.VISIBLE else mainBinding.pbMainUI.visibility = View.INVISIBLE
    }

    private fun hidePicture() {
        mainBinding.ivIllustrationMainUI.visibility = View.GONE
    }

    private fun showNone(isData: Boolean) {
        if(isData) {
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
}