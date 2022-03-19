package com.cahyadesthian.thedatastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException


/*
* KENAPA ADA KELAS INI?
* Perlu diketahui, kita tidak bisa membuat ViewModel dengan constructor secara langsung.
* Untuk itu, kita perlu membuat class yang extend ke ViewModelProvider untuk bisa
* memasukkan constructor ke dalam ViewModel. Berikut adalah kode pada ViewModelFactory
* */

class ViewModelFactory(private val pref: SettingPreferences): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class : " + modelClass.name)
    }
}