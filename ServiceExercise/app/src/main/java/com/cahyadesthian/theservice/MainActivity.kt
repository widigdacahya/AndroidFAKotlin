package com.cahyadesthian.theservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button

class MainActivity : AppCompatActivity() {


    /**
     * STEP 12
     *  membuat ServiceConnection untuk menghubungkan MainActivity dengan MyBoundService
     * */
    private var mServiceBound = false
    private lateinit var mBoundService: MyBoundService

    private val mServiceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as MyBoundService.MyBinder
            mBoundService = myBinder.getService
            mServiceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mServiceBound = false
        }


    }

    /*
    *
    * Kode di atas merupakan sebuah listener untuk menerima callback dari ServiceConnetion.
    * Kalau dilihat ada dua callback,
    * yakni ketika mulai terhubung dengan kelas service d
    * an juga ketika kelas service sudah terputus.
    *
    * */




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

            //12_2
            val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
            bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)

            /*
            * Note
            * > penggunaan bindService yang digunakan untuk memulai mengikat kelas
            *   MyBoundService ke kelas MainActivity
            *
            *
            * > mBoundServiceIntent adalah sebuah intent eksplisit yang digunakan untuk menjalankan komponen
            *   dari dalam sebuah aplikasi
            *
            * > mServiceConnection adalah sebuah ServiceConnection berfungsi
            *   sebagai callback dari kelas MyBoundService
            *
            * >  BIND_AUTO_CREATE yang membuat sebuah service jika service tersebut belum aktif.
            *
            * ada beberapa bind lain
            * BIND_ABOVE_CLIENT : yang digunakan ketika sebuah service lebih penting daripada aplikasi itu sendiri.
            * BIND_ADJUST_WITH_ACTIVITY : saat mengikat sebuah service dari activity, maka ia akan mengizinkan untuk menargetkan service mana yang lebih penting berdasarkan activity yang terlihat oleh pengguna.
            * BIND_ALLOW_OOM_MANAGEMENT : memungkinkan untuk mengikat service hosting untuk mengatur memori secara normal.
            * BIND_AUTO_CREATE : secara otomatis membuat service selama binding-nya aktif.
            * BIND_DEBUG_UNBIND : berfungsi sebagai bantuan ketika debug mengalami masalah pada pemanggilan unBind.
            * BIND_EXTERNAL_SERVICE : merupakan service yang terikat dengan service eksternal yang terisolasi.
            * BIND_IMPORTANT : service ini sangat penting bagi klien, jadi harus dibawa ke tingkat proses foreground.
            * BIND_NOT_FOREGROUND : pada service ini tak disarankan untuk mengubah ke tingkat proses foreground.
            * BIND_WAIVE_PRIORITY : service ini tidak akan mempengaruhi penjadwalan atau prioritas manajemen memori dari target proses layanan hosting.

            *
            * */


        }

        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service)
        btnStopBoundService.setOnClickListener {

            //12_3
            unbindService(mServiceConnection)
        }


    }


    //STEP 13
    /*
    * beberapa kode di metode onDestroy().
    * Tujuannya agar ketika aplikasi sudah keluar,
    * service akan berhenti secara otomatis.
    * */
    override fun onDestroy() {
        super.onDestroy()
        if(mServiceBound) {
            unbindService(mServiceConnection)
        }
    }


}