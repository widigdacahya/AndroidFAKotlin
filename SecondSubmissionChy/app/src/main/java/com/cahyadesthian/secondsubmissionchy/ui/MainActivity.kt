package com.cahyadesthian.secondsubmissionchy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.secondsubmissionchy.databinding.ActivityMainBinding
import com.cahyadesthian.secondsubmissionchy.model.UsersResponse
import com.cahyadesthian.secondsubmissionchy.ui.adapter.UserMainAdapter
import com.cahyadesthian.secondsubmissionchy.viewmodel.DetailUserViewModel
import com.cahyadesthian.secondsubmissionchy.viewmodel.UserMainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var mainViewModel : UserMainViewModel

    private lateinit var detailViewModel : DetailUserViewModel
    private lateinit var mainUserAdapter : UserMainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainUserAdapter = UserMainAdapter()
        mainUserAdapter.notifyDataSetChanged()


        detailViewModel = DetailUserViewModel()


        mainUserAdapter.setOnItemClickCallback(object : UserMainAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UsersResponse) {
                Intent(this@MainActivity,DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    startActivity(it)
                }
            }

        })


        mainViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(UserMainViewModel::class.java)

        mainBinding.apply {
            rvUserMainUI.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserMainUI.setHasFixedSize(true)
            rvUserMainUI.adapter = mainUserAdapter

            //btnSearch
            ivPersonsearchMainUI.setOnClickListener {
                searchUserInput()
            }

            // edittex
            etUsersearchMainUI.setOnKeyListener { v, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUserInput()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }



        }

        //val listOfLogin = arrayListOf<UsersResponse>()
        val listOfLogin = arrayListOf<String>()


        mainViewModel.getSearchUsers().observe(this, {

            //listOfLogin.addAll(it)
            for(i in it.indices) {
                listOfLogin.add(it[i].login)
            }
            println(listOfLogin)
//            modidifiedList.addAll(it)
//            println(modidifiedList[0].login)    //dapet username
////            for(element in modidifiedList) {
////                myAwesomeVM.setUserDetail()
////            }
//            modidifiedList.forEach {
//                myAwesomeVM.setUserDetail(it.login)
//            }
//
//            myAwesomeVM.getUserDetail().observe(this, {
//                if(it!=null) {
//                    mainUserAdapter.setList(it)
//                    showLoading(false)
//                }
//            })

            val myUser = arrayListOf<LiveData<UsersResponse>>()
            for(item in listOfLogin) {
                detailViewModel.setUserDetail(item)
                myUser.add(detailViewModel.getUserDetail())
            }
            println(myUser)


            if(it!=null) {
                mainUserAdapter.setList(it)
                showLoading(false)
            }

//            var soemthing = ArrayList<LiveData<UsersResponse>>()
//            for (i in myUser) {
//                soemthing.add(myUser[i])
//            }
//
//            val tempArr = ArrayList<UsersResponse>()
//            tempArr.addAll(myUser)
//
//            if(myUser!=null) {
//                mainUserAdapter.setList(myUser)
//                showLoading(false)
//            }
        })

        /*
        val myUser = arrayListOf< LiveData<UsersResponse>>()
        for(item in listOfLogin) {
            detailViewModel.setUserDetail(item)
            myUser.add(detailViewModel.getUserDetail())
        }
        println(myUser)
        */
    }

    private fun showLoading(condition: Boolean) {
        if(condition) mainBinding.pbMainUI.visibility = View.VISIBLE else mainBinding.pbMainUI.visibility = View.GONE
    }

    private fun searchUserInput() {
        val queryName = mainBinding.etUsersearchMainUI.text.toString()
        if(queryName.isEmpty()) return
        showLoading(true)
        mainViewModel.setSearchUsers(queryName)

    }

}