package com.cahyadesthian.theservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import java.util.function.LongFunction

class MyBoundService : Service() {

    /**
     * STEP 9 membuat bound service ini :)
     * */
    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }
    //9_1

    //9_2
    private var mBinder = MyBinder()
    //9_2

    //9_4
    private val startTime = System.currentTimeMillis()
    //9_4


    /***
     *
     * STEP 10
     * Setelah membuat kelas MyBinder,
     * kita buat sebuah variabel untuk menghitung mundur
     */
    private var mTimer: CountDownTimer = object: CountDownTimer(100000,1000) {
        override fun onTick(millisUntilFinished: Long) {
            val elapsedTime = System.currentTimeMillis()-startTime
            Log.d(TAG, "onTick: $elapsedTime")
        }

        override fun onFinish() {

        }

    }



    /**
     * STEP 11
     *  menerapkan CountTimerDown ke dalam kelas MyBoundService.
     *  Gunanya untuk memulai service.
     * */

    //11_1
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    //ini awal setelah class ini terbuebntuk
    override fun onBind(intent: Intent): IBinder {
        //11_2
        Log.d(TAG, "onBind: ")
        mTimer.start()
        return mBinder
    }



    //11_3
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    //11_4
    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind: ")
        mTimer.cancel()
        return super.onUnbind(intent)
    }


    //11_5
    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "onRebind: ")
    }



    //9_3
    internal inner class MyBinder: Binder() {
        val getService:MyBoundService = this@MyBoundService
    }
    //9_3




}