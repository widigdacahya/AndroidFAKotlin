package com.cahyadesthian.thebroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.lang.Exception


/**
 * STEP 6
 * menambahkan kode untuk menerima data dari pesan yang masuk.
 *
 * */

class SmsReceiver : BroadcastReceiver() {

    //6_1
    companion object {
        private val TAG = SmsReceiver::class.java.simpleName
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        /**
         * STEP 7
         * */
        val bundle = intent.extras
        try {

            /*
                Bundle dengan key "pdus" sudah merupakan standar yang digunakan oleh system
            */
            if(bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<*>
                for(aPdusObj in pdusObj) {
                    val currentMessage = getIncominMessage(aPdusObj as Any, bundle)
                    val senderNum = currentMessage.displayOriginatingAddress
                    val message = currentMessage.displayMessageBody
                    Log.d(TAG, "senderNum: $senderNum ; message: $message")

                    val showSmsIntent = Intent(context,SmsReceiverActivity::class.java)
                    showSmsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_NO, senderNum)
                    showSmsIntent.putExtra(SmsReceiverActivity.EXTRA_SMS_MESSAGE,message)
                    context.startActivity(showSmsIntent)
                }

            }

        } catch (e: Exception) {
            Log.d(TAG, "Exception smsReceiver $e")
        }

    }

    //6_2
    private fun getIncominMessage(anObject: Any, bundle: Bundle): SmsMessage {
        val currentSMS : SmsMessage
        val format = bundle.getString("format")
        currentSMS = if(Build.VERSION.SDK_INT >= 23) {
            SmsMessage.createFromPdu(anObject as ByteArray, format)
        } else SmsMessage.createFromPdu(anObject as ByteArray)
        return currentSMS
    }
    //Setelah metode getIncomingMessage dibuat, erapkan ke metode onReceive di kelas SmsReceiver.


}