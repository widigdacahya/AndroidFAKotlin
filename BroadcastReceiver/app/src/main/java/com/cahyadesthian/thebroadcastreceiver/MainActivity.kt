package com.cahyadesthian.thebroadcastreceiver

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.cahyadesthian.thebroadcastreceiver.databinding.ActivityMainBinding
import java.util.jar.Manifest

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
 * */

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //STEP 10
    //10_1
    companion object {
        private const val SMS_REQUEST_CODE  = 101
    }


    private var mainBinding : ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding?.root)

        mainBinding?.btnPermission?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when(v?.id) {

            //10_2
            R.id.btn_permission -> PermissionManager.check(this, android.Manifest.permission.READ_SMS, SMS_REQUEST_CODE)
        }

    }

    //10_3
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == SMS_REQUEST_CODE) {
            when {
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> Toast.makeText(this, "Sms Receiver persmission diterima", Toast.LENGTH_SHORT).show()
                else -> Toast.makeText(this, "Sms Receiver permission ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mainBinding = null
    }



}