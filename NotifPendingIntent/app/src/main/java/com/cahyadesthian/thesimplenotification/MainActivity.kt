package com.cahyadesthian.thesimplenotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat

/**
 * STEP 1 make layout
 *
 * */

class MainActivity : AppCompatActivity() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_01"
        private const val CHANNEL_NAME = "dicoding channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //aksi untuk onCLick pada button
    fun sendNotification(view: View) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://portofolio.cahyadesthian.com/"))
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_MUTABLE else 0
        )


        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_baseline_circle_notifications_24))
            .setContentTitle(resources.getString(R.string.content_title))
            .setContentText(resources.getString(R.string.content_text))
            .setSubText(resources.getString(R.string.subtext))
            .setAutoCancel(true)

        /**
         *
         * Small Icon : Ikon ini yang akan muncul pada status bar (wajib ada).
         * Large Icon : Ikon ini yang akan muncul di sebelah kiri dari text notifikasi.
         * Content Intent : Intent ini sebagai action jika notifikasi ditekan.
         * Content Title : Judul dari notifikasi (wajib ada).
         * Content Text : Text yang akan muncul di bawah judul notifikasi (wajib ada).
         * Subtext : Text ini yang akan muncul di bawah content text.
         * Auto Cancel : Digunakan untuk menghapus notifikasi setelah ditekan.
         *
         *
         * */


        //Version oreo keatas perlu channel

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //create or update
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,NotificationManager.IMPORTANCE_DEFAULT)
            //channel.description = CHANNEL_NAME

            mBuilder.setChannelId(CHANNEL_ID)

            mNotificationManager.createNotificationChannel(channel)
        }


        val notification = mBuilder.build()

        mNotificationManager.notify(NOTIFICATION_ID,notification)
    }



}