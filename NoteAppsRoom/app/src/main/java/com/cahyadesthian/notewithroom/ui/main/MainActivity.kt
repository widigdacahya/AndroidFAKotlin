package com.cahyadesthian.notewithroom.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cahyadesthian.notewithroom.R
import com.cahyadesthian.notewithroom.databinding.ActivityMainBinding
import com.cahyadesthian.notewithroom.helper.ViewModelFactory
import com.cahyadesthian.notewithroom.ui.insert.NoteAddUpdateActivity


/**
 * STEP 20
 *
 * */

class MainActivity : AppCompatActivity() {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding

    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = NoteAdapter()

        binding?.rvNotesMainUI?.layoutManager = LinearLayoutManager(this)
        binding?.rvNotesMainUI?.setHasFixedSize(true)
        binding?.rvNotesMainUI?.adapter = adapter


        /**
         * STEP 21
         * Setelah menginisialisasi RecyclerView dan NoteAdapter,
         * tambahkan kode di bawah ini untuk menghubungkan
         * MainViewModel dengan MainActivity
         *
         *
         * after that make on fab(floating action button)
         * */
        //21_1
        val mainViewModel = obtainViewModel(this@MainActivity)
        //21_1

        //21_3
        mainViewModel.getAllNotes().observe(this, {noteList ->
            if(noteList != null) adapter.setListNotes(noteList)
        })
        //21_3


        /**
         * STEP 22
         *
         * */
        binding?.fabMainUI?.setOnClickListener { view ->
            if(view.id == R.id.fab_mainUI) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivity(intent)
            }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        _activityMainBinding = null
    }


    //21_2
    private fun obtainViewModel(activity: AppCompatActivity) : MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity,factory).get(MainViewModel::class.java)
    }
    //21_2



}