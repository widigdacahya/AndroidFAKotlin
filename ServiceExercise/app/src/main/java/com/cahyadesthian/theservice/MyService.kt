package com.cahyadesthian.theservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*
import java.lang.UnsupportedOperationException

class MyService : Service() {

    //Step 6
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    //step 6_1


    /**
     *
     * STEP 3 MELENGKAPI SERVICE
     *
     * */
    override fun onBind(intent: Intent): IBinder {

        throw UnsupportedOperationException("Not Implemented Yet")

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.d(TAG, "Service dijalankan...")


        //step 6_2
        serviceScope.launch {
            delay(3000)
            stopSelf()
            Log.d(TAG, "Service dihentikan")
        }
        //step 6_2

        return START_STICKY
    }


    //step 6_3

    override fun onDestroy() {
        super.onDestroy()
        serviceJob.cancel()
        Log.d(TAG, "onDestroy")
    }

    //step 6_3

    companion object {
        internal val TAG = MyService::class.java.simpleName
    }


}

/**
 * NOTE
 *
 * START_STICKY menandakan bahwa bila service tersebut dimatikan oleh sistem
 * Android karena kekurangan memori, ia akan diciptakan kembali jika
 * sudah ada memori yang bisa digunakan.
 * Metode onStartCommand() juga akan kembali dijalankan
 *
 *
 *
 * stopSelf() berfungsi untuk  memberhentikan atau mematikan MyService dari sistem Android.
 * */