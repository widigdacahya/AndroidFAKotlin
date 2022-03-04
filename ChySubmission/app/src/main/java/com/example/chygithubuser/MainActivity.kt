package com.example.chygithubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chygithubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val githubUserList = ArrayList<GithubUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        mainBinding.rvGithubUserUI.setHasFixedSize(true)
        githubUserList.addAll(listOfGithubUser)
        showGithubUserRecylerList()

    }


    private val listOfGithubUser:ArrayList<GithubUser>
        get() {
            val userUsername = resources.getStringArray(R.array.username)
            val userName = resources.getStringArray(R.array.name)
            val userLocation = resources.getStringArray(R.array.location)
            val userRepo = resources.getStringArray(R.array.repository)
            val userCompany = resources.getStringArray(R.array.company)
            val userFollower = resources.getStringArray(R.array.followers)
            val userFollowing = resources.getStringArray(R.array.following)
            val userAvatar = resources.obtainTypedArray(R.array.avatar)
            //userAvatar.recycle() //<- if i put it here, the app suddenly close

            val listToReturn = ArrayList<GithubUser>()
            for(i in userName.indices) {
                val aUserData = GithubUser(userUsername[i],userName[i],userLocation[i],userRepo[i],userCompany[i],userFollower[i],userFollowing[i],userAvatar.getResourceId(i,-1))
                listToReturn.add(aUserData)
            }
            userAvatar.recycle()
            return listToReturn
        }


    private fun showGithubUserRecylerList() {
        mainBinding.rvGithubUserUI.layoutManager = LinearLayoutManager(this)
        val listGithubUserAdapter = ListGithubUserAdapter(githubUserList)
        mainBinding.rvGithubUserUI.adapter = listGithubUserAdapter


        listGithubUserAdapter.setOnItemClickCallback(object : ListGithubUserAdapter.OnItemClickCallback {
            override fun onItemClicked(userData: GithubUser) {
                anItemClicked(userData)

                val intentToDetail =Intent(this@MainActivity,UserDetailActivity::class.java)
                intentToDetail.putExtra("EXTRA_DATA", userData)
                startActivity(intentToDetail)

            }

        })

    }

    private fun anItemClicked(userGithub: GithubUser) {
        Toast.makeText(this, "${userGithub.githubUserName} detail", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.about_menu) {
            val intentToAbout = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(intentToAbout)
        }
        return super.onOptionsItemSelected(item)
    }
}