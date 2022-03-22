package com.cahyadesthian.thebroadcastreceiver

import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 *
 * STEP 12
 * */

//12_1 inherit JobIntentService()
class DownloadService : JobIntentService(){


    //12_2
    companion object {
        val TAG :String = DownloadService::class.java.simpleName
    }
    //12_2

    //12_3
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(intent!=null) {
            enqueueWork(this,this::class.java,101,intent)
        }

        return super.onStartCommand(intent, flags, startId)
    }
    //12_3


    override fun onHandleWork(intent: Intent) {

        //12_4
        Log.d(TAG, "Download Service dijalankan")
        try {
            Thread.sleep(5000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //ada merah di ACTION_DOWNALD_SATTUS, later would be written in MainActivity
        val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
        sendBroadcast(notifyFinishIntent)

    }
    //12_4


    /*
    * KEtika
    * val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
    * sendBroadcast(notifyFinishIntent)
    * kode diatas jalan , onReceive() yang donwload selesai berjalan (14_3 di main)
    * */


}