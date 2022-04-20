package com.cahyadesthian.learnexercisethree.ui.activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.learnexercisethree.R
import com.cahyadesthian.learnexercisethree.data.database.FavUser
import com.cahyadesthian.learnexercisethree.data.model.UserItemResponse
import com.cahyadesthian.learnexercisethree.databinding.ActivityFavouriteBinding
import com.cahyadesthian.learnexercisethree.ui.adapter.UserRowRecyclerViewAdapter
import com.cahyadesthian.learnexercisethree.viewmodel.FavViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



/**
 * [Favourite Activity]
 * a screen that show all favourited githubUser
 * that had choosen by user,
 * it had delete button to delete all list if any list
 * for single delete, click unlove from single user detail
 * */

class FavouriteActivity : AppCompatActivity() {

    private lateinit var favBinding: ActivityFavouriteBinding
    private lateinit var rowRecyclerViewAdapter : UserRowRecyclerViewAdapter
    private lateinit var favViewModel: FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favBinding = ActivityFavouriteBinding.inflate(layoutInflater)
        setContentView(favBinding.root)

        rowRecyclerViewAdapter = UserRowRecyclerViewAdapter()

        favViewModel = ViewModelProvider(this).get(FavViewModel::class.java)

        rowRecyclerViewAdapter.setOnItemClickCallback(object: UserRowRecyclerViewAdapter.OnItemClickCallback {

            override fun onItemClicked(data: UserItemResponse) {
                Intent(this@FavouriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID,data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        favBinding.apply {
            rvFavUser.layoutManager = LinearLayoutManager(this@FavouriteActivity)
            rvFavUser.setHasFixedSize(true)
            rvFavUser.adapter = rowRecyclerViewAdapter
        }

        favViewModel.getFavUser()?.observe(this, {

            if(it!=null){
                val list = transformChange(it)
                rowRecyclerViewAdapter.setData(list)

                Log.v("soemethin in if", "\n\nWhat if nothing\n\n")

            } else {
                Log.v("soemethin els if", "\n\nso here is null\n\n")

            }
        })


    }

    private fun transformChange(users: List<FavUser>): ArrayList<UserItemResponse> {
        val listUsers = ArrayList<UserItemResponse>()
        for(user in users) {
            val userMapped = UserItemResponse(user.ID,user.LOGIN,user.AVATAR_URL)
            listUsers.add(userMapped)
        }
        return listUsers
    }


    /**
     * Menu Creation
     * when there is such a fav user, display delete
     * when there is not, hide the menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        CoroutineScope(Dispatchers.IO).launch {
            val some = favViewModel.isThereAny()
            withContext(Dispatchers.Main) {
                if(some == 0) {
                    //menuInflater.inflate(R.menu.delete_menu,menu)
                } else {
                    menuInflater.inflate(R.menu.delete_menu,menu)
                }
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * [Handle when we click the menu]
     * in this case, delete all favurited user
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            if(item.itemId == R.id.menu_delete) {
                    val alertBuilder = AlertDialog.Builder(this)
                    alertBuilder.setPositiveButton("Yes, I am Sure") { _, _ ->
                        favViewModel.deleteAllUser()
                        Toast.makeText(this,"All Favorited User Deleted", Toast.LENGTH_SHORT).show()
                        val moveToMain = Intent(this,MainActivity::class.java)
                        moveToMain.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
                        moveToMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(moveToMain)
                    }
                    alertBuilder.setNegativeButton("No") { _, _ -> }
                    alertBuilder.setTitle("Are you sure?")
                    alertBuilder.setMessage("Really wanna delete all of these?")
                    alertBuilder.create().show()
            }

        return super.onOptionsItemSelected(item)
    }




}