package com.cahyadesthian.thebroadcastreceiver

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

/**
 * STEP 9
 *
 * metode untuk pengecekan permission ke dalam PermissionManager.
 * */

object PermissionManager {

    fun check(activity: Activity, permission: String, requestCode:Int) {
        if(ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }
    /**
     * STEP 10
     * Setelah kita buat metode untuk checkpermission,
     * panggil metode check ke dalam MainActivity seperti berikut:
     *
     * */

}