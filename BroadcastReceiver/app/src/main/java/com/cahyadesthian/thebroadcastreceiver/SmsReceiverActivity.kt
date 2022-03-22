package com.cahyadesthian.thebroadcastreceiver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cahyadesthian.thebroadcastreceiver.databinding.ActivitySmsReceiverBinding

/**
 * STEP 5
 * menambahkan kode di SmsReceiverActivity untuk memberi aksi pada
 * Textview dan Button yang sudah kita masukkan di activity_sms_receiver.xml.
 * */


class SmsReceiverActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SMS_NO = "extra_sms_no"
        const val EXTRA_SMS_MESSAGE = "extra_sms_message"
    }

    private var smsRecBinding : ActivitySmsReceiverBinding?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        smsRecBinding = ActivitySmsReceiverBinding.inflate(layoutInflater)
        setContentView(smsRecBinding?.root)

        title = getString(R.string.incoming_message)

        smsRecBinding?.btnClose?.setOnClickListener {
            finish()
        }

        val senderNo = intent.getStringExtra(EXTRA_SMS_NO)
        val senderMessage = intent.getStringExtra(EXTRA_SMS_MESSAGE)

        smsRecBinding?.tvFromSmsreceiverUI?.text  = getString(R.string.from, senderNo)
        smsRecBinding?.tvMessageSmsreceiverUI?.text = senderMessage


    }


    override fun onDestroy() {
        super.onDestroy()
        smsRecBinding = null
    }

}