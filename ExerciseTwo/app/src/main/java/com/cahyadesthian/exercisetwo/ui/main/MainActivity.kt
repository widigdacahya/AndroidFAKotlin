package com.cahyadesthian.exercisetwo.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.exercisetwo.R
import com.cahyadesthian.exercisetwo.data.model.User
import com.cahyadesthian.exercisetwo.databinding.ActivityMainBinding
import com.cahyadesthian.exercisetwo.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        mainBinding.apply {
            rvUserMainUI.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserMainUI.setHasFixedSize(true)
            rvUserMainUI.adapter = adapter

            ivBtnsearchMainui.setOnClickListener{
                searchUser()
            }


            etQuerysearchMainUI.setOnKeyListener { v, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }



        }

        //set recyler view dari data
        viewModel.getSearchUsers().observe(this, {
            if(it!=null) {
                adapter.setList(it)
                showLoading(false)
            }
        })
    }

    //show loading
    private fun showLoading(state:Boolean) {
        if(state) mainBinding.pbMainUI.visibility = View.VISIBLE else mainBinding.pbMainUI.visibility = View.GONE
    }


    //search user
    private fun searchUser() {
        mainBinding.apply {
            val query = etQuerysearchMainUI.text.toString()
            if(query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }
}