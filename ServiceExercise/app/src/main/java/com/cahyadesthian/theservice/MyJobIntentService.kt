package com.cahyadesthian.theservice

import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

/**
 * STEP 7 kela ini,
 * tambahkan
 * <uses-permission android:name="android.permission.WAKE_LOCK"/> di AndroidManifest
 * dan dibagian aplikasinya juga dikasih permission android:permission="android.permission.BIND_JOB_SERVICE"
 *
 * */

/**
// IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
const val ACTION_FOO = "com.cahyadesthian.theservice.action.FOO"
const val ACTION_BAZ = "com.cahyadesthian.theservice.action.BAZ"


const val EXTRA_PARAM1 = "com.cahyadesthian.theservice.extra.PARAM1"
const val EXTRA_PARAM2 = "com.cahyadesthian.theservice.extra.PARAM2"
*/

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 */
//class MyJobIntentService : IntentService("MyJobIntentService") {
class MyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 1000
        internal const val EXTRA_DURATION = "extra_duration"
        private val TAG = MyJobIntentService::class.java.simpleName


        fun enqueueWork(context: Context, intent:Intent) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }

    }

    override fun onHandleWork(intent: Intent) {
        Log.d(TAG, "onHandleWork: Mulai . . . .")
        val duration = intent.getLongExtra(EXTRA_DURATION, 0)

        try {
            Thread.sleep(duration)
            Log.d(TAG, "onHandleWork: Selesai.....")

        }catch (e: InterruptedException) {
            e.printStackTrace()
            Thread.currentThread().interrupt()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }


    /*
    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FOO -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
            }
            ACTION_BAZ -> {
                val param1 = intent.getStringExtra(EXTRA_PARAM1)
                val param2 = intent.getStringExtra(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
            }
        }
    }
    */

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    /**
    private fun handleActionFoo(param1: String?, param2: String?) {
        TODO("Handle action Foo")
    }
    */

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */

    /**
    private fun handleActionBaz(param1: String?, param2: String?) {
        TODO("Handle action Baz")
    }
    */

}