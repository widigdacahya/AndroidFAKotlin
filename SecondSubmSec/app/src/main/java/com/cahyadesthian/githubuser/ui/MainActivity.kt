package com.cahyadesthian.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.githubuser.databinding.ActivityMainBinding
import com.cahyadesthian.githubuser.model.SearchRespone
import com.cahyadesthian.githubuser.model.UserSearchResponse
import com.cahyadesthian.githubuser.model.UsersResponse
import com.cahyadesthian.githubuser.ui.adapter.UserAdapter
import com.cahyadesthian.githubuser.ui.adapter.UserAdapterComplete
import com.cahyadesthian.githubuser.viewmodel.DetailUserViewModel
import com.cahyadesthian.githubuser.viewmodel.UserItemDetailViewModel
import com.cahyadesthian.githubuser.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelAddition: DetailUserViewModel

    private lateinit var userItemDetailViewModel : UserItemDetailViewModel

    private lateinit var adapter : UserAdapter
    private lateinit var adapterComplete: UserAdapterComplete

    private var listItems : ArrayList<UserSearchResponse>? = null
    private var listIdDariItems : ArrayList<String>? = null

    private var listPenampunUserDetail : ArrayList<UsersResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapterComplete = UserAdapterComplete()
        adapterComplete.notifyDataSetChanged()



        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        viewModelAddition = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

        mainBinding.apply {
            rvUserMainUI.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUserMainUI.setHasFixedSize(true)
            rvUserMainUI.adapter = adapterComplete

            ivSearcbtnMainUI.setOnClickListener {
                searchUserInput()
            }

            editTextMainUI.setOnKeyListener { v, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUserInput()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }


        /*
        viewModel.getSearchUsers().observe(this, {
            /*
            if(it!=null) {
                adapter.setList(it)
                showLoading(false)
            }*/
            for (eachLogin in it) {
                idGet.add(eachLogin.login)
            }
            //println(idGet)

            for(element in idGet) {
                viewModelAddition.setUserDetail(element)

                //var userTemp = viewModelAddition.setUserDetailWow(element)
                //userGithubList.add(userTemp)
                //userGithubList.add(userTmp)
                viewModelAddition.getUserDetail().observe(this,{
                    if(it!=null) {
                        userGithubList.add(it)
                        //println(userGithubList)
                        //adapterComplete.setListComplete(userGithubList)
                        //println("UserGithubList ==== " + userGithubList + "\n")

                    }
                })
                //println("\n\nUserGithubList = " + userGithubList)
            }
            //println("UserGithubList = " + userGithubList)
            //println("\n\nUserGithubList = " + userGithubList)
            //adapterComplete.setListComplete(userGithubList)
            //showLoading(false)

        })
        */

        userItemDetailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserItemDetailViewModel::class.java)
        userItemDetailViewModel.getItemUsersYangDicari().observe(this, {listUserDariItems ->
            setSearchItems(listUserDariItems)
        })
        /*
        userItemDetailViewModel.listUserDariItems.observe(this, { listUserDariItems ->
            setSearchItems(listUserDariItems)
        })
        */

    }



    private fun showLoading(isLoading: Boolean) {
        if(isLoading) mainBinding.pbMainUI.visibility = View.VISIBLE else mainBinding.pbMainUI.visibility = View.GONE
    }

    private fun searchUserInput() {
        val queryName = mainBinding.editTextMainUI.text.toString()
        if(queryName.isEmpty()) return
        showLoading(true)
        //viewModel.setSearchUsers(queryName)
        //setSearchItems(queryName)
        userItemDetailViewModel.findItemsUsersYangDicari(queryName)
    }



    private fun setSearchItems(listItem: ArrayList<UserSearchResponse>) {
        for(itemUser in listItem) {
            listItems?.add(itemUser)
            println("Anyonesssss?")
            println(listItems)
        }
        println("Anyone?")
        println(listItems)

        for(userItem in listItems!!) {
            listIdDariItems?.add(userItem.login)
        }

        for(eachId in listIdDariItems!!) {
            userItemDetailViewModel.setUserDetail(eachId)
            userItemDetailViewModel.getUserDetail().observe(this, {
                listPenampunUserDetail?.add(it)
            })

        }
        listPenampunUserDetail?.let { adapterComplete.setListComplete(it) }

    }

//    fun tambahUserDetail(listUserLengkap: ArrayList<UsersResponse>) {
//        for()
//    }

}
