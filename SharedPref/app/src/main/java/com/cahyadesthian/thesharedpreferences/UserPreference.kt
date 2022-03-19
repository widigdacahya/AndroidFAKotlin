package com.cahyadesthian.thesharedpreferences

import android.content.Context


/**
 * Kelas untuk manipulasi objek
 * SHARED PREFERENCES
 * baik berupa penambahan data atau mengubah data
 *
 * */

internal class UserPreference(context: Context){

    //ada SharedPreference yang diciptakan dari activity atau kelas lain.
    //Hanya diciptakan sekali
    private val preference = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /*
    * Ketika Anda membuat obyek dari kelas UserPreference pada Activity berikutnya,
    * maka obyek Shared Preferences akan diciptakan dan hanya diciptakan sekali.
    * Jika sudah ada, obyek yang sudah ada yang akan dikembalikan.
    * Semua itu Anda lakukan di konstruktor kelas UserPreference.
    * */



    /*
    * Di sini juga terdapat 2 metode yaitu setUser dan getUser.
    *Untuk menyimpan data kita harus mengakses obyek editor yang dimiliki oleh preferences.
    *
    * */
    fun setUser(value: UserModel) {
        val editor = preference.edit()
        editor.putString(NAME, value.name)
        editor.putString(EMAIL, value.email)
        editor.putInt(AGE, value.age)
        editor.putString(PHONE_NUMBER, value.phoneNumber)
        editor.putBoolean(LOVE_HER, value.isLove)
        editor.apply()
        /*
        * editor.apply(), di sinilah data akan disimpan ke dalam preferences.
        * Selain apply() ada metode lainnya yaitu commit().
        * Perbedaan dari Apply dan Commit adalah pada mekanismenya.
        * Apply dijalankan secara asynchronous, sedangkan Commit secara synchronous.
        * Perhatikan betapa mudahnya proses menyimpan dan membaca data.
        * Kita hanya menggunakan class model lalu metode getter dan setter saja.
        * */
    }

    fun getUser(): UserModel {
        val model = UserModel()
        model.name = preference.getString(NAME,"")
        model.email = preference.getString(EMAIL, "")
        model.age = preference.getInt(AGE,0)
        model.phoneNumber = preference.getString(PHONE_NUMBER,"")
        model.isLove = preference.getBoolean(LOVE_HER, false)

        return model
    }




    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val AGE = "age"
        private const val PHONE_NUMBER = "phone"
        private const val LOVE_HER = "isLove"
    }
}