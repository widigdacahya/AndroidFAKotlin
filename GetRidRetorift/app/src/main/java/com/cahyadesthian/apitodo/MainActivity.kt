package com.cahyadesthian.apitodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.apitodo.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException


const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var toDoAdapter : ToDoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setupRecyclerView()

        //make api request(right way from view model, but in the charge of it only focuse on retroiffit, it would just called here)
        //use lifecycle because want ot bg thread use coroutine
        lifecycleScope.launchWhenCreated {
            mainBinding.pbTodo.visibility = View.VISIBLE
            val response = try {
                RetrofitInstance.api.getListTodo()
            }catch (e: IOException) {
                mainBinding.pbTodo.visibility = View.GONE
                Log.e(TAG, "IOException, you might not connected with the internet" )
                return@launchWhenCreated
            }catch (e: HttpException) {
                mainBinding.pbTodo.visibility = View.GONE
                Log.e(TAG, "HttpException, response is unexpected" )
                return@launchWhenCreated
            }
            if(response.isSuccessful && response.body() != null) {
                toDoAdapter.todos = response.body()!!
            } else {
                Log.e(TAG, "Unsuccesful response" )
            }
            mainBinding.pbTodo.visibility = View.GONE

        }

        //setupRecyclerView()

    }


    private fun setupRecyclerView() = mainBinding.rvTodos.apply {
        toDoAdapter = ToDoListAdapter()
        adapter = toDoAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}