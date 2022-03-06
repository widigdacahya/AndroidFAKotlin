package com.example.thebackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thebackgroundthread.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        mainBinding.btnStartUI.setOnClickListener {
            try {
                //compressing simulation
                for(i in 0..10) {
                    Thread.sleep(500)
                    val precentage = i*10
                    if (precentage==100) {
                        mainBinding.tvStatusUI.text = getString(R.string.task_completed)
                    } else {
                        mainBinding.tvStatusUI.text = String.format(getString(R.string.compressing),precentage)
                    }
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }

    }
}