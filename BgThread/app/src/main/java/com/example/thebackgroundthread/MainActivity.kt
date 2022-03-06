package com.example.thebackgroundthread

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.thebackgroundthread.databinding.ActivityMainBinding
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)


        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        //[model block UI Thread / lagging]
        /*
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
        */

        //[model pake executer]
        mainBinding.btnStartUI.setOnClickListener {
            executor.execute {
                try {
                    //simulate process on background thread'
                    for(i in 0..10) {
                        Thread.sleep(500)
                        val precentage = i*10

                        //handler.post to make somethin on UI Thread
                        handler.post {
                            if(precentage==100) {
                                mainBinding.tvStatusUI.text = getString(R.string.task_completed)
                            } else {
                                mainBinding.tvStatusUI.text = String.format(getString(R.string.compressing),precentage)
                            }
                        }

                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }

    }
}