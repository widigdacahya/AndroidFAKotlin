package com.cahyadesthian.thebroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.cahyadesthian.thebroadcastreceiver.databinding.ActivityMainBinding
import android.Manifest

/**
 * STEP 1 atur atur manja string values anda xml file
 *
 * STEP 2 prepare MainActivity
 * like binding and set onclick listenr,onDestroy
 *
 * STEP 3 buat activity SmsReceiverActivity, and set the layout
 *
 * STEP 4 menambahkan style baru
 * <style name="Theme.Broadcast.SmsReceiver" parent="Theme.MaterialComponents.DayNight.Dialog" />
 * Idenya adalah menampilkan sebuah Activity seolah-olah menjadi sebuah dialog.
 * then set the style to Manifest
 *
 * STEP 5
 * menambahkan kode di SmsReceiverActivity untuk memberi aksi pada
 * Textview dan Button yang sudah kita masukkan di activity_sms_receiver.xml.
 *
 * STEP 6
 * membuat kelas Receiver
 * new -> other -> broadcast receiver
 *
 * STEP 7
 * terapkan fun getIncomingMessage ke SmsReceiver
 *
 *
 * STEP 8
 * manambahn permssion da intent filter di manifest
 * <uses-permission android:name="android.permission.RECEIVE_SMS"/>
 * <uses-permission android:name="android.permission.READ_SMS" />
 *
 * <intent-filter>
 *     <action android:name="android.provider.Telephony.SMS_RECEIVED" />
 * </intent-filter>
 *
 *
 *
 * STEP 9
 * Diataas versi marhsmallow perlu perssimio manager
 * jadi mari kita buat object PermissionManager
 *
 *
 *
 * STEP 10
 * Pemanggilan metode check di mainActivity
 *
 *
 *
 * STEP 11 mau buat yang kayak downlaod
 * jadi buat btn di xml
 *
 * STEP 12 membuat kelas baru
 * DownloadService
 *
 *
 * STEP 13 daftar service yang DownloadService ke Manifest
 *
 *
 * STEP 14
 * Pada MainActivity lengkapi kode yang ada dengan menambahkan
 * sebuah Intent untuk menjalankan DownloadService
 *
 * */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //STEP 10
    //10_1
    companion object {
        private const val SMS_REQUEST_CODE  = 101

        //14_1
        const val ACTION_DOWNLOAD_STATUS = "download_status"
    }

    //14_2
    private lateinit var downloadReceiver: BroadcastReceiver

    private var mainBinding : ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        mainBinding?.btnPermission?.setOnClickListener(this)

        //11
        mainBinding?.btnDownload?.setOnClickListener(this)

        //14_3
        downloadReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                Log.d(DownloadService.TAG, "Download Selesai")
                Toast.makeText(context, "Download Selesai", Toast.LENGTH_SHORT).show()
            }
        }
        val downloadIntentFilter = IntentFilter(ACTION_DOWNLOAD_STATUS)
        registerReceiver(downloadReceiver, downloadIntentFilter)
    }
    /**
     *
     * Pada metode onCreate() kita membuat sebuah obyek dari DownloadReceiver.
     * Kemudian MainActivity diregistrasikan untuk mendengar event/action dengan
     * tag: ACTION_DOWNLOAD_STATUS. Ketika event/action tersebut ditangkap oleh MainActivity,
     * maka obyek downloadReceiver akan dijalankan
     *
     *
     * Ketika baris ini dijalankan pada DownloadService.
     * val notifyFinishIntent = Intent(MainActivity.ACTION_DOWNLOAD_STATUS)
     * sendBroadcast(notifyFinishIntent)
     * */



    override fun onClick(v: View) {

        when(v.id) {

            //10_2
            R.id.btn_permission -> PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE)

            //11
            R.id.btn_download -> {
                //14_4
                val downloadServiceIntent = Intent(this, DownloadService::class.java)
                startService(downloadServiceIntent)

            }


        }

    }

    //10_3
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == SMS_REQUEST_CODE) {
            when(PackageManager.PERMISSION_GRANTED) {
                grantResults[0] -> Toast.makeText(this, "Sms Receiver persmission diterima", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Sms Receiver permission ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        //14_5
        unregisterReceiver(downloadReceiver)

        mainBinding = null
    }



}