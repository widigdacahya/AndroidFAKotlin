package com.cahyadesthian.thecountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cahyadesthian.thecountdown.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.startTimer()
        viewModel.seconds.observe(this, Observer {
            mainBinding.tvCountdown.text = it.toString()
        })
        viewModel.finished.observe(this, Observer {
            if(it) Toast.makeText(this, "Time's Up ⏰", Toast.LENGTH_SHORT).show()
        })
    }
}