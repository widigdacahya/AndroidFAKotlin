package com.cahyadesthian.theservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * STEP 1 membuat xml
         * Step 2 mengenalkan button button itu ke mainActiivty ini
         * STEP 3 membuat kelas servie dengan nama MyService
         * */
        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        btnStartService.setOnClickListener {

            //STEP 4
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
            //step 4

        }

        val btnStartJobIntentService = findViewById<Button>(R.id.btn_start_job_intent_service)
        btnStartJobIntentService.setOnClickListener {

            //step 8
            val mStartIntentService = Intent(this, MyJobIntentService::class.java)
            mStartIntentService.putExtra(MyJobIntentService.EXTRA_DURATION,5000L)
            MyJobIntentService.enqueueWork(this,mStartIntentService)
            //step 8

        }

        val btnStartBoundService = findViewById<Button>(R.id.btn_start_bound_service)
        btnStartBoundService.setOnClickListener {

        }

        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service)
        btnStopBoundService.setOnClickListener {

        }


    }
}