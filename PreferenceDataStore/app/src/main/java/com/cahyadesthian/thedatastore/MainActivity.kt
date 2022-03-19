package com.cahyadesthian.thedatastore

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial

//Pembuatan INstance Data Store
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
/*
* Pada kode di atas, kita membuat extension function pada Context dengan nama dataStore
* yang dibuat dengan menggunakan property delegation by preferencesDataStore.
* Property delegation adalah sebuah mekanisme untuk mendelegasikan suatu tugas kepada class lain.
* Dengan menggunakan cara ini, Anda tidak perlu tahu bagaimana cara membuat DataStore secara detail,
* Anda cukup menyerahkannya saja ke class preferencesDataStore.
* Selain itu, kode ini dibuat di top-level supaya menjadi Singleton yang cukup dipanggil sekali.
* */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //[untuk menguba tema...wwiii]
        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme_mainUI)

        /*
        switchTheme.setOnCheckedChangeListener{ _: CompoundButton?, isChecked: Boolean ->
            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }
        //code above changed to below model to save the setting or preferences
        */

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this,ViewModelFactory(pref)).get(MainViewModel::class.java)
        mainViewModel.getThemeSetting().observe(this,
            {isDarkModeActive: Boolean ->
                if(isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    switchTheme.isChecked = true
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    switchTheme.isChecked = false
                }
            })

        switchTheme.setOnCheckedChangeListener{ _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }


    }
}